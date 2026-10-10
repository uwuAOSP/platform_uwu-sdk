package com.github.houbb.nlp.common.format.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.nlp.common.format.ICharFormat;

import java.util.List;

/**
 * 抽象格式化处理
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public abstract class AbstractCharFormatInit implements ICharFormat {

    @Override
    public char format(char ch) {
        Pipeline<ICharFormat> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<ICharFormat> formatList = pipeline.list();

        char result = ch;
        for(ICharFormat format : formatList) {
            result = format.format(result);
        }

        return result;
    }

    /**
     * 初始化列表
     *
     * @param pipeline     当前列表泳道
     * @since 0.0.1
     */
    protected abstract void init(final Pipeline<ICharFormat> pipeline);

}
