window.history.pushState("dummy", "", "");
window.addEventListener("popstate", function(e) {
	if(e.state){
		if (e.state.call == "detailPop") {
			processDetailClose();
		} 
		// 구매 1단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "purchasePop") {
			// 구매 1단계 팝업 닫기
			closePurchaseStep01();
		}
		// 구매 2단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "purchaseStep1" && boxStatus == "payment") {
			purchaseStatus = "sentence";
			boxStatus = "";
			$(".payment_container").hide();
			$(".purchase_container").show();
		}
		// follow 팝업에서 호출된 경우
		else if (e.state.call == "followPop") {
			$(".popup_container").hide();
		}
		// upload 팝업에서 호출된 경우
		else if (e.state.call == "uploadPop") {
			$(".popup_container").hide();
		}
		// 리워드 1단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "rewardPop") {
			$(".popup_container").hide();
		}
		// 리워드 2단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "rewardStep1") {
			reward();
		}
		// 로그인 폼 일경우
		else if (e.state.call == "login") {
			$(".login_container").hide();
		}
		// 회원가입폼 일경우
		else if (e.state.call == "signup") {
			$(".signup_container").show();
		}
		// 회원가입폼 숨김 일경우
		else if (e.state.call == "signupHide") {
			$(".signup_container").hide();
		}
		// 패스워드 재발송
		else if (e.state.call == "loginHelpHide") {
			$(".loginhelp_container").hide();
		}
		// 개인페이지에서 리스트로 이동
		else if (e.state.call == "list") {
			hidePersonal();

			switch (e.state.mainIndex) {
			case 0:
				currentSwiper = followSwiper;
				break;
			case 1:
				currentSwiper = popularSwiper;
				break;
			case 2:
				currentSwiper = newSwiper;
				break;
			case 3:
				currentSwiper = mySwiper;
				break;
			}
			
			if (currentSwiper !== "") {
				currentSwiper.slideTo(e.state.index, 0);
		    	mainSwiper.unlockSwipes();
		    	mainSwiper.slideTo(e.state.mainIndex);
		        currentSwiper.slideTo(e.state.index, 0);
			}
		}
		// 상세 팝업이 발생한 상태에서 개인작가 페이지로 이동한 경우
		else if (e.state.call == "detailOpen") {
			hidePersonal();
			switch (e.state.mainIndex) {
			case 0:
				currentSwiper = followSwiper;
				break;
			case 1:
				currentSwiper = popularSwiper;
				break;
			case 2:
				currentSwiper = newSwiper;
				break;
			case 3:
				currentSwiper = mySwiper;
				break;
			}
			
			if (currentSwiper !== "") {
				currentSwiper.slideTo(e.state.index, 0);
		    	mainSwiper.unlockSwipes();
		    	mainSwiper.slideTo(e.state.mainIndex);
		        currentSwiper.slideTo(e.state.index, 0);
			}
			
			// 상세 팝업 호출
			loadDetail(e.state.paintingId, e.state.color, e.state.colorDark);
		}
	}
}, false);

/**
 * 히스토리 추가
 * @param data
 */
function addHistory(data) {
	window.history.pushState(data, "", "");
}

/**
 * 히스토리 가장 상단 정보 변경
 */
function replaceHistory(data) {
	window.history.replaceState(data, "", "");
}