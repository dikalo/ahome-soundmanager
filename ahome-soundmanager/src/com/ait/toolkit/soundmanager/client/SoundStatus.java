package com.ait.toolkit.soundmanager.client;

import com.ait.toolkit.core.client.JsObject;
import com.google.gwt.core.client.JavaScriptObject;

public class SoundStatus extends JsObject {

	SoundStatus(JavaScriptObject peer) {
		jsObj = peer;
	}

	public native String getErrorType()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		var error = peer.error;
		if (error) {
			error.type;
		}
		return "";
	}-*/;

	public native String getStatus()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		return peer.status;
	}-*/;

}
