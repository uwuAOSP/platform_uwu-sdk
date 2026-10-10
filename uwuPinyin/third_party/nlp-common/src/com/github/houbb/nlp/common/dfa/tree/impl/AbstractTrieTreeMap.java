package com.github.houbb.nlp.common.dfa.tree.impl;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.nlp.common.constant.NlpConst;
import com.github.houbb.nlp.common.dfa.tree.ITrieTreeMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p> project: nlp-common-ITrieTreeMap </p>
 * <p> create on 2020/2/7 13:23 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public abstract class AbstractTrieTreeMap implements ITrieTreeMap {

    /**
     * 获取容易变化的 map
     * @return 结果
     * @since 0.0.2
     */
    protected abstract Map getStaticVolatileMap();

    /**
     * 获取单词集合
     * @return 单词集合
     * @since 0.0.2
     */
    protected abstract Collection<String> getWordCollection();

    @Override
    public Map getTrieTreeMap() {
        Map innerWordMap = getStaticVolatileMap();
        if(MapUtil.isNotEmpty(innerWordMap)) {
            return innerWordMap;
        }

        synchronized (AbstractTrieTreeMap.class) {
            if(MapUtil.isEmpty(innerWordMap)) {
                Collection<String> allLines = getWordCollection();
                Set<String> wordSet = Guavas.newHashSet();

                for(String line : allLines) {
                    if(StringUtil.isEmpty(line)) {
                        continue;
                    }

                    // 加入第一个单词信息
                    String[] strings = line.split(StringUtil.BLANK);
                    wordSet.add(strings[0]);
                }

                initInnerWordMap(wordSet, innerWordMap);
            }
        }

        return innerWordMap;
    }

    @Override
    public ITrieTreeMap add(String word) {
        // 首先初始化 map
        Map innerWordMap = getStaticVolatileMap();
        if(MapUtil.isEmpty(innerWordMap)) {
            getTrieTreeMap();
        }

        synchronized (DefaultTrieTreeMap.class) {
            initInnerWordMap(word, innerWordMap);
        }

        return this;
    }

    @Override
    public ITrieTreeMap clear() {
        Map map = getStaticVolatileMap();
        if(ObjectUtil.isNotNull(map)) {
            map.clear();
        }

        return this;
    }

    /**
     * 基于前缀树初始化 Map
     * @param wordCollection 分词数据集合
     * @param innerWordMap 内部单词集合
     * @since 0.0.2
     */
    private void initInnerWordMap(final Collection<String> wordCollection, Map innerWordMap) {
        // 创建 map
        if(CollectionUtil.isEmpty(wordCollection)) {
            return;
        }

        // 加载字典
        for (String key : wordCollection) {
            initInnerWordMap(key, innerWordMap);
        }
    }

    /**
     * 基于前缀树初始化 Map
     * @param word 单词
     * @param innerWordMap 内部单词集合
     * @since 0.0.2
     */
    @SuppressWarnings("unchecked")
    private void initInnerWordMap(final String word, Map innerWordMap) {
        if(StringUtil.isEmpty(word)) {
            return;
        }

        // 用来按照相应的格式保存敏感词库数据
        char[] chars = word.toCharArray();
        final int size = chars.length;

        // 每一个新词的循环，直接将结果设置为当前 map，所有变化都会体现在结果的 map 中
        Map currentMap = innerWordMap;

        for (int i = 0; i < size; i++) {
            // 截取敏感词当中的字，在敏感词库中字为HashMap对象的Key键值
            char charKey = chars[i];
            // 如果集合存在
            Object wordMap = currentMap.get(charKey);

            // 如果集合存在
            if (ObjectUtil.isNotNull(wordMap)) {
                // 直接将获取到的 map 当前当前 map 进行继续的操作
                currentMap = (Map) wordMap;
            } else {
                //不存在则，则构建一个新的map，同时将isEnd设置为0，因为他不是最后一
                Map<String, Boolean> newWordMap = new HashMap<>(8);
                newWordMap.put(NlpConst.IS_END, false);

                // 将新的节点放入当前 map 中
                currentMap.put(charKey, newWordMap);

                // 将新节点设置为当前节点，方便下一次节点的循环。
                currentMap = newWordMap;
            }

            // 判断是否为最后一个，添加是否结束的标识。
            if (i == size - 1) {
                currentMap.put(NlpConst.IS_END, true);
            }
        }
    }

}
