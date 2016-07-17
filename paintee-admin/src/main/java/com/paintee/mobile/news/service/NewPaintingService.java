/**
@file NewPaintingService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | NewPaintingService.java |    
| Package | com.paintee.mobile.news.service |    
| Project name | paintee-admin |    
| Type name | NewPaintingService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 4:03:19 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.news.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;

/**
@class NewPaintingService
com.paintee.mobile.news.service \n
   ㄴ NewPaintingService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 4:03:19 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public interface NewPaintingService {

	/**
	 @fn getPopularInfo
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param search
	 @return
	 @throws Exception 
	*/
	public Map<String, Object> getNewPatingInfo(PaintingSearchVO search) throws Exception;
}
