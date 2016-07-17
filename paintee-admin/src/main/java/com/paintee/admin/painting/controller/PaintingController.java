/**
@file PaintingController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingController.java |    
| Package | com.paintee.admin.test.controller |    
| Project name | paintee-admin |    
| Type name | PaintingController |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:14:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.painting.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paintee.admin.painting.service.PaintingService;
import com.paintee.common.repository.entity.Painting;

/**
@class PaintingController
com.paintee.admin.test.controller \n
   ㄴ PaintingController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:14:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - jsp view 를 포함한 controller
*/
@Controller(value="com.paintee.admin.painting.PaintingController")
@RequestMapping(value="/admin/painting")
public class PaintingController {

	@Autowired
	private PaintingService paintingService;
	
	/**
	 @fn test
	 @brief 함수 간략한 설명 : 신규 그림 정보를 조회 
	 @remark
	 - 함수의 상세 설명 : 신규 그림 정보를 조회
	 @return 
	*/
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public void list(Model model) {
		// 데이터 조건 설정
		Map<String, Object> result = paintingService.getPatingList();
		model.addAttribute("list", result.get("list"));
		model.addAttribute("count", result.get("count"));
	}
	
	/**
	 @fn modPainting
	 @brief 함수 간략한 설명 : 그림업데이트 상태 변경
	 @remark
	 - 함수의 상세 설명 : 
	 @return 
	 */
	@RequestMapping(value="/mod", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, String> modPainting(Painting painting) {
		paintingService.modPaintingStatus(painting);
		Map<String, String> result = new HashMap<>();
		result.put("msg", "상태가 변경되었습니다.");
		return result;
	}
}
