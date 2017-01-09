var baseUploadCount = 5;
var doUploadCount = 0;
var onceAboutUpload = true;
var sourceWidth;
var sourceHeight;


//업로드화면
function upload(){
	if (userID == "") {
		showLogin();
		return ;
	}

    likeInfo();
}
function likeInfo() {
	AjaxCall.call(apiUrl+"/user/me/likedCountInfo", null, "GET", likeInfoRes);
}
function likeInfoRes(result, status) {
	if(result.errorNo == 0) {
		var likeCount = parseInt(result.likeCount, 10);
		var uploadedCount = parseInt(result.uploadedCount, 10);
		var doTotaluploadCount = 1;

		if(likeCount >= baseUploadCount) {
			doTotaluploadCount = Math.floor(likeCount/baseUploadCount) + 1;
		}

		doUploadCount = doTotaluploadCount - uploadedCount;

		boxStatus = "upload"
		$(".upload_container").show();

		initUpload(likeCount, doTotaluploadCount, uploadedCount);
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

//			console.log("width : " + width);
//			console.log("height : " + height);

			//이미지 이면서 1080x1500 인 이미지
			if(!fileType.match(/image\//) || width < 1080 || height < 1500) {
				failUpload();
//				alert($.i18n.t('uploadPop.failContent'));
//				resetUpload();
			} else {
				showCrop(image.src, width, height)
                // successUpload();
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
	formData.append("privateAt", 'N');
	
	formData.append("x", parseInt(croped.x,10));
	formData.append("y", parseInt(croped.y,10));
	formData.append("xWidth", parseInt(croped.width,10));
	formData.append("yWidth", parseInt(croped.height,10));
	
	// 2016-10-04 : ..가로/세로 Problem
	formData.append("rotate", parseInt(croped.rotate,10));

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

        // [tuesday] mainSwiper 순서 하나씩 미룸
		selectMenu(4);
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
function initUpload(likeCount, doTotaluploadCount, uploadedCount){
    $(".upload_box").empty();
    $(".upload_box").removeClass("upload_box_preview");
    var upload = new Upload();
    upload.setTitle("Upload Painting");

//    upload.setContents("당신의 그림이 Post될 때 마다,<br>추가로 업로드할 수 있는 그림의 수가 늘어납니다.<br>지금까지 253회 Post된 당신은 최대 50개의 그림을 올릴 수 있고<br> 지금 <span class='reward_money'>7</span>개 의 그림을 더 올릴 수 있습니다.<br><br><br>업로드를 위해서는<br>가로 사이즈 <b>1080px</b> 세로 사이즈 <b>1440px</b><br>이상의 이미지가 필요합니다.");
    var content = "<span data-i18n='[html]uploadPop.content1'></span>" + 
                  "<div class='upload_count'>♥ " + likeCount + "</div>" +
                  "</span><span data-i18n='[html]uploadPop.content2'></span>" +
                  "<div class='upload_count'>max " + doTotaluploadCount + " paintings</div>" +
//                  "<div class='upload_count'>now " + doUploadCount + " more paintings</div><br><br>"+
                  "</span><span data-i18n='[html]uploadPop.content3'></span><b>" + doUploadCount +
                  "</b></span><span data-i18n='[html]uploadPop.content4'></span>";
    // "<br><br><br><br><span style='color:rgb(150,0,0)'>타인의 저작물을 무단으로 업로드할 경우, 게시물에 대해 재재를 받을 수 있습니다. 자세한 사항은 <b>Terms & Policy</b> 항목을 확인하세요.</span>";
    
    upload.setContents(content);
//    upload.setContents("<span data-i18n='[html]uploadPop.content1'></span>"+likeCount+"<span data-i18n='[html]uploadPop.content2'></span>"+doTotaluploadCount+"<span data-i18n='[html]uploadPop.content3'></span><span class='reward_money'>"+doUploadCount+"</span><span data-i18n='[html]uploadPop.content4'></span>");
    
    
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
    $(".upload_box").removeClass("upload_box_preview");

	var uploadFail = new Upload();
	uploadFail.setTitle("Upload Painting");
	uploadFail.setContents('<span data-i18n="[html]uploadPop.failContent" style="color:rgb(150,0,0)"></span>');
	uploadFail.setBottom("<div class='popup_btn upload_btn uploadFileBox'><label for='painteeFile' class='upload_btn_text'>Select image file </label><img class='icon' src='ico/folder.png'><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></div>");
	uploadFail.buildUpload();

//	console.log(doUploadCount);

    if(doUploadCount > 0) {
        resetUpload();
    }

	delete uploadFail;

	// 다국어 처리
	exeTranslation('.base_position', lang);
}

function successUpload() {
    $(".upload_box").empty();
    $(".upload_box").addClass("upload_box_preview");
    if(cropedPreview){
        $(".upload_box").append(cropedPreview);
    }

    var uploadSuccess = new Upload();

    uploadSuccess.setTitle("Upload Painting");
    uploadSuccess.setContents('<span data-i18n="[html]uploadPop.successContent"></span><br><div class="upload_sentence"><span class="character_counter"><span id="paintingSentenceCount">0</span>/200</span><textarea id="painting_sentence_text" name="painting_sentence_text" class="upload_sentence_textarea" length="200"></textarea>');
    uploadSuccess.setBottom("<div class='popup_cancle_btn upload_btn uploadFileBox'><img class='icon' src='ico/folder.png'><label for='painteeFile' class='upload_btn_text'>Select image file </label></div><div id='update_painting_sentence_btn' class='popup_btn upload_btn'><div class='purchase_btn_text'>Done </div><img class='icon' src='ico/done.png'></div>");
    uploadSuccess.buildUpload();

	//미리보기 생성
/*	var previewFile = document.querySelector('input[name=painteeFile]').files[0];
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
	}*/

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
        $(".upload_box").removeClass("upload_box_preview");
		createPainting();
	});

    delete uploadSuccess;

    // 다국어 처리
    exeTranslation('.base_position', lang);
}

/**
 *  이미지 crop 화면 시작
 *  src : 이미지 주소
 *  originalWidth/originalHeight : 원본 이미지 크기
 *  croped.x: 이미지가 잘리기 시작하는 left 시작점
 *  croped.y: 이미지가 잘리기 시작하는 top 시작점
 *  croped.width: croped.x로부터 잘려지게될 사이즈
 *  croped.height: croped.y로부터 잘려지게될 사이즈
 */

var cropper;
var croped;
var cropedPreview;

function showCrop(src, originalWidth, originalHeight){
    $(".stopper").show();
    $("#crop_original_image").removeAttr("src");
    $("#crop_original_image").attr("src", src);
    var loadChecher = setInterval(function(){
        if(!$("#crop_original_image")[0].complete){
            return false;
        }else{
            clearInterval(loadChecher);
            initCrop(src, originalWidth, originalHeight);
        }
    }, 500);
    $(".crop_container").show();
}
function initCrop(src, originalWidth, originalHeight){
    var cropBox = {
        width: 648,
        height: 900,
        top: 20,
        left: 200
    };

//    console.log("originalWidth : " + originalWidth);
//    console.log("originalHeight : " + originalHeight);

    cropBox.height  = mainHeight*0.8;
    cropBox.width   = cropBox.height*0.72;
    if(cropBox.width>mainWidth*0.8){
        cropBox.width   = mainWidth*0.8;
        cropBox.height  = mainWidth*10/9;
    }
    cropBox.top     = (mainHeight-cropBox.height)/2;
    cropBox.left    = (mainWidth-cropBox.width)/2;

    var cropCanvas = {
        width: originalWidth*cropBox.width/1080,
        height: originalHeight*cropBox.height/1500,
        top: 0,
        left: 0
    };
    cropCanvas.top     = (mainHeight-cropCanvas.height)/2;
    cropCanvas.left    = (mainWidth-cropCanvas.width)/2;

    var original = $("#crop_original_image")[0];
    var originalRatio = cropBox.width/1080;

    sourceWidth = originalWidth;
    sourceHeight = originalHeight;
    previewRatio = originalRatio;

    cropper = new Cropper(original, {
        viewMode:1,
        dragMode:'move',
        background:false,
        cropBoxMovable:false,
        cropBoxResizable:false,
        zoomable:true,
        toggleDragModeOnDblclick:false,
        responsive: false,
        aspectRatio:18/25,
        ready: function(){
            if(this.cropper.getImageData().rotate>0){
                cropCanvas.width = cropCanvas.width+cropCanvas.height;
                cropCanvas.height = cropCanvas.width-cropCanvas.height
                cropCanvas.width = cropCanvas.width-cropCanvas.height;
                cropCanvas.top = (mainHeight-cropCanvas.height)/2;
                cropCanvas.left = (mainWidth-cropCanvas.width)/2;
            }
            this.cropper.setCanvasData(cropCanvas);
            this.cropper.setCropBoxData(cropBox);
            $(".stopper").hide();
        },
        zoom: function(e){
//            console.log(e.detail.ratio);
            if(e.detail.ratio>originalRatio){
                e.preventDefault();
            }
        }
    });
}

$(".crop_confirm_btn").click(function(){
    croped = cropper.getData();
//    console.log(croped);
/*    cropedPreview = cropper.getCroppedCanvas({
        width: $(".upload_box").width(),
        height: $(".upload_box").height()
    });*/
    cropedPreview = $("<div>").addClass("upload_preview").css({
        width: $(".upload_box").width(),
        height: $(".upload_box").height()
    });

    var previewRatio=$(".upload_box").width()/croped.width;
//    console.log(sourceWidth);
//    console.log(sourceHeight);
//    console.log("ratio : " + previewRatio);
//    console.log("width : " + sourceWidth*previewRatio);
//    console.log("height : " + sourceHeight*previewRatio);
//    console.log("left : " + croped.x*previewRatio);
//    console.log("top : " + croped.y*previewRatio);
//    console.log("croped.rotate : " + croped.rotate);

    cropedImg = $("<img>").attr("src", $("#crop_original_image").attr("src"));

//    console.log("rotate : " + croped.rotate);

    if(croped.rotate==90){
        cropedImg.css("transform-origin", "0px 0px");
        cropedImg.css("transform", "rotate("+croped.rotate+"deg)");
        cropedImg.css({
            width: sourceWidth*previewRatio,
            height: sourceHeight*previewRatio,
            left: -(croped.x*previewRatio)+sourceHeight*previewRatio,
            top: -(croped.y*previewRatio)
        });
    }else{
        cropedImg.css({
            width: sourceWidth*previewRatio,
            height: sourceHeight*previewRatio,
            left: -(croped.x*previewRatio),
            top: -(croped.y*previewRatio)
        });
    }

    cropedPreview.append(cropedImg);
    $(".crop_container").hide();
    cropper.destroy();
    successUpload();
})
$(".crop_return_btn").click(function(){
    $(".crop_container").hide();
    cropper.destroy();
})

// checkPainteeFile -> initCrop -> successUpload (preview 수정) -> createPainting (cropCenter 수정)
