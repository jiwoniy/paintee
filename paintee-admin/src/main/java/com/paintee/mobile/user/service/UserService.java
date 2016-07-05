/**
@file UserService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserService.java |    
| Package | com.paintee.mobile.user.service |    
| Project name | paintee-admin |    
| Type name | UserService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:46:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.user.service;

import java.util.Map;

import com.paintee.common.repository.entity.User;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class UserService
com.paintee.mobile.user.service \n
   ㄴ UserService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:46:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 service
*/
public interface UserService {
	/**
	 @fn follow
	 @brief 함수 간략한 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @remark
	 - 함수의 상세 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @param followId
	 @param followingId
	 @return 
	*/
	public int follow(String followId, String followingId);
	
	/**
	 @fn checkDuplicate
	 @brief 함수 간략한 설명 : 사용자 이름 중복 체크
	 @remark
	 - 함수의 상세 설명 : 사용자 이름 중복 체크
	 @param user
	 @return 
	*/
	public int checkDuplicate(User user);
	
	/**
	 @fn updateUser
	 @brief 함수 간략한 설명 : 사용자 정보 수정
	 @remark
	 - 함수의 상세 설명 : 사용자 정보 수정
	 @param user
	 @return 
	*/
	public int updateUser(User user);

	/**
	 @fn postedCountInfo
	 @brief 함수 간략한 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @param loginedUserVO
	 @return 
	*/
	public Map<String, Object> postedCountInfo(LoginedUserVO loginedUserVO);
}
