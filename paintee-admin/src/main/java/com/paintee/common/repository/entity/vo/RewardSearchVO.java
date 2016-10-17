/**
@file RewardVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | RewardVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 6:25:26 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

/**
@class RewardVO
com.paintee.common.repository.entity.vo \n
   ㄴ RewardVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 6:25:26 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public class RewardSearchVO extends PagingVO {
	private List<String> rewardStatus;
	private Integer seq;
	private Integer money;
	private String userId;
	
	public List<String> getRewardStatus() {
		return rewardStatus;
	}

	public void setRewardStatus(List<String> rewardStatus) {
		this.rewardStatus = rewardStatus;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}








