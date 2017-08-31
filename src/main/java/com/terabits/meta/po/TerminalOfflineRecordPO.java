package com.terabits.meta.po;

public class TerminalOfflineRecordPO {
    private int id;
    private String imei;
    private String gmtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "TerminalOfflineRecordPO{" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                '}';
    }
}
