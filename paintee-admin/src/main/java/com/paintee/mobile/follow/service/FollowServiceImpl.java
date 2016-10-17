/**
@file FollowServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowServiceImpl.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | FollowServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Follow;
import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.FollowVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.FollowHelper;

/**
@class FollowServiceImpl
com.paintee.mobile.follow.service \n
   ㄴ FollowServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service 구현체
*/
@Service(value="com.paintee.mobile.follow.service.FollowServiceImpl")
public class FollowServiceImpl implements FollowService {
	
	private final static Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);

	/**
	@brief Follow 대한 데이터 처리를 진행하는 헬퍼객체
	 */
	@Autowired
	private FollowHelper followHelper;
	
	/**
	@brief 업로드된 그림의 파일 정보를 관리하는 헬퍼객체
	 */
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인 사용자가 팔로잉한 사용자가 구매한 그림과 업로드한 그림의 목록을 가져오기
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인 사용자가 팔로잉한 사용자가 구매한 그림과 업로드한 그림의 목록을 가져오기
	 @see com.paintee.mobile.follow.service.FollowService#getFollowPaintingInfo(com.paintee.common.repository.entity.vo.FollowSearchVO)
	*/
	@Override
	public Map<String, Object> getFollowPaintingInfo(FollowSearchVO searchVO) {
		
		List<FollowVO> list = followHelper.selectFollowPaintingList(searchVO);
		logger.debug("list ::: {}", list);
		
		// 파일정보 조회
		for (FollowVO follow : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(follow.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				follow.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인 사용자의 팔로잉한 사용자 카운트와 팔로워의 카운트
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인 사용자의 팔로잉한 사용자 카운트와 팔로워의 카운트
	 @see com.paintee.mobile.follow.service.FollowService#getFollowCount(com.paintee.common.repository.entity.vo.FollowSearchVO)
	*/
	@Override
	public FollowVO getFollowCount(FollowSearchVO search) {
		return followHelper.selectFollowCount(search);
	}
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인한 사용자를 팔로잉한 사용자의 목록을 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인한 사용자를 팔로잉한 사용자의 목록을 조회
	 @see com.paintee.mobile.follow.service.FollowService#getFollowsList(com.paintee.common.repository.entity.vo.FollowSearchVO)
	*/
	@Override
	public List<FollowVO> getFollowsList(FollowSearchVO search) {
		return followHelper.selectFollowsList(search);
	}
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인한 사용자가 팔로잉한 사용자의 목록을 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인한 사용자가 팔로잉한 사용자의 목록을 조회
	 @see com.paintee.mobile.follow.service.FollowService#getFollowingList(com.paintee.common.repository.entity.vo.FollowSearchVO)
	*/
	@Override
	public List<FollowVO> getFollowingList(FollowSearchVO search) {
		return followHelper.selectFollowingList(search);
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인 사용자의 팔로워를 추가
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인 사용자의 팔로워를 추가 
	 @see com.paintee.mobile.follow.service.FollowService#addFollows(com.paintee.common.repository.entity.Follow)
	*/
	@Override
	public void addFollows(Follow follow) {
		followHelper.insertFollowByName(follow);
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인 사용자의 팔로워를 삭제
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인 사용자의 팔로워를 삭제
	 @see com.paintee.mobile.follow.service.FollowService#delFollows(com.paintee.common.repository.entity.Follow)
	*/
	@Override
	public void delFollows(Follow follow) {
		followHelper.deleteFollowByName(follow);
	}
}
