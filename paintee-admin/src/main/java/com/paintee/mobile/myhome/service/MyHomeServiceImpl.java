/**
@file MyHomeServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeServiceImpl.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | MyHomeServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.vo.MyHomeSearchVO;
import com.paintee.common.repository.entity.vo.MyHomeVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.MyHomeHelper;

/**
@class MyHomeServiceImpl
com.paintee.mobile.follow.service \n
   ㄴ MyHomeServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service 구현채
*/
@Service(value="com.paintee.mobile.follow.service.MyHomeServiceImpl")
public class MyHomeServiceImpl implements MyHomeService {
	private final static Logger logger = LoggerFactory.getLogger(MyHomeServiceImpl.class);

	/**
	@brief My 목록 관련 정보를 관리하는 헬퍼객체
	 */
	@Autowired
	private MyHomeHelper myhomeHelper;
	
	/**
	@brief 업로드된 그림의 파일 정보를 관리하는 헬퍼객체
	 */
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 내가 업로드 하거나 포스트한 그림의 정보를 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 내가 업로드 하거나 포스트한 그림의 정보를 조회<br />그림이 삭제된 경우 내가 내 그림을 구매한 경우 해당 그림은 보이지 않지만 
	                        <br />다른 사람이 삭제된 그림을 구매한 경우 해당 그림 정보를 조회한다.
	 @see com.paintee.mobile.myhome.service.MyHomeService#getMyHomePaintingInfo(com.paintee.common.repository.entity.vo.MyHomeSearchVO)
	*/
	@Override
	public Map<String, Object> getMyHomePaintingInfo(MyHomeSearchVO searchVO) {
		
		// 목록 사용 카운트
		MyHomeVO my = myhomeHelper.selectMyHomeInfo(searchVO);
		
		// 목록 리스트 
		// 만약 화면에서 업로드 부분을 비활성화 시켰다면
		if (searchVO.getUpload().equalsIgnoreCase("N")) searchVO.setArtistId("");
		// 만약 화면에서 포스트 부분을 비활성화 시켰다면
		if (searchVO.getPost  ().equalsIgnoreCase("N")) searchVO.setUserId("");
		
		List<MyHomeVO> list = myhomeHelper.selectMyHomePaintingList(searchVO);
		logger.debug("list ::: {}", list);
		
		// 파일정보 조회
		for (MyHomeVO myhome : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(myhome.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				myhome.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("my", my);
		result.put("list", list);
		result.put("uploadClass", searchVO.getUpload());
		result.put("postClass", searchVO.getPost());
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}
}
