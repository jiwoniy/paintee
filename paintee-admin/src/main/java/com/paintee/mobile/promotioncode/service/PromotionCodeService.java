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
package com.paintee.mobile.promotioncode.service;

import java.util.List;
import java.util.Map;

import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.mobile.support.obejct.LoginedUserVO;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;


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
public interface PromotionCodeService {

	/**
	 @fn rewardInfo
	 @brief 함수 간략한 설명 : 리워드 팝업에 보여질 정보 조회
	 @remark
	 - 함수의 상세 설명 : 리워드 요청 팝업에 필요한 정보를 조회한다.
	 @param user
	 @return 
	*/
	public Map<String, Object> promotioncodeInfo(PromotionCode promotioncode, User user);

	/**
	 @fn addReward
	 @brief 함수 간략한 설명 : 리워드 정보 등록
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자가 신청한 리워드 정보를 등록하고 사용자 테이블의 리워드 관련 정보를 업데이트 한다.
	 @param reward 
	*/
    public int addPromotionCode(PromotionCodeVO promotioncode);

    
    public Map<String, Object> checkPromotionCode(PromotionCodeVO promotioncode, LoginedUserVO loginedUserVO);
	/**
	 @fn rewardHistory
	 @brief 함수 간략한 설명 : 로그인 사용자와입력한 프로모션코드대조하여 유효한 코드인지 체크.
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자와입력한 프로모션코드대조하여 유효한 코드인지 체크.
	 				유효하면 사용처리 까지 완료.
	 @param promotioncode, user
	 @return 
	*/
//	public List<PromotionCodeSearchVO> promotioncodeHistory(User user);

}
