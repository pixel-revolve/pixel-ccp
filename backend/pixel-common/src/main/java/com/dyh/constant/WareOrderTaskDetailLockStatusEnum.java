package com.dyh.constant;

/**
 * 库存工作单细节锁定字段枚举值
 * @author pixel-revolve
 * @date 2023/04/10
 */
public enum WareOrderTaskDetailLockStatusEnum {
    // 锁定状态：1->已锁定 2->已解锁 3->扣减
    LOCKED(1,"已锁定"),
    UNLOCKED(2,"已解锁"),
    DEDUCTION(3,"扣减");

    private final Integer code;
    private final String msg;

    WareOrderTaskDetailLockStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
