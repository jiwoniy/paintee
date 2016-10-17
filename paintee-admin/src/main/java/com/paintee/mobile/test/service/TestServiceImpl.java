/**
@file TestServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestServiceImpl.java |    
| Package | com.paintee.mobile.test.service |    
| Project name | paintee-admin |    
| Type name | TestServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 6:16:52 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.file.service.FileService;
import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.helper.FollowHelper;

/**
@class TestServiceImpl
com.paintee.mobile.test.service \n
   ㄴ TestServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 6:16:52 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.mobile.test.service.TestServiceImpl")
public class TestServiceImpl implements TestService {
	private final static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private FollowHelper followHelper;
	
	@Autowired
	private FileService fileService;

	public long saveFileInfo(FileInfo fileInfo) throws Exception {
		long fileGroupSeq = 0;
		logger.debug("saveFileInfo");

		if(fileInfo != null) {
			fileGroupSeq = fileService.createFileInfo(fileInfo, null, false, null);
		}

		logger.debug("fileGroupSeq:{}", fileGroupSeq);
		return fileGroupSeq;
	}
}
