/**
@file FollowHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | FollowHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:57:00 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.TuesdayPainting;
import com.paintee.common.repository.entity.vo.TuesdayPaintingSearchVO;
import com.paintee.common.repository.entity.vo.TuesdayPaintingVO;
import com.paintee.common.repository.mapper.TuesdayPaintingMapper;

/**
@class FollowHelper
com.paintee.common.repository.helper \n
   ㄴ FollowHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:57:00 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - TB_FOLLOW 테이블에 접근하기위한 helper
   mapper 의 경우 테이블 변경이 일어날경우 해당 mapper 를 재생성해야 하므로 helper 를 별도 생성하여 helper 를 사용한다.
   helper.xml 은 별도 쿼리 구현시 생성하여 사용한다. 별도 구현 sql 이 존재 하지 않을경우 생성하지 않아도 된다.
*/
public interface TuesdayPaintingHelper extends TuesdayPaintingMapper {

	/**
	 @fn selectRewardInfo
	 @brief 함수 간략한 설명 : 화요의 그림 목록 조회
	 @remark
	 - 함수의 상세 설명 : 화요의 그림 목록을 조회한다.
	 @param user
	 @return 
	*/
	List<TuesdayPaintingVO> selectTuesdayPaintingList(TuesdayPaintingSearchVO search);

	/**
	 @fn selectRewardInfo
	 @brief 함수 간략한 설명 : 오늘 일자에 해당 그림이 무료 화요의 그림일 경우 정보를 조회한다.
	 @remark
	 - 함수의 상세 설명 : 오늘 일자에 해당 그림이 무료 화요의 그림일 경우 정보를 조회하며 그렇지 않을경우 null 이다.
	 @param user
	 @return 
	*/
	TuesdayPainting selectFreeTuesdayPaintingByPaintingId(String paintingId);
}
