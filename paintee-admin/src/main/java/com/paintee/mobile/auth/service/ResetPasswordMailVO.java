/**
@file ResetPasswordMailVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | ResetPasswordMailVO.java |    
| Package | com.paintee.mobile.auth.service |    
| Project name | paintee-admin |    
| Type name | ResetPasswordMailVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 15. 오후 10:39:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.service;

import com.paintee.common.object.BaseObject;

/**
@class ResetPasswordMailVO
com.paintee.mobile.auth.service \n
   ㄴ ResetPasswordMailVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 15. 오후 10:39:07 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 비밀번호 초기화
*/
public class ResetPasswordMailVO extends BaseObject {
	private static final long serialVersionUID = 7607664648897494890L;

	private String title;
	private String password;
	private String senderName;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
