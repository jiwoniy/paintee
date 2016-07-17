function ProfileController() {
}
ProfileController.prototype = {
	getUserInfo: function() {
		var controller = this;

		AjaxCall.call(apiUrl+"/user/me", null, "GET", function(result, status) { controller.getUserInfoRes(result, status); });
	},
	getUserInfoRes: function(result, status) {
		if(result.errorNo == 0) {
			// console.log(result);

			var userInfo = result.userInfo;

			if(userInfo.providerId != "PAINTEE") {
				$('#profileUserPassword').attr('disabled', true);
				$('#profileUserPassword').addClass('input_disable');
				$('#profileConfirmPassord').attr('disabled', true);
				$('#profileConfirmPassord').addClass('input_disable');
			} else {
				$('#profileUserPassword').attr('disabled', false);
				$('#profileUserPassword').removeClass('input_disable');
				$('#profileConfirmPassord').attr('disabled', false);
				$('#profileConfirmPassord').removeClass('input_disable');
			}

			$('#profileUserName').val(userInfo.name);
			$('#profileIntroduction').val(userInfo.introduce);
			$('#profileBasicAddr').val(userInfo.basicAddr);
			$('#profileDetailAddr').val(userInfo.detailAddr);
			$('#profileCity').val(userInfo.city);
			$('#profileZipcode').val(userInfo.zipcode);

			if(userInfo.location) {
				$('#profileLocation').val(userInfo.location);
			}
			
			setProfilePostUI($("[name=profileLocation]").val());
			
			//사용자 정보 set
		    boxStatus = "profile";
		    setBox();
		    sideOff();

		    $(".profile_container").show();
		}
	},
	updateProfile: function() {
		var controller = this;

		if(controller.validUserInfo()) {
			//email, user name 중복 체크
			controller.checkDuplication();
		}
	},
	updateProfileRes: function(result, status) {
		if(result.errorNo == 0) {
			alert($.i18n.t('alert.profile.processModify'));
//			alert('수정 되었습니다.');
			$(".profile_container").hide();
		}
	},
	validUserInfo: function() {
		var result = true;

		result = chkAlphaNum($('#profileUserName').val());

		if(!result) {
			alert($.i18n.t('alert.common.ruleName'));
//			alert("이름은 최소 4자 최대 10자 의 영문, 숫자 조합으로 입력하셔야 합니다.");
		} else {
			if($('#profileUserPassword').val() != '' || $('#profileConfirmPassord').val() != '') {
				if($('#profileUserPassword').val() != $('#profileConfirmPassord').val()) {
					alert($.i18n.t('alert.common.differentPassword'));
//					alert("비밀번호와 비밀번화 확인 값이 일치하지 않습니다.");
				} else {
					result = chkPassword($('#profileUserPassword').val());

					if(!result) {
						alert($.i18n.t('alert.common.rulePassword'));
//						alert("비밀번호는 최소 8자 최대 12자 의 문자와 숫자 조합으로 입력하셔야 합니다.");
					} else {
						result = true;
					}
				}
			}
		}

		return result;
	},
	checkDuplication: function() {
		var controller = this;

		var param = {};
		param.name=$('#profileUserName').val();

		AjaxCall.call(apiUrl+"/user/chkduplicate", param, "POST", function (result, status) { controller.checkDuplicationRes(result, status); });
	},
	checkDuplicationRes: function(result, status) {
		var controller = this;

		if(result.errorNo == 0) {
			// console.log('update user');
			var param = {};

			if($('#profileUserPassword').val() != '') {
				param.password=$('#profileUserPassword').val();
			}

			param.name=$('#profileUserName').val();
			param.introduce=$('#profileIntroduction').val();
			param.basicAddr=$('#profileBasicAddr').val();
			param.detailAddr=$('#profileDetailAddr').val();
			param.city=$('#profileCity').val();
			param.zipcode=$('#profileZipcode').val();
			param.location=$('#profileLocation option:selected').val();

			AjaxCall.call(apiUrl+"/user/me", param, "PUT", function (result, status) { controller.updateProfileRes(result, status); });
		} else if(result.errorNo == 1) {
			alert($.i18n.t('alert.profile.existName'));
//			alert('이미 사용중인 이름 입니다.');
		}
	}
}

// 프로필 수정화면
function showProfile() {
	new ProfileController().getUserInfo();
}

$("[name=profileLocation]").change(function(e){
	
	setProfilePostUI($("[name=profileLocation]").val());
	e.stopPropagation();
});

function setProfilePostUI(type) {
	
	if (type == 'Korea') {
		// 기본 주소 선택시 
		console.log('test');
		$("[name=profileCity]").attr("disabled", "disabled");
        $("[name=profileCity]").addClass("input_disable")
		$("[name=profileBasicAddr]").attr("readOnly", "readOnly");
		$("#profileBasicAddr").focus(function () {

			$(function() { $("#profileBasicAddr").postcodifyPopUp(); });
//			execDaumPostcode('profile', 'profileZipcode', 'profileBasicAddr')
		});
	} else {
		// 주소에 설정된 이벤트 삭제
		$("[name=profileBasicAddr]").off();
		$("[name=profileCity]").attr("disabled", false);
        $("[name=profileCity]").removeClass("input_disable")
		$("[name=profileBasicAddr]").attr("readOnly", false);
	}
}

$('#profileUpdateBtn').on('click', function() {
	new ProfileController().updateProfile();
});

$('#logoutBtn').on('click', function(){
    logout();
})
