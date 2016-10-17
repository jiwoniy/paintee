/**
@file PersonalHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | PersonalHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:57:00 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.vo.PersonalSearchVO;
import com.paintee.common.repository.entity.vo.PersonalVO;
import com.paintee.common.repository.mapper.PaintingMapper;

/**
@class PersonalHelper
com.paintee.common.repository.helper \n
   ㄴ PersonalHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:57:00 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 개인페이지 정보에 필요한 테이블을 접근하기 위한 헬퍼 클리스
*/
public interface PersonalHelper extends PaintingMapper {
	public List<PersonalVO> selectPersonalPaintingList(PersonalSearchVO searchVO);
	public PersonalVO selectPersonalPaintingInfo(PersonalSearchVO search);
}
