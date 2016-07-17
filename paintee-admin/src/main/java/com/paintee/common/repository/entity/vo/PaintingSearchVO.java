/**
@file PaintingSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | PopularVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 12:00:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

import com.paintee.common.repository.entity.FileInfo;

/**
@class PaintingSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ PopularSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 12:00:44 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림 목록 조회시 사용되는 검색 조건 처리 VO
*/
public class PaintingSearchVO extends PagingVO {
	
    /**
	@brief 직렬화 버젼 키
	*/
	private static final long serialVersionUID = -1913077104807476258L;
	/**
	@brief 그림 상태
	*/
	private String paintingStatus;
	/**
	@brief 구매 상태
	 */
	private List<String> purchaseStatusList;
    /**
    @brief 그림 업로드 시 공개/비공개
    */
    private String privateAt;
    /**
    @brief 업로드된 그림의 파일 정보
     */
	private FileInfo fileInfo;
	
	public String getPaintingStatus() {
		return paintingStatus;
	}
	public void setPaintingStatus(String paintingStatus) {
		this.paintingStatus = paintingStatus;
	}
	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}
	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	public String getPrivateAt() {
		return privateAt;
	}
	public void setPrivateAt(String privateAt) {
		this.privateAt = privateAt;
	}
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
}
