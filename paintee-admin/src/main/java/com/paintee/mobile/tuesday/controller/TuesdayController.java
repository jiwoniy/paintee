package com.paintee.mobile.tuesday.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.mobile.tuesday.service.TuesdayService;

/**
@class TuesdayController
com.paintee.mobile.tuesday.controller.TuesdayController \n
   ㄴ TestController.java
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
@RestController(value="com.paintee.mobile.tuesday.controller.TuesdayController")
public class TuesdayController {
	private final static Logger logger = LoggerFactory.getLogger(TuesdayController.class);

	@Autowired
	private TuesdayService tuesdayService;

	@RequestMapping(value="/api/tuesday", method={RequestMethod.GET})
	public Map<String, Object> getTuesdayData() throws Exception {

		return null;
	}
}
