/**
@file FileRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FileRestController.java |    
| Package | com.paintee.common.file.controller |    
| Project name | paintee-admin |    
| Type name | FileRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 1. 오후 10:56:21 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.file.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.file.service.FileService;
import com.paintee.common.repository.entity.FileInfo;

/**
@class FileRestController
com.paintee.common.file.controller \n
   ㄴ FileRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 1. 오후 10:56:21 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 첨부파일 관련 공통 rest controller
*/
@RestController("com.paintee.common.file.controller.FileRestController")
@RequestMapping(value = "/api/cmm")
public class FileRestController {
	private final static Logger logger = LoggerFactory.getLogger(FileRestController.class);

	@Autowired
	private FileService fileService;

	/**
	 @fn download
	 @brief 함수 간략한 설명 : 첨부파일 삭제
	 @remark
	 - 함수의 상세 설명 : 첨부파일 정보를 삭제한다. 물리파일 삭제포함.
	 @param fileId
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/file/delete/{fileId}", method = RequestMethod.POST)
	public Map<String, Object> delete(@PathVariable String fileId, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int errorNo = 0;
		String errorMsg = "";

		FileInfo fileInfo = fileService.getFileInfo(fileId);

		//TODO:권한 체크해야함.
		fileService.deleteFileInfo(fileId);

		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);

		return resultMap;
	}

	/**
	 @fn update
	 @brief 함수 간략한 설명 : 첨부파일 정보 수정
	 @remark
	 - 함수의 상세 설명 : 첨부파일 정보를 수정 한다.
	 @param fileId
	 @param fileInfo
	 @param request
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/file/update/{fileId}", method = RequestMethod.POST)
	public Map<String, Object> update(@PathVariable String fileId, @RequestBody FileInfo fileInfo, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int errorNo = 0;
		String errorMsg = "";

		FileInfo oldFileInfo = fileService.getFileInfo(fileId);

		//TODO:권한 체크해야함.
		fileInfo.setId(fileId);
		fileService.updateFileInfo(fileInfo);

		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);

		return resultMap;
	}
}
