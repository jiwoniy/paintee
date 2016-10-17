/**
@file PurchaseController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseController.java |    
| Package | com.paintee.mobile.purchase.controller |    
| Project name | paintee-admin |    
| Type name | PurchaseController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:33:20 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.purchase.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.mobile.purchase.service.PurchaseService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PurchaseController
com.paintee.mobile.purchase.controller \n
   ㄴ PurchaseController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:33:20 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매와 관련된 처리를 진행하는 컨트롤러 
*/
@RestController(value="com.paintee.mobile.purchase.controller.PurchaseRestController")
public class PurchaseRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(PurchaseRestController.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	/**
	 @fn purchasePopInfo
	 @brief 함수 간략한 설명 : 구매에 필요한 기능 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 구매에 필요한 기능 정보를 조회
	 @param paintingId
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/purchasePopInfo", method={RequestMethod.GET})
	public Map<String, Object> purchasePopInfo(@RequestParam("paintingId") String paintingId, LoginedUserVO loginedUserVO) throws Exception {
		logger.debug("purchasePopInfo paintingId ::: {}", paintingId);
		// 구매관련 정보 등록
		return purchaseService.purchasePopInfo(paintingId, loginedUserVO);
	}
	
	/**
	 @fn addPurchase
	 @brief 함수 간략한 설명 : 구매 정보를 추가
	 @remark
	 - 함수의 상세 설명 : 사용자가 요청한 구매 정보를 추가한다.
	 @param purchase
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/purchase", method={RequestMethod.POST})
	public Map<String, Object> addPurchase(@RequestBody PurchaseSearchVO purchase, LoginedUserVO loginedUserVO) throws Exception {
		logger.debug(purchase.toString());
		// 로그인 사용자의 구매자 아이디 입력
		purchase.setUserId(loginedUserVO.getUserId());
		// 구매관련 정보 등록
		return purchaseService.addPurchase(purchase);
	}
	
	/**
	 @fn resendPurchase
	 @brief 함수 간략한 설명 : 구매 상태 변경 처리
	 @remark
	 - 함수의 상세 설명 : 마이페이지에서 그림의 상태 변경을 요청했을때 상태 변경을 처리한다.
	                <br />1. 재발송 요청시
	                <br />2. 취소 요청시
	                <br />3. 완료 요청시
	                <br />4. 환불취소 요청시
	 @param purchase
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value={"/api/resendStatusPurchase", "/api/cancelPurchase", "/api/completeStatusPurchase", "/api/cancelRefundPurchase"}, method={RequestMethod.POST})
	public Map<String, Object> changeStatusPurchase(@RequestBody PurchaseSearchVO purchase) throws Exception {
		logger.debug(purchase.toString());
		return purchaseService.updateStatusPurchase(purchase);
	}
	
	/**
	 @fn delStatusPainting
	 @brief 함수 간략한 설명 : 그림의 상태를 삭제로 변경 
	 @remark
	 - 함수의 상세 설명 : 마이페이지에서 그림을 삭제 요청 했을때 처리 그림의 상태를 삭제로 변경한다.
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/delStatusPainting", method={RequestMethod.POST})
	public Map<String, Object> delStatusPainting(@RequestBody Painting painting, LoginedUserVO loginedUserVO) throws Exception {
		return purchaseService.delStatusPainting(painting);
	}

	/**
	 @fn delStatusPurchase
	 @brief 함수 간략한 설명 : 구매 상태를 삭제로 변경
	 @remark
	 - 함수의 상세 설명 : 마이페이지에서 그림을 구매상태를 삭제로 요청 했을때 그림의 구매 상태를 삭제로 변경한다.
	 @param loginedUserVO
	 @return
	 @throws Exception 
	 */
	@RequestMapping(value="/api/delStatusPurchase", method={RequestMethod.POST})
	public Map<String, Object> delStatusPurchase(@RequestBody PurchaseSearchVO purchase) throws Exception {
		return purchaseService.delStatusPurchase(purchase);
	}
}