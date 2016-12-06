// 상세화면의 구조
//function DetailStructure(paintingId, fileId, artistName, artistId, artistSentence, uploadDate, postedNum){
function DetailStructure(paintingId, paintingInfo){
    this.paintingId     = paintingId;
    this.fileId          = paintingInfo.fileInfo.id;
    this.artistName     = paintingInfo.artistName;
    this.artistId       = paintingInfo.artistId;
    this.artistSentence = convertToBr(paintingInfo.sentence);
    this.uploadDate     = toDate(paintingInfo.uploadDate);
    this.postedNum      = paintingInfo.postedNum;
    // 히스토리 사용 부분 추가
    this.colorDark      = paintingInfo.colorDark;
    this.color          = paintingInfo.color;

    this.commentCnt     = paintingInfo.commentCnt;
    this.likeCnt       = paintingInfo.likeCnt;
    this.liked          = paintingInfo.liked > 0?true:false;

    this.detail             =$(".detail");

    this.detailBgContainer  =$("<div>").addClass("detail_bg_container");
    this.detailBgImg        =$("<img>").addClass("detail_bg_img").swipe({
                                swipeUp:function(){
                                    showPostee(0);
                                },
                                tap:function(){
                                    showPostee(0);
                                },
                                click:function(){
                                    showPostee(0);
                                },
                                swipeDown:function(){
                                    closeDetail();
                                },
                                threshold:10,
                                fallbackToMouseEvents: true
                            });
    this.detailBgBottom     =$("<div>").addClass("detail_bg_bottom");

    this.detailContainer    =$("<div>").addClass("detail_container").addClass("swiper_container_detail");
    this.wrapper            =$("<div>").addClass("swiper-wrapper");

    this.detailArtist       =$("<div>").addClass("detail_artist").swipe({
                                tap:function(){
                                    hidePostee();
                                },
                                click:function(){
                                    hidePostee();
                                },
                                threshold:10,
                                fallbackToMouseEvents: true
                            });
    this.detailArtistBtn    =$("<div>").addClass("detail_artist_btn");
    this.followed = paintingInfo.followed;

    if(paintingInfo.followed || (userInfo && this.artistId == userInfo.userId)) {
    	this.detailArtistFollow =$("<div>").addClass("detail_artist_followed");
    } else {
    	this.detailArtistFollow =$("<div>").addClass("detail_artist_follow");
    }

    this.detailArtistTop    =$("<div>").addClass("detail_artist_top");
    this.detailLikeNum      =$("<div>").addClass("detail_count_num");
    this.detailCommentNum   =$("<div>").addClass("detail_count_num");
    this.detailPostNum      =$("<div>").addClass("detail_count_num");

    this.detailArtistBottom =$("<div>").addClass("detail_artist_bottom").html("Share to ");
    this.sociconFacebook =$("<img id='detail_fac_share' src='ico/social_facebook_white.png'>").addClass("icon").addClass("social_img");
    this.sociconTwitter  =$("<img id='detail_twi_share' src='ico/social_twitter_white.png'>").addClass("icon").addClass("social_img");
    this.urlCopyIcon     =$("<img id='detail_url_copy' src='ico/social_url_white.png'>").addClass("icon").addClass("social_img");

    this.detailArtistSentence=$("<div>").addClass("detail_artist_sentence").addClass("swiper-slide");
    this.detailArtistDate   =$("<div>").addClass("detail_artist_date");
    this.detailPosted       =$("<div>").addClass("detail_posted swiper-slide");

    this.detailBtn            =$("<div>").addClass("detail_btn");

    this.likeSeq              =$("<div>").addClass("like_sequence");
    this.likeSeqCir           =$("<div>").addClass("like_sequence_circle");
    
    this.detailBtnLike        =$("<img>").attr("src", "ico/like.png").addClass("list_btn_icon").addClass("list_btn_like")
                                        .click(function(){
                                        		riseBubble(this, paintingId, paintingInfo.artistId);
                                        });
    this.detailBtnLiked       =$("<img>").attr("src", "ico/liked.png").addClass("list_btn_icon").addClass("list_btn_liked")
                                            .click(function(){
                                            	dropBubble(this, paintingId, paintingInfo.artistId);
                                           });
    
    this.detailBtnComment     =$("<img>").attr("src", "ico/comment.png").addClass("list_btn_icon").addClass("detail_btn_comment")
                                        .click(function(){
                                               purchase(paintingId, paintingInfo.artistName, "comment", "CASH");
                                        });
    this.detailBtnPost        =$("<img>").attr("src", "ico/post.png").addClass("list_btn_icon").addClass("detail_btn_post")
                                        .click(function() {
                                               purchase(paintingId, paintingInfo.artistName, "post", "CASH");
                                        });
    this.detailPagenation   =$("<div>").addClass("swiper-pagination").addClass("detail_pagination");
    this.closeBtn          =$("<div>").addClass("close_btn").html("<img class='icon' src='ico/close.png' />").click(function(){
                                hidePostee();
                                closeDetail();
                            })
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
    setLikedNum: function(likedNum){
    	var paintingId = this.paintingId;
        this.detailLikeNum.append("<img class='list_info_posted_ico' src='ico/like.png'><div class='list_info_posted_num list_info_detail_comment_num'><span id='detail_likes_num'>"+likedNum+"</span> likes</div>")
        .click(function(){
        		showLikes(paintingId)
        });
    },
    setCommentedNum: function(commentedNum){
        this.detailCommentNum.append("<img class='list_info_posted_ico' src='ico/comment.png'><div class='list_info_posted_num'><span id='detail_comment_num'>"+commentedNum+"</span> comments</div>");
    },
    setPostedNum: function(postedNum){
        this.detailPostNum.append("<img class='list_info_posted_ico' src='ico/post.png'><div class='list_info_posted_num'>"+postedNum+" posts</div>");
    },
    buildDetail : function(){
        this.setBG(this.fileId);
        this.setArtist(this.artistName);
        this.setFollow(this.artistId);
        this.setSentence(this.artistSentence);
        this.setDate(this.uploadDate);
        this.setLikedNum(this.likeCnt);
        this.setCommentedNum(this.commentCnt);
        this.setPostedNum(this.postedNum);

        this.detailBgContainer.append(this.detailBgImg);
        this.detailBgBottom.append(this.detailArtistBtn);
        this.detailBgBottom.append(this.detailArtistFollow);

        this.detailArtistTop.append(this.detailLikeNum);
        this.detailArtistTop.append(this.detailCommentNum);
        this.detailArtistTop.append(this.detailPostNum);

        this.detailArtistBottom.append(this.sociconFacebook);
        this.detailArtistBottom.append(this.sociconTwitter);
        this.detailArtistBottom.append(this.urlCopyIcon);
        this.detailArtist.append(this.detailArtistTop);
        this.detailArtist.append(this.detailArtistBottom);

        this.detailArtistSentence.append(this.detailArtistDate);
        this.detailPosted.append(this.detailArtistSentence);
        this.wrapper.append(this.detailPosted);

        this.detailContainer.append(this.wrapper);
        this.detailContainer.append(this.detailArtist);
        this.detailContainer.append(this.closeBtn);
        this.detailContainer.append(this.detailPagenation);

        this.detailBtn.append(this.detailBtnPost);
        this.detailBtn.append(this.detailBtnComment);

        console.log(this.liked);
        if(this.liked) {
        	this.detailBtn.append(this.detailBtnLiked);
        } else {
        	this.detailBtn.append(this.detailBtnLike);
        }

        this.likeSeq.append(this.likeSeqCir);
        this.detailBtn.append(this.likeSeq);

        this.detail.append(this.detailBgContainer);
        this.detail.append(this.detailBgBottom);
        this.detail.append(this.detailContainer);
        this.detail.append(this.detailBtn);

        var detailController = new DetailController();

        var picArtistId = this.artistId;
        var artistName = this.artistName;

        //follow 버튼 이벤트
        if(this.followed) {
        	 this.detailArtistFollow.on('click', function() { alert(artistName + $.i18n.t('alert.detail.existFollow')); });
        } else if(!userInfo || this.artistId != userInfo.userId) {
        	this.detailArtistFollow.on('click', function(){
                detailController.artistFollow(picArtistId);
                $(this).addClass("detail_artist_followed").removeClass("detail_artist_follow");
            });
        }

        var paintingId = this.paintingId;
        var fileId = this.fileId;

        // 소셜 공유
        var data = {name: artistName, page: paintingId, fileId: fileId};
        $("#detail_fac_share").click(function(e) {
        	shareSocial($.extend({type: "facebook"}, data));
        });
        $("#detail_twi_share").click(function(e) {
        	shareSocial($.extend({type: "twitter"}, data));
        });
        //url 복사
        $("#detail_url_copy").click(function(e) {
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
var initPostedSlideCnt = 1;

//현재 그림 정보를 위한 변수들
var selectedPaintingId;
var selectedArtistId;
var selectedArtistName;

var thisDetailSwiper = null;

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
		$(".detail_bg_container").css("background-color", color);
		$(".detail_bg_bottom").css("background-color", "hsla("+colorDark+", 1)");
		$(".detail_container").css("background-color", "hsla("+colorDark+", 0.6)");
        if(painteeFB.isCordova()){
            if(StatusBar){
                StatusBar.backgroundColorByHexString("#505050")
            };
        }

		// 소셜공유에서 직접 호출한 경우
        if (call == 'comment') {
            refreshDetailPosted(detailSwiper);
            showPostee(1);
		}else if (call == 'personal') {
			showPersonal(get.user, get.page);
			get = "";
            callPosted(detailSwiper);
		}else{
            callPosted(detailSwiper);
        }
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
        effect: 'coverflow',
        centeredSlides: true,
        slidesPerView: 'auto',
        coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows : false
        },
        mousewheelControl : true,
        pagination: '.detail_pagination',
        paginationClickable: true
	     //scrollbar: '.swiper-scrollbar-detail',
	     //scrollbarHide: true
	});

	thisDetailSwiper = this.detailSwiper;

    this.detailSwiper.on("onSetTranslate", function(swiper){
	     changeMode(swiper);
	});
    callPosted(detailSwiper);
    showNotice("<span data-i18n='notice.closeDetail'><span>");
}

//디테일화면 css값 설정
function setDetailLayout(){
    mainWidth = $(window).width();
    mainHeight = $(window).height();
    if(mainHeight<625){
        postedLockBreakpoint = -375;
    }else{
        postedLockBreakpoint = -(mainHeight*0.6);
    };
    $(".detail_container").css("height", mainHeight-50);
    $(".detail_bg_container").css("height", mainHeight-50);
    $(".detail_bg_container").find("img").css("height", "100%");
    $(".detail_bg_img").css("left", (mainWidth-(mainHeight-50)*0.72)/2);
    $(".detail_margin").css("height", mainHeight-100);
    $(".detail_artist").css("height", mainHeight-50);
    $(".detail_artist_sentence").css("width", mainWidth);
    $(".detail_container").hide();
}

//디테일화면 닫기
function closeDetail(){
    if(isDetail){
    	history.back();
    	processDetailClose();
        $(".notice_box").hide();
        if(painteeFB.isCordova()){
            if(StatusBar){
                StatusBar.backgroundColorByHexString("#8ab82e")
            };
        }
    }
}

// 댓글 화면 표시
function showPostee(index){
    $(".notice_box").hide();
    $(".detail_container").fadeIn(500, function(){
        detailSwiper.slideTo(index);
    });
    detailSwiper.update();
}

function hidePostee(){
    $(".detail_container").fadeOut(500);
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
    if(translate>(mainHeight-250)/2){
        hidePostee();
    }else{
        callPosted(swiper);
        $(".detail_posted").filter(".swiper-slide-active").css("opacity", 1);
        $(".detail_posted").filter(".swiper-slide-next").css("opacity", 0.2);
        $(".detail_posted").filter(".swiper-slide-prev").css("opacity", 0.1);
    }

}

//Detail화면의 댓글 구조
function Posted(purchaseSeq, userId, userName, userSentence, sentenceType){
    this.purchaseSeq =purchaseSeq;
    this.userId = userId;
    this.userName = userName;
    this.userSentence = userSentence;

    this.container   =$("<div>").addClass("detail_posted swiper-slide");
    if(sentenceType == "comment") {
    	this.postedby    =$("<div>").addClass("detail_postedby").html("commented by ");
    } else {
    	this.postedby    =$("<div>").addClass("detail_postedby").html("posted by ");
    }
    this.postee      =$("<div>").addClass("detail_postee_btn").click(function () {
        	processDetailClose();
            $(".notice_box").hide();
        	// 히스토리 설정
        	replaceHistory({"call": "detailOpen", "paintingId": selectedPaintingId, "colorDark": colorDark, "color": color, "mainIndex": mainSwiper.activeIndex, "index": currentSwiper.activeIndex});
        	addHistory({"call": "personal"});
        	showPersonal(userName);
        });;
    this.sentence    =$("<div>").addClass("detail_posted_sentence").css("width", mainWidth);
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
        this.postedby.append(this.postee);
        this.sentence.append(this.postedby);
        this.sentence.append(this.postedby);
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
				sentence: $.i18n.t('detailPop.noData'),
				sentenceType: ""
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
	var newPosted = new Posted(postedInfo.purchaseSeq, postedInfo.userId, postedInfo.userName, convertToBr(postedInfo.sentence), postedInfo.sentenceType);
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

//Detail화면의 댓글 무한스크롤 (50개씩 추가)
function callPosted(swiper){
	var isPostedEnd;

	var rowPerPage = 50;
	var detailPostedCnt = swiper.slides.length - initPostedSlideCnt;

	if(detailPostedCnt == 0 || detailPostedCnt == ((swiper.activeIndex+1) - initPostedSlideCnt)) {//현재 화면에 출력된 slide 중 가장 마지막 slide 호출시 rowPerPage 만큼 데이터를 요청한다.
		new PostedController().getPostedData(detailPostedCnt, rowPerPage, swiper);
	}
}

function refreshDetailPosted(swiper) {
	var slideCnt = swiper.slides.length;

	postedObj = new Array();
	postedIndex = new Array();
	postedNodataIndex = new Array();

	for(var i=0; i<=slideCnt; i++) {
		if(i>1) {
			swiper.removeSlide(1);
		}
	}

	var postNum = parseInt($('#detail_comment_num').html(), 10);
	postNum += 1;

	$('#detail_comment_num').html(postNum);

	callPosted(swiper);
}

function processDetailLikesNum(value) {
	var likesNum = parseInt($('#detail_likes_num').html(), 10);
	likesNum += value;

	$('#detail_likes_num').html(likesNum);
}
