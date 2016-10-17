/**
@file UserHandlerMethodArgumentResolver.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserHandlerMethodArgumentResolver.java |    
| Package | com.paintee.mobile.support.resolver |    
| Project name | paintee-admin |    
| Type name | UserHandlerMethodArgumentResolver |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 9. 오후 9:21:51 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.support.resolver;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.paintee.common.repository.entity.User;
import com.paintee.mobile.auth.service.LoginService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class UserHandlerMethodArgumentResolver
com.paintee.mobile.support.resolver \n
   ㄴ UserHandlerMethodArgumentResolver.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 9. 오후 9:21:51 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 로그인 사용자의 정보를 controller 에 injection 하기위한 resolver
*/
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private final static Logger logger = LoggerFactory.getLogger(UserHandlerMethodArgumentResolver.class);

	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	*/
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return LoginedUserVO.class.isAssignableFrom(parameter.getParameterType());
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	*/
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		LoginedUserVO loginedUserVO = null;

		String painteeHash = webRequest.getHeader("X-PAINTEE-HASH");

		if(painteeHash != null) {
			boolean isEffective = loginService.hashCheck(painteeHash);

			if(isEffective) {
				loginedUserVO = new LoginedUserVO();

				User user = loginService.getUser(painteeHash);

				BeanUtils.copyProperties(loginedUserVO, user);
			}
		}

		return loginedUserVO;
	}

}
