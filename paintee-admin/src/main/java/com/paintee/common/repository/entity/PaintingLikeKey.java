package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class PaintingLikeKey extends BaseEntity {
    private String userId;

    private String paintingId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(String paintingId) {
        this.paintingId = paintingId;
    }
}