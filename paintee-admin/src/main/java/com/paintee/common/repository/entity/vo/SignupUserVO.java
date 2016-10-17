/**
@file SignupUserVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | SignupUserVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | SignupUserVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 24. 오후 11:26:09 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import com.paintee.common.repository.entity.User;

/**
@class SignupUserVO
com.paintee.common.repository.entity.vo \n
   ㄴ SignupUserVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 24. 오후 11:26:09 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원 가입 VO
*/
public class SignupUserVO extends User {
	private static final long serialVersionUID = -2061727222542342236L;

    private String providerId;

    private String providerUserId;

    private String accessToken;

    private String refreshToken;

    private Long expireTime;

    private String accessGubun;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getAccessGubun() {
		return accessGubun;
	}

	public void setAccessGubun(String accessGubun) {
		this.accessGubun = accessGubun;
	}
}
