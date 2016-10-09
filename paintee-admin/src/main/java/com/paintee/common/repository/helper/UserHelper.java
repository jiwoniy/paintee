/**
@file UserHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | UserHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:59:02 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.mapper.UserMapper;

/**
@class UserHelper
com.paintee.common.repository.helper \n
   ㄴ UserHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:59:02 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자에 대한 helper
*/
public interface UserHelper extends UserMapper {
	
	/**
	 @fn updateUserPurchaseInfo
	 @brief 함수 간략한 설명 : 회원의 구매카운트를 증가시킨다.
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자가 구매시 구매카운트를 증가시킨다.
	 @param user
	 @return 
	*/
	public void updateUserInfo(User user);
}
