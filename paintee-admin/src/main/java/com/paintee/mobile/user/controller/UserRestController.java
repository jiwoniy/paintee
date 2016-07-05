/**
@file UserRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserRestController.java |    
| Package | com.paintee.mobile.user.controller |    
| Project name | paintee-admin |    
| Type name | UserRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:45:30 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.User;
import com.paintee.mobile.support.obejct.LoginedUserVO;
import com.paintee.mobile.user.service.UserService;

/**
@class UserRestController
com.paintee.mobile.user.controller \n
   ㄴ UserRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:45:30 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 controller
*/
@RestController(value="com.paintee.mobile.user.controller.UserRestController")
public class UserRestController {
	private final static Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	/**
	 @fn follow
	 @brief 함수 간략한 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @remark
	 - 함수의 상세 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @param followingId
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/user/{followingId}/follow", method={RequestMethod.POST})
	public Map<String, Object> follow(@PathVariable String followingId, LoginedUserVO loginedUserVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		logger.debug("loginedUserVO:{}", loginedUserVO);
		int errorNo = 0;
		String errorMsg = "";

		//TODO: 로그인 hash 구현시 hash 에 해당하는 사용자 id 를 넣어주어야 함.
		String followId = loginedUserVO.getUserId();

		errorNo = userService.follow(followId, followingId);

		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);

		return resultMap;
	}
	
	/**
	 @fn getUserInfo
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/user/me", method={RequestMethod.GET})
	public Map<String, Object> getUserInfo(LoginedUserVO loginedUserVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("errorNo", 0);
		resultMap.put("userInfo", loginedUserVO);

		return resultMap;
	}

	/**
	 @fn chkduplicate
	 @brief 함수 간략한 설명 : 사용자 이름 중복 체크
	 @remark
	 - 함수의 상세 설명 : 사용자 이름 중복 체크
	 @param user
	 @param response
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value = "/api/user/chkduplicate", method = {RequestMethod.POST})
	public Map<String, Object> chkduplicate(@RequestBody User user, LoginedUserVO loginedUserVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		int errorNo = 1;

		if(user.getName() != null && user.getName().trim().length() > 0) {
			if(user.getName().equals(loginedUserVO.getName())) {
				errorNo = 0;
			} else {
				errorNo = userService.checkDuplicate(user);
			}
		}

		resultMap.put("errorNo", errorNo);

		return resultMap;
	}

	/**
	 @fn updateUserInfo
	 @brief 함수 간략한 설명 : 사용자 정보 수정
	 @remark
	 - 함수의 상세 설명 : 사용자 정보 수정
	 @param user
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/user/me", method={RequestMethod.PUT})
	public Map<String, Object> updateUserInfo(@RequestBody User user, LoginedUserVO loginedUserVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		user.setUserId(loginedUserVO.getUserId());

		int errorNo = userService.updateUser(user);

		resultMap.put("errorNo", errorNo);

		return resultMap;
	}

	/**
	 @fn postedInfo
	 @brief 함수 간략한 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/user/me/postedCountInfo", method={RequestMethod.GET})
	public Map<String, Object> postedCountInfo(LoginedUserVO loginedUserVO) throws Exception {
		Map<String, Object> resultMap = userService.postedCountInfo(loginedUserVO);

		resultMap.put("errorNo", 0);

		return resultMap;
	}
}
