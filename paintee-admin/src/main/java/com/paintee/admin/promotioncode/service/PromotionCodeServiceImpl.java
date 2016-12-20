/**
@file PromotionCodeServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PromotionCodeServiceImpl.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PromotionCodeServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.promotioncode.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.PromotionCodeExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PromotionCodeHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.repository.mapper.CodeMapper;

/**
@class PromotionCodeServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ PromotionCodeServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:59:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 관리자의 구매 목록에 필요한 정보를 조회하는 service 구현채
*/
@Service(value="com.paintee.admin.promotionCode.service.PromotionCodeServiceImpl")
public class PromotionCodeServiceImpl implements PromotionCodeService {
	private final static Logger logger = LoggerFactory.getLogger(PromotionCodeServiceImpl.class);
	
	@Autowired
	private PromotionCodeHelper promotionCodeHelper;
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	@Autowired
	private FileInfoHelper fileInfoHelper;	
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 관리자 구매 목록 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 관리자의 구매 목록 화면에 필요한 데이터를 조회한다.
	 @see com.paintee.admin.promotionCode.service.PromotionCodeService#getPromotionCodeList(com.paintee.common.repository.entity.vo.PromotionCodeSearchVO)
	*/
	@Override
	public Map<String, Object> getPromotionCodeList(PromotionCodeSearchVO search) {
		
		List<PromotionCodeVO> list = promotionCodeHelper.selectPromotionCodeList(search);
		logger.debug("list : " + list);
		
		int count = promotionCodeHelper.selectPromotionCodeListCount(search);
		logger.debug("전체 개수 : " + count);
		
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 구매상태 변경시의 처리할 일 정의
	 @remark
	   1. 구매테이블의 상태를 변경
	   2. 구매상태를 발송으로 변경한 경우 
		  - 구매한 그림의 사용자의 수익 전체 금액(earn_total_money) 증가
	 @see com.paintee.admin.promotionCode.service.PromotionCodeService#modPromotionCodeStatus(com.paintee.common.repository.entity.PromotionCode)
	*/
	@Override
	public void modPromotionCodeStatus(PromotionCode promotionCode) {
		String codeValue = promotionCode.getCodeValue();
		String useYn = promotionCode.getUseYn();
		
		if(useYn.equals("N")){
			
		}
		else if(useYn.equals("Y")){
			
		}
		
//		promotionCode.setStatusUpdateDate(new Date());
		promotionCodeHelper.updateByPrimaryKeySelective(promotionCode);
	}
}
