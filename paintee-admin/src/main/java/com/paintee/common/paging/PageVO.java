package com.paintee.common.paging;

import java.util.List;

public class PageVO {
	// List 데이터
	private List<Object> list;
	// 호출할 페이지 URL
	private String url;
	// 현재 페이지 번호
	private int pageNo;
	// 전체 게시물 개수
	private int count;
	// 한페이지에 보여질 목록 수
	private int listSize = PageVO.Constants.PAGE_LIST_COUNT;
	// 마지막 페이지 구하기
	private int lastPage;	
	// 목록에 보여질 탭 사이즈
	private int tabSize  = PageVO.Constants.PAGE_TAB_COUNT;
	// 현재 페이지에 해당하는 탭 위치 
	private int currTab;
	// 페이지 블럭의 시작페이지 번호
	private int beginPage;
	// 페이지 블럭의 마지막 페이지 번호
	private int endPage;
	
	public PageVO(int pageNo, int count) {
		this("", pageNo, count, null);
	}
	
	public PageVO(String url, int pageNo, int count) {
		this(url, pageNo, count, null);
		setup();
	}
	
	public PageVO(String url, int pageNo, int count, List<Object> list) {
		this.url = url;
		this.pageNo = pageNo;
		this.count  = count;
		this.list = list;
		setup();
	}
	
	private void setup() {
		// 마지막 페이지 구하기
		lastPage = (count % listSize == 0) ? count / listSize 
				                           : count / listSize + 1;	
		// 현재 페이지에 해당하는 탭 위치 
		currTab   = (pageNo  -1) / tabSize + 1;
		// 페이지 블럭의 시작페이지 번호
		beginPage = (currTab -1) * tabSize + 1;
		// 페이지 블럭의 마지막 페이지 번호
		endPage   = (currTab * tabSize < lastPage) ? currTab * tabSize 
				                                   : lastPage;
	}

	public List<Object> getList() {return list;   }
	public int getPageNo   () { return pageNo;    }
	public int getLastPage () { return lastPage;  }
	public int getBeginPage() { return beginPage; }
	public int getEndPage  () { return endPage;   }
	public String getUrl   () { return url;       }

	private static class Constants {
		public static final int PAGE_LIST_COUNT = 10;
		public static final int PAGE_TAB_COUNT  = 10;
	}
}