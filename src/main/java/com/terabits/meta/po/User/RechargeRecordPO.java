package com.terabits.meta.po.User;

public class RechargeRecordPO {
    private int id;
    private String openId;
    private Double payment;
    private Double present;
    private String orderId;
    private String tradeNo;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Double getPresent() {
        return present;
    }

    public void setPresent(Double present) {
        this.present = present;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "RechargeRecordPO{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", payment=" + payment +
                ", present=" + present +
                ", orderId='" + orderId + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
