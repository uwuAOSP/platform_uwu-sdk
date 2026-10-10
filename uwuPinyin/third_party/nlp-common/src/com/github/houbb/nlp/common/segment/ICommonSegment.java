package com.github.houbb.nlp.common.segment;

import java.util.List;

/**
 * 通用的分割实现
 * <p> project: nlp-common-ICommonSegment </p>
 * <p> create on 2020/2/7 11:10 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public interface ICommonSegment {

    /**
     * 对文本进行分割
     * @param text 文本
     * @return 结果
     * @since 0.0.2
     */
    List<String> segment(final String text);

}
