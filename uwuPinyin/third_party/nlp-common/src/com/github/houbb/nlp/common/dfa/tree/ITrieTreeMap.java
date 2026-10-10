package com.github.houbb.nlp.common.dfa.tree;

import java.util.Map;

/**
 * <p> project: nlp-common-ITrieTreeMap </p>
 * <p> create on 2020/2/7 13:23 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public interface ITrieTreeMap {

    /**
     * 获取对应的词信息
     *
     * @return 词信息列表
     * @since 0.0.2
     */
    Map getTrieTreeMap();

    /**
     * 新增单词
     * @param word 单词
     * @return this
     * @since 0.0.2
     */
    ITrieTreeMap add(final String word);

    /**
     * 清空操作
     * @return this
     * @since 0.0.2
     */
    ITrieTreeMap clear();

}
