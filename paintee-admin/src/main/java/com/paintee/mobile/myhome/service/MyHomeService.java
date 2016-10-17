/**
@file MyHomeService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeService.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | MyHomeService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.myhome.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.MyHomeSearchVO;

/**
@class MyHomeService
com.paintee.mobile.follow.service \n
   ㄴ MyHomeService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:07 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service
*/
public interface MyHomeService {

	/**
	 @fn getFollowPaintingInfo
	 @brief 함수 간략한 설명 : 내가 업로드 하거나 포스트한 그림의 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 내가 업로드 하거나 포스트한 그림의 정보를 조회<br />그림이 삭제된 경우 내가 내 그림을 구매한 경우 해당 그림은 보이지 않지만 
	                        <br />다른 사람이 삭제된 그림을 구매한 경우 해당 그림 정보를 조회한다.
	 @param search
	 @return 
	*/
	public Map<String, Object> getMyHomePaintingInfo(MyHomeSearchVO search);
}
