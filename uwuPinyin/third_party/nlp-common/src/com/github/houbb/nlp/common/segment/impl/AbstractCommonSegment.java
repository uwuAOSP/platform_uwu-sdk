package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.nlp.common.segment.ICommonSegment;

import java.util.Collections;
import java.util.List;

/**
 * 抽象通用分词
 *
 * <p> project: nlp-common-AbstractCommonSegment </p>
 * <p> create on 2020/2/7 11:17 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public abstract class AbstractCommonSegment implements ICommonSegment {

    /**
     * 执行分词
     * @param text 文本
     * @return 结果
     * @since 0.0.2
     */
    protected abstract List<String> doSegment(final String text);

    @Override
    public List<String> segment(String text) {
        if(null == text) {
            return Collections.emptyList();
        }
        if(StringUtil.isEmptyTrim(text)) {
            return Collections.singletonList(text);
        }

        return doSegment(text);
    }

}
