/**
@file PurchaseService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseService.java |    
| Package | com.paintee.admin.purchase.service |    
| Project name | paintee-admin |    
| Type name | PurchaseService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:58:49 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.promotioncode.service;

import java.util.Map;

import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;

/**
@class PromotionCodeService
com.paintee.admin.promotioncode.service \n
   ㄴ PromotionCodeService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 12. 12. 오후 10:58:49 |
    | Class Version | v1.0 |
    | 작업자 | Yeuntaek |
 @section 상세설명
 - 관리자의 구매 목록 정보를 처리하는 service
*/
public interface PromotionCodeService {
	
	/**
	 @fn gePromotionCodeList
	 @brief 함수 간략한 설명 : 관리자 메뉴의 구매정보 데이터 조회
	 @remark
	 - 함수의 상세 설명 : 관리자 메뉴의 구매정보 데이터 조회
	 @param search
	 @return
	 @throws Exception 
	*/
	public Map<String, Object> getPromotionCodeList(PromotionCodeSearchVO search);

	/**
	 @fn modPromotionCodeStatus
	 @brief 함수 간략한 설명 : 상태 변경
	 @remark
	 - 함수의 상세 설명 : 프로모션 코드상태를  변경 처리한다.
	 @param reward
	 */
	public void modPromotionCodeStatus(PromotionCode promotioncode);
	
}
 