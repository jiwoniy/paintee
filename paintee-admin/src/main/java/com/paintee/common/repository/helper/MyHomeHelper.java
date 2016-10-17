/**
@file MyHomeHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | MyHomeHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:57:00 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.vo.MyHomeSearchVO;
import com.paintee.common.repository.entity.vo.MyHomeVO;
import com.paintee.common.repository.mapper.FollowMapper;

/**
@class MyHomeHelper
com.paintee.common.repository.helper \n
   ㄴ MyHomeHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:57:00 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 내가 업로드한 그림 및 구매한 그림의 정보를 조회
*/
public interface MyHomeHelper extends FollowMapper {
	public List<MyHomeVO> selectMyHomePaintingList(MyHomeSearchVO searchVO);
	public MyHomeVO selectMyHomeInfo(MyHomeSearchVO search);
}
