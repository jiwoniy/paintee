/**
@file PostedService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PostedService.java |    
| Package | com.paintee.mobile.posted.service |    
| Project name | paintee-admin |    
| Type name | PostedService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 8:30:26 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.posted.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.PostedSearchVO;

/**
@class PostedService
com.paintee.mobile.posted.service \n
   ㄴ PostedService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 8:30:26 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자의 sentence 정보 관련 service
*/
public interface PostedService {

	/**
	 @fn getPostedData
	 @brief 함수 간략한 설명 : 구매한 사용자의 sentence 정보 목록 조회
	 @remark
	 - 함수의 상세 설명 : 구매한 사용자의 sentence 정보 목록을 조회 한다. 페이징 처리함.
	 @param searchVO
	 @return 
	*/
	public Map<String, Object> getPostedData(PostedSearchVO searchVO);
}
