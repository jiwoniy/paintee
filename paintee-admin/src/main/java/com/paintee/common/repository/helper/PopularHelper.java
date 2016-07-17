/**
@file PopularHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PopularHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | PopularHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오전 12:36:48 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.common.repository.entity.vo.PopularVO;

/**
@class PopularHelper
com.paintee.common.repository.helper \n
   ㄴ PopularHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오전 12:36:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 인기 그림 데이터베이스 처리 헬퍼
*/
public interface PopularHelper {
	/**
	 @fn selectPopularPaintingList
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 120일 이내 가장 많이 구매된 그림의 정보를 조회
	 @param popular
	 @return 
	*/
	List<PopularVO> selectPopularPaintingList(PaintingSearchVO popular);
}
