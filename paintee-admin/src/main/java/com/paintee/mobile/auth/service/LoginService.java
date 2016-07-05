/**
@file LonginService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | LoginService.java |    
| Package | com.paintee.mobile.auth.service |    
| Project name | paintee-admin |    
| Type name | LoginService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 2:02:51 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.service;

import java.util.Map;

import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.UserLoginVO;

/**
@class LoginService
com.paintee.mobile.auth.service \n
   ㄴ LoginService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 2:02:51 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 인증관련 service
*/
public interface LoginService {
	/**
	 @fn login
	 @brief 함수 간략한 설명 : 사용자 인증
	 @remark
	 - 함수의 상세 설명 : email 과 passwod 를 사용하여 사용자 인증을 한다.
	 @param userLoginVO
	 @return 
	*/
	public Map<String, Object> login(UserLoginVO userLoginVO);

	/**
	 @fn loginSocial
	 @brief 함수 간략한 설명 : 소셜 사용자 인증
	 @remark
	 - 함수의 상세 설명 : email 과 providerId, accessToken 을 사용하여 사용자 인증을 한다.
	 @param userLoginVO
	 @return 
	*/
	public Map<String, Object> loginSocial(UserLoginVO userLoginVO);

	/**
	 @fn hashCheck
	 @brief 함수 간략한 설명 : hash 에 대한 유효성 검증
	 @remark
	 - 함수의 상세 설명 : hash 에 대한 유효성 검증
	 @param painteeHash
	 @return 
	*/
	public boolean hashCheck(String painteeHash);

	/**
	 @fn getUser
	 @brief 함수 간략한 설명 : hash 정보를 사용하여 해당 사용자 정보 조회
	 @remark
	 - 함수의 상세 설명 : hash 정보를 사용하여 해당 사용자 정보 조회
	 @param painteeHash
	 @return 
	*/
	public User getUser(String painteeHash);
	
	/**
	 @fn resetpassword
	 @brief 함수 간략한 설명 : 비밀번호 초기화후 메일로 임시비밀번호 발송
	 @remark
	 - 함수의 상세 설명 : 비밀번호 초기화후 메일로 임시비밀번호 발송
	 @param userLoginVO
	 @return
	 @throws Exception 
	*/
	public Map<String, Object> resetpassword(UserLoginVO userLoginVO) throws Exception;
}
