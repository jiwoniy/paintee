package com.paintee.common.repository.entity.vo;

public class LikeUserVO extends PaintingLikeVO {
	private String userName;
	private Integer friend;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getFriend() {
		return friend;
	}
	public void setFriend(Integer friend) {
		this.friend = friend;
	}
}
