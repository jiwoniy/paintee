/**
@file PasswordGenerator.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PasswordGenerator.java |    
| Package | com.paintee.common.util |    
| Project name | paintee-admin |    
| Type name | PasswordGenerator |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 15. 오후 10:30:37 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.util;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
@class PasswordGenerator
com.paintee.common.util \n
   ㄴ PasswordGenerator.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 15. 오후 10:30:37 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 비밀번호 생성 util
*/
@Component(value="com.paintee.common.util.PasswordGenerator")
public class PasswordGenerator {
	private String[] baseString = new String[]{
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "0"};

	/**
	* @fn randomPassword
	* @brief 함수 간략한 설명 : 랜덤 비밀번호 생성
	* @remark - 함수의 상세 설명 : 20자리의 clientSecretAlias를 생성한다.
	* @return
	*/
	public String randomPassword() {
		StringBuilder planText = new StringBuilder();
		Random r = new Random();
		
		for (int i = 0; i < 8; i++) {
			planText.append(baseString[r.nextInt(baseString.length)]);
		}
	
		return planText.toString();
	}
}
