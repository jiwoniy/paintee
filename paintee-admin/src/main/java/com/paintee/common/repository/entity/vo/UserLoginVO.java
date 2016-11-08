/**
@file UserLoginVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserLoginVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | UserLoginVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 3:30:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import com.paintee.common.repository.entity.User;

/**
@class UserLoginVO
com.paintee.common.repository.entity.vo \n
   ㄴ UserLoginVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 3:30:07 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 인증 VO
*/
public class UserLoginVO extends User {
	private static final long serialVersionUID = 5836352521716082983L;

	private String accessGubun;

	public String getAccessGubun() {
		return accessGubun;
	}

	public void setAccessGubun(String accessGubun) {
		this.accessGubun = accessGubun;
	}
}
