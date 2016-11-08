/**
@file PersonalServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalServiceImpl.java |    
| Package | com.paintee.mobile.personal.service |    
| Project name | paintee-admin |    
| Type name | PersonalServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.personal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.PaintingExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.UserExample;
import com.paintee.common.repository.entity.vo.PersonalSearchVO;
import com.paintee.common.repository.entity.vo.PersonalVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PersonalHelper;
import com.paintee.common.repository.helper.UserHelper;

/**
@class PersonalServiceImpl
com.paintee.mobile.personal.service \n
   ㄴ PersonalServiceImpl.java
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
@Service(value="com.paintee.mobile.follow.service.PersonalServiceImpl")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalHelper personalHelper;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 개인페이지에 필요한 정보를 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 개인페이지 홈화면에 필요한 정보와 그림 목록을 조회한다.
	 @see com.paintee.mobile.personal.service.PersonalService#getPersonalPaintingInfo(com.paintee.common.repository.entity.vo.PersonalSearchVO)
	*/
	@Override
	public Map<String, Object> getPersonalPaintingInfo(PersonalSearchVO searchVO) {
		
		// 개인 페이지 정보
		PersonalVO personalVO = personalHelper.selectPersonalPaintingInfo(searchVO);
		
		List<PersonalVO> list = personalHelper.selectPersonalPaintingList(searchVO);
		
		// 파일정보 조회
		for (PersonalVO personal : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(personal.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				personal.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		String artistId = personalVO.getArtistId();
		if (artistId == null) {
			result.put("errorNo", "100");
			return result;
		} 
		result.put("personal", personalVO);
		result.put("list", list);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 그림의 상태를 체크
	 @remark
	 - 오버라이드 함수의 상세 설명 : 개인페이지 호출 시 소셜 공유를 통해 들어온 경우 요청한 그림이 올바른 상태인지 체크
	                        <br />존재하지 않는 그림인지 삭제된 그림인지 체크 
	 @see com.paintee.mobile.personal.service.PersonalService#getPersonalPaintingStatus(com.paintee.common.repository.entity.Painting)
	*/
	@Override
	public Map<String, Object> getPersonalPaintingStatus(Painting painting) {
		// 요청한 그림이 존재하는지를 판단
		PaintingExample example = new PaintingExample();
		PaintingExample.Criteria where = example.createCriteria();
		where.andPaintingIdEqualTo(painting.getPaintingId())
		     .andPaintingStatusEqualTo("N");
		
		int count = paintingHelper.countByExample(example);
		
		Map<String, Object> result = new HashMap<>();
		if (count == 0) {
			result.put("errorNo", "100");
			return result;
		} 
		result.put("errorNo", "0");
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 작가 상태를 체크
	 @remark
	 - 오버라이드 함수의 상세 설명 : 개인페이지 호출 시 소셜 공유를 통해 들어온 경우 요청한 작가가 올바른 상태인지 체크
	                        <br />존재하지 않는 작가인지 체크
	 @see com.paintee.mobile.personal.service.PersonalService#getPersonalArtistStatus(com.paintee.common.repository.entity.User)
	*/
	@Override
	public Map<String, Object> getPersonalArtistStatus(User user) {
		UserExample example = new UserExample();
		UserExample.Criteria where = example.createCriteria();
		where.andNameEqualTo(user.getName());
		
		int count = userHelper.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		// 요청한 사용자가 없는 경우
		if (count == 0) {
			result.put("errorNo", "100");
			return result;
		} 
		result.put("errorNo", "0");
		return result;
	}
}
