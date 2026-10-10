package com.github.houbb.nlp.common.dfa.tree.impl;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.nlp.common.dfa.tree.ITrieTreeMap;

/**
 * <p> project: nlp-common-TrieTreeMaps </p>
 * <p> create on 2020/2/7 16:51 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public final class TrieTreeMaps {

    private TrieTreeMaps(){}

    /**
     * 默认实现
     * @return 实现
     * @since 0.0.2
     */
    public static ITrieTreeMap defaults() {
        return Instances.singleton(DefaultTrieTreeMap.class);
    }

}
