/**
@file PersonalRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalRestController.java |    
| Package | com.paintee.mobile.personal.controller |    
| Project name | paintee-admin |    
| Type name | PersonalRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.personal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PersonalSearchVO;
import com.paintee.mobile.personal.service.PersonalService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PersonalRestController
com.paintee.mobile.personal.controller \n
   ㄴ PersonalRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:23:44 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 개인페이지 그림의 정보를 처리하는 컨트롤러 객체
*/
@RestController(value="com.paintee.mobile.follow.controller.PersonalRestController")
public class PersonalRestController {

	@Autowired
	private PersonalService personalService;

	/**
	 @fn personalInfo
	 @brief 함수 간략한 설명 : 개인페이지에 보여여할 홈 정보와 그림 목록 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 개인페이지에 보여여할 홈 정보와 그림 목록 정보를 조회
	 @param search
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/index/personal", method=RequestMethod.GET)
	public Map<String, Object> personalInfo(PersonalSearchVO search, LoginedUserVO loginedUserVO) throws Exception {
		// 요청 데이터 페이징 정보
		search.setRowPerPage(5);
		
		List<String> paintingStatusList = new ArrayList<>();
		paintingStatusList.add("N");  // 정상
		search.setPaintingStatusList(paintingStatusList);
		
		if (loginedUserVO != null) {
			search.setLoginId(loginedUserVO.getUserId());
		}
		return personalService.getPersonalPaintingInfo(search);
	}
	
	/**
	 @fn pictureStatus
	 @brief 함수 간략한 설명 : 그림의 상태를 조회
	 @remark
	 - 함수의 상세 설명 : 그림의 상태를 조회
	 @param painting
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/index/personal/pictureStatus", method=RequestMethod.GET)
	public Map<String, Object> pictureStatus(Painting painting) throws Exception {
		return personalService.getPersonalPaintingStatus(painting);
	}
	
	/**
	 @fn artistStatus
	 @brief 함수 간략한 설명 : 그림 작가의 상태 조회
	 @remark
	 - 함수의 상세 설명 : 그림 작가의 상태 조회
	 @param user
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/index/personal/artistStatus", method=RequestMethod.GET)
	public Map<String, Object> artistStatus(User user) throws Exception {
		return personalService.getPersonalArtistStatus(user);
	}

}
