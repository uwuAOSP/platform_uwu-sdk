package com.github.houbb.nlp.common.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.nlp.common.constant.NlpConst;
import com.github.houbb.nlp.common.format.ICharFormat;

import java.util.List;
import java.util.Map;

/**
 * 中文繁体转简体测试
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class ChineseTsCharFormat implements ICharFormat {

    /**
     * 繁体=》简体
     *
     * @since 0.1.2
     */
    private static final Map<Character, Character> TS_CHAR_MAP = Guavas.newHashMap();

    static {
        final long startTime = System.currentTimeMillis();
        List<String> lines = StreamUtil.readAllLines(NlpConst.NLP_CHINESE_TS_CHAR_PATH);
        for (String line : lines) {
            String[] entries = line.split(StringUtil.BLANK);
            char tChar = entries[0].charAt(0);
            char sChar = entries[1].charAt(0);
            TS_CHAR_MAP.put(tChar, sChar);
        }
        final long costTime = System.currentTimeMillis() - startTime;
        System.out.println("[NLP Format] chinese traditional-simple dict load finished, cost "
                + costTime + " ms");
    }


    @Override
    public char format(char ch) {
        Character simple = TS_CHAR_MAP.get(ch);
        if (ObjectUtil.isNull(simple)) {
            // 返回本身
            return ch;
        }

        // 返回简体
        return simple;
    }

}
