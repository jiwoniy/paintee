package com.paintee.mobile.tuesday.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.TuesdayPainting;
import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.TuesdayPaintingSearchVO;
import com.paintee.mobile.support.obejct.LoginedUserVO;
import com.paintee.mobile.tuesday.service.TuesdayPaintinService;

/**
@class TuesdayPaintingController
com.paintee.mobile.tuesday.controller.TuesdayPaintingController \n
   ㄴ TuesdayPaintingController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 10. 18. 오후 11:33:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 화요의 그림 api
*/
@RestController(value="com.paintee.mobile.tuesday.controller.TuesdayPaintingController")
public class TuesdayPaintingController {
	private final static Logger logger = LoggerFactory.getLogger(TuesdayPaintingController.class);

	@Autowired
	private TuesdayPaintinService tuesdayService;
/*
	@RequestMapping(value="/api/tuesday", method={RequestMethod.GET})
	public TuesdayPainting getTuesdayData() throws Exception {

		return tuesdayService.getTuesdayData();
	}
*/	
	/**
	 @fn getTuesdayData
	 @brief 함수 간략한 설명 : 화요의 그림 목록
	 @remark
	 - 함수의 상세 설명 : 관리자가 선택한 이주의 화요 그림 목록을 조회한다.
	 @param loginedUserVO
	 @param startRow
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/tuesday", method=RequestMethod.GET)
	public Map<String, Object> getTuesdayData(LoginedUserVO loginedUserVO, 
			@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) 
					throws Exception {
		
		
		// 데이터 조건 설정
		TuesdayPaintingSearchVO search = new TuesdayPaintingSearchVO();
		
		// 요청 데이터 페이징 정보
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		
		// 공개인것만 
		search.setPrivateAt("N");
		
		// 로그인 사용자 아이디
		if (loginedUserVO != null) {
			search.setUserId(loginedUserVO.getUserId());
		} else {
			search.setUserId("00000");
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = tuesdayService.getTuesdayData(search);
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		return resultMap;
	}
}
