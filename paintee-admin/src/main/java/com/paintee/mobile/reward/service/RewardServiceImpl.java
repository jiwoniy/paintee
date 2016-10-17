/**
@file RewardServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardServiceImpl.java |    
| Package | com.paintee.mobile.reward.service |    
| Project name | paintee-admin |    
| Type name | RewardServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:54:10 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.reward.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.RewardResultVO;
import com.paintee.common.repository.entity.vo.RewardSearchVO;
import com.paintee.common.repository.entity.vo.RewardVO;
import com.paintee.common.repository.entity.vo.UserVO;
import com.paintee.common.repository.helper.RewardHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.repository.mapper.CodeMapper;

/**
@class RewardServiceImpl
com.paintee.mobile.reward.service \n
   ㄴ RewardServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:54:10 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.mobile.reward.service.RewardServiceImpl")
public class RewardServiceImpl implements RewardService {
	private final static Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);
	
	@Autowired
	private RewardHelper rewardHelper;
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private UserHelper userHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 리워드 신청을 위한 기본 정보를 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 기본 리워드 요청을 위한 사용자 정보와 은행정보를 조회
	 @see com.paintee.mobile.reward.service.RewardService#rewardInfo(com.paintee.common.repository.entity.User)
	*/
	@Override
	public Map<String, Object> rewardInfo(User user) {
		
		// 기본 정보 조회
		RewardVO reward = rewardHelper.selectRewardInfo(user);
		
		// 은행 목록 조회
		CodeExample example = new CodeExample();
		CodeExample.Criteria where = example.createCriteria();
		where.andCodeGroupEqualTo("bank");
		List<Code> banks = codeMapper.selectByExample(example);
		
		Map<String, Object> result = new HashMap<>();
		result.put("reward", reward);
		result.put("banks", banks);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 리워드 요청
	 @remark
	 - 오버라이드 함수의 상세 설명 : 요청한 리워드에 대한 처리를 진행
	                        <br />1. 리워드 요청 정보를 저장
	                        <br />2. 사용자 테이블의 리워드 요청 금액을 증가시킴
	 @see com.paintee.mobile.reward.service.RewardService#addReward(com.paintee.common.repository.entity.Reward)
	*/
	@Override
	public void addReward(Reward reward) {
		// 리워드 정보 등록
		rewardHelper.insertSelective(reward);
		
		// 사용자 테이블 리워드 관련 정보 업데이트
		User user = new User();
		
		// 요청한 리워드 금액 + 수수료
		user.setUserId(reward.getUserId());
		user.setEarnRewordMoney(reward.getEarmRequestedMoney() + (float)reward.getEarmRequestedCommission());
		
		// 사용자 테이블 업데이트
		userHelper.updateUserInfo(user);
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 리워드 히스토리 팝업에 필요한 정보를 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 사용자 화면의 리워드 히스토리 요청 팝업에 필요한 정보를 조회
	 @see com.paintee.mobile.reward.service.RewardService#rewardHistory(com.paintee.common.repository.entity.User)
	*/
	@Override
	public List<RewardResultVO> rewardHistory(User user) {
		List<RewardResultVO> list = rewardHelper.selectRewardHistoryList(user);
		logger.debug("list : " + list);
		return list;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 사용자의 리워드 관련 정보를 변경한다.
	   1. 리워드 요청 정보 삭제
	   2. 사용자 테이블 리워드 정보 수정
	      - 요청 수수료와 요청 금액을 합산한 금액을 tb_user 테이블의 earn_reward_money에서 뺀다.	 
	 @see com.paintee.mobile.reward.service.RewardService#cancelReward(com.paintee.common.repository.entity.vo.RewardSearchVO)
	*/
	@Override
	public void cancelReward(RewardSearchVO search) {
		// 1. 리워드 요청 정보 삭제
		rewardHelper.deleteByPrimaryKey(search.getSeq());
		
		// 2. 사용자 테이블 리워드 정보 수정
		UserVO user = new UserVO();
		user.setUserId(search.getUserId());
		user.setEarnRewordMoney(-((float)search.getMoney()));
		userHelper.updateUserInfo(user);
	}
}