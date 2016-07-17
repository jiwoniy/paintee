/**
@file MailService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MailService.java |    
| Package | com.paintee.common.mail |    
| Project name | paintee-admin |    
| Type name | MailService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 13. 오후 3:36:48 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.mail;

/**
@class MailService
com.paintee.common.mail \n
   ㄴ MailService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 13. 오후 3:36:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - email 서비스
*/
public interface MailService {
	/**
	 @fn sendMail
	 @brief 함수 간략한 설명 : email 발송
	 @remark
	 - 함수의 상세 설명 : email 발송
	 @param to
	 @param subject
	 @param text 
	 @throws Exception 
	*/
	public void sendMail(String to, String subject, String text) throws Exception;
}
