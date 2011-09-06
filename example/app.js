/**
 * We'll demonstrate how to display a banner ad with the Greystripe module.
 */
var window = Ti.UI.createWindow({ backgroundColor: '#fff' });

/**
 * First, let's set up the module. We need to tell it our applicationId, which is available
 * at the following URL: https://developer.greystripe.com/user/new
 */
Titanium.Greystripe = Ti.Greystripe = require('ti.greystripe');
Ti.Greystripe.setup({
    applicationId: '<<< YOUR APP ID HERE >>>' 
});

/**
 * Now let's add a banner ad at the top of our app.
 */
var view = Ti.Greystripe.createView({
    height: 48,
    top: 0, left: 0, right: 0
});
window.add(view);

/**
 * We can listen for certain events on the ad -- "success" and "fail".
 */
view.addEventListener('success', function() {
    alert('Displayed the ad!');
});
view.addEventListener('fail', function() {
    alert('The ad could not be displayed!');
});

/**
 * Finally, we'll open the window and log the current device's MAC Address. Note that you
 * can use use this MAC Address to set your device to receive test or production ads from
 * Greystripe! Look for the "Devices" tab on Greystripe's website to find out more.
 */
Ti.API.info(Ti.Platform.macaddress);
window.open();