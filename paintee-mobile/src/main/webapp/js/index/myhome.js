$(document).ready(function() {
	// 페이지 로딩 시 Popular 스와이프 홈 화면 정보구성
	initMy();
});

//list container 시작        
var mySwiper = new Swiper('.swiper_container_my', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-my',
    scrollbarHide: true,
    lazyLoading: true,
    lazyLoadingInPrevNext: true,
    lazyLoadingInPrevNextAmount: 3    
})

mySwiper.on("onSlideChangeStart", function(swiper) {
	if (userID !== "") {
		// 화면에 로딩된 슬라이드 그림 개수
		var slidesCnt = swiper.slides.length - 1;
		// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
		if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
			new MyHomeController().getHomeInfo(slidesCnt);
		}
	}
});

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
mySwiper.on("onTransitionEnd", function(swiper){listLock(swiper)});

// side menu에 이벤트 설정
$("#menu_my").click(function(){
    selectMenu(3);
});

// 초기 설정들
// 가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
mySwiper.disableMousewheelControl();

mySwiper.on("onSetTranslate", function(swiper, translate) {
	swipeToMenu(swiper, translate)
});

function MyHomeController() {
	this.startRow = 0;
	this.post = true;
	this.upload = true;
}

MyHomeController.prototype = {
	getHomeInfo : function (startRow) {
		
		var upload = ($("#uploadBtn").hasClass("home_btn_inactive")) ? "N" : "Y";
		var post   = ($("#postBtn"  ).hasClass("home_btn_inactive")) ? "N" : "Y";
		this.startRow = startRow;
		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/myhome/info?startRow=" + startRow + "&upload=" + upload + "&post=" + post, 
			null,
			"GET", 
			function(result) {
				controller.getHomeInfoRes(result);
			}
		);
	}, 	
	getHomeInfoRes : function (result) {
		// 처음 로딩시에만 메인화면 구성
		if (this.startRow == 0) {
			setMyHome(result);
			
			if (result.uploadClass == "N") $("#uploadBtn").addClass("home_btn_inactive");
			if (result.postClass   == "N") $("#postBtn"  ).addClass("home_btn_inactive");
		}
		
		for ( var index in result.list) {
			addPainting(mySwiper, 1, "my", result.list[index]);
		}
	}
};

// 각각의 home 화면 설정
function initMy(){
    if (userID == "") {
        var myHome = new Home();
        var logInBtn = $("<div>").addClass("login_btn").html("Log in").click(function(){showLogin()});
        myHome.setTitle("my");
        myHome.setExplain("<span data-i18n='[html]my.notloginexplain'></span><br><br><br>");
        myHome.hideNext();
        myHome.setContents(logInBtn);
        mySwiper.appendSlide(myHome.buildStructure());
        delete myHome;
        delete logInBtn;
    }else{
		// 로그인 상태일 경우 홈카운트 가져오기
    	// 테이블에서 가져올 데이터의 시작 위치를 처음 로딩시 0번째 부터 조회
		new MyHomeController().getHomeInfo(0);
    }
	// 다국어 변경 적용
	exeTranslation('.main_container', lang);
}

function setMyHome(result) {
    mySwiper.removeAllSlides();
    var myHome = new Home();
    myHome.setTitle("my");
    var introduce = (result.my.introduce) ? result.my.introduce : "<span data-i18n='[html]my.loginexplain'></span><br><span data-i18n='my.login-introduce'></span>";
    myHome.setExplain(introduce + " <img class='icon' style='width: 14px; height: 14px' src='ico/create_white.png' onclick='showProfile()'>");
    var content1 =
        $("<div>").attr("id", "uploadBtn").addClass("home_btn_my").html("uploaded ").append($("<b>").html(" " + result.my.uploadCount))
    var content2 =
        $("<div>").attr("id", "postBtn").addClass("home_btn_my").html("posted ").append($("<b>").html(" " + result.my.postCount))
    content1.click(function(){
    	btnToggle(this);
    	new MyHomeController().getHomeInfo(0);
    });
    content2.click(function(){
    	btnToggle(this)
    	new MyHomeController().getHomeInfo(0);
    });
    myHome.hideNext();
    myHome.setContents(content1);
    myHome.setContents(content2);
    mySwiper.appendSlide(myHome.buildStructure());
    
    delete myHome;
    delete content1;
    delete content2;
    
    // 다국어 변경 적용
	exeTranslation('.main_container', lang);
}
