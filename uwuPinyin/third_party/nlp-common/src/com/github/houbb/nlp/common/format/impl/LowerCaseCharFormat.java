package com.github.houbb.nlp.common.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.nlp.common.format.ICharFormat;

/**
 * 英文大小写格式化处理
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class LowerCaseCharFormat implements ICharFormat {

    @Override
    public char format(char ch) {
        return Character.toLowerCase(ch);
    }

}
