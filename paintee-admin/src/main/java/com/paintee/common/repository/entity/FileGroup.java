package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;
import java.util.Date;

public class FileGroup extends BaseEntity {
    private Long seq;

    private String groupName;

    private Date createdDate;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}