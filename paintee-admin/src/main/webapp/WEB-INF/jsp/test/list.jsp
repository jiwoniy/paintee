
<html>

	<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
		<title>test</title>
	</head>
	<body>
	
	<input type="text" name="" class="postcodify_postcode5" value="" />
<button id="postcodify_search_button">검색</button><br />
<input type="text" name="" class="postcodify_address" value="" /><br />
<input type="text" name="" class="postcodify_details" value="" /><br />
<input type="text" name="" class="postcodify_extra_info" value="" /><br />

<!-- jQuery와 Postcodify를 로딩한다 -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
		

	</body>

</html>

