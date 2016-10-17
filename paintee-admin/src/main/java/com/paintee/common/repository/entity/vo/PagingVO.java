package com.paintee.common.repository.entity.vo;

import com.paintee.common.object.BaseEntity;

public class PagingVO extends BaseEntity {
	/** 페이징 시작번호 */
	private Integer startRow;
	/** 한페이지 가져올 데이터수 */
	private Integer rowPerPage;
	
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
}
