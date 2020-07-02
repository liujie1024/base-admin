package com.mb.broadcast.entity;

import java.io.Serializable;

/**
 * 查询参数
 */
public class BroadcastParam implements Serializable {

    private static final long serialVersionUID = -2329363073946825232L;

    private String progressSearch;

    public String getProgressSearch() {
        return progressSearch;
    }

    public void setProgressSearch(String progressSearch) {
        this.progressSearch = progressSearch;
    }
}
