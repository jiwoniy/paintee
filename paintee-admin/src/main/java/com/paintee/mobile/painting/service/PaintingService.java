/**
@file PaintingService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingService.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PaintingService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:58:49 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.painting.service;

import java.util.Map;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PaintingService
com.paintee.mobile.painting.service \n
   ㄴ PaintingService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:58:49 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 service
*/
public interface PaintingService {
	/**
	 @fn getPaintingInfo
	 @brief 함수 간략한 설명 : 그림에 대한 상세 정보 조회
	 @remark
	 - 함수의 상세 설명 : 그림에 대한 상세 정보를 조회 한다.
	 @param paintingId
	 @param loginedUserVO
	 @return 
	*/
	public PaintingVO getPaintingInfo(String paintingId, LoginedUserVO loginedUserVO) throws Exception;
	
	/**
	 @fn createPainting
	 @brief 함수 간략한 설명 : 그림 정보 생성
	 @remark
	 - 함수의 상세 설명 : 그림 정보 생성
	 @param painting
	 @param fileInfo
	 @param loginedUserVO
	 @return 
	 @throws Exception 
	*/
	public Map<String, Object> createPainting(Painting painting, FileInfo fileInfo, LoginedUserVO loginedUserVO) throws Exception;

	/**
	 @fn updatePainting
	 @brief 함수 간략한 설명 : painting 정보 수정
	 @remark
	 - 함수의 상세 설명 : painting 정보 수정
	 @param painting
	 @return 
	*/
	public boolean updatePainting(Painting painting);
}
