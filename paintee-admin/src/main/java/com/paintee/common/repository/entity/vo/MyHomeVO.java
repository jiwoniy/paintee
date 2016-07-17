/**
@file MyHomeVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | MyHomeVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 6:25:26 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import com.paintee.common.repository.entity.Painting;

/**
@class MyHomeVO
com.paintee.common.repository.entity.vo \n
   ㄴ MyHomeVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 6:25:26 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public class MyHomeVO extends Painting {
	
	private String artistName;
	private String sentenceName;
	private String fileId;
	private String introduce;
	private Integer uploadCount;
	private Integer postCount;
	
	/** 
	 * My 리스트에 나오는 그림이 업로드된 그림인지 구매된 그림인지 표시
	 * 업로드 : U, 구매 : P 
	 */
	private String type;
	
	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getSentenceName() {
		return sentenceName;
	}

	public void setSentenceName(String sentenceName) {
		this.sentenceName = sentenceName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getUploadCount() {
		return uploadCount;
	}

	public void setUploadCount(Integer uploadCount) {
		this.uploadCount = uploadCount;
	}

	public Integer getPostCount() {
		return postCount;
	}

	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}