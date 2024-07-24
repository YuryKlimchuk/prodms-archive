package com.hydroyura.prodms.archive.client;

public class ServerErrorRes {

    private String timestamp;
    private String path;
    private String msg;


    public ServerErrorRes() {}


    public String getTimestamp() {
        return timestamp;
    }

    public ServerErrorRes setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ServerErrorRes setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ServerErrorRes setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
