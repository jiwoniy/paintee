/**
@file PurchaseService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseService.java |    
| Package | com.paintee.mobile.purchase.service |    
| Project name | paintee-admin |    
| Type name | PurchaseService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:43:55 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.reward.service;

import java.util.List;
import java.util.Map;

import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.RewardResultVO;
import com.paintee.common.repository.entity.vo.RewardSearchVO;

/**
@class PurchaseService
com.paintee.mobile.purchase.service \n
   ㄴ PurchaseService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:43:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public interface RewardService {

	/**
	 @fn rewardInfo
	 @brief 함수 간략한 설명 : 리워드 팝업에 보여질 정보 조회
	 @remark
	 - 함수의 상세 설명 : 리워드 요청 팝업에 필요한 정보를 조회한다.
	 @param user
	 @return 
	*/
	public Map<String, Object> rewardInfo(User user);

	/**
	 @fn addReward
	 @brief 함수 간략한 설명 : 리워드 정보 등록
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자가 신청한 리워드 정보를 등록하고 사용자 테이블의 리워드 관련 정보를 업데이트 한다.
	 @param reward 
	*/
	public void addReward(Reward reward);

	/**
	 @fn rewardHistory
	 @brief 함수 간략한 설명 : 로그인 사용자의 리워드 히스토리 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 리워드 히스토리 정보를 조회
	 @param user
	 @return 
	*/
	public List<RewardResultVO> rewardHistory(User user);

	/**
	 @fn cancelReward
	 @brief 함수 간략한 설명 : 사용자 요청 리워드 취소
	 @remark
	 - 함수의 상세 설명 : 사용자가 요청한 리워드를 취소한다.(리워드 삭제, 사용자 리워드 관련 금액 변경)
	 @param search 
	*/
	public void cancelReward(RewardSearchVO search);
}
