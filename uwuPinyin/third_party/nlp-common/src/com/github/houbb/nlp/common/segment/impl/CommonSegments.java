package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.nlp.common.dfa.tree.ITrieTreeMap;
import com.github.houbb.nlp.common.dfa.tree.impl.TrieTreeMaps;
import com.github.houbb.nlp.common.segment.ICommonSegment;

/**
 * <p> project: nlp-common-CommonSegments </p>
 * <p> create on 2020/2/7 16:22 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public final class CommonSegments {

    private CommonSegments(){}

    /**
     * 单个的分词实现
     * @return 实现
     * @since 0.0.3
     */
    public static ICommonSegment single() {
        return Instances.singleton(SingleCommonSegment.class);
    }

    /**
     * 按字符拆分的分词实现
     * @return 实现
     * @since 0.0.3
     */
    public static ICommonSegment chars() {
        return Instances.singleton(CharCommonSegment.class);
    }

    /**
     * 简单的分词实现
     * @return 实现
     * @since 0.0.2
     */
    public static ICommonSegment simple() {
        return Instances.singleton(SimpleCommonSegment.class);
    }

    /**
     * 快速向前的分词实现
     * @return 分词
     * @since 0.0.2
     */
    public static ICommonSegment fastForward() {
        final ITrieTreeMap map = TrieTreeMaps.defaults();
        return fastForward(map);
    }

    /**
     * 快速向前的分词实现
     * @param map 前赘树 map
     * @return 分词
     * @since 0.0.2
     */
    public static ICommonSegment fastForward(final ITrieTreeMap map) {
        return new FastForwardCommonSegment(map);
    }

    /**
     * 默认的分词实现
     * @return 分词
     * @since 0.0.3
     */
    public static ICommonSegment defaults() {
        return new DefaultCommonSegment();
    }

}
