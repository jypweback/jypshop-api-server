package com.jypshop.common.exception;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/14
 * Github : http://github.com/jypweback
 * Description :
 */
public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
