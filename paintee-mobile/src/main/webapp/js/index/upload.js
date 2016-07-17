var baseUploadCount = 5;
var doUploadCount = 0;
var onceAboutUpload = true;

//업로드화면
function upload(){
	if (userID == "") {
		showLogin();
		return ;
	}

    postedInfo();
}
function postedInfo() {
	AjaxCall.call(apiUrl+"/user/me/postedCountInfo", null, "GET", postedInfoRes);
}
function postedInfoRes(result, status) {
	if(result.errorNo == 0) {
		var postedCount = parseInt(result.postedCount, 10);
		var uploadedCount = parseInt(result.uploadedCount, 10);
		var doTotaluploadCount = 1;

		if(postedCount >= baseUploadCount) {
			doTotaluploadCount = Math.floor(postedCount/baseUploadCount) + 1;
		}

		doUploadCount = doTotaluploadCount - uploadedCount;

		boxStatus = "upload"
		$(".upload_container").show();

		initUpload(postedCount, doTotaluploadCount, uploadedCount);
		setBox();

		replaceHistory({"call": "uploadPop"});
	    addHistory({"call": "upload"});
	}
}

function Upload(){
    this.title      = $("<div>").addClass("upload_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("upload_contents").addClass("popup_contents");
    this.bottomMargin    = $("<div>").addClass("popup_margin_bottom");
    this.bottom     = $("<div>").addClass("upload_bottom").addClass("popup_bottom");
}

Upload.prototype = {
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
    	$('#upload_popup_box').css('backgroundImage', '');

        $(".upload_box").append(this.title);
        $(".upload_box").append(this.contents);
        $(".upload_box").append(this.bottomMargin);
        $(".upload_box").append(this.bottom);
    }
}

function checkPainteeFile(file) {
	var reader = new FileReader();
	var image  = new Image();
	var fileType = file.type;

	reader.readAsDataURL(file);
	reader.onload = function(_file) {
		image.src    = _file.target.result;

		image.onload = function() {
			var width = this.width;
			var height = this.height;

			//이미지 이면서 1080x1500 인 이미지
			if(!fileType.match(/image\//) || width < 1080 || height < 1500) {
				failUpload();
//				alert($.i18n.t('uploadPop.failContent'));
//				resetUpload();
			} else {
				successUpload();
//				$('.painting_preview').append('<img src="'+ this.src +'" width="120px" height="150px"/>');
			}
		};

		image.onerror= function() {
			failUpload();
		};
	};
}

function createPainting() {
	var sentence = $('#painting_sentence_text').val();

	if ($("[name=painting_sentence_text]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptySentence'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	if (getCharCount($("[name=painting_sentence_text]").val()) > 200) {
		alert($.i18n.t('alert.purchase.exceedSentence'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	var enter = getEnterCount($("[name=painting_sentence_text]"));
	if (enter > 5) {
		alert($.i18n.t('alert.purchase.limitEnterCount'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	var formData = new FormData($('#paintingCreateForm').get(0));

	formData.append("sentence", sentence);
	formData.append("artistId", userInfo.userId);

	if($('#painting_private').is(":checked")) {
		formData.append("privateAt", 'Y');
	} else {
		formData.append("privateAt", 'N');
	}

	/* 4.3 수정 */
	$("#update_painting_sentence_btn").html("<div class='purchase_btn_text'>wait </div><img src='spinner.png' class='spinner'>");
	$(".stopper").show();

	AjaxCall.callMultipart(apiUrl+"/painting", formData, createPaintingRes);
}
function createPaintingRes(result, status) {
	//4.4 수정
	$(".stopper").hide();

	if(result.errorNo == 0) {
		dataReload(["initMy();", "initFollow();", "initNew();"]);

		selectMenu(3);
		$(".popup_container").hide();
		$(".upload_box").empty();

		boxStatus = "clickedCloseBtn";
		closePopup(); 
	} else {
		alert('error');
	}
}
function resetUpload() {
	boxStatus = "uploadPop";

//	$('.painting_preview').empty();
	$('.uploadFileBox').empty();
	$('.uploadFileBox').html("<label for='painteeFile' class='upload_btn_text'>Select image file </label><img class='icon' src='ico/folder.png'>");

	$('#upload_file_input_box').empty();
	$('#upload_file_input_box').html("<form id='paintingCreateForm' name='paintingCreateForm' method='POST' enctype='multipart/form-data'><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></form>");


	$('#painteeFile').on('change', function(e) {
		
		if(this.files[0]) {
			checkPainteeFile(this.files[0]);
		}
	});
}
function initUpload(postedCount, doTotaluploadCount, uploadedCount){
    $(".upload_box").empty();
    var upload = new Upload();
    upload.setTitle("Upload Painting");
//    upload.setContents("당신의 그림이 Post될 때 마다,<br>추가로 업로드할 수 있는 그림의 수가 늘어납니다.<br>지금까지 253회 Post된 당신은 최대 50개의 그림을 올릴 수 있고<br> 지금 <span class='reward_money'>7</span>개 의 그림을 더 올릴 수 있습니다.<br><br><br>업로드를 위해서는<br>가로 사이즈 <b>1080px</b> 세로 사이즈 <b>1440px</b><br>이상의 이미지가 필요합니다.");
    var content = "<span data-i18n='[html]uploadPop.content1'></span>" + 
                  postedCount + 
                  "<span data-i18n='[html]uploadPop.content2'></span>" + 
                  doTotaluploadCount + 
                  "<span data-i18n='[html]uploadPop.content3'></span><span class='reward_money'>" + 
                  doUploadCount + 
                  "</span><span data-i18n='[html]uploadPop.content4'></span>";
    // "<br><br><br><br><span style='color:rgb(150,0,0)'>타인의 저작물을 무단으로 업로드할 경우, 게시물에 대해 재재를 받을 수 있습니다. 자세한 사항은 <b>Terms & Policy</b> 항목을 확인하세요.</span>";
    
    upload.setContents(content);
//    upload.setContents("<span data-i18n='[html]uploadPop.content1'></span>"+postedCount+"<span data-i18n='[html]uploadPop.content2'></span>"+doTotaluploadCount+"<span data-i18n='[html]uploadPop.content3'></span><span class='reward_money'>"+doUploadCount+"</span><span data-i18n='[html]uploadPop.content4'></span>");
    
    
    upload.setBottom("<div class='popup_btn upload_btn uploadFileBox'></div>");
    upload.buildUpload();

    if(doUploadCount > 0) {
        resetUpload();
    }

    // console.log("uploadedCount:"+uploadedCount);
    if(uploadedCount == 0 && onceAboutUpload == true) {
    	showAboutUpload();
    	boxStatus = "upload"
    	onceAboutUpload = false;
    }

    delete upload;

    // 다국어 처리
    exeTranslation('.base_position', lang);
}

function failUpload(){
	$(".upload_box").empty();

	var uploadFail = new Upload();
	uploadFail.setTitle("Upload Painting");
	uploadFail.setContents('<span data-i18n="[html]uploadPop.failContent" style="color:rgb(150,0,0)"></span>');
	uploadFail.setBottom("<div class='popup_btn upload_btn uploadFileBox'><label for='painteeFile' class='upload_btn_text'>Select image file </label><img class='icon' src='ico/folder.png'><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></div>");
	uploadFail.buildUpload();

    if(doUploadCount > 0) {
        resetUpload();
    }

	delete uploadFail;

	// 다국어 처리
	exeTranslation('.base_position', lang);
}

function successUpload() {
    $(".upload_box").empty();

    var uploadSuccess = new Upload();

    uploadSuccess.setTitle("Upload Painting");
    uploadSuccess.setContents('<span data-i18n="[html]uploadPop.successContent"></span><br><div class="upload_sentence"><span class="character_counter"><span id="paintingSentenceCount">0</span>/200</span><textarea id="painting_sentence_text" name="painting_sentence_text" class="upload_sentence_textarea" length="200"></textarea><input id="painting_private" name="painting_private" type="checkbox"> private</div>');
    uploadSuccess.setBottom("<div class='popup_cancle_btn upload_btn uploadFileBox'><img class='icon' src='ico/folder.png'><label for='painteeFile' class='upload_btn_text'>Select image file </label></div><div id='update_painting_sentence_btn' class='popup_btn upload_btn'><div class='purchase_btn_text'>Done </div><img class='icon' src='/ico/done.png'></div>");
    uploadSuccess.buildUpload();

	//미리보기 생성
	var previewFile = document.querySelector('input[name=painteeFile]').files[0];
	if (previewFile) {
		var previewReader = new FileReader();

		
		previewReader.addEventListener("load", function () {
			var boxWidth = $('#upload_popup_box').width();
			var originalWidth = this.width;
			// console.log("boxWidth:"+boxWidth+", originalWidth:"+originalWidth);
//			$('.painting_preview').append('<img src="'+ previewReader.result +'" width="120px" height="150px"/>');
			$('#upload_popup_box').css('backgroundImage', 'url('+previewReader.result+')').css('background-size', boxWidth/1080*originalWidth).css('background-position', 'center center');
		}, false);

		var image = new Image();

		previewReader.readAsDataURL(previewFile);
		previewReader.onload = function(_file) {
			image.src = previewReader.result;

			image.onload = function() {
				var boxWidth = $('#upload_popup_box').width();
				var originalWidth = this.width;
				// console.log("boxWidth:"+boxWidth+", originalWidth:"+originalWidth);
//				$('.painting_preview').append('<img src="'+ previewReader.result +'" width="120px" height="150px"/>');
				$('#upload_popup_box').css('backgroundImage', 'url('+previewReader.result+')').css('background-size', boxWidth/1080*originalWidth).css('background-position', 'center center');
			};

			image.onerror= function() {
				failUpload();
			};
		};
	}

	//구매시의 한마디 
	$("[name=painting_sentence_text]").keyup(function () {
		// 남은 글자 수를 구합니다.
		var inputLength = getCharCount($(this).val());    
		var remain = 200 - inputLength;

		$('#paintingSentenceCount').html(inputLength);

		if (remain >= 0) {
			$('#paintingSentenceCount').css('color', 'black');
		} else {
			$('#paintingSentenceCount').css('color', 'red');
		}
	});

	$("[name=painting_sentence_text]").blur(function () {
		var enter = getEnterCount($("[name=painting_sentence_text]"));
		if (enter > 5) {
			//alert("줄바꿈은 5회까지 가능합니다.");
			alert($.i18n.t('alert.purchase.limitEnterCount'));
		}
	});

	$("#update_painting_sentence_btn").on('click', function(){
		if($('#painteeFile').val() == '') {
//			alert('업로드 파일을 선택하세요.');
			alert($.i18n.t('alert.upload.choiceFile'));
			return;
		}

		createPainting();
	});

    delete uploadSuccess;

    // 다국어 처리
    exeTranslation('.base_position', lang);
}

/*
function updatePaintingSentence(paintingSeq) {
	var sentence = $('#painting_sentence_text').val();

	if ($("[name=painting_sentence_text]").val().trim().length == 0) {
		alert($.i18n.t('alert.purchase.emptySentence'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	if (getCharCount($("[name=painting_sentence_text]").val()) > 200) {
		alert($.i18n.t('alert.purchase.exceedSentence'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	var enter = getEnterCount($("[name=painting_sentence_text]"));
	if (enter > 5) {
		alert($.i18n.t('alert.purchase.limitEnterCount'));
		$("[name=painting_sentence_text]").focus();
		return ;
	}

	var param = {};
	param.sentence = sentence;
	param.artistId = userInfo.userId;

	if($('#painting_private').is(":checked")) {
		param.privateAt = 'Y';
	} else {
		param.privateAt = 'N';
	}

	AjaxCall.call(apiUrl+"/painting/"+paintingSeq, param, "PUT", updatePaintingSentenceRes);
}
function updatePaintingSentenceRes(result, status) {
	if(result.errorNo == 0) {
		dataReload(["initMy();", "initFollow();", "initNew();"]);
		selectMenu(3);
		$(".popup_container").hide();
		$(".upload_box").empty();

		boxStatus = "clickedCloseBtn";
		closePopup(); 
	} else {
		alert('error');
	}
}*/
