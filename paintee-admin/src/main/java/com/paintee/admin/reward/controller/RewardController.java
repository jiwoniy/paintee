/**
@file RewardController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardController.java |    
| Package | com.paintee.admin.reward.controller |    
| Project name | paintee-admin |    
| Type name | RewardController |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:14:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.reward.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paintee.admin.reward.service.RewardService;
import com.paintee.common.repository.entity.Reward;

/**
@class RewardController
com.paintee.admin.reward.controller \n
   ㄴ RewardController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:14:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 관리자 리워드 목록 및 상태 변경을 처리하는 controller
*/
@Controller(value="com.paintee.admin.reward.RewardController")
@RequestMapping(value="/admin/reward")
public class RewardController {

	@Autowired
	private RewardService rewardService;
	
	/**
	 @fn list
	 @brief 함수 간략한 설명 : 리워드 목록 데이터를 조회한다.
	 @remark
	 - 함수의 상세 설명 : 리워드 목록 데이터를 조회한다.
	 @return 
	*/
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public void list(Model model) {
		Map<String, Object> result = rewardService.getRewardList();
		model.addAttribute("list", result.get("list"));
		model.addAttribute("count", result.get("count"));
	}
	
	/**
	 @fn modReward
	 @brief 함수 간략한 설명 : 리워드 상태 변경
	 @remark
	 - 함수의 상세 설명 : 리워드 상태 변경시 처리
	 @return 
	 */
	@RequestMapping(value="/mod", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> modReward(Reward reward) {
		rewardService.modRewardStatus(reward);
		
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "상태가 변경되었습니다.");
		return result;
	}
}