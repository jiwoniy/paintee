/**
@file RewardRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardRestController.java |    
| Package | com.paintee.mobile.reward.controller |    
| Project name | paintee-admin |    
| Type name | RewardRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:33:20 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.reward.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.RewardResultVO;
import com.paintee.common.repository.entity.vo.RewardSearchVO;
import com.paintee.mobile.reward.service.RewardService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class RewardRestController
com.paintee.mobile.purchase.controller \n
   ㄴ RewardRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:33:20 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 
*/
@RestController(value="com.paintee.mobile.reward.controller.RewardRestController")
public class RewardRestController {
	
	@Autowired
	private RewardService rewardService;
	
	/**
	 @fn info
	 @brief 함수 간략한 설명 : 리워드를 위한 기본 사용자 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 리워드를 위한 기본 사용자 정보를 조회
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/reward/info", method={RequestMethod.GET})
	public Map<String, Object> info(LoginedUserVO loginedUserVO) throws Exception {
		// 구매관련 정보 등록
		User user = new User();
		user.setUserId(loginedUserVO.getUserId());
		
		Map<String, Object> result = rewardService.rewardInfo(user);
		return result;
	}
	
	/**
	 @fn addReward
	 @brief 함수 간략한 설명 : 리워드 추가
	 @remark
	 - 함수의 상세 설명 : 리워드 추가 
	 @param loginedUserVO
	 @param reward
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/reward", method={RequestMethod.POST})
	public Map<String, Object> addReward(LoginedUserVO loginedUserVO, @RequestBody Reward reward) throws Exception {
		
		// 리워드 정보 등록
		reward.setUserId(loginedUserVO.getUserId());
		rewardService.addReward(reward);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}
	
	/**
	 @fn rewardHistory
	 @brief 함수 간략한 설명 : 리워드 히스토리
	 @remark
	 - 함수의 상세 설명 : 리워드 팝업에서 사용자가 리워드 히스토리 버튼을 클릭 시 해당 사용자의 리워드 히스토리 정보를 조회
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/rewardHistory", method={RequestMethod.GET})
	public List<RewardResultVO> rewardHistory(LoginedUserVO loginedUserVO) throws Exception {
		// 구매관련 정보 등록
		User user = new User();
		user.setUserId(loginedUserVO.getUserId());
		
		List<RewardResultVO> result = rewardService.rewardHistory(user);
		return result;
	}
	
	/**
	 @fn cancelReward
	 @brief 함수 간략한 설명 : 리워드 취소
	 @remark
	 - 함수의 상세 설명 : 사용자가 요청한 리워드를 취소한다. 리워드 취소는 요청 상태일 경우만 가능 
	 @param loginedUserVO
	 @param search
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/cancelReward", method={RequestMethod.POST})
	public Map<String, Object> cancelReward(LoginedUserVO loginedUserVO, @RequestBody RewardSearchVO search) throws Exception {
		search.setUserId(loginedUserVO.getUserId());
		
		rewardService.cancelReward(search);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}
}













