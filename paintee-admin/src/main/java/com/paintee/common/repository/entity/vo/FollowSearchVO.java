/**
@file FollowSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | FollowSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

/**
@class FollowSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ FollowSearchVO.java
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
public class FollowSearchVO extends PagingVO {
	private static final long serialVersionUID = -3180373314948888878L;

	private String userId;

	private String privateAt;
	
	private List<String> paintingStatusList;
	private List<String> purchaseStatusList;

	private String followName;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPrivateAt() {
		return privateAt;
	}

	public void setPrivateAt(String privateAt) {
		this.privateAt = privateAt;
	}

	public List<String> getPaintingStatusList() {
		return paintingStatusList;
	}

	public void setPaintingStatusList(List<String> paintingStatusList) {
		this.paintingStatusList = paintingStatusList;
	}

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}

	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}

	public String getFollowName() {
		return followName;
	}

	public void setFollowName(String followName) {
		this.followName = followName;
	}
	
}
