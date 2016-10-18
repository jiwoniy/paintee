package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class TuesdayPaintingPostKey extends BaseEntity {
    private Integer tuesdaySeq;

    private String userId;

    public Integer getTuesdaySeq() {
        return tuesdaySeq;
    }

    public void setTuesdaySeq(Integer tuesdaySeq) {
        this.tuesdaySeq = tuesdaySeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}