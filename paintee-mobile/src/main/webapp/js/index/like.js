
// 좋아요 애니메이션
/**
 * 좋아요 선택 시 호출되는 함수
 */
function riseBubble(bubble, paintingId, artistId){
	if (!userInfo) {
		alert($.i18n.t('alert.common.notLogin'));
		return;
	}
	var controller = new PaintingLikeController(bubble, paintingId, artistId);
	controller.addPaintingLike();
}

function dropBubble(bubble, paintingId, artistId){
	if (!userInfo) {
		alert($.i18n.t('alert.common.notLogin'));
		return;
	}	
	var controller = new PaintingLikeController(bubble, paintingId, artistId);
	controller.cancelPaintingLike();
}

// 좋아요 전체 목록 보기
function showLikes(paintingId){
	// 히스토리 설정
	replaceHistory({"call": "followPop"});
    addHistory({"call": "dummy"});

	setBox();
	$(".people_container").show();
	$(".people_box").empty();
	var people = new People();
	people.setTitle("Likes");
	people.buildUpload();

	new PaintingLikeController(null, paintingId).getLikeUserList();
}

function addLikes(name, isfriend) {
	var adder = new Follows();
	$(adder.build(name, isfriend)).appendTo($(".people_contents"));
	delete adder;
}

function Likes() {
	this.likes = $("<div>").addClass("people_list");
	this.name = $("<div>").addClass("people_list_name");
	this.btn = $("<div>").addClass("people_list_add")
			             .html("<div class='people_list_btn_text'> </div><img class='icon' src='ico/add_black.png'>");
	this.freind = $("<div>").addClass("people_list_add")
			                .html("<div class='people_list_btn_text'> </div><img class='icon img_transparent' src='ico/done.png'>");
	this.build = function(name, isfriend) {
		$(this.name).html(name).click(function () {
			history.back();
			showPersonal(name);
		});;
		$(this.likes).append(this.name);
		if (isfriend) {
			$(this.likes).append(this.freind);
		} else {
			$(this.likes).append(this.btn);
		}
		var btn = this.btn;
		this.btn.click(function () {
			popName = "followPop";
			new FollowController().addFollow(btn, name);
		});

		return this.follows;
	}
}

function PaintingLikeController(bubble, paintingId, artistId) {
	this.bubble = bubble;
	this.paintingId = paintingId;
	this.artistId = artistId;
}

PaintingLikeController.prototype = {
	addPaintingLike: function () {
		var controller = this;
		var data = {
			paintingId: this.paintingId,
			artistId: controller.artistId
		};
		
		AjaxCall.call(
				apiUrl + "/painting/like",
				data, 
				"POST", 
				function (result) {
					controller.addPaintingLikeRes(result);			
				}
		);
	},
	addPaintingLikeRes: function (result) {
		var controller = this;
		// 기존 입력 내용 지우기
	    var listBtnLiked = $("<img>").attr("src", "ico/liked.png").addClass("list_btn_icon").addClass("list_btn_liked").click(function(){dropBubble(this, controller.paintingId, controller.artistId)});
	    var likeSeqCir  = $("<div>").addClass("like_sequence_circle");

	    $(this.bubble).parent().find(".like_sequence").show().find(".like_sequence_circle")
	    .animate({width: "120%", height: "120%", top: "-10%", left: "-10%", opacity: "0"}, 500, "swing", function(){$(this).parent().hide();
        $(this).replaceWith(likeSeqCir)});
	    $("div.list_btn[data-likeId='" + controller.paintingId + "'] > img.list_btn_like, div.tue_btn > img.list_btn_like").replaceWith(listBtnLiked);
	    $("[data-like=like_" + controller.paintingId + "]").html(parseInt($("[data-like=like_" + controller.paintingId + "]").html()) + 1);

	    processDetailLikesNum(1);
	},
	cancelPaintingLike: function () {
		var controller = this;
		var data = {"artistId": controller.artistId};
		
		AjaxCall.call(apiUrl + "/painting/" + controller.paintingId + "/like", 
			data, 
			"DELETE",
			function (result) {
				controller.cancelPaintingLikeRes(result);			
			}
		);
	},
	cancelPaintingLikeRes: function (result) {
		var controller = this;
	    var listBtnLike =$("<img>").attr("src", "ico/like.png").addClass("list_btn_icon").addClass("list_btn_like").click(function(){riseBubble(this, controller.paintingId, controller.artistId)});
	    var likeSeqCir  =$("<div>").addClass("like_sequence_circle");
	    $(this.bubble).parent().find(".like_sequence").show().find(".like_sequence_circle")
	    .animate({width: "120%", height: "120%", top: "-10%", left: "-10%", opacity: "0"}, 500, "swing", function(){$(this).parent().hide();$(this).replaceWith(likeSeqCir)});
	    $("div.list_btn[data-likeId='" + controller.paintingId + "'] > img.list_btn_liked, div.tue_btn > img.list_btn_liked").replaceWith(listBtnLike);
	    $("[data-like=like_" + controller.paintingId + "]").html(parseInt($("[data-like=like_" + controller.paintingId + "]").html()) - 1);
	    // dataReload(["initPopular();"]);

	    processDetailLikesNum(-1);
	},
	//해당 그림을 좋아요 한 사람 목록 요청 AJAX
	getLikeUserList : function() {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/painting/" + controller.paintingId + "/like/users",
			null,
			"GET", 
			function(result) {
				controller.getLikeUserListRes(result);
			}
		);
	},
	// 해당 그림을 좋아요 한 사람 목록 요청 후 처리 함수
	getLikeUserListRes : function(result) {
		for ( var index in result.list) {
			var isFriend = false;
			if(result.list[index].friend > 0) {
				isFriend = true;
			}

			addLikes(result.list[index].userName, isFriend);
		}
	},
};
