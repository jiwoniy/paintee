/**
@file PurchaseSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | PurchaseSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import java.util.List;

import com.paintee.common.repository.entity.Purchase;

/**
@class PurchaseSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ PurchaseSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자에 대한 정보 관련 vo
*/
public class PurchaseSearchVO extends Purchase {
	private static final long serialVersionUID = -3180373314948888877L;

	private List<String> statusList;
	private String changeAddr;
	private Integer serviceCnt;
	private Integer startRow;
	private Integer rowPerPage;
	
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public String getChangeAddr() {
		return changeAddr;
	}
	public void setChangeAddr(String changeAddr) {
		this.changeAddr = changeAddr;
	}
	public Integer getServiceCnt() {
		return serviceCnt;
	}
	public void setServiceCnt(Integer serviceCnt) {
		this.serviceCnt = serviceCnt;
	}
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
