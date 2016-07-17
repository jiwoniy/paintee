// 페이지 로딩시 최초 처리할 일 정의
$(document).ready(function () {
	// 페이지 로딩 시 Popular 스와이프 홈 화면 정보구성
	initNew();
});

// list container 시작  
var newSwiper = new Swiper('.swiper_container_new', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-new',
    scrollbarHide: true,
    lazyLoading: true,
    lazyLoadingInPrevNext: true,
    lazyLoadingInPrevNextAmount: 3    
})

newSwiper.on("onSlideChangeStart", function(swiper){
	// 화면에 로딩된 슬라이드 그림 개수
	var slidesCnt = swiper.slides.length - 1;
	// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
	if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
		var controller = new NewController();
		controller.getListData(slidesCnt);
	}
});


// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
newSwiper.on("onTransitionEnd", function(swiper){listLock(swiper)});

//side menu에 이벤트 설정
$("#menu_new").click(function(){
    selectMenu(2);
});

//초기 설정들
//가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
newSwiper.disableMousewheelControl();

newSwiper.on("onSetTranslate", function(swiper, translate){swipeToMenu(swiper, translate)});

// Ajax 방식의 데이터 처리를 위한 컨트롤러 객체
function NewController() {
	this.startRow = 0;
}

NewController.prototype = {
	// 그림 정보 조회 : 업로드 카운트 및 이미지 목록 
	getListData: function (startRow) {
		this.startRow = startRow;
		var controller = this;
		AjaxCall.call(apiUrl + "/newIndex?startRow=" + startRow, 
			null, 
			"GET", 
			function (result) {
				controller.getListDataRes(result);			
			}
		);
	},
	// 그림 정보 조회 후 처리내용
	getListDataRes: function (result) {
		if (!this.startRow) {
			$("#new_count").text(result.count);
		}
		for (var index in result.list) {
			addPainting(newSwiper, 1, "new", result.list[index]);
			// 그림은 최대 100개 까지만 조회
			if (newSwiper.slides.length > 100) {
				break;
			}
		} 
	}
};



// New 홈 화면 및 그림 목록 요청
function initNew(){
  // 기존 설정된 슬라이더 제거
  newSwiper.removeAllSlides();
	
  var newHome = new Home();
	  newHome.setTitle("new");
	  newHome.setExplain("<span data-i18n='new.explain'><span>");
	  newHome.setContents($("<div>").html("<span id='new_count'>0</span><span data-i18n='new.content'><span>"));
	  newSwiper.appendSlide(newHome.buildStructure());
  delete newHome;

  // 다국어 변경 적용
  exeTranslation('.main_container', lang);
  
  //테이블에서 가져올 데이터의 시작 위치를 처음 로딩시 0번째 부터 조회
  new NewController().getListData(0);
}
