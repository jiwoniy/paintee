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
package com.paintee.mobile.painting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paintee.common.file.service.FileService;
import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FollowExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.helper.FollowHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.mobile.support.obejct.LoginedUserVO;

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
@Service(value="com.paintee.mobile.painting.service.PaintingServiceImpl")
public class PaintingServiceImpl implements PaintingService {
	private final static Logger logger = LoggerFactory.getLogger(PaintingServiceImpl.class);

	@Autowired
	private PaintingHelper paintingHelper;

	@Autowired
	private FileService fileService;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private FollowHelper followHelper;

	public PaintingVO getPaintingInfo(String paintingId, LoginedUserVO loginedUserVO) throws Exception {
		/*
		PaintingExample example = new PaintingExample();
		PaintingExample.Criteria pWhere = example.createCriteria();
		pWhere.andPaintingIdEqualTo(paintingId);
		Painting painting = paintingHelper.selectByExample(example).get(0);
		*/
		
		logger.debug("paintingId ::: " + paintingId);
		PaintingVO painting = paintingHelper.selectPaintingInfo(paintingId);
		
		//파일정보 조회
		List<FileInfo> fileInfoList = fileService.getFileInfoList(painting.getFileGroupSeq());

		if(fileInfoList != null && fileInfoList.size() > 0) {
			FileInfo fileInfo = fileInfoList.get(0);

			painting.setFileInfo(fileInfo);
		}

		painting.setFollowed(false);

		if(loginedUserVO != null) {
			FollowExample followExample = new FollowExample();
			FollowExample.Criteria where = followExample.createCriteria();
			where.andUserIdEqualTo(loginedUserVO.getUserId());
			where.andFollowingEqualTo(painting.getArtistId());

			int followCount = followHelper.countByExample(followExample);
			if(followCount > 0) {
				painting.setFollowed(true);
			}
		}

		logger.debug("painting2:{}", painting);

		return painting;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 그림 정보 생성
	 @remark
	 - 오버라이드 함수의 상세 설명 : 그림 정보 생성
	 @see com.paintee.mobile.painting.service.PaintingService#createPainting(com.paintee.common.repository.entity.Painting, com.paintee.common.repository.entity.FileInfo, com.paintee.mobile.support.obejct.LoginedUserVO)
	*/
	@Transactional
	public Map<String, Object> createPainting(Painting painting, FileInfo fileInfo, LoginedUserVO loginedUserVO) throws Exception {
		Long fileGroupSeq = fileService.createFileInfo(fileInfo, null, false, loginedUserVO.getUserId());
		User user = userHelper.selectByPrimaryKey(loginedUserVO.getUserId());

		String newPaintingId = UUID.randomUUID().toString();

		Date today = new Date();

		painting.setPaintingId(newPaintingId);
		painting.setArtistId(loginedUserVO.getUserId());
		painting.setFileGroupSeq(fileGroupSeq);
		painting.setLocation(user.getLocation());
		painting.setOriginalSize(String.valueOf(fileInfo.getSize()));
		painting.setUploadDate(today);
		painting.setCreatedDate(today);
		painting.setPaintingStatus("N");

		paintingHelper.insertSelective(painting);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("paintingSeq", painting.getSeq());
		resultMap.put("paintingId", painting.getPaintingId());
		resultMap.put("fileId", fileInfo.getId());

		User updateUploadCount = new User();
		updateUploadCount.setUserId(loginedUserVO.getUserId());
		updateUploadCount.setUploadCnt(user.getUploadCnt()+1);

		userHelper.updateByPrimaryKeySelective(updateUploadCount);

		return resultMap;
	}
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see com.paintee.mobile.painting.service.PaintingService#updatePainting(com.paintee.common.repository.entity.Painting)
	*/
	public boolean updatePainting(Painting painting) {
		boolean result = false;

		int count = paintingHelper.updateByPrimaryKeySelective(painting);

		if(count > 0) {
			result = true;
		}

		return result;
	}
}
