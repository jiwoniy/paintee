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
package com.paintee.mobile.promotioncode.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.PromotionCodeExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.common.repository.entity.vo.UserVO;
import com.paintee.common.repository.helper.PromotionCodeHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.repository.mapper.CodeMapper;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PromotionCodeImpl
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
@Service(value="com.paintee.mobile.promotioncode.service.PromotionCodeServiceImpl")
public class PromotionCodeServiceImpl implements PromotionCodeService {
	private final static Logger logger = LoggerFactory.getLogger(PromotionCodeServiceImpl.class);
	
	@Autowired
	private PromotionCodeHelper promotioncodeHelper;
	
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
	public Map<String, Object> promotioncodeInfo(PromotionCode promotioncode, User user) {
		
		// 기본 정보 조회
//		PromotionCodeVO promotioncode = promotioncodeHelper.selectPromotionCodeInfo(user);
//		
//		// 은행 목록 조회
//		CodeExample example = new CodeExample();
//		CodeExample.Criteria where = example.createCriteria();
//		where.andCodeGroupEqualTo("bank");
//		List<Code> banks = codeMapper.selectByExample(example);
//		
		Map<String, Object> result = new HashMap<>();
		result.put("promotioncode", promotioncode);
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
	public int addPromotionCode(PromotionCodeVO promotioncode) {
		// 리워드 정보 등록
		
		int count = promotioncode.getAddCount();
		int check = 0;
		for(int i = 0 ; i < count ; i++){
			String string = random(6);
			
			PromotionCodeExample example = new PromotionCodeExample();
			PromotionCodeExample.Criteria where = example.createCriteria();
			where.andCodeValueEqualTo(string);
			
			int contain =  promotioncodeHelper.countByExample(example) ;
			if(contain == 0){
				
				PromotionCode code = new PromotionCode();
				code.setCodeValue(string);
				code.setUseYn("N");
				code.setIssueDate(new Date());
				
				promotioncodeHelper.insertSelective(code);
				
				check++;
			}
			else{
				i--;
			}
			
		}
		return check;
	}
	
	
	public String random(int length){
		char[] chars;
		
		StringBuilder buffer = new StringBuilder();
		for(char ch = '0' ; ch <= '9' ; ++ch){
			buffer.append(ch);
		}
		for(char ch = 'a' ; ch <= 'z' ; ++ch){
			buffer.append(ch);
		}
		for(char ch = 'A' ; ch <= 'Z' ; ++ch){
			buffer.append(ch);
		}
		chars = buffer.toString().toCharArray();
			

		
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		
		for(int i = 0 ; i < length ; i++){
			randomString.append(chars[random.nextInt(chars.length)]);
		}
		return randomString.toString();
	}
	
	@Override
	public Map<String, Object> checkPromotionCode(PromotionCodeVO promotioncode, LoginedUserVO loginedUserVO){
		PromotionCodeExample example = new PromotionCodeExample();
		
		
		PromotionCodeExample.Criteria where = example.createCriteria();
		where.andCodeValueEqualTo(promotioncode.getCodeValue());
		where.andUseYnEqualTo("N");
		
		
		String possible = "";
		int count =  promotioncodeHelper.countByExample(example) ;
		System.out.println("count : "+count+"  where : "+where.getCriteria());
		if(promotioncodeHelper.countByExample(example) > 0){
			possible = "possible";
			
			PromotionCode code = new PromotionCode();
			code.setUsedDate(new Date());
			code.setCodeValue(promotioncode.getCodeValue());
			code.setUseYn("Y");
			code.setUsedUser(loginedUserVO.getUserId());
			code.setUsedEmail(loginedUserVO.getEmail());
			
			promotioncodeHelper.updatePromotionCodeInfo(code);
		}
		else{
			possible = "impossible";
		}
		

		
			Map<String, Object> result = new HashMap<>();
			result.put("status", possible);
		
			return result;
		
	}
//	@Override
//	public List<PromotionCodeSearchVO> promotioncodeHistory(User user){
//		List<PromotionCodeSearchVO> result = new List<>();
//		
//		return result;
//	}
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
//	@Override
//	public void cancelReward(RewardSearchVO search) {
//		// 1. 리워드 요청 정보 삭제
//		rewardHelper.deleteByPrimaryKey(search.getSeq());
//		
//		// 2. 사용자 테이블 리워드 정보 수정
//		UserVO user = new UserVO();
//		user.setUserId(search.getUserId());
//		user.setEarnRewordMoney(-((float)search.getMoney()));
//		userHelper.updateUserInfo(user);
//	}
}