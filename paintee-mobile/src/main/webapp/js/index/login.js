
function LogInController(email, password) {
	this.email = email;
	this.password = password;
	this.accessGubun = 'W';
}
LogInController.prototype = {
	doLogin: function() {
		var param = {};
		param.email = this.email;
		param.password = this.password;
		param.accessGubun = this.accessGubun;

		var controller = this;

		AjaxCall.call(apiUrl+"/login", param, "POST", function(result, status) { controller.doLoginRes(result, status); });
	},
	doLoginRes: function(result, status) {

		if(result.errorNo == 0) {
			// console.log(result);


			setUserInfoCookie(result);

			var userAgent = navigator.userAgent;

			location.reload();
			//login 후 cookie 를 페이지에 적용하기 위하여 새로고침해야함.
			//location.href = "/";
		} else if(result.errorNo == 401 || result.errorNo == 402 || result.errorNo == 404) {
//			alert('이메일과 비밀번호를 확인하세요.');
			alert($.i18n.t('alert.login.confirmEmailPass'));
		}
	},
	doSocialLogin: function(email, name, accessToken, expireTime, userId, providerId) {
		var param = {};

		param.email = email;
		param.accessToken = accessToken;
		param.expireTime = expireTime;
		param.userId = userId;
		param.providerId = providerId;
		param.accessGubun = this.accessGubun;

		var controller = this;

		AjaxCall.call(apiUrl+"/login/social", param, "POST", function(result, status) { controller.doLoginRes(result, status); });
	},
	doResetPasswod: function() {
		var param = {};
		param.email = this.email;

		var controller = this;

		AjaxCall.call(apiUrl+"/resetpasswd", param, "POST", function(result, status) { controller.doResetPasswodRes(result, status); });
	},
	doResetPasswodRes: function(result, status) {
		if(result.errorNo == 0) {
			// console.log(result);
			alert($.i18n.t('alert.login.resetPassSendMail'));
//			alert('비밀번호 초기화 메일 발송.');
		} else if(result.errorNo == 401) {
			alert($.i18n.t('alert.login.confirmEmail'));
//			alert('이메일을 확인하세요.');
		}
	}

}

// 로그인과 함께 다시 side menu 초기화
function logIn(){
    // console.log("log in!!!");

    var userEmail = $('#userEmail').val();
    var userPassword = $('#userPassword').val();

    var logInController = new LogInController(userEmail, userPassword);
    logInController.doLogin();

/*    $(".login_container").hide();
    userID = "asummer";
    initFollow(userID);
    initMy(userID);
    initMenu(userID);

    mainSwiper.slideTo(0);
    mainSwiper.unlockSwipes();
    mainSwiper.lockSwipeToPrev();*/
}

function logout() {
    // console.log("log out!!!");

    clearUserInfoCookie();

    //location.href="/";
    location.reload();
}

//로그인 화면
function showLogin(){

	// 히스토리 설정
	  replaceHistory({"call": "login"});
    addHistory({"call": "dummy"});

    $(".login_container").show();
}

$(".login_btn_main").click(function(){
 logIn();
});
$(".login_signup_btn").click(function(){
	// 히스토리 설정
	replaceHistory({"call": "signupHide"});
	addHistory({"call": "signup"});
    $(".signup_container").show();
});
$(".signup_login_btn").click(function(){
	history.back();
	$(".signup_container").hide();
});
$(".login_help").click(function(){
	replaceHistory({"call": "loginHelpHide"});
	addHistory({"call": "loginHelp"});
	$(".loginhelp_container").show();
});
$(".help_login_btn").click(function(){
	history.back();
	$(".loginhelp_container").hide();
});

//비밀번호 초기화
$('#resetPasswordBtn').on('click', function() {
    var resetUserEmaiil = $('#resetUserEmaiil').val();
    var logInController = new LogInController(resetUserEmaiil, '');
    logInController.doResetPasswod();
});

function loginSocialUser(response, providerId) {

var accessToken = response.authResponse.accessToken;
var expireTime = response.authResponse.expiresIn;
var userId = response.authResponse.userID;
var providerId = providerId;


	if (response.status === 'connected') {
		FB.api('/me', {fields: 'email,name'}, function(response) {
			new LogInController().doSocialLogin(response.email, response.name, accessToken, expireTime, userId, providerId);
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

$('#login_facebook_btn').on('click', function() {
 
	 FB.login(function(response) {
		loginSocialUser(response, "FACEBOOK")
	}, {scope: 'email,user_likes'});
});
