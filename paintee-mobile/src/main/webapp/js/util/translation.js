/**
 * 다국어 처리
 * @param area
 * @param lang
 */
function exeTranslation(area, lang) {
    if (!lang){
        if(navigator.appName=="Netscape"){
            if(navigator.language == "ko") lang = "ko";
            else lang = "en";
        }else{
            if(navigator.browserLanguage.substr(0,2) == "ko") lang = "ko";
            else lang = "en";
        }
    }
	
    
	$.i18n.init({
		
		lng: lang,
		debug: false,
		useLocalStorage: false,
		localStorageExpirationTime: 86400000, 
		resGetPath: 'locales/__lng__/translation.json'
	}, function () {
		$(area).i18n();
	});
}
