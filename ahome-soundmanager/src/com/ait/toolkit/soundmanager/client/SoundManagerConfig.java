package com.ait.toolkit.soundmanager.client;

import com.ait.toolkit.core.client.JsObject;
import com.ait.toolkit.core.client.JsoHelper;

/**
 * Configuration class for the SoundManager API
 * 
 * @author Alain Ekambi
 * 
 */
public class SoundManagerConfig extends JsObject {

	public SoundManagerConfig() {
		jsObj = JsoHelper.createObject();
	}

	/**
	 * The directory where SM2 can find the flash movies (soundmanager2.swf, soundmanager2_flash9.swf and debug versions etc.) Note that SM2 will append the correct SWF file name,
	 */
	public void setUrl(String value) {
		JsoHelper.setAttribute(jsObj, "url", value);
	}

	/**
	 * For scripting the SWF (object/embed property), 'always' or 'sameDomain'
	 */
	public void setAllowScripAccess(String value) {
		JsoHelper.setAttribute(jsObj, "allowScriptAccess", value);
	}

	/**
	 * SWF background color. N/A when wmode = 'transparent'
	 */
	public void setBgColor(String value) {
		JsoHelper.setAttribute(jsObj, "bgColor", value);
	}

	/**
	 * if console is being used, do not create/write to #soundmanager-debug
	 */
	public void setConsoleOnly(boolean value) {
		JsoHelper.setAttribute(jsObj, "consoleOnly", value);
	}

	/**
	 * enable debugging output (console.log() with HTML fallback)
	 */
	public void setDebugMode(boolean value) {
		JsoHelper.setAttribute(jsObj, "debugMode", value);
	}

	/**
	 * enable debugging output inside SWF, troubleshoot Flash/browser issues
	 */
	public void setDebugFlash(boolean value) {
		JsoHelper.setAttribute(jsObj, "debugFlash", value);
	}

	/**
	 * flash build to use (8 or 9.) Some API features require 9. Note that only Flash 8 and Flash 9 are supported at this time; other values will result in errors.
	 */
	public void setFlashVersion(int value) {
		JsoHelper.setAttribute(jsObj, "flashVersion", value);
	}

	/**
	 * msec affecting whileplaying/loading callback frequency. If null, default of 50 msec is used.
	 */
	public void setFlashPollingInterval(int value) {
		JsoHelper.setAttribute(jsObj, "flashPolingInterval", value);
	}

	/**
	 * msec affecting whileplaying/loading callback frequency. If null, native HTML5 update events are used.
	 */
	public void setHtml5PollingInterval(int value) {
		JsoHelper.setAttribute(jsObj, "html5PolingInterval", value);
	}

	/**
	 * HTML5 Audio() format support test. Use /^probably$/i; if you want to be more conservative.
	 */
	public void setHtml5Text(String value) {
		JsoHelper.setAttribute(jsObj, "html5Test", value);
	}

	/**
	 * msec to wait for flash movie to load before failing (0 = infinity)
	 */
	public void setFlashLoadTimeout(int value) {
		JsoHelper.setAttribute(jsObj, "flashLoadTimeout", value);
	}

	/**
	 * if an id is not provided to createSound(), this prefix is used for generated IDs - 'sound0', 'sound1' etc.
	 */
	public void setIdPrefix(String value) {
		JsoHelper.setAttribute(jsObj, "idPrefix", value);
	}

	/**
	 * if true, appends ?ts={date} to break aggressive SWF caching.
	 */
	public void setNoSwfCache(boolean value) {
		JsoHelper.setAttribute(jsObj, "noSwfCache", value);
	}

	/**
	 * overrides useHTML5audio. if true and flash support present, will try to use flash for MP3/MP4 as needed since HTML5 audio support is still quirky in browsers.
	 */
	public void setPreferFlash(boolean value) {
		JsoHelper.setAttribute(jsObj, "preferFlash", value);
	}

	/**
	 * use console.log() if available (otherwise, writes to #soundmanager-debug element)
	 */
	public void setUseConsole(boolean value) {
		JsoHelper.setAttribute(jsObj, "useConsole", value);
	}

	/**
	 * requires flashblock.css, see demos - allow recovery from flash blockers. Wait indefinitely and apply timeout CSS to SWF, if applicable.
	 */
	public void setUseFlashBlock(boolean value) {
		JsoHelper.setAttribute(jsObj, "useConsole", value);
	}

	/**
	 * position:fixed flash movie can help increase js/flash speed, minimize lag
	 */
	public void setUseHighPerformance(boolean value) {
		JsoHelper.setAttribute(jsObj, "useHighPerformance", value);
	}

	/**
	 * use HTML5 Audio() where supported. Some browsers may not support "non-free" MP3/MP4/AAC codecs. Ideally, transparent vs. Flash API where possible.
	 */
	public void setUseHtml5Audio(boolean value) {
		JsoHelper.setAttribute(jsObj, "useHTML5Audio", value);
	}

	/**
	 * force SM2 to wait for window.onload() before trying to call soundManager.onready()
	 */
	public void setWaitForWindowLoad(boolean value) {
		JsoHelper.setAttribute(jsObj, "useHTML5Audio", value);
	}

	/**
	 * flash rendering mode - null, 'transparent', or 'opaque' (last two allow z-index)
	 */
	public void setWMode(boolean value) {
		JsoHelper.setAttribute(jsObj, "wmode", value);
	}

	/**
	 * Queues an event callback/handler for successful initialization and "ready to use" state of SoundManager 2. An optional scope parameter can be specified; if none, the
	 * callback is scoped to the window. If onready() is called after successful initialization, the callback will be executed immediately. The onready() queue is processed before
	 * soundManager.onload().
	 */
	public native void setOnReady(SoundReadyHandler callback)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.onready = function() {
			callback.@com.ait.toolkit.soundmanager.client.SoundReadyHandler::onReady()();
		};
	}-*/;

	/**
	 * Queues an event callback/handler for SM2 init failure, processed at (or immediately, if added after) SM2 initialization has failed, just before soundManager.onerror() is
	 * called. An optional scope parameter can be specified; if none, the callback is scoped to the window. Additionally, a status object containing success and error->type
	 * parameters is passed as an argument to your callback.
	 */
	public native void setOnTimeout(SoundTimeOutHandler callback)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.ontimeout = function(s) {
			var status = @com.ait.toolkit.soundmanager.client.SoundStatus::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
			callback.@com.ait.toolkit.soundmanager.client.SoundTimeOutHandler::onTimeOut(Lcom/ait/toolkit/soundmanager/client/SoundStatus;)(status);
		};
	}-*/;

}
