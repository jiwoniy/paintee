var signupSocialAuthResponse = {};
var signupSocialProviderId = '';

function SignupController() {
}

SignupController.prototype = {
	doSignup: function() {
		var controller = this;

		if(controller.validSignupInfo()) {
			var param = {};
			param.email=$('#signupUserId').val();
			param.password=$('#signupUserPassword').val();
			param.name=$('#signupUserName').val();
			param.providerId="PAINTEE";

			//email, user name 중복 체크
			controller.checkDuplication(param);
		}
	},
	doSignupRes: function(result, status) {
		//4.4 수정
		$(".stopper").hide();

		if(result.errorNo == 0) {

			if(result.providerId == 'PAINTEE') {
				alert($.i18n.t('alert.signup.processMemberJoin'));
//				alert('회원가입이 정상적으로 처리되었습니다.\n이메일을 확인하세요.');
			} else if(result.providerId == 'FACEBOOK') {
				setUserInfoCookie(result);
				alert($.i18n.t('alert.signup.processSocialMemberJoin'));
//				alert('회원가입이 정상적으로 처리되었습니다.');
			}

//			location.href = "/";
			location.reload();
		} else if(result.errorNo != 0) {
			alert($.i18n.t('alert.common.confirmEmailPass'));
//			alert('이메일과 비밀번호를 확인하세요.');
		}
	},
	validSignupInfo: function() {
		var result = false;

		result = chkEmail($('#signupUserId').val());

		if(!result) {
			alert($.i18n.t('alert.signup.correctEmailAddr'));
//			alert("바른 이메일 주소를 입력하셔야 합니다.");
		} else {
			if($('#signupUserPassword').val() != $('#signupConfirmPassord').val()) {
				alert($.i18n.t('alert.common.differentPassword'));
//				alert("비밀번호와 비밀번화 확인 값이 일치하지 않습니다.");
			} else {
				result = chkPassword($('#signupUserPassword').val());

				if(!result) {
					alert($.i18n.t('alert.common.rulePassword'));
//					alert("비밀번호는 최소 8자 최대 12자 의 문자와 숫자 조합으로 입력하셔야 합니다.");
				} else {
					result = chkAlphaNum($('#signupUserName').val());

					if(!result) {
						alert($.i18n.t('alert.common.ruleName'));
//						alert("이름은 최소 4자 최대 10자 의 영문, 숫자 조합으로 입력하셔야 합니다.");
					} else {
						result = true;
					}
				}
			}
		}

		return result;
	},
	checkDuplication: function(param) {
		var controller = this;

		AjaxCall.call(apiUrl+"/signup/chkduplicate", param, "POST", function (result, status) { controller.checkDuplicationRes(result, status, param); });
	},
	checkDuplicationRes: function(result, status, param) {
		var controller = this;

		if(result.errorNo == 0) {
			// console.log('regist user');

			//TODO:Language 정보 취득 (현재 브라우저의 location 정보를 넣음.)
			var signupLanguage = 'en';
			var signupLanguages = navigator.language.split("-");
			if(signupLanguages && signupLanguages[0]) {
				signupLanguage = signupLanguages[0];
			}

			param.accessGubun = "W";
			param.language = signupLanguage;

			if(param.providerId == 'PAINTEE') {
				$("#signup_btn").html("<img src='spinner.png' class='spinner'>");
			} else if(param.providerId == 'FACEBOOK') {
				$("#social_username_signup_btn").html("<div class='purchase_btn_text'>wait </div> <img src='spinner.png' class='spinner'>");
				signupSocialAuthResponse = {};
				signupSocialProviderId = '';
			}
			$(".stopper").show();

			AjaxCall.call(apiUrl+"/signup", param, "POST", function (result, status) { controller.doSignupRes(result, status); });
		} else if(result.errorNo == 1) {
			alert($.i18n.t('alert.common.existEmail'));
//			alert('이미 사용중인 email 입니다.');
		} else if(result.errorNo == 2) {
			alert($.i18n.t('alert.common.existName'));
//			alert('이미 사용중인 이름 입니다.');
		}
	},
	validFacebookSignup: function(email, name, accessToken, expireTime, userId, providerId) {
		var controller = this;

		var param = {};
		param.email=email;
		param.name=$.trim(name);
		param.providerId=providerId;
		param.providerUserId=userId;
		param.accessToken = accessToken;

		//email, user name 중복 체크
		controller.checkDuplication(param);
	}
}

function registSocialUser(response, providerId) {
	var providerId = providerId;
	signupSocialAuthResponse = response;
	signupSocialProviderId = providerId;
	var laccessToken = response.authResponse.accessToken;
	
	if (response.status === 'connected') {
		
		FB.api('/me', {fields: 'email,name'}, function(response) {
		
		 	signupSocialAuthResponse.email = response.email;
		 	showUsername();
		 });
	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		// console.log('Please log into this app.');
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		//console.log('Please log into Facebook.');
	}
}

$('#social_username_signup_btn').on('click', function() {
	checkSignupUsername();
});

$(".signup_terms").click(function(){
	showAboutPolicy();
});

function checkSignupUsername() {
	var accessToken = signupSocialAuthResponse.authResponse.accessToken;
	var expireTime = signupSocialAuthResponse.authResponse.expiresIn;
	var userId = signupSocialAuthResponse.authResponse.userID;
	var username = $('#signup_social_username').val();

	// Logged into your app and Facebook.
	new SignupController().validFacebookSignup(signupSocialAuthResponse.email, username, accessToken, expireTime, userId, signupSocialProviderId);
}

$('#signup_btn').on("click", function() { new SignupController().doSignup(); });

$('#signup_facebook_btn').on('click', function() {
	//openFB.login(function(response) {
	  FB.login(function(response) {
		 registSocialUser(response, "FACEBOOK")
	 }, {scope: 'email,user_likes'});
});

/*  4.4 수정  */
function showUsername(){
	boxStatus = "username";
	setBox();
	sideOff();
	$(".username_container").show();
}
