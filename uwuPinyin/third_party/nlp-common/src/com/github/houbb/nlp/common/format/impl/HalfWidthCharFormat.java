package com.github.houbb.nlp.common.format.impl;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.nlp.common.format.ICharFormat;

/**
 * 全角半角格式化处理
 * @author binbin.hou
 * @since 0.0.1
 */
public class HalfWidthCharFormat implements ICharFormat {

    @Override
    public char format(char ch) {
        return CharUtil.toHalfWidth(ch);
    }

}
