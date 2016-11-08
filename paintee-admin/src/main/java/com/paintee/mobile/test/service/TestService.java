/**
@file TestService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestService.java |    
| Package | com.paintee.mobile.test.service |    
| Project name | paintee-admin |    
| Type name | TestService |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 6:16:39 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.test.service;

import com.paintee.common.repository.entity.FileInfo;

/**
@class TestService
com.paintee.mobile.test.service \n
   ㄴ TestService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 6:16:39 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public interface TestService {

	/**
	 @fn saveFileInfo
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @return 
	*/
	public long saveFileInfo(FileInfo fileInfo) throws Exception;
}
