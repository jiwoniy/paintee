openFB.init({appId: '235488130138448'});

window.fbAsyncInit = function() {
	FB.init({
		appId      : '235488130138448',
		cookie     : true,  // enable cookies to allow the server to access
		// the session
		xfbml      : true,  // parse social plugins on this page
		version    : 'v2.5' // use graph api version 2.5
	});

	FB.getLoginStatus(function(response) {
		//console.log('언제...');
		//statusChangeCallback(response);
	});

	/*
	function statusChangeCallback(response) {
	    console.log('statusChangeCallback');

	    // The response object is returned with a status field that lets the
	    // app know the current login status of the person.
	    // Full docs on the response object can be found in the documentation
	    // for FB.getLoginStatus().
	    if (response.status === 'connected') {
	      // Logged into your app and Facebook.
	    	console.log('connected');
	    } else if (response.status === 'not_authorized') {
	      // The person is logged into Facebook, but not your app.
	    	console.log('not_authorized');
	    } else {
	      // The person is not logged into Facebook, so we're not sure if
	      // they are logged into this app or not.

	    }
	}
	*/


		// FB.getLoginStatus(function(response) {
		// 		 if (response.status === 'connected') {
		// 			 console.log('Logged in.');
		// 		 }
		// 		 else {
		// 			 console.log('initiate FB login...');
		// 			 FB.login();
		// 		 }
		// 	 });
	// Now that we've initialized the JavaScript SDK, we call
	// FB.getLoginStatus().  This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide.  They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	//    your app or not.
	//
	// These three cases are handled in the callback function.
};

//Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
	js.src ="http://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
