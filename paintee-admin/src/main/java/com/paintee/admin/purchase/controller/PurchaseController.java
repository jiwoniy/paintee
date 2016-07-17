/**
@file PurchaseController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseController.java |    
| Package | com.paintee.admin.purchase.controller |    
| Project name | paintee-admin |    
| Type name | PurchaseController |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:14:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.purchase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paintee.admin.purchase.service.PurchaseService;
import com.paintee.common.repository.entity.Purchase;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;

/**
@class PurchaseController
com.paintee.admin.purchase.controller \n
   ㄴ PurchaseController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:14:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 관리자 구매 목록 및 상태 변경을 처리하는 controller
*/
@Controller(value="com.paintee.admin.purchase.PurchaseController")
@RequestMapping(value="/admin/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	/**
	 @fn list
	 @brief 함수 간략한 설명 : 구매 목록 데이터를 조회한다.
	 @remark
	 - 함수의 상세 설명 : 구매 목록 데이터를 조회한다. 환불처리와 삭제된 데이터를 제외한다.
	 @return 
	*/
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public void list(Model model) {
		// 데이터 조건 설정
		PurchaseSearchVO search = new PurchaseSearchVO();
		
		// 목록에 조회할 상태
		// 구매상태가 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7/완료-99
		List<String> purchaseStatus = new ArrayList<>();
		purchaseStatus.add("1"); 
		purchaseStatus.add("2"); 
		purchaseStatus.add("3"); 
		purchaseStatus.add("4"); 
		purchaseStatus.add("5"); 
		purchaseStatus.add("99");
		search.setStatusList(purchaseStatus);
		
		Map<String, Object> result = purchaseService.getPurchaseList(search);
		
		model.addAttribute("list", result.get("list"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("statusList", result.get("statusList"));
	}
	
	/**
	 @fn modPurchase
	 @brief 함수 간략한 설명 : 구매 상태 변경한다.
	 @remark
	 - 함수의 상세 설명 : 구매 상태를 변경한다.
	 @return 
	 */
	@RequestMapping(value="/mod", method={RequestMethod.POST})
	public String modPurchase(Purchase purchase, RedirectAttributes model) {
		purchaseService.modPurchaseStatus(purchase);
		model.addFlashAttribute("msg", "상태가 변경되었습니다.");
		return "redirect:/admin/purchase/list";
	}
}
