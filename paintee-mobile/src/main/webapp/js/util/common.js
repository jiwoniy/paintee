var convertEngMonth = function (mon){
	if     (mon == '1')    	return 'Jan';
	else if(mon == '2')		return 'Feb';
	else if(mon == '3')		return 'Mar';
	else if(mon == '4')		return 'Apr';
	else if(mon == '5')		return 'May';
	else if(mon == '6')		return 'Jun';
	else if(mon == '7')		return 'Jul';
	else if(mon == '8')		return 'Aug';
	else if(mon == '9')		return 'Sep';
	else if(mon == '10')	return 'Oct';
	else if(mon == '11')	return 'Nov';
	else if(mon == '12')	return 'Dec';
}

var toDate = function (timestamp) {
	var date = new Date(timestamp);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();

	var retVal = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	// " " + (hour < 10 ? "0" + hour : hour) + ":"
	// + (min < 10 ? "0" + min : min) + ":"
	// + (sec < 10 ? "0" + sec : sec);
	return retVal;
};

var toEngDateStr = function (timestamp) {
	var date = new Date(timestamp);
	return date.getDate() + ". " + convertEngMonth(date.getMonth() + 1);
};

var toDateTime = function (timestamp) {
	var date = new Date(timestamp);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();

	var retVal = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day)
		+ " " + (hour < 10 ? "0" + hour : hour) + ":"
		+ (min < 10 ? "0" + min : min)
		+ ":" + (sec < 10 ? "0" + sec : sec);
	return retVal;
};

var setUserInfoCookie = function(userInfo) {
	
	var cDay = 7;
	var cName = "userInfo";
	var cValue = JSON.stringify(userInfo);
	var expire = new Date();
	expire.setDate(expire.getDate() + cDay);
	cookies = 'userInfo=' + escape(cValue) + '; path=/ ';
	// console.log(cookies);
	if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
	
	
	//window.localStorage.setItem('cookie', cookies);
//	window.sessionStorage.setItem('cookie', cookies);
	document.cookie = cookies;
	
};
var clearUserInfoCookie = function() {
	var cDay = -7;
	var cName = "userInfo";
	var cValue = '';
	var expire = new Date();
	expire.setDate(expire.getDate() + cDay);
	cookies = 'userInfo=' + escape(cValue) + '; path=/ ';
	// console.log(cookies);
	if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
	
//	window.localStorage.setItem('cookie', cookies);
//	window.sessionStorage.setItem('cookie', cookies);
	document.cookie = cookies;
	
	
};
var getUserInfoCookie = function() {
	var cName = 'userInfo=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cName);
	var cValue = '';
	
	if(start != -1) {
		start += cName.length;

		var end = cookieData.indexOf(';', start);

		if(end == -1) {
			end = cookieData.length;
		}

		cValue = cookieData.substring(start, end);
	}

	var userInfo = null;

	if(cValue != null && cValue != '') {
		userInfo = JSON.parse(unescape(cValue));
	}
	// console.log(userInfo);
	return userInfo;
};

Date.prototype.timestampToDate = function () {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var day = this.getDate();
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
};

Date.prototype.timestampToFullDate = function () {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var day = this.getDate();
	var hour = this.getHours();
	var min = this.getMinutes();
	var sec = this.getSeconds();
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
};

Number.prototype.toDateFormat = function () {
	var date = new Date(this);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
};

(function ($) {
	// check box multiple select/deselect
	$.checkboxGroup = function (group_name) {
		var checkAll = $('.' + group_name + '.checkbox-head');
		var checkboxes = $('.' + group_name + '.checkbox-item');

		$('.' + group_name).iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});

		checkAll.on('ifChecked ifUnchecked', function (event) {
			if (event.type == 'ifChecked') {
				checkboxes.iCheck('check');
			} else {
				checkboxes.iCheck('uncheck');
			}
		});

		checkboxes.on('ifChanged', function (event) {
			checkAll.prop('checked', checkboxes.filter(':checked').length == checkboxes.length);
			checkAll.iCheck('update');
		});
	};

})(jQuery);

// jquery 확장
jQuery.fn.serializeObject = function() {
	var obj = null;
	try {
		// this[0].tagName이 form tag일 경우
		if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				obj = {};
				jQuery.each(arr, function() {
					// obj의 key값은 arr의 name, obj의 value는 value값
					obj[this.name] = this.value;
				});
			}
		}
	} catch(e) {
		alert(e.message);
	} finally  {}

	return obj;
};


/**
 * 
 * @param obj
 */
function getEnterCount(obj) {
	if (typeof(obj) === "string") {
		obj = $("#" + obj);
	}
	var temp;
	var senVal = obj.val();
	var len = senVal.length;
	var enter = 0;

	// 초기 최대길이를 텍스트 박스에 뿌려준다.
	for (var index = 0; index < len; index++) {
		temp = senVal.charAt(index);
		if (temp == '\n') { // 엔터 키 횟수 증가
			enter++;
		}
	}
	return enter;
}	

function chkEmail(v) {
	var regEmail = /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/;

	if(!regEmail.test(v)) {
		return false;
	}

	return true;
}

function chkPassword(str){
	var regPassword = /^.*(?=.{8,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

	if(!regPassword.test(str)) {
		return false;
	}

	return true;
}

function chkAlphaNum(str){
	var regAlphaNum = /^[A-Za-z0-9+]{4,14}$/;

	if(!regAlphaNum.test(str)) {
		return false;
	}

	return true;
}

/**
 * 숫자 인지 체크
 * @param str
 * @returns
 */
function chkNum(str){
	return /^[0-9]+$/.test(str);
}

/**
 * 숫자만 입력받기
 * @param event
 * @returns {Boolean}
 */
function limitNumber(event) {
	event = event || window.event;
	var keyCode = (event.which) ? event.which : event.keyCode;
	event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

function getCharCount(value) {
	var totalByte = 0;
	for (var i = 0; i < value.length; i++) {
	    oneChar = value.charAt(i);
	    if (escape(oneChar).length > 4) {
	        totalByte += 2;
	    } else {
	        totalByte++;
	    }
	}
	return totalByte;
}

function shareSocial(data) {
	var url = "";
//	var hostAndFileName = "www.paintee.com:8080/index.html?";
	var hostAndFileName = "www.paintee.me/index.html?";
	hostAndFileName = encodeURIComponent(hostAndFileName);
	var param = "user=" + data.name;
	if (data.page) {
		param += "&page=" + data.page;
	}
	param = encodeURIComponent(param);
	
	switch (data.type) {
	case "facebook":
		url = "https://www.facebook.com/sharer/sharer.php?u=http://" + hostAndFileName + param;
		break;
	case "twitter":
		url = "http://twitter.com/home?status=paintee http://" + hostAndFileName + param;
		break;
	case "pinterest":
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http%3A//" + hostAndFileName + param;
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http://me2.do/xndJIG4P";
		url = "https://kr.pinterest.com/pin/create/button?media=http://www.mlec.co.kr/upload/memberFile/mlec-0975e88b-7600-4ebd-93f3-376ccae91ab9.jpg&url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=" + encodeURIComponent("http://www.naver.com");
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=" + encodeURIComponent("http://www.naver.com");
//		https://kr.pinterest.com/pin/create/button/?url=https%3A%2F%2Fwww.youtube.com%2Fattribution_link%3Fa%3D3qHmOcyQVYY%26u%3D%252Fwatch%253Fv%253DOg_kaPMZxN0%2526feature%253Dshare&description=%EC%8A%B9%EA%B1%B4%EC%9D%B4%EC%9D%98%20%ED%86%A0%EB%AF%B8%EC%B9%B4%EC%B9%B4%EA%B3%A0%20%EC%A0%90%EB%B3%B4%EC%A0%9C%ED%8A%B8%EA%B8%B0%20%EC%86%8C%EA%B0%9C&is_video=true&media=https%3A%2F%2Fi.ytimg.com%2Fvi%2FOg_kaPMZxN0%2Fmaxresdefault.jpg
		break;
	}
	
	var pop =  window.open(url, "social", "width=630,height=250,scrollbars=yes,resizable=yes,toolbar=no");
	if (pop) pop.focus();  

}

/**
 * 주소 복사기능 
 * @param url
 */
function urlCopy(data) {
	var url = "http://www.paintee.me/index.html";
	var param = "?user=" + data.name;
	if (data.page) {
		param += "&page=" + data.page;
	}
		
	url += param;
	
    var IE = (document.all) ? true : false;
    if (IE) {
         window.clipboardData.setData('Text', url);
         alert($.i18n.t('alert.common.copyAddress'));
    } else {
        temp = prompt($.i18n.t('alert.common.copyToClipboard'), url);
    }
}

function toDate (timestamp, dateFormat) {
    var date = new Date(timestamp);

    var retVal = $.datepicker.formatDate(dateFormat, date);
    return retVal;
};

/**
 * 미디어에 따른 이미지 경로를 처리하기 위한 함수
 * 고정된 값을 설정하므로 공통적인 부분을 반환함
 * @param fileId
 * @returns {Array}
 */
function getImageUrls(fileId, ratio) {
	if (!ratio) {
		return imageUrl + "/cmm/file/view/2/" + fileId;
	}
	if (ratio == 1) 
		return imageUrl + "/cmm/file/view/2/" + fileId + " 729w, " + imageUrl + "/cmm/file/view/3/" + fileId + " 405w";
	if (ratio == 2) 
		return imageUrl + "/cmm/file/view/2/" + fileId + " 405w, " + imageUrl + "/cmm/file/view/3/" + fileId + " 225w";
	if (ratio == 3) 
		return imageUrl + "/cmm/file/view/2/" + fileId + " 270w, " + imageUrl + "/cmm/file/view/3/" + fileId + " 150w";
}

function convertToBr(str) {
	return str.replace(new RegExp('\r?\n','g'), '<br />');
}
