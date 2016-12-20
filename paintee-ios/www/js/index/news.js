// 0: hide, 1:head, 2:contents
var newsStatus = 0;

function showNewsHead(){
    if(newsStatus==0){
        newsStatus = 1;
        $(".news_container").css("top", mainHeight-60);
        $(".news_box").css("top", "0px");
    }
}

function hideNewsHead(){
    if(newsStatus==1){
        $(".news_container").css("top", "100%");
        $(".news_box").css("top", "0px");
        newsStatus = 0;
    }
}

function showNewsContents(){
    if(newsStatus==1){
        newsStatus = 2;
        $(".news_container").css(
            {   "top": 0,
                "background-color": "rgba(0,0,0,0.8)"
            });

        $(".news_box").css(
            {   "top": "10%",
                "width": "80%",
                "background-color": "rgb(255,255,255)",
                "color": "rgb(80,80,80)"
        });
        $("#news_like").find(".header_ico").attr("src", "ico/like_dark.png");
        $("#news_comment").find(".header_ico").attr("src", "ico/comment_dark.png");
        $("#news_post").find(".header_ico").attr("src", "ico/post_dark.png");
        $("#news_follow").find(".header_ico").attr("src", "ico/follows_dark.png");

        $(".news_contents").empty();
        // 사용자의 새로운 좋아요, 댓글, post, follow 소식을 가져올수 있도록 수정되어야 함
        // 현재 임시로 데이터 입력

        addNews("asummer", "like");
        addNews("paintee", "post");
        addNews("heyday31", "like");
        addNews("layla", "like");
        addNews("asummer", "follow");
        addNews("Leah", "like");
        addNews("jjongnigrim", "follow");
        addNews("nia1", "like");
        addNews("jingo", "like");
        addNews("jingo", "follow");
        addNews("asummer", "like");
        addNews("paintee", "post");
        addNews("heyday31", "like");
        addNews("layla", "like");
        addNews("asummer", "follow");
        addNews("Leah", "like");
        addNews("jjongnigrim", "follow");
        addNews("nia1", "like");
        addNews("jingo", "like");
        addNews("jingo", "follow");

        exeTranslation('.news_contents', lang);
    }
}

function hideNewsContents(){
    if(newsStatus==2){
        $(".news_container").css(
            {   "top": mainHeight-60,
                "background-color": "rgba(0,0,0,0)"
        });
        $(".news_box").css(
            {   "top": "0px",
                "width": "288px",
                "background-color": "rgba(25,25,25,0.2)",
                "color": "rgb(255,255,255)"
        });
        newsStatus = 1;
    }
}

$(".news_header").click(function(e){
    showNewsContents();
    e.stopPropagation();
})

$(".news_container").click(function(){
    clearNews();
    $(this).find(".news_contents").empty();
    hideNewsContents();
})

/**
 * 모든 알림을 확인한것으로 초기화
 **/
function clearNews(){
    // 아이콘 수정
    $("#news_like").html('<img class="header_ico" src="ico/like_deact.png">')
    $("#news_comment").html('<img class="header_ico" src="ico/comment_deact.png">')
    $("#news_post").html('<img class="header_ico" src="ico/post_deact.png">')
    $("#news_follow").html('<img class="header_ico" src="ico/follows_deact.png">')

}

/**
 * 새로운 소식 목록
 */

function addNews(name, type) {
	var adder = new News();
	$(adder.build(name, type)).appendTo($(".news_contents"));
	delete adder;
}

function News() {
    this.list = $("<div>").addClass("news_list");
	this.name = $("<span>").addClass("news_list_name");
	this.like = "<span data-i18n='news.like'></span>";
    this.comment = "<span data-i18n='news.comment'></span>";
    this.post = "<span data-i18n='news.post'></span>";
    this.follow = "<span data-i18n='news.follow'></span>";
	this.build = function(name, type) {
		$(this.name).html(name).click(function () {
			showPersonal(name);
		});
        $(this.list).append(this.name);
		if(type == "like") {
			$(this.list).append(this.like);
		}else if(type == "comment") {
			$(this.list).append(this.comment);
		}else if(type == "post") {
			$(this.list).append(this.post);
		}else if(type == "follow") {
			$(this.list).append(this.follow);
		}

		return this.list;
	}
}
