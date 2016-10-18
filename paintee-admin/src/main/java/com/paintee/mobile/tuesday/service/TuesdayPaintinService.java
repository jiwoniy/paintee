package com.paintee.mobile.tuesday.service;

import com.paintee.common.repository.entity.TuesdayPainting;

/**
@class TuesdayController
com.paintee.mobile.tuesday.service.TuesdayPaintinService \n
   ㄴ TuesdayPaintinService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 10. 18. 오후 11:33:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 화요의 그림 service
*/
public interface TuesdayPaintinService {
	/**
	 @fn getTuesdayData
	 @brief 함수 간략한 설명 : 화요의 그림 데이터 조회
	 @remark
	 - 함수의 상세 설명 : 화요의 그림 데이터 조회
	 @return 
	*/
	public TuesdayPainting getTuesdayData();
}
