/**
@file MyHomeSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | MyHomeSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

/**
@class MyHomeSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ MyHomeSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 내가 팔로잉한 사용자의 그림에 대한 정보 관련 vo
*/
public class MyHomeSearchVO extends PagingVO {
	private static final long serialVersionUID = -3180373314948888878L;

	private String userId;

	private String artistId;

	private List<String> purchaseStatusList;
	private List<String> paintingStatusList;
	
	private String upload;
	private String post;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}

	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	
	public List<String> getPaintingStatusList() {
		return paintingStatusList;
	}

	public void setPaintingStatusList(List<String> paintingStatusList) {
		this.paintingStatusList = paintingStatusList;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
}
