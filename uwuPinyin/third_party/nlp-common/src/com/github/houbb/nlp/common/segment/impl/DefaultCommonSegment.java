package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;

import java.util.Collections;
import java.util.List;

/**
 * 默认的分词策略
 *
 * 中文不做分词处理
 *
 * <p> project: nlp-common-SimpleCommonSegment </p>
 * <p> create on 2020/2/7 11:22 </p>
 *
 * @author binbin.hou
 * @since 0.0.3
 */
@ThreadSafe
public class DefaultCommonSegment extends SimpleCommonSegment {

    @Override
    protected List<String> getChineseSegments(String text) {
        return Collections.singletonList(text);
    }

}
