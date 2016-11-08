/**
@file PostedSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PostedSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | PostedSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

/**
@class PostedSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ PostedSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자에 대한 정보 관련 vo
*/
public class PostedSearchVO extends PagingVO {
	private static final long serialVersionUID = -3180373314948888877L;

	private String privateAt;
	private String purchaseStatus;
	private List<String> purchaseStatusList;
	private String paintingId;

	public String getPrivateAt() {
		return privateAt;
	}

	public void setPrivateAt(String privateAt) {
		this.privateAt = privateAt;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}

	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
}
