package com.dyh.exception;

import lombok.Getter;
import lombok.Setter;


/**
 * 无库存抛出的异常
 * @author pixel-revolve
 * @date 2023/04/17
 */
public class NoStockException extends RuntimeException {

    @Getter
    @Setter
    private Long spuId;

    public NoStockException(Long spuId) {
        super("商品id："+ spuId + "库存不足！");
    }

    public NoStockException(String msg) {
        super(msg);
    }

}
