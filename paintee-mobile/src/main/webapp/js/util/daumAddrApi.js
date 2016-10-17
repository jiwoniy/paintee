// 우편번호 찾기 화면을 넣을 element
var postLayer = document.getElementById('layer');
var profilePostLayer = document.getElementById('profileLayer');
var openLayer = null;

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
	postLayer.style.display = 'none';
	profilePostLayer.style.display = 'none';
}



function execDaumPostcode(searchModule, zipcodeFieldName, basicAddrFieldName) {
    if(searchModule == 'purchase') {
    	openLayer = postLayer;
    } else if(searchModule == 'profile') {
    	openLayer = profilePostLayer;
    }
//    http://localhost/paintee-admin/api/test?url=http://dmaps.daum.net/map_js_init/postcode.v2.js?
		var url = "http://paintee.me/paintee-admin/api/test?url=http://dmaps.daum.net/map_js_init/postcode.v2.js?";
		$.get( url, function() {
			daum.postcode.load(function(){
					new daum.Postcode({
							oncomplete: function(data) {
									// oncomplete: function(data) {
					            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
					            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					            var fullAddr = data.address; // 최종 주소 변수
					            var extraAddr = ''; // 조합형 주소 변수

					            // 기본 주소가 도로명 타입일때 조합한다.
					            if(data.addressType === 'R'){
					                //법정동명이 있을 경우 추가한다.
					                if(data.bname !== ''){
					                    extraAddr += data.bname;
					                }
					                // 건물명이 있을 경우 추가한다.
					                if(data.buildingName !== ''){
					                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					                }
					                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
					            }

					            // 우편번호와 주소 정보를 해당 필드에 넣는다.
					            document.querySelector('[name='+zipcodeFieldName+']').value = data.zonecode; //5자리 새우편번호 사용
					            document.querySelector('[name='+basicAddrFieldName+']').value = fullAddr;

					            // iframe을 넣은 element를 안보이게 한다.
					            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
					            openLayer.style.display = 'none';
					        },
					        onclose: function (state) {
					        	// 다음 주소 api 호출시 히스토리 삭제됨 ??
					            // 다음 주소 호출후 강제로 히스트로 쌓기
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
					        },
					        width : '100%',
					        height : '100%'
									//팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
									// 예제를 참고하여 다양한 활용법을 확인해 보세요.

					  }).embed(openLayer);
					});
		});

/*
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.querySelector('[name='+zipcodeFieldName+']').value = data.zonecode; //5자리 새우편번호 사용
            document.querySelector('[name='+basicAddrFieldName+']').value = fullAddr;

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            openLayer.style.display = 'none';
        },
        onclose: function (state) {
        	// 다음 주소 api 호출시 히스토리 삭제됨 ??
            // 다음 주소 호출후 강제로 히스트로 쌓기
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
        },
        width : '100%',
        height : '100%'
    }).embed(openLayer);
	*/
    // iframe을 넣은 element를 보이게 한다.
    openLayer.style.display = 'block';
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
function initLayerPositionForPurchase(){
    var width = 410; //우편번호서비스가 들어갈 element의 width

    if ($(".purchase_box").width() < 660) {
    	width = 300;
    }
    var height = $(".purchase_box").height( ) - 20; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    postLayer.style.width = width + 'px';
    postLayer.style.height = height + 'px';
    postLayer.style.border = borderWidth + 'px #334455 solid';


    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
//    postLayer.style.left = (($(".purchase_box").width( ) - width)/2 - borderWidth) + 'px';
    postLayer.style.right = '10px';
    postLayer.style.top = (($(".purchase_box").height( ) - height)/2 - borderWidth) + 'px';
}

function initLayerPositionForProfile(){
    var width = 410; //우편번호서비스가 들어갈 element의 width
    var height = 300; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    profilePostLayer.style.width = width + 'px';
    profilePostLayer.style.height = height + 'px';
    profilePostLayer.style.border = borderWidth + 'px #334455 solid';

    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
//    postLayer.style.left = (($(".purchase_box").width( ) - width)/2 - borderWidth) + 'px';
    profilePostLayer.style.left = '10px';
    profilePostLayer.style.top = (($(".profile_box").height( ) - height)/2 - borderWidth) + 'px';
}
