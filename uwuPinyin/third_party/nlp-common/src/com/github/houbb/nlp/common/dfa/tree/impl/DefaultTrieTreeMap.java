package com.github.houbb.nlp.common.dfa.tree.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.nlp.common.constant.NlpConst;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 可以新增单词。
 *
 *
 * <p> project: nlp-common-DefaultTrieTreeMap </p>
 * <p> create on 2020/2/7 13:32 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class DefaultTrieTreeMap extends AbstractTrieTreeMap {

    /**
     * 内部单词 map
     *
     * @since 0.0.2
     */
    private static volatile Map innerWordMap = Guavas.newHashMap();

    @Override
    protected Map getStaticVolatileMap() {
        return innerWordMap;
    }

    @Override
    protected Collection<String> getWordCollection() {
        final String dictPath = getDictPath();
        List<String> allLines = StreamUtil.readAllLines(dictPath);
        Set<String> wordSet = Guavas.newHashSet();

        for(String line : allLines) {
            if(StringUtil.isEmpty(line)) {
                continue;
            }

            // 加入第一个单词信息
            String word = getLineWord(line);
            wordSet.add(word);
        }

        return wordSet;
    }

    /**
     * 获取字典路径
     * @return 路径
     * @since 0.0.2
     */
    protected String getDictPath() {
        return NlpConst.NLP_WORD_FREQ_DICT_PATH;
    }

    /**
     * 获取每一行的单词信息
     * @param line 每一行的内容
     * @return 单词信息
     * @since 0.0.2
     */
    protected String getLineWord(final String line) {
        // 加入第一个单词信息
        String[] strings = line.split(StringUtil.BLANK);
        return strings[0];
    }

}
