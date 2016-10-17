/**
@file AdminSessionInterceptor.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | AdminSessionInterceptor.java |    
| Package | com.paintee.admin.intercepetor |    
| Project name | paintee-admin |    
| Type name | AdminSessionInterceptor |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 4:34:43 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.paintee.mobile.support.interceptor.TokenInterceptor;

/**
@class AdminSessionInterceptor
com.paintee.admin.intercepetor \n
   ㄴ AdminSessionInterceptor.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 4:34:43 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 관리자 로그인 체크 
*/
public class AdminSessionInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("postHandle executed");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("afterCompletion executed");
	}
}
