// 개인페이지 생성
var personal = "";
var isPersonal = false;

/**
 * 특정 아티스트 정보 보이기
 * @param username
 */             
function showPersonal(username, paintingId){
    if (personal != "") hidePersonal();
    isPersonal = true;
    color = "250,60%,50%";
    colorDark = "250,60%,20%";
    
    personal = new Personal(username);
    mainSwiper.appendSlide(personal.buildStructure());
    personal.setSwiper();
    personal.swiper.on("onSlideChangeStart", function(swiper){
    	// 화면에 로딩된 슬라이드 그림 개수
		var slidesCnt = swiper.slides.length - 1;
		// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
		if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
			new PersonalController().getPersonInfo(slidesCnt);
		}
    });
    personal.swiper.on("onTransitionEnd", function(swiper){listLock(swiper)});
    personal.swiper.on("onSetTranslate", function(swiper, translate){swipeToMenu(swiper, translate)});
      
    initPersonal(paintingId);
    selectMenu(4);
}

/**
 * 아티스트 정보 초기화 및 숨기기
 */
function hidePersonal(){
    isPersonal = false;
    mainSwiper.removeSlide(4);
    personal = "";
}

function Personal(username){
	this.username = username;
    this.container  = $("<div>").addClass("personal_container").addClass("swiper-slide");
    this.list       = $("<div>").addClass("list_container").addClass("swiper_container_personal");
    this.homeBtn    = $("<div>").addClass("home_btn").css("font-weight", 700).html(username).click(function(){currentSwiper.slideTo(0);});
    this.bottom     = $("<div>").addClass("bottom_bar").css("background-color", "hsl(250,60%,20%)");
    this.wrapper    = $("<div>").addClass("swiper-wrapper");
    this.scroll     = $("<div>").addClass("swiper-scrollbar").addClass("swiper-scrollbar-personal");
    this.swiper;
}

Personal.prototype = {
    setSwiper       : function(){
                        this.swiper = new Swiper('.swiper_container_personal', {
                            slidesPerView: 'auto',
                            centeredSlides: true,
                            spaceBetween: mainWidth*0.05,
                            mousewheelControl : true,
                            scrollbar: '.swiper-scrollbar-personal',
                            scrollbarHide: true,
                            preloadImages: false,
                            lazyLoading: true,
                            lazyLoadingInPrevNext: true,
                            lazyLoadingInPrevNextAmount: 3                            
                        })
                    },
    buildStructure  : function(){
                        this.list.append(this.homeBtn);
                        this.list.append(this.bottom);
                        this.list.append(this.wrapper);
                        this.list.append(this.scroll);
                        this.container.append(this.list);
        
                        return this.container;
                    }
}

function initPersonal(paintingId) {
	// 기본 페이지 로딩 시의 데이터 조회
	new PersonalController(paintingId).getPersonInfo(0);
}

function setPersonal(result) {
    var personalHome = new Home();
    personalHome.setTitle(personal.username);
    var introduce = (result.personal.introduce) ? result.personal.introduce : personal.username+"<span data-i18n='personal.contents'>님이 업로드한 그림들입니다.</span>";
    personalHome.setExplain(introduce);
    var contents1 = $("<div>").addClass("follow_artist").html("<br><br><img style='width:12px; height: 12px' class='icon' src='ico/star_white.png'> <span id='personalFollow'>follow artist</span>");
    var contents2 = $("<div>").addClass("detail_artist_bottom").html("Share to <img class='icon social_img' id='personal-facebook' src='ico/social_facebook_white.png'><img class='icon social_img' id='personal-twitter' src='ico/social_twitter_white.png'><img class='icon social_img' id='personal-url' src='ico/social_url_white.png'>");  // 수정부분
    
    personalHome.hideNext();
    personalHome.setContents(contents1);
    personalHome.setAdd(contents2); 
    personal.swiper.appendSlide(personalHome.buildStructure());
    delete personalHome;
    delete content1;
    
    var data = {name: personal.username};
    $("#personal-facebook").click(function() {
    	shareSocial({name: personal.username, type: "facebook"});
    });
    $("#personal-twitter").click(function() {
    	shareSocial({name: personal.username, type: "twitter"});
    });
    $("#personal-url").click(function() {
    	urlCopy({"name": personal.username});
    });
    
    $("#personalFollow").click(function () {
    	if (!userInfo) {
    		alert($.i18n.t('alert.common.notLogin'));
    		return;
    	}
		if (result.personal.followCnt != 0) {
			alert(personal.username + $.i18n.t('alert.detail.existFollow'));
			return;
		}
		selectedArtistId = result.personal.artistId; 
		selectedArtistName = personal.username; 
    	new DetailController().artistFollow(result.personal.artistId);
    }); // 수정부분
    
    // 다국어 처리
    exeTranslation('.main_container', lang);      
}

function PersonalController(paintingId) {
	this.startRow = 0;
	this.paintingId = paintingId;
}

PersonalController.prototype = {
	// 개인페이지 홈 정보와 그림 목록을 조회 AJAX
	getPersonInfo : function (startRow) {
		this.startRow = startRow;
		var controller = this;
		var param = "?startRow=" + startRow  + "&artistName=" + personal.username;
		// 
		if (this.paintingId) {
			param += "&paintingId=" + this.paintingId;
		}
		AjaxCall.call(
			apiUrl + "/index/personal" + param, 
			null,
			"GET", 
			function(result) {
				// 에러코드 100 번일 경우 사용자 작가 개인 페이지 호출 시 작가가 존재하지 않는 경우
				if (result.errorNo == 100) {
					alert($.i18n.t('alert.common.notExistArtist'));
//					location.href = "/";
					location.reload();
				}  	
				controller.getPersonInfoRes(result);
			}
		);
	}, 	
	// 개인페이지 홈 정보와 그림 목록을 조회 후 처리하는 함수
	getPersonInfoRes : function (result) {
		var controller = this;
		// 처음 로딩시에만 메인화면 구성
		if (this.startRow == 0) {
			setPersonal(result);
		}
		for ( var index in result.list) {
			var pageType = "my";
			// 소셜에서 들어온 경우 Lazy Loading을 위해서 값 설정을 변경함 : 다른 페이지와 구분값 설정
			if (controller.paintingId) pageType = "personal";
			
			addPainting(personal.swiper, 1, pageType, result.list[index]);
		}
		// 개인페이지에서 상세화면을 닫았을때 요청한 그림을 목로에서 바로 보여주기 위해 슬라이드를 해당 그림으로 이동시킴
		if (controller.paintingId) {
			personal.swiper.slideTo(personal.swiper.slides.length - 1, 0);
		}
	},
	// 그림의 상태 조회 AJAX
	getPictureStatus : function (paintingId) {
		var controller = this;
		AjaxCall.call(
				apiUrl + "/index/personal/pictureStatus?paintingId=" + paintingId, 
				null,
				"GET", 
				function(result) {
					// 에러코드 100 번일 경우 사용자 작가 개인 페이지 호출 시 작가의 그림이 존재하지 않는 경우
					if (result.errorNo == 100) {
						alert($.i18n.t('alert.common.notExistPicture'));
					}  
					else {
						// 그림이 존재하는 경우만 상세 페이지 호출
						loadDetail(get.page, "200,60%,50%", "200,60%,20%", "personal");
					}
				}
		);
	},
	// 작가 상태 조회 AJAX
	getArtistStatus : function (name) {
		var controller = this;
		AjaxCall.call(
				apiUrl + "/index/personal/artistStatus?name=" + name, 
				null,
				"GET", 
				function(result) {
					// 에러코드 100 번일 경우 사용자 작가 개인 페이지 호출 시 작가  존재 여부 체크
					if (result.errorNo == 100) {
						alert($.i18n.t('alert.common.notExistArtist'));
					}  
					// 그림 작가가 있을 경우에만 다음 단계 진행
					else {
						// 개인페이지 진행
						processPersonalView();
					}
				}
		);
	}
};

//get 방식으로 user, painting 가져오기 
function getRequest() {
    if(location.search.length > 1) {
        var get = new Object();
        var ret = location.search.substr(1).split('&');
        for(var i = 0; i < ret.length; i++) {
            var r = ret[i].split('=');
            get[r[0]] = r[1];
        }
        return get;
    } else {
        return false;
    }
}

$(document).ready(function () {
//  user만 있으면 개인페이지로 이동, user, page가 있으면 상세화면으로 이동
//  http://localhost:9080/index.html?user=작가이름&page=그림아이디
	if (get) {
		if(get.user) {
			// 작가가 존재하는 지 체크 한 다음 개인페이지 이동여부 진행
			new PersonalController().getArtistStatus(get.user);
		}
	};
});

function processPersonalView() {
    if(get.page) {
    	// 개인 페이지의 그림 요청시 해당 그림이 존재할 경우만 다음 단계 진행을 위해 현재 그림의 상태값을 조회
    	new PersonalController().getPictureStatus(get.page)
    } else if(get.user) {
    	// 트위트의 경우 마지막에 / 가 URL 뒤에 추가되는 경우가 생겨서 삭제처리함
    	var len = get.user.length;
    	var lastChar = get.user.substr(len -1, 1);
    	if (lastChar == "/") {
    		get.user = get.user.substr(0, len -1);
    	}
        showPersonal(get.user);
        get = "";
    }
}
