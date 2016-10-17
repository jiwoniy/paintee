/**
@file PurchaseService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseService.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PurchaseService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:58:49 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.reward.service;

import java.util.Map;

import com.paintee.common.repository.entity.Reward;

/**
@class PurchaseService
com.paintee.mobile.painting.service \n
   ㄴ PurchaseService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:58:49 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 service
*/
public interface RewardService {
	
	/**
	 @fn getPurchaseList
	 @brief 함수 간략한 설명 : 관리자 메뉴의 리워드 관련 데이터 조회
	 @remark
	 - 함수의 상세 설명 : 관리자 메뉴의 리워드 관련 데이터 조회
	 @param search
	 @return
	 @throws Exception 
	*/
	public Map<String, Object> getRewardList();

	public void modRewardStatus(Reward reward);
}
