/**
@file ConfirmMailVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | ConfirmMailVO.java |    
| Package | com.paintee.common.mail |    
| Project name | paintee-admin |    
| Type name | ConfirmMailVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 13. 오후 4:19:38 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.mail;

import com.paintee.common.object.BaseObject;

/**
@class ConfirmMailVO
com.paintee.common.mail \n
   ㄴ ConfirmMailVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 13. 오후 4:19:38 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원가입 confirmmaile vo
*/
public class ConfirmMailVO extends BaseObject {
	private static final long serialVersionUID = 6518299502568393978L;

	private String title;
	private String confirmUrl;
	private String senderName;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getConfirmUrl() {
		return confirmUrl;
	}
	public void setConfirmUrl(String confirmUrl) {
		this.confirmUrl = confirmUrl;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
