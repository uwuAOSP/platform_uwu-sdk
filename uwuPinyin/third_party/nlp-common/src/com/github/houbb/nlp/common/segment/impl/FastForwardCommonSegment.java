package com.github.houbb.nlp.common.segment.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.nlp.common.constant.NlpConst;
import com.github.houbb.nlp.common.dfa.tree.ITrieTreeMap;

import java.util.List;
import java.util.Map;

/**
 * 贪心最大长度
 * <p> project: nlp-common-FastForwardCommonSegment </p>
 * <p> create on 2020/2/7 11:33 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
@ThreadSafe
public class FastForwardCommonSegment extends SimpleCommonSegment {

    /**
     * 前赘树 map
     * @since 0.0.2
     */
    private final ITrieTreeMap trieTreeMap;

    /**
     * 获取实例
     * @param trieTreeMap map 信息
     * @since 0.0.2
     */
    public FastForwardCommonSegment(ITrieTreeMap trieTreeMap) {
        this.trieTreeMap = trieTreeMap;
    }

    @Override
    protected List<String> getChineseSegments(String text) {
        List<String> resultList = Guavas.newArrayList();

        for(int i = 0; i < text.length(); i++) {
            int wordLength = getWordMatchLength(text, i);

            // 命中
            if (wordLength > 0) {
                // 保存当前词组
                String phrase = text.substring(i, i + wordLength);
                resultList.add(phrase);

                // 增加 i 的步长
                // 为什么要-1，因为默认就会自增1
                i += wordLength - 1;
            } else {
                // 直接保存当前单个中文
                resultList.add(String.valueOf(text.charAt(i)));
            }
        }

        return resultList;
    }

    /**
     * 获取字典中单词匹配的长度
     * @param txt 文本
     * @param beginIndex 开始下标
     * @return 长度
     * @since 0.0.2
     */
    @SuppressWarnings("all")
    private int getWordMatchLength(String txt, int beginIndex) {
        Map nowMap = trieTreeMap.getTrieTreeMap();

        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            // 获取当前的 map 信息
            nowMap = getCurrentMap(nowMap, txt, i);

            if (ObjectUtil.isNotNull(nowMap)) {
                lengthCount++;

                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                boolean isEnd = isEnd(nowMap);
                if (isEnd) {
                    // 只在匹配到结束的时候才记录长度，避免不完全匹配导致的问题。
                    // eg: 敏感词 敏感词xxx
                    // 如果是 【敏感词x】也会被匹配。
                    actualLength = lengthCount;
                }
            } else {
                // 直接跳出循环
                break;
            }
        }

        return actualLength;
    }

    /**
     * 获取当前的 Map
     * （1）这个方法独立出来，便于后期各种拓展。
     *
     * 比如：
     *
     * 数字格式化
     * 英文格式化
     * 大小写
     * 全角半角
     * 停顿词
     *
     * @param nowMap 原始的当前 map
     * @param txt 文本信息
     * @param index 下标
     * @return 实际的当前 map
     * @since 0.0.2
     */
    @SuppressWarnings("all")
    private Map getCurrentMap(Map nowMap,
                              final String txt,
                              final int index) {
        char charAt = txt.charAt(index);

        // 格式化
        // 这里做一次重复词的处理
        // 启用忽略重复&当前下标不是第一个
        // 这里后期可以添加各种格式化策略
        // 比如字符串的映射
        // 比如忽略重复词等等。

        return (Map) nowMap.get(charAt);
    }

    /**
     * 判断是否结束
     * BUG-FIX: 避免出现敏感词库中没有的文字。
     * @param map map 信息
     * @return 是否结束
     * @since 0.0.1
     */
    @SuppressWarnings("all")
    private static boolean isEnd(final Map map) {
        if(ObjectUtil.isNull(map)) {
            return false;
        }

        Object value = map.get(NlpConst.IS_END);
        if(ObjectUtil.isNull(value)) {
            return false;
        }

        return (boolean)value;
    }

}
