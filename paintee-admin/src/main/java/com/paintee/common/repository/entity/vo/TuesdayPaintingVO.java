/**
@file TuesdayPaintingVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TuesdayPaintingVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | TuesdayPaintingVO |    
| Company | Paintee | 
| Create Date | 2016. 10. 21. 오후 6:25:26 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

/**
@class TuesdayPaintingVO
com.paintee.common.repository.entity.vo \n
   ㄴ TuesdayPaintingVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 10. 21. 오후 6:25:26 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public class TuesdayPaintingVO extends PaintingVO {
	
    private Integer tuesdaySeq;

    private String title;

    private String comment;

    private String paintingId;
    
	private String fileId;

	private String startDate;

	private String endDate;

	private String postYn;

	/**
	 * 그림의 로그인한 사용자의 좋아요 카운트
	 */
	private Integer loginLikeCnt; 
	
	
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getLoginLikeCnt() {
		return loginLikeCnt;
	}

	public void setLoginLikeCnt(Integer loginLikeCnt) {
		this.loginLikeCnt = loginLikeCnt;
	}

	public Integer getTuesdaySeq() {
		return tuesdaySeq;
	}

	public void setTuesdaySeq(Integer tuesdaySeq) {
		this.tuesdaySeq = tuesdaySeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPostYn() {
		return postYn;
	}

	public void setPostYn(String postYn) {
		this.postYn = postYn;
	}
}