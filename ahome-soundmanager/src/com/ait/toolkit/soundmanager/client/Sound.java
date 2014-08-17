package com.ait.toolkit.soundmanager.client;

import com.ait.toolkit.core.client.JsObject;
import com.ait.toolkit.core.client.JsoHelper;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Sound object create by the {@link SoundManager}
 */
public class Sound extends JsObject {

	Sound(JavaScriptObject peer) {
		jsObj = peer;
	}

	public String getId() {
		return JsoHelper.getAttribute(jsObj, "id");
	}

	/**
	 * Starts playing the given sound, with an optional options object. (Will start loading if applicable, and will play ASAP.)
	 */
	public native Sound play()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.play();
		return this;
	}-*/;

}
