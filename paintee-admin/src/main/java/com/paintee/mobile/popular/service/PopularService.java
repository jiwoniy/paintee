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
package com.paintee.mobile.popular.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;

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
public interface PopularService {
	
	/**
	 @fn getPopularInfo
	 @brief 함수 간략한 설명 : 인기구매그림 정보
	 @remark
	 - 함수의 상세 설명 : 많이 구입된 그림의 정보를 조회한다.
	 @param 
	 @return
	 @throws Exception 
	*/
	public Map<String, Object> getPopularInfo(PaintingSearchVO popular) throws Exception;
}
