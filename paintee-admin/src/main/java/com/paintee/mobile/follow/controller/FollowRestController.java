/**
@file FollowRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowRestController.java |    
| Package | com.paintee.mobile.follow.controller |    
| Project name | paintee-admin |    
| Type name | FollowRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Follow;
import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.FollowVO;
import com.paintee.mobile.follow.service.FollowService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class FollowRestController
com.paintee.mobile.follow.controller \n
   ㄴ FollowRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:23:44 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - Follow 목록 정보 controller
*/
@RestController(value="com.paintee.mobile.follow.controller.FollowRestController")
public class FollowRestController {

	private final static Logger logger = LoggerFactory.getLogger(FollowRestController.class);

	@Autowired
	private FollowService followService;

	/**
	 @fn followCount
	 @brief 함수 간략한 설명 : 로그인 사용자의 팔로잉 사용자 카운트와 팔로워의 카운트
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 팔로잉 사용자 카운트와 팔로워의 카운트
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/index/follow/count", method=RequestMethod.GET)
	public Map<String, Object> followCount(LoginedUserVO loginedUserVO) throws Exception {
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 로그인 사용자 아이디
		search.setUserId(loginedUserVO.getUserId());
		
		FollowVO follow = followService.getFollowCount(search);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("follow", follow);
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");

		return resultMap;
	}
	
	/**
	 @fn paintingList
	 @brief 함수 간략한 설명 : Follow 목록 그림 정보 조회
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자가 follow 하는 사람들이 구매하거나 업로드한 그림의 목록을 조회
	                <br />최근순으로 표시하고 구매한 그림과 업로드한 그림의 순서는 없이 동일하게 표출함
	 @param loginedUserVO
	 @param startRow
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/index/follow/list", method=RequestMethod.GET)
	public Map<String, Object> paintingList(LoginedUserVO loginedUserVO, 
			@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) 
					throws Exception {
		
		// 로그인 사용자 아이디
		String userId = loginedUserVO.getUserId();
		
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 요청 데이터 페이징 정보
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		
		// 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7
		List<String> purchaseStatusList = new ArrayList<>();
		purchaseStatusList.add("1");  // 요청
		purchaseStatusList.add("2");  // 발송
		purchaseStatusList.add("4");  // 재발송요청
		purchaseStatusList.add("5");  // 재발송처리
		purchaseStatusList.add("99");  // 완료
		search.setPurchaseStatusList(purchaseStatusList);
		
		List<String> paintingStatusList = new ArrayList<>();
		paintingStatusList.add("N");  // 정상
		search.setPaintingStatusList(paintingStatusList);
		
		// 공개인것만 
		search.setPrivateAt("N");
		
		// 로그인 사용자 아이디
		search.setUserId(userId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = followService.getFollowPaintingInfo(search);
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		return resultMap;
	}
	
	/**
	 @fn followsList
	 @brief 함수 간략한 설명 : 로그인한 사용자를 팔로잉한 사용자의 목록을 조회
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자를 팔로잉한 사용자의 목록을 조회
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value={"/api/index/follows"}, method=RequestMethod.GET)
	public Map<String, Object> followsList(LoginedUserVO loginedUserVO) throws Exception {
		
		logger.debug("loginedUserVO:{}", loginedUserVO);
		
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 로그인 사용자 아이디
		search.setUserId(loginedUserVO.getUserId());
		
		Map<String, Object> resultMap = new HashMap<>();

		List<FollowVO> list = followService.getFollowsList(search);
		resultMap.put("list", list);
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		
		return resultMap;
	}

	/**
	 @fn addFollows
	 @brief 함수 간략한 설명 : 로그인 사용자의 팔로워를 추가 
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 팔로워를 추가 
	 @param loginedUserVO
	 @param follow
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value={"/api/index/follows"}, method=RequestMethod.POST)
	public Map<String, Object> addFollows(LoginedUserVO loginedUserVO, @RequestBody Follow follow) throws Exception {
		
		logger.debug("Follow ::: {}", follow);
		
		// 로그인 사용자 아이디
		follow.setUserId(loginedUserVO.getUserId());
		
		followService.addFollows(follow);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		return resultMap;
	}

	/**
	 @fn followingList
	 @brief 함수 간략한 설명 : 로그인한 사용자가 팔로잉한 사용자의 목록을 조회 
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자가 팔로잉한 사용자의 목록을 조회 
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value={"/api/index/following"}, method=RequestMethod.GET)
	public Map<String, Object> followingList(LoginedUserVO loginedUserVO) throws Exception {
		logger.debug("loginedUserVO:{}", loginedUserVO);
		
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 로그인 사용자 아이디
		search.setUserId(loginedUserVO.getUserId());
		
		Map<String, Object> resultMap = new HashMap<>();
		List<FollowVO> list = followService.getFollowingList(search);
		resultMap.put("list", list);
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		
		return resultMap;
	}
	
	/**
	 @fn delFollowing
	 @brief 함수 간략한 설명 : 로그인 사용자의 팔로워를 삭제 
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 팔로워를 삭제 
	 @param loginedUserVO
	 @param name
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value={"/api/index/following/{name}"}, method=RequestMethod.DELETE)
	public Map<String, Object> delFollowing(LoginedUserVO loginedUserVO, @PathVariable String name) throws Exception {
		logger.debug("Follow ::: {}", name);
		
		// 로그인 사용자 아이디
		Follow follow = new Follow();
		follow.setUserId(loginedUserVO.getUserId());
		follow.setFollowing(name);
		
		followService.delFollows(follow);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("errorNo", 0);
		resultMap.put("errorMsg", "");
		return resultMap;
	}	
}