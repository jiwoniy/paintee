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

import com.paintee.common.repository.entity.User;

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
public class RewardVO extends User {
	
	/** 전체 수익 금액에서 리워드한 금액을 뺀 나머지 금액 */
	private String remainMoney;
	
	/** 그림이 Post 된 총 카운트 */
	private String sellCount;
	
	/** 그림이 Post 된 총 카운트 */
	private int requestCount;
	
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public String getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(String remainMoney) {
		this.remainMoney = remainMoney;
	}
	public String getSellCount() {
		return sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
}