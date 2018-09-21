package com.gwtsz.android.rxbus.event;

/**
 * 事件封装
 * 为了区分事件类别，加入了code属性
 * 另一个属性Object，则是消息的本体
 */
public class RxBusBaseMessage {

    private String code;
    private Object object;

    private RxBusBaseMessage() {
    }

    public RxBusBaseMessage(String code, Object object) {
        this.code = code;
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
