var purchaseController;
var onceAboutPost = true;
// 구매화면으로 이동
function purchase(paintingId, artistName) {
	
	if (userID == "") {
		showLogin();
		return ;
	} 
	this.paintingId = paintingId;
	
	// 구매 팝업 정보 조회
	purchaseController = new PurchaseController(paintingId, artistName);
	purchaseController.purchasePopInfo();
}

function initPurchasePop(result) {
//	console.log(JSON.stringify(result));
	replaceHistory({"call": "purchasePop"});
    addHistory({"call": "purchaseStep1"});
    
	purchaseController.basicAddr  = result.user.basicAddr;
	purchaseController.detailAddr = result.user.detailAddr;
	 
	$(".purchase_container").show();
    
    // 주소설정...
    $("[name=senderName]").val(userInfo.name);
    $("[name=location]").val(result.user.location ? result.user.location : 'Korea');
    $("[name=receiverBasicAddr]").val(result.user.basicAddr);
    $("[name=receiverDetailAddr]").val(result.user.detailAddr);
    $("[name=receiverZipcode]").val(result.user.zipcode);
    
    // 기존 설정된 이벤트 제거
    $(".purchase_pay_btn").off("click");
    $(".purchase_pay_btn" ).click(function() {
    	payment(result.user.serviceCnt);
    });

    // 우편번호 입력박스 키이벤트 등록
    $("[name=receiverZipcode]").keydown(function (event) {
    	limitNumber(event);
    })
    .keyup(function (event) {
    	limitNumber(event);
    });
    
    purchaseStatus = "sentence";
    setWidth();
    
    setPostUI($("[name=location]").val());
    
    // 다국어 처리
    exeTranslation('.base_position', lang);
}

function setPurchase(){
    if(mainWidth<500){
        purchaseWidth = mainWidth*0.9;
        if(purchaseStatus!="address"){
            $(".purchase_box").css("left", mainWidth*0.1);
            $(".purchase_next_btn").show();   
        }else{
            $(".purchase_box").css("left", -purchaseWidth);
            $(".purchase_prev_btn").show();
        }
    }else if(mainWidth<950){
        purchaseWidth = 450;
        if(purchaseStatus!="address"){
            $(".purchase_box").css("left", mainWidth*0.1);
            $(".purchase_next_btn").show();
        }else{
            $(".purchase_box").css("left", mainWidth*0.9-900);
            $(".purchase_prev_btn").show();
        }
    }else{
        purchaseWidth = 450;
        $(".purchase_box").css("left", (mainWidth-900)/2);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").hide();
    }
    $(".purchase_box").width(purchaseWidth*2);
    $(".purchase_box_left").width(purchaseWidth);            
    $(".purchase_box_right").width(purchaseWidth);
}

$(".purchase_next_btn").click(function(){
    purchaseStatus="address";
    if(mainWidth<500){
        $(".purchase_box").css("left", -purchaseWidth);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").show();
    }else if(mainWidth<950){
        $(".purchase_box").css("left", mainWidth*0.9-900);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").show();
    }
});

$(".purchase_prev_btn").click(function(){
    purchaseStatus="sentence";
    if(mainWidth<500){
        purchaseWidth = mainWidth*0.9;
        $(".purchase_box").css("left", mainWidth*0.1);
        $(".purchase_next_btn").show();
        $(".purchase_prev_btn").hide();
    }else if(mainWidth<950){
        purchaseWidth = 450;
        $(".purchase_box").css("left", mainWidth*0.1);
        $(".purchase_next_btn").show();
        $(".purchase_prev_btn").hide();
    }
});

$(".purchase_container").click(function(){
	// 구매 팝업 닫기
	closePurchaseStep01();
    // 뒤로가기 강제 호출
    history.back();
});

function closePurchaseStep01() {
    $(".purchase_container").hide();
    purchaseStatus = "";
    boxStatus = "";
    // 입력데이터 초기화
    resetPurchase();
}

$(".purchase_box").click(function(e){
    e.stopPropagation();
});

$("[name=location]").change(function(e){
	setPostUI($("[name=location]").val());
	e.stopPropagation();
});


function callback(searchModule){
	replaceHistory({"call": "purchasePop"});
    addHistory({"call": "purchaseStep1"});
    
    if($(".detail").css("display") == "block") {
        replaceHistory({"call": "detailPop"});
        addHistory({"call": "dummy"});
    }
    if(searchModule == 'purchase') {
        // 구매정보 히스트리
    	replaceHistory({"call": "purchasePop"});
        addHistory({"call": "purchaseStep1"});
    } else if(searchModule == 'profile') {

    }
};

function setPostUI(type) {
	if (type == 'Korea') {
		// 기본 주소 선택시 
		$("[name=receiverCity]").attr("disabled", "disabled");
        $("[name=receiverCity]").addClass("input_disable")
		$("[name=receiverZipcode]").attr("readOnly", "readOnly");
		$("[name=receiverBasicAddr]").attr("readOnly", "readOnly");
		$("[name=receiverBasicAddr]").focus(function () {
			
			$(function() { $("[name=receiverBasicAddr]").postcodifyPopUp(); });
//			execDaumPostcode('purchase', 'receiverZipcode', 'receiverBasicAddr')
			});
	} else {
		// 주소에 설정된 이벤트 삭제
		$("[name=receiverBasicAddr]").off();
		$("[name=receiverCity]").attr("disabled", false);
        $("[name=receiverCity]").removeClass("input_disable")
		$("[name=receiverZipcode]").attr("readOnly", false);		
		$("[name=receiverBasicAddr]").attr("readOnly", false);
	}
}

// 구매시의 한마디 
$("[name=sentence]").keyup(function () {
    // 남은 글자 수를 구합니다.
    var inputLength = getCharCount($(this).val());    
    var remain = 200 - inputLength;

    $('#pSentenceCount').html(inputLength);
    if (remain >= 0) {
        $('#pSentenceCount').css('color', 'black');
    } else {
        $('#pSentenceCount').css('color', 'red');
    }
});

$("[name=sentence]").blur(function () {
	var enter = getEnterCount($("[name=sentence]"));
	if (enter > 5) {
		alert($.i18n.t('alert.purchase.limitEnterCount'));
	}
});

//결재화면
function payment(serviceCnt) {

	addHistory({"call": "purchaseStep2"});
	
	// 구매입력 항목 체크
	if (!validPurchase()) { return false; }
	
    purchaseStatus = "";
    boxStatus = "payment";
    $(".purchase_container").hide();
    $(".payment_container").show();
    initPayment(serviceCnt);
    setBox();
}

function validPurchase() {
	if ($("[name=sentence]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptySentence'));
		$("[name=sentence]").focus();
		return false;
	}
	
	if (getCharCount($("[name=sentence]").val()) > 200) {
		alert($.i18n.t('alert.purchase.exceedSentence'));
		$("[name=sentence]").focus();
		return false;
	}
	
	var enter = getEnterCount($("[name=sentence]"));
	if (enter > 5) {
		alert($.i18n.t('alert.purchase.limitEnterCount'));
		$("[name=sentence]").focus();
		return false;
	}
	
	if ($("[name=senderName]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptySenderName'));
		$("[name=senderName]").focus();
		return false;
	}
	if ($("[name=receiverName]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptyReceiverName'));
		$("[name=receiverName]").focus();
		return false;
	}
	if ($("[name=receiverBasicAddr]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptyAddress'));
		$("[name=receiverBasicAddr]").focus();
		return false;
	}
	if ($("[name=location]").val() != 'Korea') {
		if ($("[name=receiverCity]").val().trim().length == 0) {
			alert($.i18n.t('alert.purchase.emptyCity'));
			$("[name=receiverCity]").focus();
			return false;
		}
	}
	if ($("[name=receiverZipcode]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptyZipcode'));
		$("[name=receiverZipcode]").focus();
		return false;
	}
	
	if (!chkNum($("[name=receiverZipcode]").val())) {
		alert($.i18n.t('alert.purchase.inputOnlyNumber'));
		$("[name=receiverZipcode]").focus();
		return false;
	}
	var change = false;
	if (purchaseController.basicAddr != $("[name=receiverBasicAddr]").val().trim()) {
		change = true;
	}
	if (purchaseController.detailAddr != $("[name=receiverDetailAddr]").val().trim()) {
		change = true;
	}
	if (change) {
		if (confirm($.i18n.t('alert.purchase.changeAddress'))) {
			purchaseController.changeAddr = "Y";
		}
	}
	return true;
}

function Payment(){
    this.title      = $("<div>").addClass("payment_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("payment_contents").addClass("popup_contents");
    this.bottomMargin    = $("<div>").addClass("popup_margin_bottom");
    this.bottom     = $("<div>").addClass("payment_bottom").addClass("popup_bottom");
    this.sociconFacebook =$("<img id='fac_share' src='ico/social_facebook.png'>").addClass("icon").addClass("social_img");
    this.sociconTwitter  =$("<img id='twi_share' src='ico/social_twitter.png'>").addClass("icon").addClass("social_img");
    this.sociconUrl      =$("<img id='url_share' src='ico/social_url.png'>").addClass("icon").addClass("social_img");
}

Payment.prototype = {
    setTitle    : function(title){
        $(this.title).html(title);
    },
    setContents : function(contents){
        $(this.contents).html(contents);
    },
    setBottom   : function(bottom){
        $(this.bottom).html(bottom);
    },
    buildPayment : function(){
        $(".payment_box").append(this.title);
        $(".payment_box").append(this.contents);
        $(".payment_box").append(this.bottomMargin);
        $(".payment_box").append(this.bottom);
    }
}

function initPayment(serviceCnt){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");
    if (serviceCnt <= 0) {
         var contents = "<span class='reward_money'>" + serviceCnt + "/3</span><br>"
                     + "<span data-i18n='[html]purchasePop1.alert'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='/ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div>");
        payment.buildPayment();
    }else{
        var contents = "<span class='reward_money'>" + serviceCnt + "/3</span><br>"
                     + "<span data-i18n='[html]purchasePop1.contents'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='/ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='/ico/payment.png'></div>");
        payment.buildPayment();
        $(".payment_btn").click(function(){
                purchaseController.addPurchase(serviceCnt);
                showPurchaseSpinner();
        })
    }
    delete payment;
    
    // 다국어 처리
    exeTranslation('.base_position', lang);
}

function showPurchaseSpinner(){
    $(".payment_btn").html("<div class='purchase_btn_text'>wait </div><img src='spinner.png' class='spinner'>");
    $(".stopper").show();
}

function PurchaseController(paintingId, artistName) {
	this.paintingId = paintingId;
	this.artistName = artistName;
	this.basicAddr;
	this.detailAddr;
	this.changeAddr = 'N';
}

PurchaseController.prototype = {
	purchasePopInfo: function () {
		var controller = this;
		AjaxCall.call(apiUrl + "/purchasePopInfo?paintingId=" + controller.paintingId, 
			"", 
			"GET", 
			function (result) {
				// 해당 그림이 정상 상태가 이닐경우
				if (result.errorNo == "100") {
					alert($.i18n.t('alert.common.delPainting'));
					return;	
				}
				controller.purchasePopInfoRes(result);			
			}
		);		
	},
	purchasePopInfoRes: function (result) {
		if (result.count == 0 && onceAboutPost == true) {
			showAboutPost();
			onceAboutPost = false;
		}
		initPurchasePop(result);
	},
	addPurchase: function (serviceCnt) {
		var controller = this;
		var data = {
			userId: userID,
			paintingId: this.paintingId,
			sentence: $("[name=sentence]").val(),
			privateAt: ($("[name=privateAt]").prop("checked")) ? "Y" : "N",
			receiverBasicAddr: $("[name=receiverBasicAddr]").val(),
			receiverDetailAddr: $("[name=receiverDetailAddr]").val(),
			receiverZipcode: $("[name=receiverZipcode]").val(),
			receiverCity: $("[name=receiverCity]").val(),
			receiverName: $("[name=receiverName]").val(),
			senderName: $("[name=senderName]").val(),
			location: $("[name=location]").val(),
			purchaseStatus: "1",
			changeAddr: controller.changeAddr,
			serviceCnt: serviceCnt
		};
		
		AjaxCall.call(apiUrl + "/purchase", 
			data, 
			"POST", 
			function (result) {
				controller.addPurchaseRes(result);			
			}
		);
	},
	addPurchaseRes: function (result) {
        // 스피너 화면 중지
		$(".stopper").hide();
	    
		// 기존 입력 내용 지우기
		resetPurchase();
		completePayment(result);
    	dataReload(["initMy();", "initPopular();"]);
	},
	cancelPurchase: function (listData) {
		var controller = this;
		var data = {
			seq: listData.seq,
			userId: listData.artistId,
			purchaseStatus: "3", // 환불 요청
			paintingId: listData.paintingId
		};
		
		AjaxCall.call(apiUrl + "/cancelPurchase", 
			data, 
			"POST", 
			function (result) {
				controller.cancelPurchaseRes(result, listData);			
			}
		);
	},
	cancelPurchaseRes: function (result, listData) {
		$("#exeBtn" + listData.seq).off("click");
		$("#exeBtn" + listData.seq).removeClass("list_status_preparing")
				                   .addClass("list_status_refund")
				                   .html("refund")
				                   .click(function () {
				                	   showRefund(this, listData);
					       		   }); 
		alert($.i18n.t('alert.purchase.processCancel'));
        initMy();
	},
	resendPurchase: function (listData) {
		var controller = this;
		var data = {
			seq: listData.seq,
			purchaseStatus: "4" // 재발송 요청
		};
		
		AjaxCall.call(apiUrl + "/resendStatusPurchase", 
				data, 
				"POST", 
				function (result) {
					controller.resendPurchaseRes(result, listData.seq);			
				});
	},
	resendPurchaseRes: function (result, seq) {
		$("#exeBtn" + seq).remove();
		alert($.i18n.t('alert.purchase.processResend'));
        initMy();
	},
	cancelRefundPurchase: function (listData) {
		var controller = this;
		var data = {
				seq: listData.seq,
				purchaseStatus: "1" // 요청
		};
		AjaxCall.call(apiUrl + "/cancelRefundPurchase", 
				data, 
				"POST", 
				function (result) {
			controller.cancelRefundPurchaseRes(result, listData);			
		});
	},
	cancelRefundPurchaseRes: function (result, listData) {
		$("#exeBtn" + listData.seq).off("click");
		$("#exeBtn" + listData.seq).removeClass("list_status_refund")
				                   .addClass("list_status_preparing")
				                   .html("preparing")
				                   .click(function () {
				                	   showCancel(this, listData);
					       		   }); 
		alert($.i18n.t('alert.purchase.processCancelRefund'));
        initMy();
	},
	completePurchase: function (listData) {
		var controller = this;
		var data = {
				seq: listData.seq,
				purchaseStatus: "99" // 완료
		};
		
		AjaxCall.call(apiUrl + "/completeStatusPurchase", 
				data, 
				"POST", 
				function (result) {
			controller.completePurchaseRes(result, listData);			
		});
	},
	completePurchaseRes: function (result, listData) {
		$("#exeBtn" + listData.seq).off("click");
		$("#exeBtn" + listData.seq).removeClass("list_status_sended")
				                      .addClass("list_status_done")
				                      .html("delete")
				                      .click(function () {
					       				  new PurchaseController().delStatusPurchase(listData); 
					       			  }); 
		//alert($.i18n.t('alert.purchase.processComplete'));
        initMy();
	},
	delStatusPainting: function (listData) {
		var controller = this;
		var data = {
			seq: listData.seq,
			artistId: listData.artistId,
			paintingStatus: "D" // 삭제 요청
		};
		
		AjaxCall.call(apiUrl + "/delStatusPainting", 
				data, 
				"POST", 
				function (result) {
					controller.delStatusPaintingRes(result);			
				});
	},
	delStatusPaintingRes: function (result) {
		dataReload(["initMy();", "initFollow();", "initPopular();", "initNew();"]);
		alert($.i18n.t('alert.common.processDelete'));
	},
	delStatusPurchase: function (listData) {
		var controller = this;
		var data = {
			seq: listData.seq,
			userId: listData.artistId,
			purchaseStatus: "7" // 삭제 요청
		};
		AjaxCall.call(apiUrl + "/delStatusPurchase", 
				data, 
				"POST", 
				function (result) {
					controller.delStatusPurchaseRes(result);			
				});
	},
	delStatusPurchaseRes: function (result) {
		dataReload(["initMy();", "initFollow();", "initPopular();"]);
		alert($.i18n.t('alert.common.processDelete'));
	}
};

// 구매 입력 내용 지우기
function resetPurchase() {
	$("[name=privateAt]").prop("checked", false),
	$("[name=sentence]").val("");
	$("[name=receiverBasicAddr]").val("");
	$("[name=receiverDetailAddr]").val("");
	$("[name=receiverZipcode]").val("");
	$("[name=receiverCity]").val("");
	$("[name=receiverName]").val("")
	$("[name=senderName]").val("");
	$("[name=location]").val("");
	$('#pSentenceCount').html(0);
}

function completePayment(result){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Thanks!");
    payment.setContents("<span data-i18n='[html]purchasePop2.contents'></span><br>");
    payment.contents.append(payment.sociconFacebook.css("color", "rgb(80,80,80)"));
    payment.contents.append(payment.sociconTwitter.css("color", "rgb(80,80,80)"));
    payment.contents.append(payment.sociconUrl.css("color", "rgb(80,80,80)"));
    payment.setBottom("<div class='popup_btn payment_btn'><div class='purchase_btn_text'>Go to my history </div><img class='icon' src='/ico/person_black.png'></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
		
    	$(".popup_container").hide();
    	$(".payment_container").hide();
		
		if ($(".detail").css("display") == "block") {
		    $(".detail").animate({top: 200, opacity: 0}, 200, "linear", function(){
		    	$(".detail").empty();
		    	$(".detail").hide().css("top", 0);
		    	delete detailStructure;
		    	delete detailSwiper;
		    });
		    isDetail = false; 
		}
		
        selectMenu(3);
        mySwiper.slideTo(1);
    });
    
    var data = {name: purchaseController.artistName, page: purchaseController.paintingId, fileId: result.fileId};
    $("#fac_share").click(function() {
    	shareSocial($.extend({type: "facebook"}, data));
    });
    $("#twi_share").click(function() {
    	shareSocial($.extend({type: "twitter"}, data));
    });
    $("#url_share").click(function() {
    	urlCopy(data);
    });
    
    delete payment;
    
    // 다국어 처리
    exeTranslation('.base_position', lang);    
}
