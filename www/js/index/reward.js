var rewardController = null;
// 리워드화면
function reward(){
    boxStatus = "reward";
    $(".reward_container").show();
    
    // 리워드 정보 조회
    rewardController = new RewardController()
    rewardController.getRewardInfo();
}

function Reward(){
    this.title      = $("<div>").addClass("reward_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("reward_contents").addClass("popup_contents");
    this.bottomMargin    = $("<div>").addClass("popup_margin_bottom");
    this.bottom     = $("<div>").addClass("reward_bottom").addClass("popup_bottom");
}

Reward.prototype = {
    setTitle    : function(title){
        $(this.title).html(title);
    },
    setContents : function(contents){
        $(this.contents).html(contents);
    },
    setBottom   : function(bottom){
        $(this.bottom).html(bottom);
    },
    buildUpload : function(){
        $(".reward_box").append(this.title);
        $(".reward_box").append(this.contents);
        $(".reward_box").append(this.bottomMargin);
        $(".reward_box").append(this.bottom);
    }
}

function initReward(){
	
	replaceHistory({"call": "rewardPop"});
    addHistory({"call": "rewardStep1"});
    
	var data = rewardController.result;
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward");
    /*
     * 리워드 첫페이지의 안내 문구 수정 "지금 Reward를 신청하면 남은 $00 에 수수료 $5가 제외된 금액을 받을 수 있습니다." ->  
     * 지금 Reward를 신청하면 수수료 $5가 제외된 $00을 받을 수 있습니다."
     * 
     * 리워드 최소금액이 안될 경우( $10 )
     * - "리워드가 $10 이상일 때 리워드를 신청할 수 있습니다."
     */
    var html =  "<span data-i18n='[html]rewardPop.content4'></span>" + 
				"<b>$" + (data.reward.remainMoney - 5) + " </b>" + 
				"<span data-i18n='[html]rewardPop.content5'></span><br>";
    if (data.reward.remainMoney < 10) {
    	html = "<span data-i18n='[html]rewardPop.content6'></span><br>";
    }
    reward.setContents(
    		"<span data-i18n='[html]rewardPop.content1'></span>" + 
    		data.reward.sellCount + 
    		"<span data-i18n='[html]rewardPop.content2'></span><br>" + 
    		"<span class='reward_money'>$" + data.reward.earnTotalMoney + " </span>" + 
    		"<span data-i18n='[html]rewardPop.content3'></span>" +
    		html);
    
    reward.setBottom("<div class='popup_cancle_btn reward_history_btn'><img class='icon' src='ico/playlist_add_check.png'><div class='purchase_btn_text'>History</div></div><div class='popup_btn reward_btn'><div class='purchase_btn_text'>Get reward now </div><img class='icon' src='ico/attach_money.png'></div>");
    reward.buildUpload();
    $(".reward_history_btn").click(function(){
    	rewardController.getRewardHistory();
    });
    $(".reward_btn").click(function(){
        checkReward();
    });
    delete reward;
    exeTranslation('.base_position', lang);
}

function checkReward(){
	var data = rewardController.result;
	if (data.reward.requestCount > 0) {
		alert($.i18n.t('alert.reward.existRequest'));
		return;
	}
	
	if (data.reward.remainMoney < 10) {
		alert($.i18n.t('alert.reward.possibleRequestMoney'));
		return;
	}
	
	// 히스토리 적용
    addHistory({"call": "rewardStep2"});
    
    boxStatus = "rewardStep2";
    
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward");

    var bankSelect = "<select name='bank' class='purchase_select' style='width:49%; margin-bottom: 10px'>"
    $.each(data.banks, function (index, item) {
    	bankSelect += "<option value='" + item.codeValue + "'>" + item.codeName + "</option>";
    });		
    bankSelect += "</select>";
    
    var content = "<span data-i18n='[html]rewardPop2.content1'></span>" 
    	        + "<b>" + data.reward.remainMoney + "</b> " 
    	        + "<span data-i18n='[html]rewardPop2.content2'></span>" 
    	        + "<span id='commission'>5</span></b>"
    	        + "<span data-i18n='[html]rewardPop2.content3'></span><br><br>"
                + "<span class='reward_money'>$" + (data.reward.remainMoney - 5) + "</span>"
                + "<span data-i18n='[html]rewardPop2.content4'></span><br><br><br>" 
                + bankSelect
                + "<span id='directSpan'>"
                + "<input type='text' name='directInputBank' class='purchase_input3' placeholder='name of bank'>"
                + "</span>"
                + "<br>" 
                + "<input type='text' name='accountName' class='purchase_input' placeholder='name of account holder'>"
                + "<br>" 
                + "<input type='text' name='accountNo' class='purchase_input' placeholder='account'>"
                + "<br><span data-i18n='[html]rewardPop2.content5'></span>"
                + "<br><span data-i18n='[html]rewardPop2.content6'></span>";
    var bottom = "<div class='popup_btn upload_btn'>"
    	       + "  <div class='purchase_btn_text'>Done </div>"
    	       + "  <img class='icon' src='/ico/done.png'>"
    	       + "</div>";
    reward.setContents(content);
    reward.setBottom(bottom);
    reward.buildUpload();
    
    // 다국어 처리
    exeTranslation('.base_position', lang);
    
    $("[name=bank]").change(function(e){
    	if (this.value == "0") {
    		$("#directSpan").show();
    		$("#commission").text(7);
    		$(".reward_money").text("$" + (data.reward.remainMoney - 7));
    	} else {
    		$("#directSpan").hide();
    		$("[name=directInputBank]").val("");
    		$("#commission").text(5);
    		$(".reward_money").text("$" + (data.reward.remainMoney - 5));
    	}
    });
    
    $(".popup_btn.upload_btn").click(function(){
    	rewardController.addReward();
    });
    
    // 계좌번호 입력박스 키이벤트 등록
    $("[name=accountNo]").keydown(function (event) {
    	limitNumber(event);
    })
    .keyup(function (event) {
    	limitNumber(event);
    });
}

function validReward() {
	if ($("[name=bank]").val() == '0') {
		if ($("[name=directInputBank]").val().trim() == "") {
			alert($.i18n.t('alert.reward.emptyBankName'));
//			alert("은행이름을 입력하세요");
			$("[name=directInputBank]").focus();
			return false;
		}
	}
	
	if ($("[name=accountName]").val().trim() == "") {
		alert($.i18n.t('alert.reward.emptyAccountName'));
//		alert("계좌주를 입력하세요");
		$("[name=accountName]").focus();
		return false;
	}
	if ($("[name=accountNo]").val().trim() == "") {
		alert($.i18n.t('alert.reward.emptyAccountNo'));
//		alert("계좌번호를 입력하세요");
		$("[name=accountNo]").focus();
		return false;
	}
	
	if (!chkNum($("[name=accountNo]").val())) {
		alert($.i18n.t('alert.reward.inputOnlyNumberAccountNo'));
//		alert("계좌번호는 숫자만 가능합니다.");
		$("[name=accountNo]").focus();
		return false;
	}
	
	return true;
}

// 리워드 히스토리
function Rewarded(){
    this.rewarded   = $("<div>").addClass("reward_list");
    this.money      = $("<div>").addClass("reward_list_money");
    this.account    = $("<div>").addClass("reward_list_account");
    this.required   = $("<div>").addClass("reward_list_required");
    this.problem    = $("<div>").addClass("reward_list_problem").html("problem");
    this.done       = $("<div>").addClass("reward_list_finished").html("rewarded");
    this.build      = function(data){
                        $(this.money).html(data.earmRequestedMoney);
                        $(this.account).html(data.accountInfo);
                        $(this.rewarded).append(this.money);
                        $(this.rewarded).append(this.account);
                        rewarded = this.rewarded;
                        if (data.status == "required") {
                        	$(this.required).html("reqried<div class='reward_list_required_btn'>cancel</div>")
                        	                .click(function () {
                        	                	rewardController.getCancelReward({
                        	                		seq: data.seq,
                        	                		money: data.money
                        	                	}, rewarded);
                                            });
                            $(this.rewarded).append(this.required);   
                        } else if (data.status == "problem") {
                            $(this.rewarded).append(this.problem);   
                        } else {
                            $(this.rewarded).append(this.done);   
                        }
                        return this.rewarded;
                    }
}

function addRewarded(data) {
        var adder = new Rewarded();
        $(adder.build(data)).appendTo($(".reward_contents"));
        delete adder;
}

function showRewarded(result){
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward History");
    reward.buildUpload();
    for(var i = 0; i < result.length; i++){
    	
    	var status;
    	switch (result[i].rewardStatus) {
    	case "R": status = "required"; break;
    	case "A": status = "problem"; break;
    	case "C": status = "done"; break;
    	}
    	var data = {
    		earmRequestedMoney: "$ " + result[i].earmRequestedMoney,
    		accountInfo: result[i].bankName + " " + result[i].accountNo, 
    		status: status,
    		money: result[i].earmRequestedCommission + result[i].earmRequestedMoney,
    		seq: result[i].seq
    	};
    	
        addRewarded(data);
    }
    $(".reward_box").height(result.length * 70);

    // 히스토리 적용
    addHistory({"call": "rewardStep2"});
}

function RewardController() {
}

RewardController.prototype = {
	getRewardInfo : function () {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/reward/info", 
			null,
			"GET", 
			function(result) {
				controller.getRewardInfoRes(result);
			}
		);
	}, 	
	getRewardInfoRes : function (result) {
		rewardController.result = result;
		initReward();
		setBox();
	},
	addReward: function () {
		if (!validReward()) {
			return;
		}
		
		var controller = this;
		var data = {
			accountName: $("[name=accountName]").val(),
			accountNo: $("[name=accountNo]").val(),
			bank: $("[name=bank]").val()
		};
		
		if ($("[name=bank]").val() == '99') {
			data.directInputBank = $("[name=directInputBank]").val();
			// 은행 직접 입력 시 수수료 $7
			data.earmRequestedMoney = controller.result.reward.remainMoney - 7;
			data.earmRequestedCommission = 7;
		} else {
			// 은행 선택 시 수수료 $5
			data.earmRequestedMoney = controller.result.reward.remainMoney - 5;
			data.earmRequestedCommission = 5;
		}
		
		AjaxCall.call(apiUrl + "/reward", 
			data, 
			"POST", 
			function (result) {
				controller.addRewardRes(result);			
			}
		);
	},
	addRewardRes: function (result) {
		alert($.i18n.t('alert.reward.completeProcess'));
		$(".popup_container").hide();
	},
	// 리워드 히스토리
	getRewardHistory: function () {
		var controller = this;
		AjaxCall.call(	apiUrl + "/rewardHistory", 
						null, 
						"GET", 
						function (result) {
							controller.getRewardHistoryRes(result);			
						}
		);
	},
	getRewardHistoryRes: function (result) {
		if (result.length == 0) {
			alert($.i18n.t('alert.reward.notExistHistory'));
			return;
		}
		showRewarded(result);
	},
	// 리워드 요청 취소하기
	getCancelReward: function (data, rewarded) {
		var controller = this;
		AjaxCall.call(	apiUrl + "/cancelReward", 
				data, 
				"POST", 
				function (result) {
					controller.getCancelRewardRes(result, rewarded);			
				}
		);
	},
	getCancelRewardRes: function (result) {
		alert($.i18n.t('alert.reward.cancelReward'));
		$(rewarded).remove();
	}
}
