var purchaseController;
var onceAboutPost = true;
var purchaseBox = $(".purchase_box");
var purchaseSentenceBox = $(".purchase_box_sentence");
var purchaseStructure;

function Purchase(purchaseType){
    this.purchaseType = purchaseType;
    this.doneBox  = $("<div>").addClass("purchase_box_select");
    this.doneIcon   = $("<img>").addClass("icon").attr("src", "ico/done.png");
    this.doneBtn    = $("<div>").addClass("purchase_done_btn").append($("<div>").addClass("purchase_btn_text").html("done ")).click(function(){
                            new CommentController(paintingId).addComment();
                        });

    this.postBox    = $("<div>").addClass("purchase_box_select");
    this.sendIcon   = $("<img>").addClass("icon").attr("src", "ico/mail_black.png");
    this.sendBtn    = $("<div>").addClass("purchase_address_btn").append($("<div>").addClass("purchase_btn_text").html("send a postcard ")).click(function(){
                            showAddress()
                        });

    this.divider        = $("<div>").addClass("purchase_box_select");
    this.dividerArtist  = $("<div>").addClass("purchase_box_artist");
    this.dividerSentence = $("<div>").addClass("purchase_box_divider").append("Published by Paintee co., Seoul, Korea | http://paintee.me");

    this.addressBox     = $("<div>").addClass("purchase_box_address");
    this.senderTitle    = $("<div>").addClass("purchase_col_title").attr("data-i18n", "purchasePop1.sender").html("sender");
    this.senderName     = $("<div>").addClass("purchase_col_name")
                            .append('<input type="text" id="senderName" name="senderName" class="purchase_input" placeholder="name">');
    this.senderInput    = $("<div>").addClass("purchase_col_input");
    this.addresseeTitle = $("<div>").addClass("purchase_col_title").attr("data-i18n", "purchasePop1.address").html("addressee");
    this.addresseeName  = $("<div>").addClass("purchase_col_name")
                            .append('<input type="text" id="receiverName" name="receiverName" class="purchase_input" placeholder="name">');
    this.addresseeInput = $("<div>").addClass("purchase_col_input")
                            .append('<input type="text" name="receiverBasicAddr" class="purchase_input postcodify_address" placeholder="address 1">')
                            .append('<input type="text" name="receiverDetailAddr" class="purchase_input" placeholder="address 2">')
                            .append('<input type="text" name="receiverCity" class="purchase_input" placeholder="city" style="width: 30%">')
                            .append('<input type="text" name="receiverZipcode" class="purchase_input postcodify_postcode" placeholder="zip code" style="width: 30%">')
                            .append('<select name="location" class="purchase_select" style="width: 30%"><option value="Korea" selected="selected">Korea</option><option value="USA">USA</option><option value="China">China</option><option value="Japan">Japan</option></select>')
    this.addressCheck   = $("<div>").addClass("purchase_col_check")
                            .append('<input type="checkbox" name="checkBasicAddr" class="purchase_input_check" checked> set as my address');

    this.purchaseBtn    = $("<div>").addClass("purchase_pay_btn").attr("id", "payment-point")
                            .append('<div class="purchase_btn_text">Payment </div>')
                            .append('<img class="icon" src="ico/payment.png">');
    this.purchaseTueBtn = $("<div>").addClass("purchase_pay_btn").attr("id", "payment-tuesday")
                            .append('<div class="purchase_btn_text">Payment </div>')
                            .append('<img class="icon" src="ico/payment.png">');
}

Purchase.prototype = {
    buildDone : function(){
        this.doneBtn.append(this.doneIcon);
        this.doneBox.append(this.doneBtn);

        return this.doneBox;
    },
    buildPost   : function(){
        this.sendBtn.append(this.sendIcon);
        this.postBox.append(this.sendBtn);
        return this.postBox;
    },
    buildDivider : function(){
        this.dividerArtist.html("painted by <b>"+purchaseController.artistName+"</b>");
        this.divider.append(this.dividerArtist);
        this.divider.append(this.dividerSentence);

        return this.divider;
    },
    buildAddress : function(){
        this.addressBox.append(this.senderTitle);
        this.addressBox.append(this.senderName);
        this.addressBox.append(this.senderInput);
        this.addressBox.append(this.addresseeTitle);
        this.addressBox.append(this.addresseeName);
        this.addressBox.append(this.addresseeInput);
        this.addressBox.append(this.addressCheck);
        if(this.purchaseType=="TUESDAY"){
            this.addressBox.append(this.purchaseTueBtn);
        }else{
            this.addressBox.append(this.purchaseBtn);
        }

        return this.addressBox;
    }
}

// 구매화면으로 이동
function purchase(paintingId, artistName, type, purchaseType) {

	if (userID == "") {
		showLogin();
		return ;
	}
	this.paintingId = paintingId;
    this.artistName = artistName;
    this.purchaseType = purchaseType;

    initPurchasePop(type, purchaseType);
}

function initPurchasePop(type, purchaseType) {
	replaceHistory({"call": "purchasePop"});
    addHistory({"call": "purchaseStep1"});

    purchaseStructure = new Purchase(purchaseType);
    purchaseStatus = type;

    if(purchaseStatus=="post"){
        showPost();
    }else if(purchaseStatus=="comment"){
        showComment();
    }

	$(".purchase_container").show();


    setWidth();

    // 다국어 처리
    exeTranslation('.base_position', lang);
    $(".sentence_textarea").attr("placeholder", $.i18n.t('purchasePop1.sentence'))
}

function initPurchaseAddress(result){
	purchaseController.basicAddr  = result.user.basicAddr;
	purchaseController.detailAddr = result.user.detailAddr;

    // 주소설정...
    $("[name=senderName]").val(userInfo.name);
    $("[name=location]").val(result.user.location ? result.user.location : 'Korea');
    $("[name=receiverBasicAddr]").val(result.user.basicAddr);
    $("[name=receiverDetailAddr]").val(result.user.detailAddr);
    $("[name=receiverZipcode]").val(result.user.zipcode);
    setPostUI($("[name=location]").val());

    // 기존 설정된 이벤트 제거
    $(".purchase_pay_btn").off("click");
    $("#payment-point").click(function() {
    	payment(result.user.serviceCnt, "point");
    });
    $("#payment-tuesday").click(function() {
    	payment(result.user.serviceCnt, "tuesday");
    });

    // 우편번호 입력박스 키이벤트 등록
    $("[name=receiverZipcode]").keydown(function (event) {
    	limitNumber(event);
    }).keyup(function (event) {
    	limitNumber(event);
    });


    $("[name=location]").change(function(e){
        setPostUI($("[name=location]").val());
        e.stopPropagation();
    });

}

function setPurchase(){
    if(purchaseStatus=="comment"){
        purchaseBox.css("top", mainHeight-250);
        purchaseBox.animate({top: mainHeight-270}, 200);
    }else if(purchaseStatus=="post"){
        purchaseBox.css("top", mainHeight-250);
        purchaseBox.animate({top: mainHeight-270}, 200);
    }else if(purchaseStatus=="address"){
        if(mainHeight>=660){
            purchaseBox.css("top", mainHeight-620);
        }else{
            purchaseBox.css("top", 40);
        }
    }
}

$(".sentence_textarea").focusin(function(){$(".character_counter").css("opacity", 1)});
$(".sentence_textarea").focusout(function(){$(".character_counter").css("opacity", 0)});

function showPost(){
    purchaseStatus="post";
    purchaseBox.find(".purchase_box_select").remove();
    purchaseBox.append(purchaseStructure.buildPost());
    setPurchase();
};

function showComment(){
    purchaseStatus="comment";
    purchaseBox.find(".purchase_box_select").remove();
    purchaseBox.append(purchaseStructure.buildDone());
    setPurchase();
};

function showAddress(){
    purchaseController = new PurchaseController(paintingId, artistName, purchaseType);
    purchaseController.purchasePopInfo();

    purchaseStatus="address";
    $(".character_counter").css("opacity", 0);
    purchaseBox.find(".purchase_box_select").remove();
    purchaseBox.append(purchaseStructure.buildDivider());
    purchaseBox.append(purchaseStructure.buildAddress());
    setPurchase();
}

$(".purchase_container").click(function(){
	// 구매 팝업 닫기
	closePurchaseStep01();
    // 뒤로가기 강제 호출
    history.back();
});

function closePurchaseStep01() {
    $(".purchase_container").hide();
    purchaseBox.find(".purchase_box_select").remove();
    purchaseBox.find(".purchase_box_address").remove();
    delete purchaseStructure;

    purchaseStatus = "";
    boxStatus = "";
    // 입력데이터 초기화
    resetPurchase();
}

$(".purchase_box").click(function(e){
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
var postPopOn = false;

function setPostUI(type) {
	if (type == 'Korea') {
		// 기본 주소 선택시
		$("[name=receiverCity]").attr("disabled", "disabled");
        $("[name=receiverCity]").addClass("input_disable")
		$("[name=receiverZipcode]").attr("readOnly", "readOnly");
		$("[name=receiverBasicAddr]").attr("readOnly", "readOnly");
		$("[name=receiverBasicAddr]").focus(function () {
			if(!postPopOn){
                postPopOn = true;
                $(function() { $("[name=receiverBasicAddr]").postcodifyPopUp(); });
    //			execDaumPostcode('purchase', 'receiverZipcode', 'receiverBasicAddr')
            }});
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

    var enter = getEnterCount($(this));
	if (enter > 5) {
        $(".line_counter").html($.i18n.t('alert.purchase.limitEnterCount'));
	}else{
        $(".line_counter").empty();
    }
});

$("[name=sentence]").blur(function () {
	var enter = getEnterCount($("[name=sentence]"));
	if (enter > 5) {
		alert($.i18n.t('alert.purchase.limitEnterCount'));
	}
});

//결재화면
function payment(serviceCnt, option) {

	addHistory({"call": "purchaseStep2"});

	// 구매입력 항목 체크
	if (!validPurchase()) { return false; }

    purchaseStatus = "";
    boxStatus = "payment";
    $(".purchase_container").hide();

    $(".payment_container").show();
    initPayment(serviceCnt, option);
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
    if($("[name=checkBasicAddr]").prop("checked")){
        if (purchaseController.basicAddr != $("[name=receiverBasicAddr]").val().trim()) {
            change = true;
        }
        if (purchaseController.detailAddr != $("[name=receiverDetailAddr]").val().trim()) {
            change = true;
        }
    }
	if (change) {
			purchaseController.changeAddr = "Y";
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
    this.payByPaypal  =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_paypal.png'><div class='purchase_btn_text'>by Credit Card </div>")
                        .click(function(){paymentByPaypal()});
    this.payByAndroid =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_android.png'><div class='purchase_btn_text'>by Google Account </div>")
                        .click(function(){paymentByAndroid()});
    this.payByiOS     =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_ios.png'><div class='purchase_btn_text'>by Apple Account </div>")
                        .click(function(){paymentByiOS()});
    this.payByReward  =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/attach_money.png'><div class='purchase_btn_text'>by Your Reward </div>")
                        .click(function(){paymentByReward()});
    this.payByCode    =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_promotion.png'><div class='purchase_btn_text'>by Promotion Code </div>")
                        .click(function(){paymentByCode()});
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
    setReturn   : function(){
        $(this.bottom).append(this.editAddress);
    },
    /**
     *  [payment] payment 버튼 선택시 선택 옵셥을 표출하는 함수
     *  상황에 따라 다른 옵션이 표출되어야 함 ex. 안드로이드일 경우만, 안드로이드 인앱 결제 표시
     */
    setPay   : function(){
        $(this.bottom).empty();
        $(this.bottom).height(170);
        $(this.bottomMargin).height(170);
        $(this.bottom).append($("<div>").addClass("payby_btn").html($.i18n.t('purchasePop1.select')));
        $(this.bottom).append(this.payByPaypal);
        if(checkPaymentPlatform()=="android"){
            $(this.bottom).append(this.payByAndroid);
        }else if(checkPaymentPlatform()=="iOS"){
            $(this.bottom).append(this.payByiOS);
        }
        if(checkPaymentReward){
            $(this.bottom).append(this.payByReward);
        }
        $(this.bottom).append(this.payByCode);
    },
    buildPayment : function(){
        $(".payment_box").append(this.title);
        $(".payment_box").append(this.contents);
        $(".payment_box").append(this.bottomMargin);
        $(".payment_box").append(this.bottom);
    }
}

function initPayment(serviceCnt, option){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");

    if(option == "tuesday"){
        var contents = "<span data-i18n='[html]purchasePop1.tuesday'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='ico/payment.png'></div>");
        payment.buildPayment();
        $(".payment_btn").click(function(){
                // [tuesday] 임시로 serviceCnt가 감소하지 않게 처리 필요
                purchaseController.addPurchase(serviceCnt+1);
                showPurchaseSpinner();
        })
    }else if (serviceCnt <= 0) {
         var contents = "<span class='reward_money'>" + serviceCnt + "/3</span><br>"
                     + "<span data-i18n='[html]purchasePop1.alert'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div>");
        payment.buildPayment();
    }else if(serviceCnt > 5){
        var contents = "<span data-i18n='[html]purchasePop1.contents1'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='ico/payment.png'></div>");
        payment.buildPayment();
        $(".payment_btn").click(function(){
        /**
         *  [payment] payment 버튼 선택시 선택 옵셥을 표출
         */
                payment.setPay();
        })
    }else{
        var contents = "<span class='reward_money'>" + serviceCnt + "/3</span><br>"
                     + "<span data-i18n='[html]purchasePop1.contents'></span>"
        payment.setContents(contents);
        payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/create.png'><div class='purchase_btn_text' onclick='history.back();'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='ico/payment.png'></div>");
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

/**
 *  [payment] paypal 옵션 선택
 */

function paymentByPaypal(){
    alert("Paypal로 결제되는 과정이 진행됩니다.");
    purchaseController.addPurchase(3);
    showPurchaseSpinner();
}

/**
 *  [payment] 안드로이드 인앱 옵션 선택
 */

function paymentByAndroid(){
    alert("안드로이드 인앱으로 결제되는 과정이 진행됩니다.");
    purchaseController.addPurchase(3);
    showPurchaseSpinner();
}

/**
 *  [payment] iOS 인앱 옵션 선택
 */

function paymentByiOS(){
    alert("iOS 입앱으로 결제되는 과정이 진행됩니다.");
    purchaseController.addPurchase(3);
    showPurchaseSpinner();
}

/**
 *  [payment] 리워드 옵션 선택
 */

function paymentByReward(){

    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");

    var reward = 75; // 현재 내가 받을 수 있는 리워드
    var contents1 = "<span data-i18n='[html]purchasePop1.reward1'></span>"
    var contents2 = "<span data-i18n='[html]purchasePop1.reward2'></span>"
    payment.setContents(contents1 +"<b>"+ reward +"</b>"+ contents2);
    payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/keyboard_arrow_left_black.png'><div class='purchase_btn_text' onclick='paymentCancle();'>Cancel</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='ico/attach_money.png'></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
            alert("리워드 차감 과정이 진행됩니다.");
            purchaseController.addPurchase(3);
            showPurchaseSpinner();
    })

    delete payment;
    exeTranslation('.base_position', lang);

}

/**
 *  [payment] 프로모션 코드 옵션 선택
 */

function paymentByCode(){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");

    var contents = "<span data-i18n='[html]purchasePop1.promotionCode'></span>"
    var form = '<input type="text" class="purchase_input">'
    payment.setContents(contents + form);
    payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><img class='icon' src='ico/keyboard_arrow_left_black.png'><div class='purchase_btn_text' onclick='paymentCancle();'>Cancel</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><img class='icon' src='ico/attach_money.png'></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
            alert("프로모션 코드 확인 및 사용처리 과정이 진행됩니다.");
            purchaseController.addPurchase(3);
            showPurchaseSpinner();
    })

    delete payment;
    exeTranslation('.base_position', lang);

}

function paymentCancle(){
    var bottom = $(".payment_bottom")
    var payByPaypal  =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_paypal.png'><div class='purchase_btn_text'>by Credit Card </div>")
                        .click(function(){paymentByPaypal()});
    var payByAndroid =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_android.png'><div class='purchase_btn_text'>by Google Account </div>")
                        .click(function(){paymentByAndroid()});
    var payByiOS     =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_ios.png'><div class='purchase_btn_text'>by Apple Account </div>")
                        .click(function(){paymentByiOS()});
    var payByReward  =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/attach_money.png'><div class='purchase_btn_text'>by Your Reward </div>")
                        .click(function(){paymentByReward()});
    var payByCode    =$("<div>").addClass("payby_btn").append("<img class='icon' src='ico/pay_promotion.png'><div class='purchase_btn_text'>by Promotion Code </div>")
                        .click(function(){paymentByCode()});

    bottom.empty();
    bottom.height(170);
    bottom.height(170);
    bottom.append($("<div>").addClass("payby_btn").html($.i18n.t('purchasePop1.select')));
    bottom.append(payByPaypal);
    if(checkPaymentPlatform()=="android"){
        bottom.append(payByAndroid);
    }else if(checkPaymentPlatform()=="iOS"){
        bottom.append(payByiOS);
    }
    if(checkPaymentReward){
        bottom.append(payByReward);
    }
    bottom.append(payByCode);

    exeTranslation('.base_position', lang);

}

/**
 *  [payment] 안드로이드/iOS 플랫홈 체크
 */

function checkPaymentPlatform(){

    return "android";
}

/**
 *  [payment] Reward가 $2 이상인지 확인
 */

function checkPaymentReward(){

    return true;
}



function PurchaseController(paintingId, artistName, purchaseType) {
	if(purchaseType == null || purchaseType == '')
		purchaseType = 'CASH';

	this.paintingId = paintingId;
	this.artistName = artistName;
	this.purchaseType = purchaseType;
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
		initPurchaseAddress(result);
	},
	addPurchase: function (serviceCnt) {
		var controller = this;
		var data = {
			userId: userID,
			paintingId: this.paintingId,
            artistName: this.artistName,
            purchaseType: this.purchaseType,
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

        var popClose = false;
		if(result.errorNo == '500') {
			alert($.i18n.t('alert.purchase.notFreeTuesdayPaint'));
			popClose = true;
		} else if(result.errorNo == '501') {
			alert($.i18n.t('alert.purchase.alreadyPostedTuesdayPaint'));
			popClose = true;
		}
		
		if (popClose) {
			$(".popup_container").hide();
			$(".payment_container").hide();
			return;	
		}

		
		// 기존 입력 내용 지우기
//		resetPurchase();
		closePurchaseStep01();
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
    payment.setBottom("<div class='popup_btn payment_btn'><div class='purchase_btn_text'>Go to my history </div><img class='icon' src='ico/person_black.png'></div>");
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
        selectMenu(4);
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

function CommentController(paintingId) {
	this.paintingId = paintingId;
}

CommentController.prototype = {
	addComment: function () {
		if (!userInfo) {
			alert($.i18n.t('alert.common.notLogin'));
			return;
		}

		// 구매입력 항목 체크
		if (!validComment()) { return false; }

		var controller = this;
		var data = {
				sentence: $("[name=sentence]").val()
			};
		AjaxCall.call(apiUrl + "/painting/"+controller.paintingId+"/comment",
			data,
			"POST",
			function (result) {
				controller.addCommentRes(result);
			}
		);
	},
	addCommentRes: function (result) {
		dataReload(["initMy();"]);
		$("[data-comment='" + this.paintingId + "']").html(parseInt($("[data-comment='" + this.paintingId + "']").html()) + 1);
		closePurchaseStep01();
//		refreshDetailPosted();
        loadDetail(this.paintingId, color, colorDark, 'comment');
	},
	delComment: function (listData) {
		var controller = this;
		AjaxCall.call(apiUrl + "/painting/"+listData.seq+"/comment",
				{paintingId: listData.paintingId},
				"DELETE",
				function (result) {
					controller.delCommentRes(result);
				}
		);
	},
	delCommentRes: function (result) {
		dataReload(["initMy();", "initFollow();", "initPopular();", "initNew();"]);
		alert($.i18n.t('alert.common.processDelete'));
	}
}

function validComment() {
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
	return true;
}
