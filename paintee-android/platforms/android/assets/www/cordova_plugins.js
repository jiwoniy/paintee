cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/cordova-plugin-inappbrowser/www/inappbrowser.js",
        "id": "cordova-plugin-inappbrowser.inappbrowser",
        "clobbers": [
            "cordova.InAppBrowser.open",
            "window.open"
        ]
    },
    {
        "file": "plugins/cordova-plugin-inapppurchase/www/index-android.js",
        "id": "cordova-plugin-inapppurchase.InAppBillingV3",
        "merges": [
            "inAppPurchase"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.0",
    "cordova-plugin-inappbrowser": "1.4.0",
    "cordova-plugin-inapppurchase": "1.1.0",
    "cordova-plugin-android-support-v4": "21.0.1"
};
// BOTTOM OF METADATA
});