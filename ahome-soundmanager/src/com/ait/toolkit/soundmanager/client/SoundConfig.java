package com.ait.toolkit.soundmanager.client;

import com.ait.toolkit.core.client.JsObject;
import com.ait.toolkit.core.client.JsoHelper;

public class SoundConfig extends JsObject {

	public SoundConfig(String soundUrl) {
		jsObj = JsoHelper.createObject();
		setUrl(soundUrl);
	}

	public void setUrl(String value) {
		JsoHelper.setAttribute(jsObj, "url", value);
	}

}
