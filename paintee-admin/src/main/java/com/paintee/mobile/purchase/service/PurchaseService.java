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
package com.paintee.mobile.purchase.service;

import java.util.Map;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.mobile.support.obejct.LoginedUserVO;

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
public interface PurchaseService {
	
	/**
	 @fn purchasePopInfo
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param loginedUserVO
	 @return 
	*/
	public  Map<String, Object> purchasePopInfo(String paintingId, LoginedUserVO loginedUserVO);
	
	/**
	 @fn addPurchase
	 @brief 함수 간략한 설명 : 구매정보를 등록한다.
	 @remark
	 - 함수의 상세 설명 : 구매정보를 등록한다. 
	 @param purchase
	 @throws Exception 
	*/
	public Map<String, Object> addPurchase(PurchaseSearchVO purchase) throws Exception;

	/**
	 @fn updateStatusPurchase
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 
	   : 완료 또는 재발송 상태로 변경
	   : My 페이지의 사용자가 그림의 sended 버튼을 클릭 후 Confirm 버튼을 클릭 시 완료 상태로 변경
	   : My 페이지의 사용자가 그림의 sended 버튼을 클릭 후 Resend 버튼을 클릭 시 재발송 상태로 변경 
	 @param purchase
	 @return 
	*/
	public Map<String, Object> updateStatusPurchase(PurchaseSearchVO purchase);

	/**
	 @fn delStatusPainting
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 그림의 상태를 삭제로 변경한다.
	 @param painting
	 @return 
	*/
	public Map<String, Object> delStatusPainting(Painting painting);

	/**
	 @fn delStatusPurchase
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 구매의 상태를 삭제로 변경한다.
	 @param purchase
	 @return 
	*/
	public Map<String, Object> delStatusPurchase(PurchaseSearchVO purchase);
}
