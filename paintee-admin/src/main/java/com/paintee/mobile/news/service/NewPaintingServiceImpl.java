package com.paintee.mobile.news.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.PaintingExample;
import com.paintee.common.repository.entity.vo.NewPaintingVO;
import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.NewPaintingHelper;
import com.paintee.common.repository.helper.PaintingHelper;

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
 - 신규 업로드 그림 정보 서비스 객체
*/
@Service(value="com.paintee.mobile.news.service.NewPaintingServiceImpl")
public class NewPaintingServiceImpl implements NewPaintingService {
	private final static Logger logger = LoggerFactory.getLogger(NewPaintingServiceImpl.class);
	
	/**
	@brief 그림에 대한 데이터 처리를 진행하는 헬퍼객체
	 */
	@Autowired
	private PaintingHelper paintingHelper;

	/**
	@brief 신규 업로드 그림에 대한 데이터 처리를 진행하는 헬퍼객체
	*/
	@Autowired
	private NewPaintingHelper newPaintingHelper;
	
	/**
	@brief 업로드된 그림의 파일 정보를 관리하는 헬퍼객체
	 */
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : new 목록 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 신규 업로드된 그림의 정보를 조회하는 기능 
	 @see com.paintee.mobile.news.service.NewPaintingService#getNewPatingInfo(com.paintee.common.repository.entity.vo.PaintingSearchVO)
	*/
	@Override
	public Map<String, Object> getNewPatingInfo(PaintingSearchVO search) throws Exception {
		
		PaintingExample example = new PaintingExample();
		example.createCriteria().andPaintingStatusEqualTo("N").andPrivateAtEqualTo("N");
		
		int count = paintingHelper.countByExample(example);
		logger.debug("전체 개수 : " + count);
		
		List<NewPaintingVO> list = newPaintingHelper.selectNewPaintingList(search);
		logger.debug("list : " + list);
		
		// 파일정보 조회
		for (NewPaintingVO newPainting : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(newPainting.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				newPainting.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("count", count);
		result.put("list", list);
		return result;
	}

}













