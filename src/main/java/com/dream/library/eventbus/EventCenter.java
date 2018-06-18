package com.dream.library.eventbus;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * Date:        15/9/30 下午5:38
 * Description: EasyFrame
 */
@SuppressWarnings("unused")
public class EventCenter {

    private int eventCode = -1;
    private Object data;
    private Object data1;
    private Object data2;
    private Object data3;
    private Object data4;
    private Object data5;
    private Object data6;
    private Object data7;
    private Object data8;
    private Object data9;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }

    public EventCenter(int eventCode, Object data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public EventCenter(int eventCode, Object data, Object data1) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4, Object data5) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4, Object data5, Object data6) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4, Object data5, Object data6, Object data7) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4, Object data5, Object data6, Object data7, Object data8) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;
    }

    public EventCenter(int eventCode, Object data, Object data1, Object data2, Object data3, Object data4, Object data5, Object data6, Object data7, Object data8, Object data9) {
        this.eventCode = eventCode;
        this.data = data;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;
        this.data9 = data9;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public Object getData3() {
        return data3;
    }

    public void setData3(Object data3) {
        this.data3 = data3;
    }

    public Object getData4() {
        return data4;
    }

    public void setData4(Object data4) {
        this.data4 = data4;
    }

    public Object getData5() {
        return data5;
    }

    public void setData5(Object data5) {
        this.data5 = data5;
    }

    public Object getData6() {
        return data6;
    }

    public void setData6(Object data6) {
        this.data6 = data6;
    }

    public Object getData7() {
        return data7;
    }

    public void setData7(Object data7) {
        this.data7 = data7;
    }

    public Object getData8() {
        return data8;
    }

    public void setData8(Object data8) {
        this.data8 = data8;
    }

    public Object getData9() {
        return data9;
    }

    public void setData9(Object data9) {
        this.data9 = data9;
    }

    @Override
    public String toString() {
        return "EventCenter{" +
               "eventCode=" + eventCode +
               ", data=" + data +
               ", data1=" + data1 +
               ", data2=" + data2 +
               ", data3=" + data3 +
               ", data4=" + data4 +
               ", data5=" + data5 +
               ", data6=" + data6 +
               ", data7=" + data7 +
               ", data8=" + data8 +
               ", data9=" + data9 +
               '}';
    }
}
