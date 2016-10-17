/**
@file FileService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FileService.java |    
| Package | com.paintee.common.file.service |    
| Project name | paintee-admin |    
| Type name | FileService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 1. 오후 10:56:32 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.file.service;

import java.util.List;

import com.paintee.common.repository.entity.FileGroup;
import com.paintee.common.repository.entity.FileInfo;

/**
@class FileService
com.paintee.common.file.service \n
   ㄴ FileService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 1. 오후 10:56:32 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 첨부파일 관련 서비스
*/
public interface FileService {
	/**
	 @fn getFileInfo
	 @brief 함수 간략한 설명 : 첨부파일 정보 조회
	 @remark
	 - 함수의 상세 설명 : 첨부파일 정보를 조회한다.
	 @param fileId
	 @return 
	*/
	public FileInfo getFileInfo(String fileId);
	
	/**
	 @fn getFileGroup
	 @brief 함수 간략한 설명 : 첨부파일 그룹 정보 조회
	 @remark
	 - 함수의 상세 설명 : 첨부파일 그룹 정보를 조회한다.
	 @param fileGroupSeq
	 @return 
	*/
	public FileGroup getFileGroup(long fileGroupSeq);

	/**
	 @fn createFileInfo
	 @brief 함수 간략한 설명 : 단일건에 대한첨부파일 정보 생성
	 @remark
	 - 함수의 상세 설명 : 단일건에 대한 첨부파일 정보를 생성한다. deleteOldFile 이 true 일경우 atchmnflGroupSn 에 해당하는 파일들을 전체 삭제후 등록한다.
	 @param fileInfo
	 @param fileGroupSeq
	 @param deleteOldFile
	 @param registerId
	 @return 
	 @throws Exception 
	*/
	public Long createFileInfo(FileInfo fileInfo, Long fileGroupSeq, boolean deleteOldFile, String registerId) throws Exception;

	/**
	 @fn createFileInfo
	 @brief 함수 간략한 설명 : 북수건에 대한첨부파일 정보 생성
	 @remark
	 - 함수의 상세 설명 : 북수건에 대한 첨부파일 정보를 생성한다. deleteOldFile 이 true 일경우 atchmnflGroupSn 에 해당하는 파일들을 전체 삭제후 등록한다.
	 @param fileInfoList
	 @param fileGroupSeq
	 @param deleteOldFile
	 @param registerId
	 @return 
	 @throws Exception 
	*/
	public Long createFileInfo(List<FileInfo> fileInfoList, Long fileGroupSeq, boolean deleteOldFile, String registerId) throws Exception;

	/**
	 @fn getFileInfo
	 @brief 함수 간략한 설명 : 첨부파일 그룹 일련번호로 파일목록 조회
	 @remark
	 - 함수의 상세 설명 : 첨부파일 그룹 일련번호로 파일목록을 조회 한다.
	 @param fileGroupSeq
	 @return 
	*/
	public List<FileInfo> getFileInfoList(Long fileGroupSeq);

	/**
	 @fn deleteFileGroup
	 @brief 함수 간략한 설명 : 첨부파일 그룹 정보 삭제
	 @remark
	 - 함수의 상세 설명 : 첨부파일 그룹 정보를 삭제한다. 종속되어있는 첨부파일 정보도 삭제 된다.
	 @param fileGroupSeq
	 @return 
	*/
	public int deleteFileGroup(Long fileGroupSeq);

	/**
	 @fn deleteFileInfo
	 @brief 함수 간략한 설명 : 첨부파일 정보 삭제
	 @remark
	 - 함수의 상세 설명 : 첨부파일 정보를 삭제한다.
	 @param fileIds
	 @return 
	*/
	public int deleteFileInfo(String... fileIds);

	/**
	 @fn updateFileInfo
	 @brief 함수 간략한 설명 : 첨부파일 정보 수정
	 @remark
	 - 함수의 상세 설명 : 첨부파일 정보를 수정한다.
	 @param fileInfo
	 @return 
	*/
	public int updateFileInfo(FileInfo fileInfo);
}
