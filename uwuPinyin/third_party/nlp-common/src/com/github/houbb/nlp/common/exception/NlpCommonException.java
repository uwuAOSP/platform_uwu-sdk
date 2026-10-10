package com.github.houbb.nlp.common.exception;

/**
 * <p> project: nlp-common-NlpCommonException </p>
 * <p> create on 2020/2/7 8:52 </p>
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class NlpCommonException extends RuntimeException {

    public NlpCommonException() {
    }

    public NlpCommonException(String message) {
        super(message);
    }

    public NlpCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public NlpCommonException(Throwable cause) {
        super(cause);
    }

    public NlpCommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
