/**
@file PostedServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PostedServiceImpl.java |    
| Package | com.paintee.mobile.posted.service |    
| Project name | paintee-admin |    
| Type name | PostedServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 8:30:41 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.posted.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Purchase;
import com.paintee.common.repository.entity.PurchaseExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PostedSearchVO;
import com.paintee.common.repository.helper.PurchaseHelper;
import com.paintee.common.repository.helper.UserHelper;

/**
@class PostedServiceImpl
com.paintee.mobile.posted.service \n
   ㄴ PostedServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 8:30:41 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자의 sentence 정보 관련 service 구현채
*/
@Service("com.paintee.mobile.posted.service.PostedServiceImpl")
public class PostedServiceImpl implements PostedService {
	private final static Logger logger = LoggerFactory.getLogger(PostedServiceImpl.class);

	@Autowired
	private PurchaseHelper purchaseHelper;

	@Autowired
	private UserHelper userHelper;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 구매한 사용자의 sentence 정보 목록 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 구매한 사용자의 sentence 정보 목록을 조회 한다. 페이징 처리함.
	 @see com.paintee.mobile.posted.service.PostedService#getPostedData(com.paintee.common.repository.entity.vo.PostedSearchVO)
	*/
	public Map<String, Object> getPostedData(PostedSearchVO searchVO) {
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> resultList = new ArrayList<>();

		PurchaseExample example = new PurchaseExample();
		PurchaseExample.Criteria where =  example.createCriteria();
		where.andPurchaseStatusIn(searchVO.getPurchaseStatusList());
		where.andPrivateAtEqualTo(searchVO.getPrivateAt());

		int count = purchaseHelper.countByExample(example);
		logger.debug("전체 개수 : " + count);

		List<Purchase> list = purchaseHelper.selectPostedList(searchVO);
		logger.debug("list : " + list);

		//파일정보 조회
		for (Purchase purchase : list) {
			User user = userHelper.selectByPrimaryKey(purchase.getUserId());

			if(user != null) {
				Map<String, Object> tmpMap = new HashMap<>();
				tmpMap.put("purchaseSeq", purchase.getSeq());
				tmpMap.put("userId", purchase.getUserId());
				tmpMap.put("userName", user.getName());
				tmpMap.put("sentence", purchase.getSentence());

				resultList.add(tmpMap);
			}
		}

		resultMap.put("count", count);
		resultMap.put("list", resultList);

		return resultMap;
	}
}
