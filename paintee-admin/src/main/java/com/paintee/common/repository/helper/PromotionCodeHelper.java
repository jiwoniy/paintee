/**
@file PromotionCodeHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PromotionCodeHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | PromotionCodeHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 3. 오후 11:09:41 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.vo.PostedSearchVO;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.common.repository.mapper.PromotionCodeMapper;

/**
@class PromotionCodeHelper
com.paintee.common.repository.helper \n
   ㄴ PromotionCodeHelper.java
 @section 클래스작성정보
    |    항  목      |      내  용      |
    | :-----------: | -------------  |
    |   Company     |       Paintee  |
    | Author | Administrator |
    | Date | 2016. 3. 3. 오후 11:09:41 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 프로모션 코드 관련 helper
*/
public interface PromotionCodeVO extends PromotionCodeMapper {
	/**
	 @fn selectPostedList
	 @brief 함수 간략한 설명 : 구매한 사용자의 sentence 정보 목록 조회
	 @remark
	 - 함수의 상세 설명 : 구매한 사용자의 sentence 정보 목록을 조회 한다. 페이징 처리함.
	 @param postedSearchVO
	 @return 
	*/
//	public List<PromotionCodeVO> selectPostedList(PostedSearchVO postedSearchVO);
//	
//	/**
//	 @fn selectPromotionCodeList
//	 @brief 함수 간략한 설명 : 관리자 메뉴의 구매정보를 조회한다.
//	 @remark
//	 - 함수의 상세 설명 : 
//	 @param postedSearchVO
//	 @return 
//	*/
//	public List<PromotionCodeVO> selectPromotionCodeList(PromotionCodeSearchVO promotioncodeSearchVO);
//
//	/**
//	 @fn selectPromotionCodeListCount
//	 @brief 함수 간략한 설명 : 관리자 메뉴의 구매정보 전체 카운트 
//	 @remark
//	 - 함수의 상세 설명 : 
//	 @param postedSearchVO
//	 @return 
//	*/
//	public Integer selectPromotionCodeListCount(PromotionCodeSearchVO promotioncodeSearchVO);
	
}
