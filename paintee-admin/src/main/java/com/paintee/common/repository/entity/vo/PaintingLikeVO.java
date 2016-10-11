package com.paintee.common.repository.entity.vo;

import com.paintee.common.repository.entity.PaintingLike;

public class PaintingLikeVO extends PaintingLike {
	private String artistId;

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
}
