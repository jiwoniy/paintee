/**
@file RewardServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardServiceImpl.java |    
| Package | com.paintee.admin.reward.service |    
| Project name | paintee-admin |    
| Type name | RewardServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.reward.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.vo.RewardResultVO;
import com.paintee.common.repository.entity.vo.UserVO;
import com.paintee.common.repository.helper.RewardHelper;
import com.paintee.common.repository.helper.UserHelper;

/**
@class RewardServiceImpl
com.paintee.admin.reward.service \n
   ㄴ RewardServiceImpl.java
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
@Service(value="com.paintee.admin.reward.service.RewardServiceImpl")
public class RewardServiceImpl implements RewardService {
	private final static Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);
	
	@Autowired
	private RewardHelper rewardHelper;
	
	@Autowired
	private UserHelper userHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 관리자 리워드 목록 정보를 조회한다.
	 @remark
	 - 오버라이드 함수의 상세 설명 : 관리자 리워드 목록 정보를 조회한다.
	 @see com.paintee.admin.reward.service.RewardService#getRewardList()
	*/
	@Override
	public Map<String, Object> getRewardList() {
		
		List<RewardResultVO> list = rewardHelper.selectRewardList();
		logger.debug("list : " + list);
		
		int count = rewardHelper.selectRewardListCount();
		logger.debug("전체 개수 : " + count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 	 
	    1. 비정상 변경시 처리할 일
		   - 요청 수수료와 요청 금액을 합산한 금액을 tb_user 테이블의 earn_reward_money에서 뺀다.	
	    2. 완료 변경시 처리할 일
	       - 리워드 테이블에 상태만 적용하면 된다.	 
	 @see com.paintee.admin.reward.service.RewardService#modRewardStatus(com.paintee.common.repository.entity.Reward)
	*/
	@Override
	public void modRewardStatus(Reward reward) {
		// 리워드 테이블 상태 변경
		rewardHelper.updateByPrimaryKeySelective(reward);
		
		// 비정상으로 변경
		if ("A".equalsIgnoreCase(reward.getRewardStatus())) {
			float money = reward.getEarmRequestedCommission() + reward.getEarmRequestedMoney();
			UserVO user = new UserVO();
			user.setUserId(reward.getUserId());
			user.setEarnRewordMoney(-money);
			userHelper.updateUserInfo(user);
		}
	}
}
