package com.paintee.common.repository.entity;

import java.util.Date;

public class PaintingLike extends PaintingLikeKey {
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}