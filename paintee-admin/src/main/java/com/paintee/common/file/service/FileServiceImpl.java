/**
@file FileServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FileServiceImpl.java |    
| Package | com.paintee.common.file.service |    
| Project name | paintee-admin |    
| Type name | FileServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 1. 오후 10:56:45 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.file.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileGroup;
import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.helper.FileGroupHelper;
import com.paintee.common.repository.helper.FileInfoHelper;

/**
@class FileServiceImpl
com.paintee.common.file.service \n
   ㄴ FileServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 1. 오후 10:56:45 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.common.file.service.FileServiceImpl")
public class FileServiceImpl implements FileService {
	private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	@Autowired
	private FileGroupHelper fileGroupHelper;

	@Autowired
	private FilePathGenerator filePathGenerator;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 첨부파일 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 정보를 조회한다.
	 @see com.paintee.common.file.service.FileService#getFileInfo(java.lang.String)
	*/
	public FileInfo getFileInfo(String fileId) {
		return fileInfoHelper.selectByPrimaryKey(fileId);
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :첨부파일 그룹 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 그룹 정보를 조회한다.
	 @see com.paintee.common.file.service.FileService#getFileGroup(long)
	*/
	public FileGroup getFileGroup(long fileGroupSeq) {
		return fileGroupHelper.selectByPrimaryKey(fileGroupSeq);
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 단일건에 대한 첨부파일 정보 생성
	 @remark
	 - 오버라이드 함수의 상세 설명 : 단일건에 대한 첨부파일 정보를 생성한다. deleteOldFile 이 true 일경우 atchmnflGroupSn 에 해당하는 파일들을 전체 삭제후 등록한다.
	 @see com.paintee.common.file.service.FileService#createFileInfo(com.paintee.common.repository.entity.FileInfo, java.lang.Long, boolean, java.lang.String)
	*/
	public Long createFileInfo(FileInfo fileInfo, Long fileGroupSeq, boolean deleteOldFile, String registerId) throws Exception {
		Long newFileGroupSeq = null;

		if(fileInfo != null) {
			Date today = new Date();

			List<FileInfo> fileInfoList = null;
			FileGroup fileGroup = new FileGroup();

			if(fileGroupSeq == null) {//최초에 파일 업로드
				fileGroup.setGroupName("TB_FILE_INFO");
				fileGroup.setCreatedDate(today);

				//첨부파일 그룹 정보 생성
				fileGroupHelper.insertSelective(fileGroup);
				newFileGroupSeq = fileGroup.getSeq();
			} else {//추가 파일 업로드
				fileGroup.setSeq(fileGroupSeq);

				if(deleteOldFile) {//fileGroupSeq 에 해당하는 모든 기존파일 정보삭제 및 물리적 파일 삭제
					FileInfoExample fileInfoExample = new FileInfoExample();
					FileInfoExample.Criteria where = fileInfoExample.createCriteria();
					where.andFileGroupSeqEqualTo(fileGroupSeq);

					fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);

					//물리파일삭제
					for(FileInfo deleteFile: fileInfoList) {
						File file = new File(filePathGenerator.getAbsoluteFilPath(deleteFile.getPath())+deleteFile.getName());
						file.delete();
					}

					fileInfoHelper.deleteByExample(fileInfoExample);
				}
			}

			//첨부파일 정보 생성
			fileInfo.setFileGroupSeq(fileGroup.getSeq());
			fileInfo.setCreatedDate(today);

			fileInfoHelper.insertSelective(fileInfo);
		}

		return newFileGroupSeq;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 북수건에 대한 첨부파일 정보 생성
	 @remark
	 - 오버라이드 함수의 상세 설명 : 북수건에 대한 첨부파일 정보를 생성한다. deleteOldFile 이 true 일경우 atchmnflGroupSn 에 해당하는 파일들을 전체 삭제후 등록한다.
	 @see com.paintee.common.file.service.FileService#createFileInfo(java.util.List, java.lang.Long, boolean, java.lang.String)
	*/
	public Long createFileInfo(List<FileInfo> fileInfoList, Long fileGroupSeq, boolean deleteOldFile, String registerId) throws Exception {
		Long newFileGroupSeq = null;

		int count = 0;

		if(fileInfoList != null) {
			for (FileInfo fileInfo : fileInfoList) {
				if(count == 0 && deleteOldFile == true) {
					newFileGroupSeq = createFileInfo(fileInfo, fileGroupSeq, true, registerId);
				} else {
					newFileGroupSeq = createFileInfo(fileInfo, fileGroupSeq, false, registerId);
				}

				count++;
			}
		}

		return newFileGroupSeq;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 첨부파일 그룹 일련번호로 파일목록 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 그룹 일련번호로 파일목록을 조회 한다.
	 @see com.paintee.common.file.service.FileService#getFileInfoList(java.lang.Long)
	*/
	public List<FileInfo> getFileInfoList(Long fileGroupSeq) {
		FileInfoExample fileInfoExample = new FileInfoExample();
		FileInfoExample.Criteria where = fileInfoExample.createCriteria();
		where.andFileGroupSeqEqualTo(fileGroupSeq);

		List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);

		return fileInfoList;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 첨부파일 그룹 정보 삭제
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 그룹 정보를 삭제한다. 종속되어있는 첨부파일 정보도 삭제 된다.
	 @see com.paintee.common.file.service.FileService#deleteFileGroup(java.lang.Long)
	*/
	public int deleteFileGroup(Long fileGroupSeq) {
		int deletedCount = 0;

		FileGroup fileGroup = getFileGroup(fileGroupSeq);

		if(fileGroup != null) {
			List<FileInfo> fileInfoList = getFileInfoList(fileGroupSeq);

			for(FileInfo fileInfo: fileInfoList) {
				deleteFileInfo(fileInfo.getId());
			}

			fileGroupHelper.deleteByPrimaryKey(fileGroupSeq);
			deletedCount++;
		}

		return deletedCount;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 첨부파일 정보 삭제
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 정보를 삭제 한다. 물리와 논리 둘다 삭제함.
	 @see com.paintee.common.file.service.FileService#deleteFileInfo(java.lang.String[])
	*/
	public int deleteFileInfo(String... fileIds) {
		int deletedCount = 0;

		if(fileIds != null) {
			for(String fileId: fileIds) {
				FileInfo deleteFile = fileInfoHelper.selectByPrimaryKey(fileId);

				if(deleteFile != null) {
					File file = new File(filePathGenerator.getAbsoluteFilPath(deleteFile.getPath())+deleteFile.getName());
					file.delete();

					fileInfoHelper.deleteByPrimaryKey(fileId);
					deletedCount++;
				}
			}
		}

		return deletedCount;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 첨부파일 정보 수정 
	 @remark
	 - 오버라이드 함수의 상세 설명 : 첨부파일 정보를 수정한다.
	 @see com.paintee.common.file.service.FileService#updateFileInfo(com.paintee.common.repository.entity.FileInfo)
	*/
	public int updateFileInfo(FileInfo fileInfo) {
		return fileInfoHelper.updateByPrimaryKeySelective(fileInfo);
	}
}
