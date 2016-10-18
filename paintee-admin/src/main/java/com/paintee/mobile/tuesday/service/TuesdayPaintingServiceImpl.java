package com.paintee.mobile.tuesday.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.TuesdayPainting;
import com.paintee.common.repository.entity.TuesdayPaintingExample;
import com.paintee.common.repository.mapper.TuesdayPaintingMapper;

/**
@class TuesdayController
com.paintee.mobile.tuesday.service.TuesdayPaintingServiceImpl \n
   ㄴ TuesdayPaintingServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 10. 18. 오후 11:33:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 화요의 그림 service 구현채
*/
@Service("com.paintee.mobile.tuesday.service.TuesdayPaintingServiceImpl")
public class TuesdayPaintingServiceImpl implements TuesdayPaintinService {
	private final static Logger logger = LoggerFactory.getLogger(TuesdayPaintingServiceImpl.class);

	@Autowired
	private TuesdayPaintingMapper tuesdayPaintingMapper;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 화요의 그림 데이터 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 화요의 그림 데이터 조회
	 @see com.paintee.mobile.tuesday.service.TuesdayPaintinService#getTuesdayData()
	*/
	@Override
	public TuesdayPainting getTuesdayData() {
		TuesdayPainting tuesdayPainting = null;

		Date today = new Date();

		TuesdayPaintingExample example = new TuesdayPaintingExample();
		TuesdayPaintingExample.Criteria where = example.createCriteria();

		where.andStartDateGreaterThanOrEqualTo(today);
		where.andEndDateLessThanOrEqualTo(today);

		List<TuesdayPainting> tuesdayPaintingList = tuesdayPaintingMapper.selectByExample(example);

		if(tuesdayPaintingList != null && tuesdayPaintingList.size() > 0) {
			tuesdayPainting = tuesdayPaintingList.get(0);
		}

		return tuesdayPainting;
	}
}
