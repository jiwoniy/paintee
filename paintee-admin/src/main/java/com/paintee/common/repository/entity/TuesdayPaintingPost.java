package com.paintee.common.repository.entity;

import java.util.Date;

public class TuesdayPaintingPost extends TuesdayPaintingPostKey {
    private Date createDate;

    private String createId;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}