/**
@file NewPaintingRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | NewPaintingRestController.java |    
| Package | com.paintee.mobile.news.controller |    
| Project name | paintee-admin |    
| Type name | NewPaintingRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 4:00:11 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.news.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.mobile.news.service.NewPaintingService;

/**
@class NewPaintingRestController
com.paintee.mobile.news.controller \n
   ㄴ NewPaintingRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 4:00:11 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 신규 업로드 그림 정보 컨트롤러 객체
*/
@RestController(value="com.paintee.mobile.news.controller.NewPaintingRestController")
public class NewPaintingRestController {
	
	/**
	@brief 신규 업로드 그림에 대한 정보를 처리하는 서비스 객체
	*/
	@Autowired
	private NewPaintingService newPaintingService;
	
	/**
	 @fn index
	 @brief 함수 간략한 설명 : 신규 업로드 목록
	 @remark
	 - 함수의 상세 설명 : 신규 업로드 목록의 정보를 조회함(신규그림 홈, 목록 그림 정보)
	 @param startRow
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/newIndex", method={RequestMethod.GET})
	public Map<String, Object> index(@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) throws Exception {
		// 데이터 조건 설정
		PaintingSearchVO search = new PaintingSearchVO();
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		search.setPaintingStatus("N"); // 그림의 상태가 정상인 것만 조회
		search.setPrivateAt("N"); // 공개된 그림만 조회
		
		return newPaintingService.getNewPatingInfo(search);
	}
}
