/*
 * Copyright (C) 2026 The uwuAOSP Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.uwuaosp.pinyin;

import com.github.houbb.pinyin.bs.PinyinBs;
import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.support.style.PinyinToneStyles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** Phrase-aware full, initial, mixed-script and T9 matching at label word boundaries. */
public final class PinyinSearch {
    private static final Map<String, List<Token>> LABELS =
            new LinkedHashMap<String, List<Token>>(128, .75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, List<Token>> eldest) {
                    return size() > 2048;
                }
            };

    private PinyinSearch() {}

    /** Prepare once per query; dictionary initialization and indexing belong off the UI thread. */
    public static Query prepare(String query, boolean t9) {
        return new Query(query == null ? "" : normalize(query, false), t9);
    }

    public static boolean matches(String query, String label) {
        return prepare(query, false).matches(label);
    }

    public static boolean matchesT9(String query, String label) {
        return prepare(query, true).matches(label);
    }

    public static final class Query {
        private final String text;
        private final boolean t9;

        private Query(String text, boolean t9) {
            this.text = text;
            this.t9 = t9 && isDigits(text);
        }

        public boolean matches(String label) {
            if (text.isEmpty() || label == null || label.isEmpty()) return false;
            List<Token> tokens = index(label);
            boolean[] positions = new boolean[text.length() + 1];
            for (Token token : tokens) {
                // Restart only at a word or syllable boundary, never inside a syllable.
                positions[0] = true;
                boolean[] next = new boolean[positions.length];
                for (int offset = 0; offset < text.length(); offset++) {
                    if (!positions[offset]) continue;
                    for (String form : t9 ? token.digits : token.forms) {
                        if (form.isEmpty()) continue;
                        int remaining = text.length() - offset;
                        if (remaining <= form.length()
                                && form.regionMatches(0, text, offset, remaining)) return true;
                        if (text.startsWith(form, offset)) next[offset + form.length()] = true;
                    }
                }
                positions = next;
            }
            return false;
        }
    }

    private static List<Token> index(String label) {
        synchronized (LABELS) {
            List<Token> cached = LABELS.get(label);
            if (cached != null) return cached;
        }
        String words =
                label.replaceAll("([A-Z])([A-Z][a-z])", "$1 $2")
                        .replaceAll("([a-z0-9])([A-Z])", "$1 $2");
        String normalized = normalize(words, true);
        List<Token> tokens = new ArrayList<>();
        for (int start = 0; start < normalized.length(); ) {
            int cp = normalized.codePointAt(start);
            int end = start + Character.charCount(cp);
            if (cp == ' ') {
                start = end;
                continue;
            } else if (isBmpHan(cp)) {
                while (end < normalized.length() && isBmpHan(normalized.codePointAt(end))) end++;
                String run = normalized.substring(start, end);
                String[] readings;
                synchronized (Dictionary.PINYIN) {
                    readings = Dictionary.PINYIN.toPinyin(run).split(" ");
                }
                for (int i = 0; i < run.length(); i++) {
                    String literal = run.substring(i, i + 1);
                    String reading =
                            readings.length == run.length()
                                    ? normalize(readings[i], false)
                                    : literal;
                    tokens.add(new Token(literal, reading));
                }
            } else if (Character.isLetterOrDigit(cp) && !isHan(cp)) {
                while (end < normalized.length()) {
                    int next = normalized.codePointAt(end);
                    if (!Character.isLetterOrDigit(next) || isHan(next)) break;
                    end += Character.charCount(next);
                }
                tokens.add(new Token(normalized.substring(start, end), null));
            } else {
                // Keep supplementary Han and symbols literal; houbb accepts UTF-16 chars only.
                tokens.add(new Token(normalized.substring(start, end), null));
            }
            start = end;
        }
        synchronized (LABELS) {
            LABELS.put(label, tokens);
        }
        return tokens;
    }

    private static final class Token {
        final String[] forms;
        final String[] digits;

        Token(String literal, String reading) {
            if (reading != null && !literal.equals(reading) && isRoman(reading)) {
                forms = new String[] {literal, reading, reading.substring(0, 1)};
            } else if (isRoman(literal)) {
                forms = new String[] {literal, literal.substring(0, 1)};
            } else {
                forms = new String[] {literal};
            }
            digits = new String[forms.length];
            for (int i = 0; i < forms.length; i++) digits[i] = toDigits(forms[i]);
        }
    }

    private static boolean isRoman(String value) {
        if (value.isEmpty()) return false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) < 'a' || value.charAt(i) > 'z') return false;
        }
        return true;
    }

    private static boolean isDigits(String value) {
        if (value.isEmpty()) return false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) < '0' || value.charAt(i) > '9') return false;
        }
        return true;
    }

    private static String toDigits(String value) {
        StringBuilder result = new StringBuilder(value.length());
        String keys = "22233344455566677778889999";
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= 'a' && c <= 'z') result.append(keys.charAt(c - 'a'));
            else if (c >= '0' && c <= '9') result.append(c);
            else return "";
        }
        return result.toString();
    }

    private static boolean isHan(int cp) {
        return Character.UnicodeScript.of(cp) == Character.UnicodeScript.HAN;
    }

    private static boolean isBmpHan(int cp) {
        return cp <= Character.MAX_VALUE && isHan(cp);
    }

    private static String normalize(String value, boolean keepBoundaries) {
        String text = Normalizer.normalize(value, Normalizer.Form.NFKC).toLowerCase(Locale.ROOT);
        // Keyboard pinyin spells ü as v; remove other tone marks without losing that distinction.
        text = Normalizer.normalize(text, Normalizer.Form.NFC).replace('ü', 'v');
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replace("u\u0308", "v");
        StringBuilder result = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            int type = Character.getType(cp);
            if (type == Character.NON_SPACING_MARK || type == Character.COMBINING_SPACING_MARK) {
                continue;
            }
            if (Character.isWhitespace(cp)
                    || Character.isSpaceChar(cp)
                    || cp == '\''
                    || cp == '-'
                    || cp == '’') {
                if (keepBoundaries) result.append(' ');
                continue;
            }
            result.appendCodePoint(isHan(cp) ? Traditional.MAP.getOrDefault(cp, cp) : cp);
        }
        return result.toString();
    }

    private static final class Dictionary {
        static final PinyinBs PINYIN =
                PinyinBs.newInstance()
                        .style(PinyinToneStyles.getTone(PinyinStyleEnum.INPUT))
                        .connector(" ")
                        .init();
    }

    private static final class Traditional {
        static final Map<Integer, Integer> MAP = load();

        private static Map<Integer, Integer> load() {
            Map<Integer, Integer> result = new HashMap<>();
            InputStream stream = PinyinSearch.class.getResourceAsStream("/nlp/chinese_ts_char.txt");
            if (stream == null)
                throw new IllegalStateException("Missing houbb traditional dictionary");
            try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    String[] pair = line.trim().split("\\s+");
                    if (pair.length == 2)
                        result.put(pair[0].codePointAt(0), pair[1].codePointAt(0));
                }
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read houbb traditional dictionary", e);
            }
            return result;
        }
    }
}
