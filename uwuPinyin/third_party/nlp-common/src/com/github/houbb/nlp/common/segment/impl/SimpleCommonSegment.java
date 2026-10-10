package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.nlp.common.util.CharUtils;

import java.util.List;

/**
 * <p> project: nlp-common-SimpleCommonSegment </p>
 * <p> create on 2020/2/7 11:22 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class SimpleCommonSegment extends AbstractCommonSegment {

    @Override
    protected List<String> doSegment(String text) {
        List<String> wordList = Guavas.newArrayList();

        // 这里后期其实除了繁简体，没有必要处理符号信息。因为和字典无关。
        StringBuilder chineseBuffer = new StringBuilder();
        StringBuilder otherBuffer = new StringBuilder();
        for(char c : text.toCharArray()) {
            if(CharUtil.isChinese(c)) {
                chineseBuffer.append(c);
                // 处理其他符号
                processOtherChars(otherBuffer, wordList);
            } else {
                otherBuffer.append(c);
                // 处理中文
                processChineseChars(chineseBuffer, wordList);
            }
        }

        // 中文
        processChineseChars(chineseBuffer, wordList);

        // 英文
        processOtherChars(otherBuffer, wordList);

        return wordList;
    }

    /**
     * 获取中文分词列表
     *
     * （1）默认直接按照中文拆分。
     * @param text 文本信息
     * @return 结果列表
     * @since 0.1.1
     */
    protected List<String> getChineseSegments(final String text) {
        return StringUtil.toCharStringList(text);
    }

    /**
     * 执行处理中文字符信息
     * @param buffer 缓存信息
     * @param wordList 结果列表
     * @since 0.1.0
     */
    private void processChineseChars(final StringBuilder buffer,
                                     final List<String> wordList) {
        final int length = buffer.length();
        if(length <= 0) {
            return;
        }

        String text = buffer.toString();
        List<String> segments = getChineseSegments(text);
        wordList.addAll(segments);

        // 清空长度
        buffer.setLength(0);
    }

    /**
     * 处理其他字符
     * （1）数字
     * （2）英文
     * （3）数字+英文
     *
     * 其他字符全部当做单个信息处理。
     *
     * @param buffer 缓冲信息
     * @param wordList 字典列表
     * @since 0.1.0
     */
    private void processOtherChars(final StringBuilder buffer,
                                   final List<String> wordList) {
        final int length = buffer.length();
        if(length <= 0) {
            return;
        }

        if(1 == length) {
            wordList.add(buffer.toString());
        } else {
            // 避免使用 Regex
            StringBuilder wordBuffer = new StringBuilder();

            for(int i = 0; i < buffer.length(); i++) {
                char c = buffer.charAt(i);
                if(CharUtil.isDigitOrLetter(c)
                        || CharUtils.isLetterOrConnector(c)) {
                    wordBuffer.append(c);
                } else {
                    // 如果不是
                    if(wordBuffer.length() > 0) {
                        wordList.add(wordBuffer.toString());
                        wordBuffer.setLength(0);
                    }

                    // 其他字符，直接添加
                    wordList.add(String.valueOf(c));
                }
            }

            // 是否存在剩余的 buffer
            if(wordBuffer.length() > 0) {
                wordList.add(wordBuffer.toString());
                wordBuffer.setLength(0);
            }
        }

        buffer.setLength(0);
    }

}
