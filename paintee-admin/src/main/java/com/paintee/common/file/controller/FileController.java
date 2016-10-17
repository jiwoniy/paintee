/**
@file FileController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FileController.java |    
| Package | com.paintee.common.file.controller |    
| Project name | paintee-admin |    
| Type name | FileController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 1. 오후 10:55:39 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.file.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paintee.common.aws.s3.AWSS3Helper;
import com.paintee.common.file.service.FilePathGenerator;
import com.paintee.common.file.service.FileService;
import com.paintee.common.repository.entity.FileInfo;

/**
@class FileController
com.paintee.common.file.controller \n
   ㄴ FileController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 1. 오후 10:55:39 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 첨부파일 관련 공통 controller
*/
@Controller("com.paintee.common.file.controller.FileController")
@RequestMapping(value = "/cmm")
public class FileController {
	private final static Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FilePathGenerator filePathGenerator;

	@Autowired
	private FileService fileService;

    @Autowired
    private AWSS3Helper awsS3Helper;

    @Value("#{properties['aws.s3.bucketName'] }")
    private String bucketName;

    /**
     @fn view
     @brief 함수 간략한 설명 : 첨부파일 보기
     @remark
     - 함수의 상세 설명 : 해당 첨부파일을 첨부파일 유형에 맞게 화면에 출력한다.
     @param fileId
     @param response
     @return
     @throws Exception 
    */
    @RequestMapping(value="/file/view/{fileId}")
	public ResponseEntity<byte[]> view(@PathVariable String fileId, HttpServletResponse response) throws Exception {
		if (fileId == null || fileId.trim().length() == 0) {
			throw new Exception("File does not exist.");
		}

		FileInfo fileInfo = fileService.getFileInfo(fileId);

		if (fileInfo == null) {
			throw new Exception("File does not exist.");
		}

		byte[] fileByte = awsS3Helper.download(bucketName, fileInfo.getPath() + fileInfo.getName());

		HttpHeaders httpHeaders = new HttpHeaders();
		logger.debug("media type:{}", MediaType.valueOf(fileInfo.getContentType()));
		httpHeaders.setContentType(MediaType.valueOf(fileInfo.getContentType()));

		return new ResponseEntity<>(fileByte, httpHeaders, HttpStatus.CREATED);
	}
//	@RequestMapping(value="/file/view/{fileId}")
//	public void view(@PathVariable String fileId, HttpServletResponse response) throws Exception {
//		if (fileId == null || fileId.trim().length() == 0) {
//			throw new Exception("File does not exist.");
//		}
//
//		FileInfo fileInfo = fileService.getFileInfo(fileId);
//
//		if (fileInfo == null) {
//			throw new Exception("File does not exist.");
//		}
//
//		File file = new File(filePathGenerator.getAbsoluteFilPath(fileInfo.getPath())+fileInfo.getName());
//
//		if(!file.exists()) {
//			throw new Exception("File does not exist.");
//		}
//
//		response.setContentType(fileInfo.getContentType());
//		response.setContentLengthLong(file.length());
//
//		OutputStream out = response.getOutputStream();
//		FileInputStream fis = null;
//
//		try {
//			fis = new FileInputStream(file);
//			FileCopyUtils.copy(fis, out);
//		} catch (Exception e) {
//			logger.error("Exception [{}]", e.getMessage());
//			throw e;
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (Exception e2) {
//					logger.error("Exception [{}]", e2.getMessage());
//				}
//			}
//		}
//
//		out.flush();
//	}

	@RequestMapping(value={"/file/view/{fileType}/{fileId}", "/file/view/{fileType}/{fileId}.png"})
	public ResponseEntity<byte[]> view(@PathVariable Integer fileType, @PathVariable String fileId, HttpServletResponse response) throws Exception {
		if (fileId == null || fileId.trim().length() == 0) {
			throw new Exception("File does not exist.");
		}
		if (fileType == null) {
			throw new Exception("File does not exist.");
		}

		if (fileType < 1 || fileType > 3) {
			throw new Exception("File does not exist.");
		}

		FileInfo fileInfo = fileService.getFileInfo(fileId);

		if (fileInfo == null) {
			throw new Exception("File does not exist.");
		}

		StringBuilder filePath = new StringBuilder();
		if(fileType == 1) {
			filePath.append(fileInfo.getPath()).append(fileInfo.getName());
		} else {
			filePath.append(fileInfo.getPath()).append(fileInfo.getName()).append("_").append(fileType);
		}

		byte[] fileByte = awsS3Helper.download(bucketName, filePath.toString());

		HttpHeaders httpHeaders = new HttpHeaders();
		logger.debug("media type:{}", MediaType.valueOf(fileInfo.getContentType()));
		httpHeaders.setContentType(MediaType.valueOf(fileInfo.getContentType()));

		return new ResponseEntity<>(fileByte, httpHeaders, HttpStatus.CREATED);
	}
	
//	public void view(@PathVariable Integer fileType, @PathVariable String fileId, HttpServletResponse response) throws Exception {
//		if (fileId == null || fileId.trim().length() == 0) {
//			throw new Exception("File does not exist.");
//		}
//		if (fileType == null) {
//			throw new Exception("File does not exist.");
//		}
//
//		if (fileType < 1 || fileType > 3) {
//			throw new Exception("File does not exist.");
//		}
//
//		FileInfo fileInfo = fileService.getFileInfo(fileId);
//
//		if (fileInfo == null) {
//			throw new Exception("File does not exist.");
//		}
//
//		StringBuilder fullPath = new StringBuilder();
//		if(fileType == 1) {
//			fullPath.append(filePathGenerator.getAbsoluteFilPath(fileInfo.getPath())).append(fileInfo.getName());
//		} else {
//			fullPath.append(filePathGenerator.getAbsoluteFilPath(fileInfo.getPath())).append(fileInfo.getName()).append("_").append(fileType);
//		}
//
//		File file = new File(fullPath.toString());
//
//		if(!file.exists()) {
//			throw new Exception("File does not exist.");
//		}
//
//		response.setContentType(fileInfo.getContentType());
//		response.setContentLengthLong(file.length());
//
//		OutputStream out = response.getOutputStream();
//		FileInputStream fis = null;
//
//		try {
//			fis = new FileInputStream(file);
//			FileCopyUtils.copy(fis, out);
//		} catch (Exception e) {
//			logger.error("Exception [{}]", e.getMessage());
//			throw e;
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (Exception e2) {
//					logger.error("Exception [{}]", e2.getMessage());
//				}
//			}
//		}
//
//		out.flush();
//	}

    /**
     @fn download
     @brief 함수 간략한 설명 : 첨부파일 다운로드
     @remark
     - 함수의 상세 설명 : 해당 첨부파일을 다운로드 한다.
     @param fileId
     @param response
     @return
     @throws Exception 
    */
	@RequestMapping(value="/file/download/{fileId}")
	public ResponseEntity<byte[]> download(@PathVariable String fileId, HttpServletResponse response) throws Exception {
		if (fileId == null || fileId.trim().length() == 0) {
			throw new Exception("File does not exist.");
		}

		FileInfo fileInfo = fileService.getFileInfo(fileId);

		if (fileInfo == null) {
			throw new Exception("File does not exist.");
		}

		byte[] fileByte = awsS3Helper.download(bucketName, fileInfo.getPath() + fileInfo.getName());

		String fileName = URLEncoder.encode(fileInfo.getOriName(), "UTF-8").replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(fileByte.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(fileByte, httpHeaders, HttpStatus.OK);
	}
	@RequestMapping(value="/file/download/{fileType}/{fileId}")
	public ResponseEntity<byte[]> download(@PathVariable Integer fileType, @PathVariable String fileId, HttpServletResponse response) throws Exception {
		if (fileId == null || fileId.trim().length() == 0) {
			throw new Exception("File does not exist.");
		}
		if (fileType == null) {
			throw new Exception("File does not exist.");
		}

		if (fileType < 1 || fileType > 3) {
			throw new Exception("File does not exist.");
		}

		FileInfo fileInfo = fileService.getFileInfo(fileId);

		if (fileInfo == null) {
			throw new Exception("File does not exist.");
		}

		StringBuilder filePath = new StringBuilder();
		if(fileType == 1) {
			filePath.append(fileInfo.getPath()).append(fileInfo.getName());
		} else {
			filePath.append(fileInfo.getPath()).append(fileInfo.getName()).append("_").append(fileType);
		}

		byte[] fileByte = awsS3Helper.download(bucketName, filePath.toString());

		String fileName = URLEncoder.encode(fileInfo.getOriName(), "UTF-8").replaceAll("\\+", "%20");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentLength(fileByte.length);
		httpHeaders.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<>(fileByte, httpHeaders, HttpStatus.OK);
	}
//	public void download(@PathVariable String fileId, HttpServletResponse response) throws Exception {
//		if (fileId == null || fileId.toString().trim().length() == 0) {
//			throw new Exception("File does not exist.");
//		}
//
//		FileInfo fileInfo = fileService.getFileInfo(fileId);
//
//		if (fileInfo == null) {
//			throw new Exception("File does not exist.");
//		}
//
//		File file = new File(filePathGenerator.getAbsoluteFilPath(fileInfo.getPath())+fileInfo.getName());
//
//		if(!file.exists()) {
//			throw new Exception("File does not exist.");
//		}
//
//		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//		response.setContentLengthLong(file.length());
//
//		String fileName = java.net.URLEncoder.encode(fileInfo.getOriName(), "UTF-8");
//
//		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
//		response.setHeader("Content-Transfer-Encoding", "binary");
//
//		OutputStream out = response.getOutputStream();
//		FileInputStream fis = null;
//
//		try {
//			fis = new FileInputStream(file);
//			FileCopyUtils.copy(fis, out);
//		} catch (Exception e) {
//			logger.error("Exception [{}]", e.getMessage());
//			throw e;
//		} finally {
//			if (fis != null) {
//				try { 
//					fis.close();
//				} catch (Exception e2) {
//					logger.error("Exception [{}]", e2.getMessage());
//				}
//			}
//		}
//
//		out.flush();
//	}
}
