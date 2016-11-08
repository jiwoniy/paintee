package com.paintee.common.repository.entity;

import com.paintee.common.object.BaseEntity;

public class FollowKey extends BaseEntity {
    private String userId;

    private String following;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}