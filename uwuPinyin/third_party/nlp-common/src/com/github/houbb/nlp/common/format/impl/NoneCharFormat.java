package com.github.houbb.nlp.common.format.impl;


import com.github.houbb.nlp.common.format.ICharFormat;

/**
 * 无格式化处理
 * @author binbin.hou
 * @since 0.0.1
 */
public class NoneCharFormat implements ICharFormat {

    @Override
    public char format(char ch) {
        return ch;
    }

}
