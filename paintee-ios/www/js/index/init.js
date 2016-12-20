$(function () {
	get = getRequest();
	// 로그인 되어있지 않은 경우 about 페이지 띄우기
	if(!userInfo && !get) {
		showAboutOverview();
	}
	
	setSideMenu();  // 사이드 메뉴 설정
});
	
// 전역변수 설정
var get;
var callType;
var mainWidth;
var slideWidth;
var userID = "";
var currentSwiper="";
var color="190,60%,50%";
var colorDark="200,60%,20%";
var purchaseWidth;
var purchaseStatus="";
var boxWidth;
var boxHeight;
var boxStatus="";
var popName="";
var fullImage=true;

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

var userInfo = getUserInfoCookie();

if(userInfo) {
	userID = userInfo.email;
} else {
	userID = '';
}

var lang = "ko";

/**
 * 사이드 메뉴의 액션 설정 및 활성화/비활성화 처리
 */
function setSideMenu() {
	// 사이드 메뉴 활성화 및 비활성화 설정
	if (!userID) {
		$("#menu_upload").addClass("side_menu_minor_inactive").click(function(){
            sideOff();
            showLogin();
        });
		$("#menu_reward").addClass("side_menu_minor_inactive").click(function(){
            sideOff();
            showLogin();
        });
	}
	else {
		$("#menu_upload").click(function(){
		    upload();
		    sideOff();
		});
		$("#menu_reward").click(function(){
		    reward();
		    sideOff();
		});
	}

	// 사이드 메뉴 언어 설정
	// 로그인 정보를 매번 가져오지 않기 때문에 사이트 로딩시 로그인 상태일 경우만 로그인 정보를 가져온다.
	if (userInfo) {
		AjaxCall.call(apiUrl + "/user/me", 
			null, 
			"GET", 
			function (result) {
				lang = result.userInfo.language ? result.userInfo.language : "ko";
				$(".side_menu_lang_select").val(lang);
			}
		);
	}
}

//var imageUrl="http://localhost:8000/paintee-admin";
//var imageUrl="http://52.78.126.27:8080/paintee-admin";

//var imageUrl="http://localhost:8080/paintee-admin";
//var imageUrl="http://52.78.93.238/paintee-admin";
var imageUrl="http://paintee.me/paintee-admin";
//var imageUrl=window.location.protocol+"//paintee.me/paintee-admin";
var apiUrl = imageUrl + "/api";

//console.log(navigator.userAgent);

//console.log(navigator);


setWidth();

// main container 시작
var mainSwiper = new Swiper('.swiper_container', {
    direction: 'vertical',
    mousewheelControl : true
});

// 각각의 home 화면 (follow/popular/new/my)
function Home(){
        this.container  =$("<div>").addClass("home_container swiper-slide");
        this.prev       =$("<div>").addClass("home_prev").html('<img class="icon" src="ico/keyboard_arrow_down.png">').click(function(){mainSwiper.slidePrev()});
        this.title      =$("<div>").addClass("home_title");
        this.contents   =$("<div>").addClass("home_contents");
        this.add        =$("<div>").addClass("home_contents_add");
        this.next       =$("<div>").addClass("home_next").html('<img class="icon" src="ico/keyboard_arrow_up.png">').click(function(){mainSwiper.slideNext()});
}

Home.prototype = {
        setTitle:       function(title){
                            this.title.html(title);
                        },
        setExplain:     function(explain){
                            this.contents.html(explain);
                        },
        setContents:    function(contents){
                            this.contents.append(contents);
                        },
        setAdd:         function(contents){
                            this.add.append(contents);
                        },
        hidePrev:       function(){
                            this.prev.hide();
                        },
        hideNext:       function(){
                            this.next.hide();
                        },
        buildStructure: function(){
                            this.container.append(this.prev);
                            this.container.append($("<div>").addClass("home_title_margin"));
                            this.container.append(this.title);
                            this.container.append($("<div>").addClass("home_contents_margin"));
                            this.container.append(this.contents);
                            this.container.append($("<div>").addClass("home_contents_margin"));
                            this.container.append(this.add);
                            this.container.append(this.next);
                            return this.container;
                        }
}

// 그림 목록 화면
function Structure(data) {
        this.index              =data.index;
        this.container          =$("<div>").addClass("list_contents swiper-slide");

        this.listInfo           =$("<div>").addClass("list_info");
        this.listInfoRow_1      =$("<div>").addClass("list_info_row");
        this.listInfoRow_2      =$("<div>").addClass("list_info_row");
        this.listInfoSentence   =$("<div>").addClass("list_info_sentence");
        this.listInfoPosted     =$("<div>").addClass("list_info_posted");
        this.listInfoDate       =$("<div>").addClass("list_info_date");

        // mobile 기기의 pixel ratio를 반영한 가변 이미지 반영
         this.listPainting       =$("<img>").addClass("list_painting").attr("index", this.index);

        this.bottom             =$("<div>").addClass("bottom_bar");
        this.listArtist         =$("<div>").addClass("list_artist_btn").click(function() {
						        	// 히스토리 설정
						        	replaceHistory({"call": "list", "mainIndex": mainSwiper.activeIndex, "index": currentSwiper.activeIndex ? currentSwiper.activeIndex : data.index});
						        	addHistory({"call": "personal"});
        							showPersonal(data.artistName)
        						});
/*        this.listPostBtn        =$("<div>").addClass("list_post_btn").html("post it")
                                           .click( function() {
                                        	   		   if (data.paintingStatus == "D") {
                                        	   			   alert($.i18n.t('alert.common.delPainting'));
                                        	   			   return;
                                        	   		   }
                                        	           purchase(data.paintingId, data.artistName);
                                        	       }
                                           );*/
        this.listBtn            =$("<div>", {"data-likeId": data.paintingId}).addClass("list_btn");
        this.likeSeq            =$("<div>").addClass("like_sequence");
        this.likeSeqCir         =$("<div>").addClass("like_sequence_circle");
        this.listBtnLike        =$("<img>").attr("src", "ico/like.png").addClass("list_btn_icon").addClass("list_btn_like")
                                            .click(function(){riseBubble(this, data.paintingId, data.artistId);});
        this.listBtnLiked       =$("<img>").attr("src", "ico/liked.png").addClass("list_btn_icon").addClass("list_btn_liked")
                                            .click(function(){dropBubble(this, data.paintingId, data.artistId)});
        this.listBtnComment     =$("<img>").attr("src", "ico/comment.png").addClass("list_btn_icon").addClass("list_btn_comment")
                                            .click(function(){
                                                   if (data.paintingStatus == "D") {
                                                       alert($.i18n.t('alert.common.delPainting'));
                                                       return;
                                                   }
                                                   purchase(data.paintingId, data.artistName, "comment", "CASH");
                                            });
        this.listBtnPost        =$("<img>").attr("src", "ico/post.png").addClass("list_btn_icon").addClass("list_btn_post")
                                            .click(function() {
                                                   if (data.paintingStatus == "D") {
                                                       alert($.i18n.t('alert.common.delPainting'));
                                                       return;
                                                   }
                                                   purchase(data.paintingId, data.artistName, "post", "CASH");
                                            });
        this.listStatusBtn      =$("<div>").addClass("list_status_btn");                 
        this.listStatusStc      =$("<div>").addClass("list_status_sentence");            
        this.listCancelBtn      =$("<div>").addClass("list_cancel_btn").html("Cancel");  
        this.listRefundBtn      =$("<div>").addClass("list_refund_btn").html("Cancel Refund");  
        //this.listResendBtn      =$("<div>").addClass("list_resend_btn").html("Resend");
        this.listConfirmBtn     =$("<div>").addClass("list_confirm_btn").html("Confirm");
        this.listDeleteBtn      =$("<div>").addClass("list_delete_btn").html("Delete");
}
Structure.prototype = {
        setSentence:        function(sentence, wrighter){
                                this.listInfoSentence.html(convertToBr(sentence)+"<br><br> <span class='list_info_sentence_wrighter'> by <b>"+wrighter+"</b></span>");
                            },
        setLikedNumber:    function(paintingId, likedNum){
                            this.listInfoPosted.append("<img class='list_info_posted_ico' src='ico/like.png'><div class='list_info_posted_num'><span data-like='like_" + paintingId + "'>"+likedNum+"</span></div>")
                        },
        setCommentedNumber: function(paintingId, commentedNum){
                            this.listInfoPosted.append("<img class='list_info_posted_ico' src='ico/comment.png'><div class='list_info_posted_num'><span data-comment='" + paintingId + "'>" + commentedNum + "</span></div>")
                        },
        setPostedNumber:    function(postedNum){
                            this.listInfoPosted.append("<img class='list_info_posted_ico' src='ico/post.png'><div class='list_info_posted_num'>"+postedNum+"</div>")
                        },
        setDate:            function(date){
                                this.listInfoDate.html(date)
                            },
       	setPainting:        function(paintingId, fileId, type){
                                if(mainWidth<729){
                                    this.listPainting.css({"width": mainWidth*0.8, "height": mainWidth*10/9});
                                }else{
                                    this.listPainting.css({"width": "648px", "height": "900px"});
                                }
                                /*
                            	// mobile 기기의 pixel ratio를 반영한 가변 이미지 반영
                                 */
                                if (type != "personal") {
                                	this.listPainting.attr("src", getImageUrls(fileId));
                                	if (window.devicePixelRatio) {
	                                	if(window.devicePixelRatio<=1){
	                                		this.listPainting.attr("srcset", getImageUrls(fileId, 1));
	                                	}else if(window.devicePixelRatio>1 && window.devicePixelRatio<=2){
	                                		this.listPainting.attr("srcset", getImageUrls(fileId, 2));
	                                	}else if(window.devicePixelRatio>2){
	                                		this.listPainting.attr("srcset", getImageUrls(fileId, 3));
	                                	}
                                	}
                                } else {
                                	this.listPainting.addClass("swiper-lazy");
                                	this.listPainting.attr("data-src", getImageUrls(fileId));
                                	if (window.devicePixelRatio) {
                                		if(window.devicePixelRatio<=1){
                                			this.listPainting.attr("data-srcset", getImageUrls(fileId, 1));
                                		}else if(window.devicePixelRatio>1 && window.devicePixelRatio<=2){
                                			this.listPainting.attr("data-srcset", getImageUrls(fileId, 2));
                                		}else if(window.devicePixelRatio>2){
                                			this.listPainting.attr("data-srcset", getImageUrls(fileId, 3));
                                		}
                                	}
                                }
                            	
                                this.listPainting.swipe({
                                    swipeUp:function(){
                                        loadDetail(paintingId, color, colorDark);
                                        replaceHistory({"call": "detailPop"});
                                        addHistory({"call": "dummy"});
                                    },
                                    tap:function(){
                                        loadDetail(paintingId, color, colorDark);
                                        replaceHistory({"call": "detailPop"});
                                        addHistory({"call": "dummy"});
                                    },
                                    click:function(){
                                        loadDetail(paintingId, color, colorDark);
                                        replaceHistory({"call": "detailPop"});
                                        addHistory({"call": "dummy"});
                                    },
                                    threshold:10,
                                    fallbackToMouseEvents: true
                                });
                            },
        setColor:           function(color){
                                this.bottom.css("background-color", color);
                            },
        setArtist:          function(name){
                                this.listArtist.html(name);
                            },
        setStatus:          function(listData){
        						// console.log(listData);
        	
                                if(listData.paintingStatus == "1"){                                                        
                                    this.listStatusBtn.addClass("list_status_preparing")
                                                      .html("preparing")
                                                      .attr("id", "exeBtn" + listData.seq)
                                                      .click(function(){
                                                            showCancel(this, listData);
                                                      });
                                } else if(listData.paintingStatus == "2" || listData.paintingStatus == "4"){
                                    this.listStatusBtn.addClass("list_status_sended")
                                                      .html("sent")
                                                      .attr("id", "exeBtn" + listData.seq)
                                                      .click(function(){
						                                  showResend(this, listData);
						                              });
                                } else if(listData.paintingStatus == "3"){                                                     
                                    this.listStatusBtn.addClass("list_status_refund")
					                                  .html("refund")
					                                  .attr("id", "exeBtn" + listData.seq)
					                                  .click(function(){
							                              showRefund(this, listData);
							                          });
                                } else if(listData.paintingStatus == "99" || listData.paintingStatus == "5"){
                                    this.listStatusBtn.addClass("list_status_done")
                                    				  .html("delete")
                                                      .attr("id", "exeBtn" + listData.seq)
					                                  .click(function(){
							                              showDelete(this, listData, listData.paintingStatus);
							                          });
                                } else if(listData.paintingStatus == "100"){
                                	this.listStatusBtn.addClass("list_status_done")
					                                  .html("delete")
					                                  .attr("id", "exeBtn" + listData.seq)
					                                  .click(function(){
					                                	  showCommentDelete(this, listData, listData.paintingStatus);
					                                  });
                                } else if(listData.paintingStatus == "N"){
                                	this.listStatusBtn.addClass("list_status_done")
					                  				  .html("delete")
                                                      .attr("id", "exeBtn" + listData.seq)
                                                      .click(function(){
							                              showDelete(this, listData, listData.paintingStatus);
							                          });
                                } 
                                // 댓글 삭제처리
                                else {
                                	this.listStatusBtn.addClass("list_status_done")
                                	.html("delete")
                                	.attr("id", "exeBtn" + listData.seq)
                                	.click(function(){
                                		showDelete(this, listData, listData.paintingStatus);
                                	});
                                }
                                
        },
        setNotice:          function(sentence, link){
                                this.listInfoSentence.html(sentence);
                                this.listPostBtn = "";
                                this.listInfoPosted.empty().html("Notice from Paintee").css("font-family", "Merriweather").css("font-weight", "700").css("font-style", "italic");
                                this.listArtist.html("NOTICE").css("font-style", "italic");
                                if(mainWidth<729){
                                    this.listPainting.css({"width": mainWidth*0.8, "height": mainWidth*10/9});
                                }else{
                                    this.listPainting.css({"width": "648px", "height": "900px"});
                                }
                                if(link){
                                    this.listPainting.click(function(){
                                        window.open(link, '_blank');
                                    })
                                }
                                this.listPainting.attr("src", "images/notice.jpg");
                            },
        buildStructure:     function(type, listData){
                                this.listInfoRow_1.append(this.listInfoSentence);
                                this.listInfo.append(this.listInfoRow_1);
                                this.listInfoRow_2.append(this.listInfoPosted);
                                this.listInfoRow_2.append(this.listInfoDate);
                                this.listInfo.append(this.listInfoRow_2);
                                this.container.append(this.listInfo);
                                this.container.append(this.listPainting);
                                this.container.append(this.bottom);
                                this.container.append(this.listArtist);
//                                this.container.append(this.listPostBtn);
                                this.listBtn.append(this.listBtnPost);
                                this.listBtn.append(this.listBtnComment);
                                if (listData.loginLikeCnt == 0) {
                                	this.listBtn.append(this.listBtnLike);
                                } else {
                                	this.listBtn.append(this.listBtnLiked);
                                }

                                this.likeSeq.append(this.likeSeqCir);
                                this.listBtn.append(this.likeSeq);

                                this.container.append(this.listBtn);
                                // 마이페이지의 그림 하단의 상태표시 버튼
                                if (type == "my") {
                                	
                            		switch(listData.paintingStatus) {
                            		case "1":
                            		case "2":
                            		case "3":
                            		case "4":
                            		case "5":
                            		case "99":
                            		case "100":   // 댓글 일 경우 삭제를 위해 추가
                             		case "N":
                            			this.container.append(this.listStatusBtn);  
                            			this.container.append(this.listCancelBtn);  
                            			this.container.append(this.listRefundBtn);  
                            			//this.container.append(this.listResendBtn);
                            			this.container.append(this.listConfirmBtn);
                            			this.container.append(this.listDeleteBtn);
                            			this.container.append(this.listStatusStc);
                            			break;
                            		}
                                	
                                }
                                return this.container;
                            }
}

// 현재 슬라이드 위치에서 앞으로 5개의 슬라이드가 없으면 새로 생성 (무한스크롤)
function addPainting(swiper, currentIndex, type, listData){
	
	if (!listData) { return; }
	var data = {
		index: swiper.slides.length,
		paintingId: listData.paintingId,
		artistName: listData.artistName,
		artistId: listData.artistId,
		likeCnt: listData.likeCnt,
		commentCnt: listData.commentCnt
	};

	// 업로드된 그림일 경우 U, 구매된 그림일 경우 P
	// 사용 페이지 : My
	if (listData.paintingStatus) {
		data.paintingStatus = listData.paintingStatus;
	}
	var newSlide = new Structure(data);
    newSlide.setSentence((listData.paintingStatus == "B") ? "It was blind by the administrator." : listData.sentence, listData.sentenceName ? listData.sentenceName : listData.artistName);
    newSlide.setLikedNumber(listData.paintingId, listData.likeCnt);
    newSlide.setCommentedNumber(listData.paintingId, listData.commentCnt);
    newSlide.setPostedNumber(listData.postedNum);
    newSlide.setDate(toEngDateStr(listData.uploadDate));
    newSlide.setArtist(listData.artistName);
    newSlide.setPainting(listData.paintingId, listData.fileId, type);
    if (type=="follow") {
        newSlide.setColor("hsl(200,60%,20%)");
    } else if (type=="popular") {
        newSlide.setColor("hsl(330,60%,20%)");
    } else if (type=="new") {
        newSlide.setColor("hsl(90,60%,20%)");
    } else if (type=="my" || type=="personal") {
        newSlide.setColor("hsl(250,60%,20%)");
        newSlide.setStatus(listData);   
	} 
    
    swiper.appendSlide(newSlide.buildStructure(type, listData));
    delete newSlide;    
}

// follow 목록에 공지사항을 추가
function addNotice(sentence, link){
    var data = {
		index: followSwiper.slides.length,
	};
	var newSlide = new Structure(data);
    newSlide.setColor("hsl(200,60%,20%)");
    newSlide.setNotice(sentence, link);
    followSwiper.appendSlide(newSlide.buildStructure("follow"));
    delete newSlide;
}

var targetTimer;
function startTimer(clicked) {
    if(targetTimer != undefined){
        hideCancel(targetTimer);
    }
    var self = this;
    targetTimer = clicked;
    this.timer = setTimeout(function() {
        hideCancel(clicked);
        delete self.timer;
    }, 5000)
}

function deleteTimer() {
    if( typeof this.timer != 'undefined') {
        clearTimeout(this.timer);
        delete this.timer;
        targetTimer = undefined;
    }
}
function hideCancel(clicked){
    if($(clicked).html()=="preparing"){
        $(clicked).parent().find(".list_cancel_btn").fadeOut();
        $(clicked).parent().find(".list_status_sentence").fadeOut();
        deleteTimer();
    }else if($(clicked).html()=="refund"){
        $(clicked).parent().find(".list_refund_btn").fadeOut();
	    $(clicked).parent().find(".list_status_sentence").fadeOut();
        deleteTimer();
    }else if($(clicked).html()=="sent"){
        //$(clicked).parent().find(".list_resend_btn").fadeOut();
        $(clicked).parent().find(".list_confirm_btn").fadeOut();
        $(clicked).parent().find(".list_status_sentence").fadeOut();
        deleteTimer();
    }else if($(clicked).html()=="delete"){
        $(clicked).parent().find(".list_delete_btn").fadeOut();
        $(clicked).parent().find(".list_status_sentence").fadeOut();
        deleteTimer();
    }
}

function showCancel(clicked, listData){
    $(clicked).parent().find(".list_cancel_btn").fadeIn().one("click", function () { 
   		new PurchaseController().cancelPurchase(listData); 
   		hideCancel(this);
    });
    $(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.cancelStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
    startTimer(clicked);
    exeTranslation('.main_container', lang);
}

function showRefund(clicked, listData){
	$(clicked).parent().find(".list_refund_btn").fadeIn().one("click", function () { 
		new PurchaseController().cancelRefundPurchase(listData); 
		hideCancel(this);
	});
	$(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.refundStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
	startTimer(clicked);
	exeTranslation('.main_container', lang);
}

function showResend(clicked, listData){
    $(clicked).parent().find(".list_confirm_btn").fadeIn().click(function(){
    	new PurchaseController().completePurchase(listData); 
    	hideCancel(this)
    });
    $(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.sendStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
    startTimer(clicked);
    exeTranslation('.main_container', lang);
}

function showDelete(clicked, listData, status){
    $(clicked).parent().find(".list_delete_btn").fadeIn().one("click", function () {
        if (status == "N"){
            new PurchaseController().delStatusPainting(listData);
        }else{
            new PurchaseController().delStatusPurchase(listData);
        }

   		hideCancel(this);
    });
    // 텍스트 문구 수정 sendStatus 블라블라
    $(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.deleteStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
    startTimer(clicked);
    exeTranslation('#my', lang);
}

function showCommentDelete(clicked, listData, status){
	$(clicked).parent().find(".list_delete_btn").fadeIn().one("click", function () {
		new CommentController().delComment(listData);
		hideCancel(this);
	});
	// 텍스트 문구 수정 sendStatus 블라블라
	$(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.deleteStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
	startTimer(clicked);
	exeTranslation('#my', lang);
}

// 최초 5개 미리 생성
initMenu(userID);

// [tuesday] tuesday 목록이 하나 더 늘어남에 따라, mainSwiper의 순서와 길이를 변경했습니다.
// mainSwiper의 첫항목과 마지막항목에서 스와이프 방지
function mainLock(mainSwiper){
    if(mainSwiper.activeIndex==0){
        mainSwiper.lockSwipeToPrev();
        tueSwiper.slideTo(0,0);
        color = "#505050";
        colorDark = "190,50%,20%";
        if(isPersonal){hidePersonal()};
        hideNewsHead();

    }else if(mainSwiper.activeIndex==1){
        mainSwiper.unlockSwipes();
        color = "#33b3cc";
        colorDark = "200,60%,20%";
        if(isPersonal){hidePersonal()};
        hideNewsHead();
        
    }else if(mainSwiper.activeIndex==2){
        mainSwiper.unlockSwipes();
        color = "#cc3380";
        colorDark = "330,60%,20%";
        if(isPersonal){hidePersonal()};
        hideNewsHead();
        
    }else if(mainSwiper.activeIndex==3){
        mainSwiper.unlockSwipes();
        color = "#8ab82e";
        colorDark = "90,60%,20%";
        if(isPersonal){hidePersonal()};
        hideNewsHead();
        
    }else if(mainSwiper.activeIndex==4){
        mainSwiper.lockSwipeToNext();
        color = "#4c33cc";
        colorDark = "250,60%,20%";
        if(isPersonal){hidePersonal()};
        //        [news] 알림창 표출 주석처리
        //        showNewsHead();
    }
    currentSwiper="";
};

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
function listLock(swiper){  
    if(swiper.isBeginning){
        if(mainSwiper.isBeginning){
            mainSwiper.unlockSwipeToNext();
        }else if(mainSwiper.isEnd){
            mainSwiper.unlockSwipeToPrev();
            //        [news] 알림창 표출 주석처리
            //        if(swiper.previousIndex==1)showNewsHead();
        }else{
            mainSwiper.unlockSwipes();
        }
        swiper.disableMousewheelControl();
        $(".swiper-scrollbar").hide();
        $(".home_btn").hide()
        $(".bottom_bar").css("opacity", 0);
    }else{
        mainSwiper.lockSwipes();
        swiper.enableMousewheelControl();
        $(".swiper-scrollbar").show();
        if(!fullImage){$(".bottom_bar").css("opacity", 1)};
        hideNewsHead();
        currentSwiper=swiper;
    }
}
mainSwiper.on("onTransitionEnd", function(mainSwiper){mainLock(mainSwiper)});
mainSwiper.on("onSlideChangeEnd", function(mainSwiper){
//    if(painteeFB.isCordova()){
//        if(mainSwiper.activeIndex==0){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#505050")
//            };
//        }else if(mainSwiper.activeIndex==1){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#33b3cc")
//            };
//        }else if(mainSwiper.activeIndex==2){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#cc3380")
//            };
//        }else if(mainSwiper.activeIndex==3){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#8ab82e")
//            };
//        }else if(mainSwiper.activeIndex==4){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#4c33cc")
//            };
//        }
//    }
});
mainSwiper.on("onSlidePrevStart", function(mainSwiper){
//    if(painteeFB.isCordova()){
//        if(mainSwiper.activeIndex==0){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#505050")
//            };
//        }else if(mainSwiper.activeIndex==1){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#33b3cc")
//            };
//        }else if(mainSwiper.activeIndex==2){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#cc3380")
//            };
//        }else if(mainSwiper.activeIndex==3){
//            if(StatusBar){
//                StatusBar.backgroundColorByHexString("#8ab82e")
//            };
//        }
//    }
});

// side menu 초기설정
function initMenu(userID){
    var sideLogin = $("#side_menu").find(".side_menu_login");
    if(userID==""){
        sideLogin.append($("<div>").addClass("login_btn").html("Log in").css("border-color", "rgb(100,100,100)").click(function(){showLogin()}));
    }else{
        sideLogin.empty()
        sideLogin.append($("<div>").addClass("side_menu_login_id").html(userInfo.name)).on("click", function(){showProfile()});

        var editBtn = $("<a>").html("edit profile · logout").on("click", function(){showProfile()});
        sideLogin.append(editBtn);
    }
}

// side menu 이동
sideMenu = $("#side_menu");
function sideOn(){
    sideMenu.css("right", mainWidth-sideMenu.width());
    sideMenu.find(".side_menu_btn").hide();
    sideMenu.find(".side_menu_close").show();

    mainSwiper.lockSwipes();
    $(".modal").show();
    $(".modal").on("touchstart mousedown click", function(e){
        e.stopPropagation();
        sideOff();
    });
    hideNewsHead();
}
function sideOff(){
    sideMenu.css("right", "100%");
    sideMenu.find(".side_menu_close").hide();
    sideMenu.find(".side_menu_btn").show();
    mainSwiper.unlockSwipes();

    $(".modal").hide();
}
sideMenu.find(".side_menu_btn").click(function(){
    sideOn();
});
sideMenu.find(".side_menu_close").click(function(){
    sideOff();
});
sideMenu.swipe({
    swipeLeft:function(){
        sideOff();
    }
});

//side menu에 이벤트 설정
function selectMenu(index){
    if(currentSwiper!==""){
        currentSwiper.slideTo(0);   
    }
    sideOff();
    mainSwiper.slideTo(index);
}

// 초기 설정들
// 가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
// [tuesday] 처음 시작할때 무조건 위로 스와이프 방지하는 부분 삭제
$(".swiper-scrollbar").hide();
$(".home_btn").hide()

// 화면 리사이즈할때 layout 재 설정
$(window).resize(function (){
	setWidth();
    sideOff();
    if(isDetail){
        setDetailLayout();
    }
});

function setWidth() {
    mainWidth = $(window).width();
    mainHeight = $(window).height();    
    if(mainWidth>729){
    	slideWidth=648;
        $(".list_painting").css({"width": "648px", "height": "900px"});
    }else{
    	slideWidth=mainWidth*0.8;
        $(".list_painting").css({"width": slideWidth, "height": mainWidth*10/9});
    }
    if(purchaseStatus!=""){
        setPurchase();
    }
    setBox();
    setStatusPosition();
}

function setStatusPosition(){
    if($(".list_artist_btn").position()){
        var fullBreakpoint = mainHeight-$(".list_painting").position().top-$(".list_painting").height();
        if(fullBreakpoint <= 50){
            fullImage = false;
            $(".list_status_btn").removeAttr("style");
            $(".list_cancel_btn").removeAttr("style");
            $(".list_refund_btn").removeAttr("style");
            $(".list_resend_btn").removeAttr("style");
            $(".list_confirm_btn").removeAttr("style");
            $(".list_delete_btn").removeAttr("style");
            $(".list_status_sentence").removeAttr("style");
        }else{
            fullImage = true;
            $(".list_status_btn").css("bottom", fullBreakpoint+10);
            $(".list_cancel_btn").css("bottom", fullBreakpoint+10);
            $(".list_refund_btn").css("bottom", fullBreakpoint+10);
            $(".list_resend_btn").css("bottom", fullBreakpoint+10);
            $(".list_confirm_btn").css("bottom", fullBreakpoint+10);
            $(".list_delete_btn").css("bottom", fullBreakpoint+10);
            $(".list_status_sentence").css("bottom", fullBreakpoint);
        }
    }
}

// upload/posted 버튼 설정
function btnToggle(btn){
    $(btn).toggleClass("home_btn_inactive");
}

// list의 맨앞으로 되돌아가기 버튼
$(".home_btn").click(function(){
    currentSwiper.slideTo(0);
})

// 스와이프해서 메뉴창 띄우기
 function swipeToMenu(swiper, translate){
     if(translate>((mainWidth/2)-(slideWidth/3))){
         sideOn();
     }
 }

// 모바일 웹브라우져를 전체화면으로 표시
$("#fullscreen_btn").click(function(){toggleFullScreen()});

function toggleFullScreen() {
    var doc = window.document;
    var docEl = doc.documentElement;

    var requestFull = docEl.requestFullscreen || docEl.mozRequestFullScreen || docEl.webkitRequestFullScreen;
    var cancelFull = doc.exitFullscreen || doc.mozCancelFullScreen || doc.webkitExitFullscreen;

    if(!doc.fullscreenElement && !doc.mozFullScreenElement && !doc.webkitFullscreenElement) {
        requestFull.call(docEl);
        $("#fullscreen_btn").find("img").attr("src", "/ico/fullscreen_exit.png");
    }else {
        cancelFull.call(doc);
        $("#fullscreen_btn").find("img").attr("src", "/ico/fullscreen.png");
    }
}


// 업로드/리워드가 표시되는 팝업창 사이즈 설정
function setBox(){
    if(boxStatus=="upload" || boxStatus=="uploadPop"){
        if(mainHeight>=400 && mainWidth >= 288){
            $(".upload_box").height(mainHeight*0.8);
            $(".upload_box").width($(".upload_box").height()*0.72);

            if($(".upload_box").width()>mainWidth*0.8){
                $(".upload_box").width(mainWidth*0.8);
                $(".upload_box").height(mainWidth*10/9);
            }
        }    
    }else if(boxStatus=="about"){
        $(".about_wrapper").width(mainWidth*6);
        $(".about_card").width(mainWidth);
        $(".about_navi").css("left", (mainWidth/2)-(aboutIndex*140)-70);
        $(".randing_card_p_contents").height(mainHeight-200)
    }
}

// 팝업 닫기
$(".return_btn").on('click', function(){
	boxStatus = 'clickedCloseBtn';

	// 구매 정보 초기화
	closePopup();
});

$(".popup_container").click(function(){
	closePopup();
    popName = "";  // 다시 초기화
});

// 로그인 닫기
$(".account_close_btn").on('click', function(){
    if($(this).parent().hasClass("login_container")){
        history.back();
    }else if($(this).parent().hasClass("signup_container")){
        history.go(-2);
    }else if($(this).parent().hasClass("loginhelp_container")){
       history.go(-2);
    }
    $(".account_container").hide();
})

var openedAboutUploadPopup = false;

function closePopup() {
	var isPopupOpened = false;

	var openPopupContainer;

	$('.popup_container').each(function( index ) {
		if($(this).css("display") != "none") {
			isPopupOpened = true;
			openPopupContainer = $(this);
		}
	});

	if(isPopupOpened) {
		var divClass = openPopupContainer.attr('class');

		// boxStatus payment
		if (divClass.indexOf('payment_container') > -1) {
			// 구매 정보 초기화
            closePurchaseStep01();
			resetPurchase();
			purchaseStatus = "";
			openPopupContainer.hide();
			history.go(-2);
			boxStatus = "";
		} 
		else if (boxStatus == "rewardStep2") {
			history.go(-2);
		}
		else if (divClass.indexOf('reward_container') > -1
					|| divClass.indexOf('people_container') > -1
					|| divClass.indexOf('profile_container') > -1) {
			openPopupContainer.hide();
		}
		else if (divClass.indexOf('upload_container') > -1) {
			if(boxStatus == "clickedCloseBtn" && openedAboutUploadPopup == false) {
				openPopupContainer.hide();
				history.back();
			}
		} else if(divClass.indexOf('username_container') > -1) {
			openPopupContainer.hide();
		}
		else {
			history.back();
		}
	}

	openedAboutUploadPopup = false;
	boxStatus = "";
}

$(".popup_box").click(function(e){
    e.stopPropagation();
});


/**
 *   홈 페이지 데이터 리로딩
 *   그림 구매시 : My, Popular  
 *   팔로우 대상 변경 시 : Follow, 
 */
function dataReload(loadPages) {
	for (var index in loadPages) {
		eval(loadPages[index]);
	}
}

/**
 *  사이드 메뉴 언어 선택시 테이블 언어 변경 및 화면 언어 적용
 */
$(".side_menu_lang_select").change(function(event) {
	lang = $(this).val();
	exeTranslation("body", lang);
	if (userID) {
		AjaxCall.call(apiUrl + "/user/me", 
			{"language": lang}, 
			"PUT", 
			function (result) {
			}
		);
	} 
});

/**
 *  초기 이미지 로딩 완료후 사용자 이용시작
 */
$(".stopper").show();
var imgChecher = setInterval(function(){
    $(".list_painting").each(function(){
        if(!this.complete) return false;
        $(".stopper").hide();
        if(userID==""){
            $(".splash").fadeOut(3000);
        }else{
            $(".splash").fadeOut(1000);
        }
        clearInterval(imgChecher);
        setWidth();
//        if(painteeFB.isCordova()){
//        	if(StatusBar){StatusBar.backgroundColorByHexString("#505050")};
//        }
        
    })
}, 500)

/**
 *  안내문구 표출
 */
function showNotice(notice){
    $(".notice_box").show();
    $(".notice_box").html(notice);
    exeTranslation(".notice_container", lang);
    $(".notice_box").delay(2000).fadeOut(2000);
}
$(".notice_box").hide();

