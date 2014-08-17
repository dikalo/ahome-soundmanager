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

	/**
	 * enable automatic loading (otherwise .load() will call with .play())
	 */
	public void setAutoLoad(boolean value) {
		JsoHelper.setAttribute(jsObj, "autoLoad", value);
	}

	/**
	 * enable playing of file ASAP (much faster if "stream" is true)
	 */
	public void setAutoPlay(boolean value) {
		JsoHelper.setAttribute(jsObj, "autoPlay", value);
	}

	public void setBufferTime(int value) {
		JsoHelper.setAttribute(jsObj, "bufferTime", value);
	}

	/**
	 * "MovieStar" MPEG4 audio mode. Null (default) = auto detect MP4, AAC etc. based on URL. true
	 */
	public void setIsMovieStar(boolean value) {
		JsoHelper.setAttribute(jsObj, "movieStar", value);
	}

	/**
	 * let sounds "restart" or "chorus" when played multiple times.
	 */
	public void setMultiShot(boolean value) {
		JsoHelper.setAttribute(jsObj, "multiShot", value);
	}

	/**
	 * allow events (onfinish()) to fire for each shot, if supported.
	 */
	public void setMultiShotEvents(boolean value) {
		JsoHelper.setAttribute(jsObj, "multiShotEvents", value);
	}

	/**
	 * "pan" settings, left-to-right, -100 to 100
	 */
	public void setPan(int value) {
		JsoHelper.setAttribute(jsObj, "pan", value);
	}

	/**
	 * <b>Note: Experimental feature</b>.<br/>
	 * Only for use with RTMP streaming, ie., Flash Media Server and similar servers. The RTMP server address which Flash will connect to using a NetStream object. Only the server
	 * address is specified here, when RTMP is in use; the url property is used to point to a specific resource on the server.
	 */
	public void setServerUrl(String value) {
		JsoHelper.setAttribute(jsObj, "serverUrl", value);
	}

	/**
	 * allows playing before entire file has loaded (recommended)
	 */
	public void setStream(boolean value) {
		JsoHelper.setAttribute(jsObj, "steam", value);
	}

	/**
	 * MIME-like hint for canPlay() tests, eg. 'audio/mp3'
	 */
	public void setType(String value) {
		JsoHelper.setAttribute(jsObj, "type", value);
	}

	/**
	 * enable crossdomain.xml request for remote domains (for ID3/waveform access)
	 */
	public void setUsePolicyFile(boolean value) {
		JsoHelper.setAttribute(jsObj, "usePolicyFile", value);
	}

	/**
	 * self-explanatory. 0-100, the latter being the max.
	 */
	public void setVolume(int value) {
		JsoHelper.setAttribute(jsObj, "volume", value);
	}

	/**
	 * position to end playback within a sound (msec)
	 */
	public void setTo(int value) {
		JsoHelper.setAttribute(jsObj, "to", value);
	}

	// TODO PeakData

	/**
	 * number of times to play the sound.
	 */
	public void setLoops(int value) {
		JsoHelper.setAttribute(jsObj, "loops", value);
	}

	/**
	 * position to start playback within a sound (msec)
	 */
	public void setFrom(int value) {
		JsoHelper.setAttribute(jsObj, "bufferTime", value);
	}

}
