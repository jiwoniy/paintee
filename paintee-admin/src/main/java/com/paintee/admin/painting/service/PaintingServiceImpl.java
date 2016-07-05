/**
@file PaintingServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingServiceImpl.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PaintingServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.painting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.UserHelper;

/**
@class PaintingServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ PaintingServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:59:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 service 구현채
*/
@Service(value="com.paintee.admin.painting.service.PaintingServiceImpl")
public class PaintingServiceImpl implements PaintingService {
	private final static Logger logger = LoggerFactory.getLogger(PaintingServiceImpl.class);
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	@Autowired
	private UserHelper userHelper;

	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see com.paintee.admin.painting.service.PaintingService#getPatingList()
	*/
	@Override
	public Map<String, Object> getPatingList() {
		
		List<PaintingVO> list = paintingHelper.selectPaintingUpdateList();
		logger.debug("list : " + list);
		
		int count = paintingHelper.selectPaintingUpdateListCount();
		logger.debug("전체 개수 : " + count);
		
		// 파일정보 조회
		for (PaintingVO painting : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(painting.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				painting.setFileInfo(fileInfo);
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 그림 상태 변경
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	     그림의 상태를 변경한다.
	   <br />1. 그림의 상태를 삭제로 변경 시 사용자의 업로드 카운트를 감소시킨다.
	   <br />2. 정상으로 변경되는 경우 기존 그림의 상태를 조회한 다음 그림의 상태가 삭제였을 경우 작가의 업로드 카운트를 증가시킨다.
	 @see com.paintee.admin.painting.service.PaintingService#modPaintingStatus(com.paintee.common.repository.entity.Painting)
	*/
	@Override
	public void modPaintingStatus(Painting painting) {
		
		User user = null;
		
		// 그림 상태를 삭제로 변경시
		if ("D".equalsIgnoreCase(painting.getPaintingStatus())) {
			// 사용자의 업데이트 카운트 감소
			user = new User();
			user.setUploadCnt(-1);
			user.setUserId(painting.getArtistId());
			userHelper.updateUserInfo(user);
		} 
		// 정상으로 변경되는 경우 기존 그림의 상태를 조회한 다음 그림의 상태가 삭제였을 경우 작가의 업로드 카운트를 1 증가시킨다.
		else if ("N".equalsIgnoreCase(painting.getPaintingStatus())) {
			Painting paint = paintingHelper.selectByPrimaryKey(painting.getSeq());
			if ("D".equalsIgnoreCase(paint.getPaintingStatus())) {
				user = new User();
				user.setUploadCnt(1);
				user.setUserId(painting.getArtistId());
				userHelper.updateUserInfo(user);
			}
		}

		// 구매 그림의 상태 변경
		paintingHelper.updateByPrimaryKeySelective(painting);
	}
}
