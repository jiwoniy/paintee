/**
@file TestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestController.java |    
| Package | com.paintee.mobile.test.controller |    
| Project name | paintee-admin |    
| Type name | TestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 4:38:55 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paintee.common.file.service.FileInfoGenerator;
import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.Purchase;
import com.paintee.mobile.test.service.TestService;

/**
@class TestController
com.paintee.mobile.test.controller \n
   ㄴ TestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 4:38:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@RestController(value="com.paintee.mobile.test.TestController")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private FileInfoGenerator fileInfoGenerator;

	@Autowired
	private TestService testService;

	/**
	 @fn test
	 @brief 함수 간략한 설명 : json 데이터 전송 테스트용 json 데이터 전송
	 @remark
	 - 함수의 상세 설명 : json 데이터 전송 테스트용 json 데이터 전송
	 @return 
	*/
	@RequestMapping(value="/api/test/ddddd", method={RequestMethod.GET})
	public String test() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		logger.debug("test");
		
		resultMap.put("test", "aaa");
		resultMap.put("ccc", 1);

		//return resultMap;
		
		return "/test/list.jsp";
//		return "redirect:/admin/purchase/list";
	}



	@RequestMapping(value="/api/upload", method={RequestMethod.POST})
	public Map<String, Object> testUpload(TestVO testVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		logger.debug("testUpload");

		MultipartFile painteeFile = testVO.getPainteeFile();
		FileInfo fileInfo = null;

		//첨부파일 업로드시
		if (painteeFile != null && !painteeFile.isEmpty()) {
			fileInfo = fileInfoGenerator.makeFileInfo(painteeFile, null, testVO.getDisplayName());
			logger.debug("fileInfo:{}", fileInfo);
		}

		testService.saveFileInfo(fileInfo);

		resultMap.put("errorMsg", "aaa");
		resultMap.put("errorNo", 0);

		return resultMap;
	}
}
