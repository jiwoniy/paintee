/**
@file LoginRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | LoginRestController.java |    
| Package | com.paintee.mobile.auth.controller |    
| Project name | paintee-admin |    
| Type name | LoginRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:51:11 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.UserLoginVO;
import com.paintee.mobile.auth.service.LoginService;

/**
@class LoginRestController
com.paintee.mobile.auth.controller \n
   ㄴ LoginRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:51:11 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 인증관련 rest controller
*/
@RestController(value="com.paintee.mobile.auth.controller.LoginRestController")
public class LoginRestController {
	private final static Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	@Value("#{config['common.login.hash.expireDay'] }")
	private int expireDay;

	@Autowired
	private LoginService loginService;
	
	 /**
	 @fn login
	 @brief 함수 간략한 설명 : 사용자 인증
	 @remark
	 - 함수의 상세 설명 : userId 와 passwod 를 사용하여 사용자 인증을 한다.
	 @param userLoginVO
	 @return 
	*/
	@RequestMapping(value = "/api/login", method = {RequestMethod.POST})
	public Map<String, Object> login(@RequestBody UserLoginVO userLoginVO, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();

		if(userLoginVO.getEmail() == null || userLoginVO.getEmail().trim().length() == 0) {
			resultMap.put("errorNo", 401);
		} else if(userLoginVO.getPassword() == null || userLoginVO.getPassword().trim().length() == 0) {
			resultMap.put("errorNo", 402);
		} else {
			resultMap = loginService.login(userLoginVO);
		}

		return resultMap;
	}

	/**
	 @fn loginSocial
	 @brief 함수 간략한 설명 : 소셜 사용자 인증
	 @remark
	 - 함수의 상세 설명 : email 과 providerId, accessToken 을 사용하여 사용자 인증을 한다.
	 @param userLoginVO
	 @param response
	 @return 
	*/
	@RequestMapping(value = "/api/login/social", method = {RequestMethod.POST})
	public Map<String, Object> loginSocial(@RequestBody UserLoginVO userLoginVO, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();

		if(userLoginVO.getEmail() == null || userLoginVO.getEmail().trim().length() == 0) {
			resultMap.put("errorNo", 401);
		} else {
			resultMap = loginService.loginSocial(userLoginVO);
		}

		return resultMap;
	}

	/**
	 @fn resetpasswd
	 @brief 함수 간략한 설명 : 비밀번호 초기화후 메일로 임시비밀번호 발송
	 @remark
	 - 함수의 상세 설명 : 비밀번호 초기화후 메일로 임시비밀번호 발송
	 @param userLoginVO
	 @param response
	 @return 
	*/
	@RequestMapping(value = "/api/resetpasswd", method = {RequestMethod.POST})
	public Map<String, Object> resetpasswd(@RequestBody UserLoginVO userLoginVO, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		if(userLoginVO.getEmail() == null || userLoginVO.getEmail().trim().length() == 0) {
			resultMap.put("errorNo", 401);
		} else {
			resultMap = loginService.resetpassword(userLoginVO);
		}

		return resultMap;
	}
}
