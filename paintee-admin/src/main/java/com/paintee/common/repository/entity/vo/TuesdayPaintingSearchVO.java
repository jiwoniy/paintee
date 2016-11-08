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
@class TuesdayPaintingSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ TuesdayPaintingSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 
*/
public class TuesdayPaintingSearchVO extends PagingVO {
	private static final long serialVersionUID = -3180373314948888878L;

	private String userId;

	private String privateAt;
	
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
}
