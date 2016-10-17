package com.paintee.mobile.popular.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.PurchaseExample;
import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.common.repository.entity.vo.PopularVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PopularHelper;
import com.paintee.common.repository.helper.PurchaseHelper;

/**
@class PopularServiceImpl
com.paintee.mobile.popular.service \n
   ㄴ PopularServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 11:57:12 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 인기 그림 정보 서비스 객체
*/
@Service(value="com.paintee.mobile.popular.service.PopularServiceImpl")
public class PopularServiceImpl implements PopularService {
	private final static Logger logger = LoggerFactory.getLogger(PopularServiceImpl.class);
	
	/**
	@brief 구매에 대한 데이터 처리를 관리하는 헬퍼객체
	 */
	@Autowired
	private PurchaseHelper purchaseHelper;

	/**
	@brief 인기 그림의 데이터 처리를 관리하는 헬퍼객체
	 */
	@Autowired
	private PopularHelper popularHelper;
	
	/**
	@brief 업로드된 그림의 파일 정보를 관리하는 헬퍼객체
	 */
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : Pouplar 목록 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 인기 그림의 정보를 조회하는 기능 
	 @see com.paintee.mobile.popular.service.PopularService#getPopularInfo(com.paintee.common.repository.entity.vo.PaintingSearchVO)
	*/
	@Override
	public Map<String, Object> getPopularInfo(PaintingSearchVO search) throws Exception {
		PurchaseExample example = new PurchaseExample();
		PurchaseExample.Criteria where =  example.createCriteria();
		where.andPurchaseStatusIn(search.getPurchaseStatusList());
		
		int count = purchaseHelper.countByExample(example);
		logger.debug("전체 개수 : " + count);
		
		List<PopularVO> list = popularHelper.selectPopularPaintingList(search);
		logger.debug("list : " + list);
		
		//파일정보 조회
		for (PopularVO popular : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(popular.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				popular.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("count", count);
		result.put("list", list);
		return result;
	}

}