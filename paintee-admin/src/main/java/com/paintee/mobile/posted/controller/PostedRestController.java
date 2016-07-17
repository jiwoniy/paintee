/**
@file PostedRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PostedRestController.java |    
| Package | com.paintee.mobile.posted.controller |    
| Project name | paintee-admin |    
| Type name | PostedRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 8:28:58 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.posted.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.PostedSearchVO;
import com.paintee.mobile.posted.service.PostedService;

/**
@class PostedRestController
com.paintee.mobile.posted.controller \n
   ㄴ PostedRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 8:28:58 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자의 sentence 정보 관련 rest controller
*/
@RestController("com.paintee.mobile.posted.controller.PostedRestController")
public class PostedRestController {
	@Autowired
	private PostedService postedService;

	@RequestMapping(value="/api/posted", method={RequestMethod.GET})
	public Map<String, Object> getPostedData(PostedSearchVO postedSearchVO) throws Exception {

		// 데이터 조건 설정
		postedSearchVO.setPrivateAt("N");
		List<String> purchaseStatusList = new ArrayList<>();
		purchaseStatusList.add("1");
		purchaseStatusList.add("2");
		purchaseStatusList.add("4");
		purchaseStatusList.add("5");
		purchaseStatusList.add("99");

		postedSearchVO.setPurchaseStatusList(purchaseStatusList);

		Map<String, Object> result = postedService.getPostedData(postedSearchVO);

		return result;
	}
}