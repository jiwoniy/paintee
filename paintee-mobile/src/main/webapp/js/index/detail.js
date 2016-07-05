// 상세화면의 구조
//function DetailStructure(paintingId, fileId, artistName, artistId, artistSentence, uploadDate, postedNum){
function DetailStructure(paintingId, paintingInfo){
    this.paintingId     = paintingId;
    this.fileId         = paintingInfo.fileInfo.id;
    this.artistName     = paintingInfo.artistName;
    this.artistId       = paintingInfo.artistId;
    this.artistSentence = convertToBr(paintingInfo.sentence);  
    this.uploadDate     = toDate(paintingInfo.uploadDate);
    this.postedNum      = paintingInfo.postedNum;
    // 히스토리 사용 부분 추가
    this.colorDark      = paintingInfo.colorDark;
    this.color          = paintingInfo.color;
    
    this.detail             =$(".detail");

    this.detailBgContainer  =$("<div>").addClass("detail_bg_container");
    this.detailBgImg        =$("<img>").addClass("detail_bg_img");
    this.detailBgBottom     =$("<div>").addClass("detail_bg_bottom");

    this.detailContainer    =$("<div>").addClass("detail_container").addClass("swiper_container_detail");
    this.wrapper            =$("<div>").addClass("swiper-wrapper");

    this.detailMargin       =$("<div>").addClass("detail_margin").addClass("swiper-slide").swipe({
                                swipeUp:function(){
                                    detailSwiper.wrapper.css("transition-duration", "300ms");
                                    detailSwiper.setWrapperTranslate(postedLockBreakpoint);
                                    detailSwiper.unlockSwipes();
                                },
                                tap:function(){
                                    detailSwiper.wrapper.css("transition-duration", "300ms");
                                    detailSwiper.setWrapperTranslate(postedLockBreakpoint);
                                    detailSwiper.unlockSwipes();
                                },
                                swipeDown:function(){
                                    if(postedLock) closeDetail();
                                },
                                threshold:10
                            });
    this.detailCloseIcon    =$("<img src='ico/close.png'>").addClass("icon").addClass("detail_margin_close");
    this.detailMarginIcon   =$("<img src='ico/keyboard_arrow_up_black.png'>").addClass("icon").addClass("detail_margin_guide");

    this.detailArtist       =$("<div>").addClass("detail_artist").addClass("swiper-slide");
    this.detailArtistTop    =$("<div>").addClass("detail_artist_top");
    this.detailArtistBtn    =$("<div>").addClass("detail_artist_btn");

    this.followed = paintingInfo.followed;

    if(paintingInfo.followed || (userInfo && this.artistId == userInfo.userId)) {
    	this.detailArtistFollow =$("<div>").addClass("detail_artist_followed");
    } else {
    	this.detailArtistFollow =$("<div>").addClass("detail_artist_follow");
    }

    this.detailArtistSentence=$("<div>").addClass("detail_artist_sentence");
    this.detailArtistDate   =$("<div>").addClass("detail_artist_date");
    this.detailArtistBottom =$("<div>").addClass("detail_artist_bottom").html("Share to ");

    this.sociconFacebook =$("<img id='detail_fac_share' src='ico/social_facebook_white.png'>").addClass("icon").addClass("social_img");
    this.sociconTwitter  =$("<img id='detail_twi_share' src='ico/social_twitter_white.png'>").addClass("icon").addClass("social_img");
    this.urlCopyIcon     =$("<img id='detail_url_copy' src='ico/social_url_white.png'>").addClass("icon").addClass("social_img");

    this.detailPostbar      =$("<div>").addClass("detail_postbar").addClass("swiper-slide");
    this.detailPostbarPostnum=$("<div>").addClass("detail_postbar_postnum");
    this.detailPostbarPostedNum=$("<span>").addClass("list_info_posted_num");

    this.detailPostBtn      =$("<div>").addClass("detail_post_btn").html("post it").click(function(){purchase(paintingId)});
    // this.detailScroll       =$("<div>").addClass("swiper-scrollbar").addClass("swiper-scrollbar-detail");
    this.returnBtn          =$("<div>").addClass("return_btn").html("<img class='icon' src='ico/keyboard_backspace.png' />");
}

DetailStructure.prototype = {
    setBG       : function(fileId){
        this.detailBgImg.attr("src", getImageUrls(fileId));
        if(window.devicePixelRatio<=1){
            this.detailBgImg.attr("srcset", getImageUrls(fileId, 1));
        }else if(window.devicePixelRatio>1 && window.devicePixelRatio<=2){
            this.detailBgImg.attr("srcset", getImageUrls(fileId, 2));
        }else if(window.devicePixelRatio>2){
            this.detailBgImg.attr("srcset", getImageUrls(fileId, 3));
        }
    },
    setArtist   : function(artistName){
    	var paintingId = this.paintingId;
    	var color      = this.color;
    	var colorDark  = this.colorDark;
    	
        this.detailArtistBtn.html(artistName);
        this.detailArtistBtn.click(function () {
        	processDetailClose();
        	// 히스토리 설정
        	replaceHistory({"call": "detailOpen", "paintingId": paintingId, "colorDark": colorDark, "color": color, "mainIndex": mainSwiper.activeIndex, "index": currentSwiper.activeIndex});
        	addHistory({"call": "personal"});
        	showPersonal(artistName);
        });
    },
    setFollow   : function(artistId){
        this.detailArtistFollow.append('<img style="width:12px; height: 12px" class="icon" src="ico/star_white.png"> follow artist');
    },
    setSentence : function(artistSentence){
        this.detailArtistSentence.html(artistSentence);
    },
    setDate     : function(uploadDate){
        this.detailArtistDate.html(uploadDate);
    },
    setPostedNum: function(postedNum){
        this.detailPostbarPostedNum.html(postedNum);
    },
    buildDetail : function(){
        this.setBG(this.fileId);
        this.setArtist(this.artistName);
        this.setFollow(this.artistId);
        this.setSentence(this.artistSentence);
        this.setDate(this.uploadDate);
        this.setPostedNum(this.postedNum);

        this.detailBgContainer.append(this.detailBgImg);

        this.detailMargin.append(this.detailCloseIcon);
        this.detailMargin.append(this.detailMarginIcon);

        this.detailArtistTop.append(this.detailArtistBtn);
        this.detailArtistTop.append(this.detailArtistFollow);
        this.detailArtistSentence.append(this.detailArtistDate);
        this.detailArtistBottom.append(this.sociconFacebook);
        this.detailArtistBottom.append(this.sociconTwitter);
        this.detailArtistBottom.append(this.urlCopyIcon);
        this.detailArtist.append(this.detailArtistTop);
        this.detailArtist.append(this.detailArtistSentence);
        this.detailArtist.append(this.detailArtistBottom);

        this.detailPostbarPostnum.append(this.detailPostbarPostedNum).append(" people already posted it");
        this.detailPostbar.append(this.detailPostbarPostnum);

        this.wrapper.append(this.detailMargin);
        this.wrapper.append(this.detailArtist);
        this.wrapper.append(this.detailPostbar);

        this.detailContainer.append(this.wrapper);

        this.detail.append(this.detailBgContainer);
        this.detail.append(this.detailBgBottom);
        this.detail.append(this.detailContainer);
        this.detail.append(this.detailPostBtn);
        // this.detail.append(this.detailScroll);

        var detailController = new DetailController();

        var picArtistId = this.artistId;
        var artistName = this.artistName;

        //follow 버튼 이벤트
        if(this.followed) {
        	this.detailArtistFollow.on('click', function() { alert(artistName + $.i18n.t('alert.detail.existFollow')); });
        } else if(!userInfo || this.artistId != userInfo.userId) {
        	this.detailArtistFollow.on('click', function() { detailController.artistFollow(picArtistId); });
        }
        
        var paintingId = this.paintingId;
        var fileId = this.fileId;
        
        // 소셜 공유
        var data = {name: artistName, page: paintingId, fileId: fileId};
        $("#detail_fac_share").click(function() {
        	shareSocial($.extend({type: "facebook"}, data));
        });
        $("#detail_twi_share").click(function() {
        	shareSocial($.extend({type: "twitter"}, data));
        });
        //url 복사
        $("#detail_url_copy").click(function() {
        	urlCopy(data);
        });
    }
}

//상세화면을 위한 변수들
var isDetail = false;
var postedLock = true;
var postedObj = new Array();
var postedIndex = new Array();
var postedNodataIndex = new Array();
var postedLockBreakpoint;

//화면이 최초 생성시 swiper 에 detail-margin, detail-artist, detail-postbar 3 의 slide 가 미리 등록되어 진다.
var initPostedSlideCnt = 3;

//현재 그림 정보를 위한 변수들
var selectedPaintingId;
var selectedArtistId;
var selectedArtistName;

function DetailController() {
}

DetailController.prototype = {
	//디테일화면에서 보여질 데이터 조회
	getDetailData: function (paintingId, color, colorDark, call) {
		var controller = this;
		AjaxCall.call(apiUrl+"/painting/"+paintingId, null, "GET", function (result, status) { controller.getDetailDataRes(result, status, paintingId, color, colorDark, call); });
	},
	getDetailDataRes: function (result, status, paintingId, color, colorDark, call) {
		// 히스트리에서 사용하기 위해서 객체 변수 추가
		result.color = color;
		result.colorDark = colorDark;
		
		initDetail(paintingId, result);
		setDetailLayout();

		$(".detail").show().css("top", 200);
		$(".detail").animate({top: 0, opacity: 1}, 200);
		$(".detail_bg_container").css("background-color", "hsl("+color+")");
		$(".detail_bg_bottom").css("background-color", "hsla("+colorDark+", 1)");
		$(".detail_artist").css("background-color", "hsla("+colorDark+", 0.7)");
		$(".detail_postbar").css("background-color", "hsla("+colorDark+", 1)");

		lockPosted(detailSwiper);
        detailSwiper.lockSwipes();
		
		// 소셜공유에서 직접 호출한 경우
		if (call == 'personal') {
			showPersonal(get.user, get.page);
			get = "";
		}
		
		callPosted(detailSwiper);
 	},
	artistFollow: function(artistId) {
		var controller = this;
		
		// 개인페이지에서 사용하는 부분이 있어 selectedArtistId 이 없을 경우 artistId를 사용하도록 변경. 04-05
		AjaxCall.call(apiUrl+"/user/"+(selectedArtistId ? selectedArtistId : artistId)+"/follow", null, "POST", function(result, status) { controller.artistFollowRes(result, status); });
	},
	artistFollowRes: function(result, status) {
		if(result.errorNo == 0) {
			dataReload(["initFollow();"]);
			alert(selectedArtistName + $.i18n.t('alert.detail.processFollow'));
		} else if(result.errorNo == 501){
			alert(selectedArtistName + $.i18n.t('alert.detail.existFollow'));
		}
	}
};

//디테일화면 표시
function loadDetail(paintingId, color, colorDark, call) {
	selectedPaintingId = paintingId;
	detailController = new DetailController().getDetailData(paintingId, color, colorDark, call);
}

//디테일화면 초기화
function initDetail(paintingId, paintingInfo){
	isDetail = true;
	postedLock = true;
	postedObj = new Array();
	postedIndex = new Array();
	
	selectedArtistId = paintingInfo.artistId;
	selectedArtistName = paintingInfo.artistName;
	
	this.detailStructure = new DetailStructure(paintingId, paintingInfo);
	this.detailStructure.buildDetail();
	this.detailSwiper = new Swiper('.swiper_container_detail', {
	     direction: 'vertical',
	     slidesPerView: 'auto',
	     centeredSlides: false,
	     freeMode: true,
	     freeModeMomentumRatio: 0.4,
	     freeModeMomentumBounceRatio: 0.5,
	     mousewheelControl : true,
	     //scrollbar: '.swiper-scrollbar-detail',
	     //scrollbarHide: true
	});
	this.detailSwiper.on("onSliderMove", function(swiper){
	     changeMode(swiper);
	});
	this.detailSwiper.on("onSetTranslate", function(swiper){
	     changeMode(swiper);
	});
}

//디테일화면 css값 설정
function setDetailLayout(){
    mainWidth = $(window).width();
    mainHeight = $(window).height();
    $(".detail_bg_container").css("height", mainHeight-50);
    $(".detail_bg_container").find("img").css("height", "100%");
    $(".detail_bg_img").css("left", (mainWidth-(mainHeight-50)*0.72)/2);
    $(".detail_margin").css("height", mainHeight-50);
    $(".detail_artist_sentence").css("width", mainWidth);
    $(".detail_artist_sentence").css("height", $(".detail_artist").height()-100);
    if(mainHeight<625){
        postedLockBreakpoint = -250;
        $(".detail_artist_sentence").css("height", 150);
    }else{
        postedLockBreakpoint = -(mainHeight*0.4);
        $(".detail_artist_sentence").css("height", -(postedLockBreakpoint)-100);
    };
}

//디테일화면 닫기
function closeDetail(){
    if(isDetail){
    	history.back();
    	processDetailClose();
    }
}

function processDetailClose() {
    if(get.page){
    	personal.swiper.slideTo(personal.swiper.slides.length - 1, 0);
        // 최초 한번만 동작하게 한다.
    	get.page = null;
    }
    $(".detail").animate({top: 200, opacity: 0}, 200, "linear", function(){
    	$(".detail").empty();
    	$(".detail").hide().css("top", 0);
    	delete detailStructure;
    	delete detailSwiper;
    });
    isDetail = false; 
}
//디테일화면의 스크롤 잠금/열기
function changeMode(swiper){            
    var translate = swiper.translate;
    if(translate>50){
        closeDetail();
    }else if(translate<postedLockBreakpoint){
        if(postedLock){
            unlockPosted(swiper);
        }
        callPosted(swiper);
    }
    else if(translate>=postedLockBreakpoint){
        if(!postedLock){
            lockPosted(swiper);
        }
    }

}

//디테일화면의 스크롤 잠금
function lockPosted(swiper){
	
    postedLock = true;
    // hidePosted(swiper);
    // swiper.params.freeMode = false;

    // $(".swiper-scrollbar-detail").hide();
    $(".detail_post_btn").appendTo($(".swiper_container_detail"))
}

//디테일화면의 스크롤 열기
function unlockPosted(swiper){
    postedLock = false;
    // swiper.params.freeMode = true;

    callPosted(swiper);

    // $(".swiper-scrollbar-detail").show();
    $(".detail_post_btn").appendTo($(".detail_postbar"))
}

//Detail화면의 댓글 구조
function Posted(purchaseSeq, userId, userName, userSentence){
    this.purchaseSeq =purchaseSeq;
    this.userId = userId;
    this.userName = userName;
    this.userSentence = userSentence;

    this.container   =$("<div>").addClass("detail_posted swiper-slide").html("posted by ").css("background-color", "hsl("+colorDark+")");
    this.postee      =$("<div>").addClass("detail_postee_btn");
    this.sentence    =$("<div>").addClass("detail_posted_sentence").css("width", mainWidth*0.96);
}
Posted.prototype = {
    setPostee:      function(userName){
        this.postee.html(userName);
    },
    setSentence:    function(userSentence){
        this.sentence.html(userSentence);
    },
    buildPosted:    function(){
        this.setPostee(this.userName);
        this.setSentence(this.userSentence);
        this.container.append(this.postee);
        this.container.append(this.sentence);

        return this.container;
    }
}

var isBlock = false;

function PostedController() {
	this.swiper = '';
}
PostedController.prototype = {
	getPostedData: function(startRow, rowPerPage, swiper) {
		var controller = this;

		if(!isBlock) {
			isBlock = true;

			this.swiper = swiper;
			AjaxCall.call(apiUrl+"/posted?paintingId="+selectedPaintingId+"&startRow="+startRow+"&rowPerPage="+rowPerPage, null, "GET", function (result, status) { controller.getPostedDataRes(result, status); } );
		}
	},
	getPostedDataRes: function(result, status) {
//		console.log("getPostedDataRes ::: " + result.list.length);
		if(result.list && result.list.length == 0 && (this.swiper.slides.length - initPostedSlideCnt) == 0) {
			var postedInfo = {
				purchaseSeq: "",
				userId: "",
				userName: "",
				sentence: $.i18n.t('detailPop.noData')
			};
			addPosted(this.swiper, postedInfo);
		} else {
			for (var index in result.list) {
				addPosted(this.swiper, result.list[index]);
			}
		}

		isBlock = false;
	}
}

function addPosted(swiper, postedInfo) {
	var newPosted = new Posted(postedInfo.purchaseSeq, postedInfo.userId, postedInfo.userName, convertToBr(postedInfo.sentence));
	postedObj[swiper.slides.length-initPostedSlideCnt] = newPosted.buildPosted();

	if(postedInfo.purchaseSeq == "" && postedInfo.userId == "" && postedInfo.userName == "") {
		postedNodataIndex.push(swiper.slides.length);
	} else {
		if(postedNodataIndex.length > 0) {
			swiper.removeSlide(postedNodataIndex);
			postedNodataIndex.pop();
		}
		postedIndex.push(swiper.slides.length);
	}
	delete newPosted;

	swiper.appendSlide(postedObj[swiper.slides.length-initPostedSlideCnt]);
}

//Detail화면의 댓글 무한스크롤 (10개씩 추가)
function callPosted(swiper){
	var isPostedEnd;

	var rowPerPage = 5;
	var detailPostedCnt = swiper.slides.length - initPostedSlideCnt;

	if(detailPostedCnt == 0 || detailPostedCnt == ((swiper.activeIndex+3) - initPostedSlideCnt)) {//현재 화면에 출력된 slide 중 가장 마지막 slide 호출시 rowPerPage 만큼 데이터를 요청한다.
		new PostedController().getPostedData(detailPostedCnt, rowPerPage, swiper);
	}
}

//Detail화면의 댓글 지우기
function hidePosted(swiper){
	swiper.removeSlide(postedIndex);
}
