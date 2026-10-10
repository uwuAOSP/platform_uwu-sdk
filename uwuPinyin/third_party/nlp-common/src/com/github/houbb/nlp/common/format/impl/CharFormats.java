package com.github.houbb.nlp.common.format.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.nlp.common.format.ICharFormat;

/**
 * 格式工具类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class CharFormats {

    private CharFormats(){}

    /**
     * 默认格式化实现
     * @return 格式化实现
     * @since 0.0.1
     */
    public static ICharFormat defaults() {
        return chains(halfWidth(), lowerCase());
    }

    /**
     * 转换为半角
     * @return 半角
     * @since 0.0.1
     */
    public static ICharFormat halfWidth() {
        return Instances.singleton(HalfWidthCharFormat.class);
    }

    /**
     * 转换为小写
     * @return 小写
     * @since 0.0.1
     */
    public static ICharFormat lowerCase() {
        return Instances.singleton(LowerCaseCharFormat.class);
    }


    /**
     * 无格式化实现
     * @return 格式化实现
     * @since 0.0.1
     */
    public static ICharFormat none() {
        return Instances.singleton(NoneCharFormat.class);
    }

    /**
     * 中文简体
     * @return 格式化实现
     * @since 0.0.1
     */
    public static ICharFormat chineseSimple() {
        return Instances.singleton(ChineseTsCharFormat.class);
    }

    /**
     * 责任链
     * @param formats 格式化
     * @return 结果
     * @since 0.0.1
     */
    public static ICharFormat chains(final ICharFormat ... formats) {
        if(ArrayUtil.isEmpty(formats)) {
            return none();
        }

        return new AbstractCharFormatInit() {
            @Override
            protected void init(Pipeline<ICharFormat> pipeline) {
                for(ICharFormat format : formats) {
                    pipeline.addLast(format);
                }
            }
        };
    }

}
