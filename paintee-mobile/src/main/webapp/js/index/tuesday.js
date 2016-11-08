/*  [tuesday] tuesday를 위한 javascript    */

$(document).ready(function () {
	// 페이지 로딩 시 Tuesday 스와이프 홈 화면 정보구성
	initTuesday();
});

// 만약 화요의 그림을 구매했다면 follow 화면으로 이동, 그렇지 않다면 화요의 그림부터 시작
mainSwiper.slideTo(0, 0);

// tuesday container(목록) 시작
var tueSwiper = new Swiper('.swiper_container_tuesday', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-tuesday',
    scrollbarHide: true,
    effect: 'coverflow',
    coverflow: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : false
    }
});

// 공통적인 설정
tueSwiper.on("onTransitionEnd", function(swiper){
    setTueBg();
    if(swiper.activeIndex==0){$(swiper.container).find(".home_btn").hide()}
});
tueSwiper.on("onSlideNextStart", function(swiper) {
    $(swiper.container).find(".home_btn").hide()
});
tueSwiper.on("onSlidePrevStart", function(swiper) {
    $(swiper.container).find(".home_btn").show()
});

//side menu에 이벤트 설정
$("#menu_tuesday").on('click', function(){
    selectMenu(0);
});

//초기 설정들
//가로휠방지 && 페이지네이션숨김
tueSwiper.disableMousewheelControl();
tueSwiper.on("onSetTranslate", function(swiper, translate){
	swipeToMenu(swiper, translate);
});


// Tuseday 화면 구성
function Tuesday(data){
        var self        = this;
        this.paintingId =data.paintingId;
        this.container  =$("<div>").addClass("tue_container swiper-slide");
        this.thumnail   =$("<div>").addClass("tue_thumnail").attr("id", data.paintingId);
        this.title      =$("<span>").addClass("tue_title");
        this.comment    =$("<div>").addClass("tue_comment");
        this.commentedby=$("<div>").addClass("tue_commentedby").html("by <span style='font-weight:700'>paintee</span>");
        this.artist     =$("<div>").addClass("tue_artist");
        this.week       =$("<div>").addClass("tue_week");
        this.expalin    =$("<div>").addClass("tue_explain");
        this.tueBtn            =$("<div>").addClass("tue_btn");
        this.listBtnLike        =$("<img>").attr("src", "ico/like.png").addClass("list_btn_icon").addClass("list_btn_like")
                                            .click(function(){riseBubble(this, data.paintingId, data.artistId);});
        this.listBtnLiked       =$("<img>").attr("src", "ico/liked.png").addClass("list_btn_icon").addClass("list_btn_liked")
                                            .click(function(){dropBubble(this, data.paintingId, data.artistId)});
        this.listBtnComment     =$("<img>").attr("src", "ico/comment.png").addClass("list_btn_icon").addClass("list_btn_comment").click(function(){
								            	purchase(data.paintingId, data.artistName, "comment", "TUESDAY");
								        	});
        this.listBtnPost        =$("<img>").attr("src", "ico/post.png").addClass("list_btn_icon").addClass("list_btn_post").click(function() {
												if (data.paintingStatus == "D") {
													alert($.i18n.t('alert.common.delPainting'));
												    return;
												}
												if(checkFreePaint(data.startDate, data.endDate) && data.postYn == 'N') {
													purchase(data.paintingId, data.artistName, "post", "TUESDAY");
												} else {
													purchase(data.paintingId, data.artistName, "post", "CASH");
												}
                                            });
        this.next       =$("<div>").addClass("home_next").html('<img class="icon" src="ico/keyboard_arrow_up.png">').click(function(){mainSwiper.slideNext()});
        // checkFreeePaint 함수를 이용해 게시기간 체크해서 내용변경
        if(checkFreePaint(data.startDate, data.endDate) && data.postYn == 'N'){
            this.expalin.html("<span data-i18n='[html]tuesday.explainFree'></span>");
            this.listBtnPost.attr("src", "ico/post_free.png");
        }else if(checkFreePaint(data.startDate, data.endDate) && data.postYn == 'Y'){
            this.expalin.html("<span data-i18n='[html]tuesday.explainAlready'></span>");
            mainSwiper.slideTo(1, 0);
        }else {
            this.expalin.html("<span data-i18n='[html]tuesday.explainNotFree'></span>");
        }
}

Tuesday.prototype = {
        setImage:       function(url){
                            this.thumnail.css("background-image", "url("+url+")")
                            .click(function(){
                                toggleTueBg(this);
                            })
                            if(mainWidth/mainHeight>18/25){
                                this.thumnail.css("background-size", mainWidth+"px "+mainWidth*25/18+"px")
                            }else{
                                this.thumnail.css("background-size", mainHeight*18/25+"px "+mainHeight+"px")
                            }
                        },
        setComment:     function(comment, title){
                            if(title!=undefined){
                                this.comment.append(this.title.html(convertToBr(title) + "<br>"));
                            }
                            this.comment.append(convertToBr(comment));
                            this.comment.append(this.commentedby);
                        },
        setArtist:      function(artistName){
                            var paintingId = this.paintingId;
                            var artistBtn = $("<span>").addClass("tue_artist_name").html(artistName).click(function(){
                                showPersonal(artistName, paintingId);
                            })
                            this.artist.html("painted by ").append(artistBtn);
                        },
        setWeek:        function(week, month){
                            this.week.html(week+"<span style='font-weight=700'> Tuesday</span>, of "+month)

                        },
        setPost:        function(listData){
                            this.tueBtn.append(this.listBtnPost);
                            this.tueBtn.append(this.listBtnComment);
                            if (listData.loginLikeCnt == 0) {
                            	this.tueBtn.append(this.listBtnLike);
                            } else {
                            	this.tueBtn.append(this.listBtnLiked);
                            }                            
        },
        setSentenceBox: function(){
                            this.sentenceBox.append(this.sentenceInput);
                            this.sentenceBox.append(this.confirm);
        },
        buildStructure: function(){
                            this.container.append(this.comment);
                            this.container.append(this.artist);
                            this.container.append(this.week);
                            this.container.append(this.expalin);
                            this.container.append(this.tueBtn);
                            this.container.append(this.next);
                            this.container.append(this.thumnail);
                            return this.container;
                        }
}

function checkFreePaint(pStartDate, pEndDate) {
	var isFree = false;

	var startDate = pStartDate.split(" ")[0];
	var startDateArr = startDate.split('-');

	var endDate = pEndDate.split(" ")[0];
	var endDateArr = endDate.split('-');

	var todayDate =new Date()


	var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
    var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
    var todayDateCompare = new Date(todayDate.getFullYear(), todayDate.getMonth()+1, todayDate.getDate());

    if(startDateCompare.getTime() <= todayDateCompare.getTime() && todayDateCompare.getTime() <= endDateCompare.getTime()) {
    	isFree = true;
    }

    return isFree;
}

function initTuesday(){
    var controller = new TuesdayController();
    controller.getListData(0);

	// 다국어 변경 적용
	exeTranslation('.main_container', lang);
}

tueSwiper.on("onSlideChangeStart", function(swiper){
	if (swiper.slides.length <= swiper.activeIndex+1 && swiper.slides.length < 100) {
		var controller = new TuesdayController();
		controller.getListData(swiper.slides.length);
	}
    $(".tue_mask").css("opacity", 1);
    $(".tue_bg").css("-webkit-filter", "blur(10px)");
    $(".tue_sentence_box").hide();

});
tueSwiper.on("onSlideChangeEnd", function(swiper){
    setTueBg();
});

// 슬라이드의 배경화면을 전환하는 함수
function setTueBg(){
    var paintingId = $(tueSwiper.slides[tueSwiper.activeIndex]).find(".tue_thumnail").attr("id");
    controller = new TuesdayController().getBgData(paintingId);
}

// 슬라이드 배경화면의 투명도를 전환하는 함수
function toggleTueBg(thum){
    var container = $(thum).parent()[0];
    var opacity = $(".tue_mask").css("opacity");
    if(opacity==1){
        $(".tue_mask").css("opacity", 0);
        $(".tue_bg").css("-webkit-filter", "blur(0px)");
        $(container).find(".tue_sentence_box").delay(10000).fadeIn(500);
    }else{
        $(".tue_mask").css("opacity", 1);
        $(".tue_bg").css("-webkit-filter", "blur(10px)");
        $(container).find(".tue_sentence_box").hide();
    }
}


function addTuesday(swiper, currentIndex, type, listData){
	if (!listData) {return;}

	var data = {
		index: swiper.slides.length,
		paintingId: listData.paintingId,
		artistName: listData.artistName,
		artistId: listData.artistId,
		startDate: listData.startDate,
		endDate: listData.endDate,
		likeCnt: listData.likeCnt,
		postYn: listData.postYn,
		tuesdaySeq: listData.tuesdaySeq
	};

	var newSlide = new Tuesday(data);

    // 각각의 슬라이드의 내용을 설정합니다.
    // 이미지 주소/추천평/week/작가이름이 tuesday 목록에서 불러온 값으로 설정되게 해주세요.
    newSlide.setImage(getImageUrls(listData.fileId));
    newSlide.setComment(listData.comment, listData.title);
//    newSlide.setWeek("1st", "Oct");
    var startDate = new Date(listData.startDate);
    newSlide.setWeek(startDate.weekCountOfMonth() + "st", startDate.convertEngMonth());
    // 게시기간이 시작되는 날짜를 기준으로 [월] [몇번째주] 로 표시됩니다.
    
    newSlide.setPost(listData);
    newSlide.setArtist(listData.artistName);

    tueSwiper.appendSlide(newSlide.buildStructure());
    delete newSlide;

    if(currentIndex==0){
        setTueBg();
    }
}

// 이부분은 임시로 popular 목록을 가져오도록 임의로 설정해놨습니다.
// 화면 예시를 위한 샘플이기때문에 서버 연동 부분은 실제 tuesday 목록을 가져올 수 있도록 수정되어야 합니다.

function TuesdayController() {
	this.startRow =0;
}
TuesdayController.prototype = {
	getListData: function (startRow) {
		this.startRow = startRow;
		var controller = this;
		AjaxCall.call(apiUrl + "/tuesday?startRow=" + startRow,
			null,
			"GET",
			function (result) {
				controller.getListDataRes(result);
			}
		);
	},
	getListDataRes: function (result) {
		for (var index in result.list) {
			addTuesday(tueSwiper, index, "tuesday", result.list[index]);
			if (tueSwiper.slides.length > 100) {
				break;
			}
            exeTranslation('.tue_container', lang);
		}
	},
	getBgData: function (paintingId) {
		var controller = this;
		AjaxCall.call(apiUrl+"/painting/"+paintingId, null, "GET", function(result){controller.getBgDataRes(result);});
	},
	getBgDataRes: function (result) {
        $(".tue_bg").css("background-image", "url("+getImageUrls(result.fileInfo.id)+")")
 	}

};
