/**
@file SingnUpRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | SingnUpRestController.java |    
| Package | com.paintee.mobile.auth.controller |    
| Project name | paintee-admin |    
| Type name | SingnUpRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 12. 오후 10:57:48 |
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.SignupUserVO;
import com.paintee.mobile.auth.service.SignUpService;

/**
@class SingnUpRestController
com.paintee.mobile.auth.controller \n
   ㄴ SingnUpRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 12. 오후 10:57:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원가입 컨트롤러
*/
@RestController(value="com.paintee.mobile.auth.controller.SingnUpRestController")
public class SingnUpRestController {
	private final static Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	@Value("#{config['common.homepage.url'] }")
	private String homepageUrl;

	@Autowired
	private SignUpService signUpService;

	/**
	 @fn signup
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param userLoginVO
	 @param response
	 @return 
	 @throws Exception
	*/
	@RequestMapping(value = "/api/signup", method = {RequestMethod.POST})
	public Map<String, Object> signup(@RequestBody SignupUserVO signupUserVO, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = signUpService.registUser(signupUserVO);

		return resultMap;
	}

	/**
	 @fn signup
	 @brief 함수 간략한 설명 : hash 정보를 통해 사용자계정 활성화
	 @remark
	 - 함수의 상세 설명 : hash 정보를 통해 사용자계정을 활성화시킨다.(0:정상, 1:해당 정보를 찾지 못한경우, 2:expire date 가 지난경우)
	 @param hash
	 @param response
	 @return 
	 @throws Exception
	*/
	@RequestMapping(value = "/api/signup/confirm/{hash}", method = {RequestMethod.GET})
	public void signup(@PathVariable String hash, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		int errorNo = signUpService.confirmHsh(hash);

		response.sendRedirect(homepageUrl);
	}

	/**
	 @fn duplicate
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param user
	 @param response
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value = "/api/signup/chkduplicate", method = {RequestMethod.POST})
	public Map<String, Object> chkduplicate(@RequestBody User user, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		int errorNo = signUpService.checkDuplicate(user);

		resultMap.put("errorNo", errorNo);

		return resultMap;
	}
}