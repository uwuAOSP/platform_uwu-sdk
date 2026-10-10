/**
 * 基于词频的处理
 * （1）word-checker
 * （2）segment
 *
 * key: char/String
 * value: count
 *
 * 不关心如何存储。
 *
 * 最后统一处理为：
 *
 * key: char/String
 * value: freq
 *
 * ---------------------------------------
 *
 * 字典的三种加载方式：
 *
 * （1）系统内置
 * （2）用户自定义
 * （3）二者混合
 *
 * <p> project: nlp-common-package-info </p>
 * <p> create on 2020/2/7 10:08 </p>
 *
 * @author binbin.hou
 * @since 0.0.2
 */
package com.github.houbb.nlp.common.data.load.freq;
