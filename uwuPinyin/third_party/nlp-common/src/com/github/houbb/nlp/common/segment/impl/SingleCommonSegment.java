package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;

import java.util.Collections;
import java.util.List;

/**
 * 单个词语通用分词
 *
 * <p> project: nlp-common-AbstractCommonSegment </p>
 * <p> create on 2020/2/7 11:17 </p>
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class SingleCommonSegment extends AbstractCommonSegment {

    @Override
    protected List<String> doSegment(String text) {
        return Collections.singletonList(text);
    }

}
