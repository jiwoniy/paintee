/**
@file UserServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserServiceImpl.java |    
| Package | com.paintee.mobile.user.service |    
| Project name | paintee-admin |    
| Type name | UserServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:46:48 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Follow;
import com.paintee.common.repository.entity.PaintingExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.UserExample;
import com.paintee.common.repository.helper.FollowHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.util.Sha512Encrypt;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class UserServiceImpl
com.paintee.mobile.user.service \n
   ㄴ UserServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:46:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 service 구현채
*/
@Service(value="com.paintee.mobile.user.service.UserServiceImpl")
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private FollowHelper followHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @remark
	 - 오버라이드 함수의 상세 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @see com.paintee.mobile.user.service.UserService#follow(java.lang.String, java.lang.String)
	*/
	public int follow(String followId, String followingId) {
		int errorNo = 500;

		Follow follow = new Follow();
		follow.setUserId(followId);
		follow.setFollowing(followingId);
		follow.setCreatedDate(new Date());

		Follow selectedFollow = followHelper.selectByPrimaryKey(follow);

		if(selectedFollow != null) {
			errorNo = 501;
		} else {
			int count = followHelper.insert(follow);
			if(count > 0) {
				errorNo = 0;
			}
		}

		return errorNo;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 사용자 이름 중복 체크
	 @remark
	 - 오버라이드 함수의 상세 설명 : 사용자 이름 중복 체크
	 @see com.paintee.mobile.user.service.UserService#checkDuplicate(com.paintee.common.repository.entity.User)
	*/
	public int checkDuplicate(User user) {
		int result = 0;

		UserExample userNameExample = new UserExample();
		UserExample.Criteria userNameWhere = userNameExample.createCriteria();
		userNameWhere.andNameEqualTo(user.getName());
		int count = userHelper.countByExample(userNameExample);

		if(count > 0) {
			result = 1;
		}

		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 사용자 정보 수정
	 @remark
	 - 오버라이드 함수의 상세 설명 : 사용자 정보 수정
	 @see com.paintee.mobile.user.service.UserService#updateUser(com.paintee.common.repository.entity.User)
	*/
	public int updateUser(User user) {
		int errorNo = 500;

		if(user.getPassword() != null && user.getPassword().trim().length() > 0) {
			user.setPassword(Sha512Encrypt.hash(user.getPassword()));
		}

		int count = userHelper.updateByPrimaryKeySelective(user);

		if(count > 0) {
			errorNo = 0;
		}

		return errorNo;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 로그인 사용자의 옆서가 post 된 개수 조회
	 @see com.paintee.mobile.user.service.UserService#postedCountInfo(com.paintee.mobile.support.obejct.LoginedUserVO)
	*/
	public Map<String, Object> postedCountInfo(LoginedUserVO loginedUserVO) {
		Map<String, Object> resultMap = new HashMap<>();

		int postedCount = paintingHelper.sumPostedNum(loginedUserVO.getUserId());

//		PaintingExample paintingExample = new PaintingExample();
//		PaintingExample.Criteria where = paintingExample.createCriteria();
//		where.andArtistIdEqualTo(loginedUserVO.getUserId());

//		int uploadedCount = paintingHelper.countByExample(paintingExample);

		User user = userHelper.selectByPrimaryKey(loginedUserVO.getUserId());
		int uploadedCount = user.getUploadCnt();

		resultMap.put("postedCount", postedCount);
		resultMap.put("uploadedCount", uploadedCount);

		return resultMap;
	}
}
