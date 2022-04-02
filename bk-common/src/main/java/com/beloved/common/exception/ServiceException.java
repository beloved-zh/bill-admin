package com.beloved.common.exception;

/**
 * 自定义异常
 *
 * @author beloved
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
}
