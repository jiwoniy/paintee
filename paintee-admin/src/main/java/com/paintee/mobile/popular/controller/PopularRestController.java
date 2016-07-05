/**
@file PopularRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PopularRestController.java |    
| Package | com.paintee.mobile.popular.controller |    
| Project name | paintee-admin |    
| Type name | PopularRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 3. 오후 5:46:32 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.popular.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.mobile.popular.service.PopularService;

/**
@class PopularRestController
com.paintee.mobile.popular.controller \n
   ㄴ PopularRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 3. 오후 5:46:32 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 최근 120일 이내의 구매수가 가장 많은 그림의 정보를 처리하는 컨트롤러 객체
*/
@RestController(value="com.paintee.mobile.popular.controller.PopularRestController")
public class PopularRestController {
	
	/**
	@brief 인기 그림에 대한 정보를 처리하는 서비스 객체
	*/
	@Autowired
	private PopularService popularService;
	
	/**
	 @fn index
	 @brief 함수 간략한 설명 : 인기 그림 목록
	 @remark
	 - 함수의 상세 설명 : 120일 이내의 많이 구매된 순으로 그림 목록을 조회함(인기그림 홈, 목록 그림 정보)
	 @param startRow
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/popularIndex", method={RequestMethod.GET})
	public Map<String, Object> index(@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) throws Exception {
		
		// 데이터 조건 설정
		PaintingSearchVO search = new PaintingSearchVO();
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		search.setPrivateAt("N");       // 공개
		search.setPaintingStatus("N");  // 정상
		
		// 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7
		List<String> purchaseStatusList = new ArrayList<>();
		purchaseStatusList.add("1");  // 요청
		purchaseStatusList.add("2");  // 발송
		purchaseStatusList.add("4");  // 재발송요청
		purchaseStatusList.add("5");  // 재발송처리
		purchaseStatusList.add("99");  // 완료
		search.setPurchaseStatusList(purchaseStatusList);
		
		return popularService.getPopularInfo(search);
	}
}
