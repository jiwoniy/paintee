cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "cordova-plugin-inappbrowser.inappbrowser",
        "file": "plugins/cordova-plugin-inappbrowser/www/inappbrowser.js",
        "pluginId": "cordova-plugin-inappbrowser",
        "clobbers": [
            "cordova.InAppBrowser.open",
            "window.open"
        ]
    },
    {
        "id": "cordova-plugin-inapppurchase.PaymentsPlugin",
        "file": "plugins/cordova-plugin-inapppurchase/www/index-ios.js",
        "pluginId": "cordova-plugin-inapppurchase",
        "clobbers": [
            "inAppPurchase"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-inappbrowser": "1.4.0",
    "cordova-plugin-whitelist": "1.3.0",
    "cordova-plugin-inapppurchase": "1.1.0",
    "cordova-plugin-android-support-v4": "21.0.1"
};
// BOTTOM OF METADATA
});