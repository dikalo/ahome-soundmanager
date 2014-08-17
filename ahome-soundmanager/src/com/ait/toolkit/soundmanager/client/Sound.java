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
	 * The number of bytes currently received while loading a sound.
	 */
	public double getBytesLoaded() {
		return JsoHelper.getAttributeAsDouble(jsObj, "bytesLoaded");
	}

	/**
	 * The total number of bytes to be downloaded, while loading a sound.
	 */
	public double getBytesTotal() {
		return JsoHelper.getAttributeAsDouble(jsObj, "bytesTotal");
	}

	/**
	 * Boolean value reflecting the buffering state of a playing or loading object. To be notified when this property changes, see onbufferchange(). Flash 9+ only.
	 */
	public boolean isBuffering() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "isBuffering");
	}

	/**
	 * Boolean value reflecting the state of an RTMP server connection (when serverURL is used to connect to a Flash Media Server or similar RTMP service.) Calls to load or play
	 * will result in a connection attempt being made, and onconnect() ultimately being called.
	 */
	public boolean isConnected() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "connected");
	}

	/**
	 * The current length of the sound, specified in milliseconds. Note that during loading, this property reflects the length of downloaded data, not the full length, until
	 * completely loaded (see whileloading().) For an approximate "full duration" value while loading, see durationEstimate.
	 */
	public double getDuration() {
		return JsoHelper.getAttributeAsDouble(jsObj, "duration");
	}

	/**
	 * The estimated duration of the sound, specified in milliseconds.
	 * 
	 * <p>
	 * Due to the dynamic nature of duration while loading, this attempts to provide the full duration by calculating parseInt((self.bytesTotal/self.bytesLoaded)*self.duration) and
	 * is updated with each whileloading() interval.
	 * </p>
	 * <p>
	 * 
	 * (Flash-only): Once the sound has fully loaded (onload() has fired), durationEstimate will be updated with the final, Flash-calculated value of duration.
	 * <p>
	 * Note that the durationEstimate method works only with Constant Bitrate (CBR)-encoded MP3s due to the consistent data/time assumption. VBR-encoded MP3s will give inaccurate
	 * results.
	 * <p>
	 * 
	 * HTML5 behaviour: Both duration and durationEstimate are updated at regular intervals during loading of HTML5 audio, directly referencing the duration property provided on
	 * the native HTML5 object. Unlike Flash, HTML5 typically gets the true and final duration value by the time playback begins.
	 */
	public double getDurationEstimate() {
		return JsoHelper.getAttributeAsDouble(jsObj, "durationEstimate");
	}

	/**
	 * Boolean property indicating whether a sound object is using HTML5 Audio for playback. Treat as read-only. This should not really be relied on for anything, but it is
	 * provided as an indicator of support on a per-sound basis. isHTML5 is set based on the URL provided to createSound(), and is updated when the URL changes.
	 */
	public boolean isHtml5() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "isHTML5");
	}

	/**
	 * Boolean value indicating load success as returned from Flash. True indicates success, False is a failure. Because of the potential for false positives, duration and other
	 * properties could be checked as a test of whether sound data actually loaded. For more granular state information, see readyState.
	 */
	public boolean isLoaded() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "loaded");
	}

	/**
	 * Numeric value indicating the current playing state of the sound.
	 * <p>
	 * 0 = stopped/uninitialised
	 * <p>
	 * 1 = playing or buffering sound (play has been called, waiting for data etc.)
	 * <p>
	 * Note that a 1 may not always guarantee that sound is being heard, given buffering and autoPlay status.
	 */
	public int getPlayState() {
		return JsoHelper.getAttributeAsInt(jsObj, "playState");
	}

	/**
	 * The current location of the "play head" within the sound, specified in milliseconds (1 sec = 1000 msec).
	 */
	public int getPosition() {
		return JsoHelper.getAttributeAsInt(jsObj, "position");
	}

	/**
	 * Boolean indicating muted status. True/False. Treat as read-only; use mute(), unmute() and toggleMute() methods to affect state.
	 */
	public boolean isMuted() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "muted");
	}

	/**
	 * Numeric value indicating a sound's current load status
	 * <p>
	 * 0 = uninitialised
	 * <p>
	 * 1 = loading
	 * <p>
	 * 2 = failed/error
	 * <p>
	 * 3 = loaded/success
	 * <p>
	 * Under Flash, readyState will move from 0 to 1, and then change to 3 when the sound's onload() event fires (and all bytes have loade.)
	 * <p>
	 * Under HTML5, readyState will move from 0 to 1 when initializing the HTTP request (from load()), and will quickly change to 3 when the sound is ready to play. Note that HTML5
	 * is buffer-based and onload() means "enough audio data has buffered to begin playback".
	 */
	public int getReadyState() {
		return JsoHelper.getAttributeAsInt(jsObj, "readyState");
	}

	/**
	 * Boolean indicating pause status. True/False. Treat as read-only; use pause(), resume() and togglePause() methods to affect state.
	 */
	public boolean isPaused() {
		return JsoHelper.getAttributeAsBoolean(jsObj, "paused");
	}

	/**
	 * Starts playing the given sound, with an optional options object. (Will start loading if applicable, and will play ASAP.)
	 */
	public native Sound play()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.play();
		return this;
	}-*/;

	/**
	 * Stops, unloads and destroys a sound, freeing resources etc.
	 */
	public native void destruct()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.destruct();
	}-*/;

	/**
	 * Starts loading the given sound, with options if specified.
	 */
	public native Sound load()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.load();
		return this;
	}-*/;

	/**
	 * Starts loading the given sound, with options if specified.
	 */
	public native Sound load(SoundConfig config)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.load(config.@com.ait.toolkit.core.client.JsObject::getJsObj()());
		return this;
	}-*/;

	public native void clearPosition()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.clearPosition();
	}-*/;

	/**
	 * Registers an event listener, fired when a sound reaches or passes a certain position while playing. Position being "listened" for is passed back to event handler. Will also
	 * fire if a sound is "rewound" (eg. via setPosition() to an earlier point) and the given position is reached again. Listeners will be removed if a sound is unloaded. An
	 * optional scope can be passed as well.
	 * <p>
	 * Note that for multiShot cases, only the first play instance's position is tracked in Flash; therefore, subsequent "shots" will not have onPosition() events being fired.
	 * <p>
	 * If you need to fire an event relative to the true duration of the sound, reference its duration once the sound has fully-loaded - ie., at or after the onload() event - as
	 * the duration will not be completely accurate until that time. durationEstimate may be referenced before onload(), but it should not be relied on when "precise" timings of
	 * say, < 1 second are desired.
	 */
	public native Sound onPosition(int offset, SoundPositionHandler callback)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer
				.onPosition(
						offset,
						function(p) {
							callback.@com.ait.toolkit.soundmanager.client.SoundPositionHandler::onPosition(I)(p);
						});
		return this;
	}-*/;

	/**
	 * Mutes the given sound. Affects muted property.
	 */
	public native Sound mute()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.mute();
		return this;
	}-*/;

	/**
	 * Pauses the given sound. (Does not toggle.) Affects paused property (boolean.)
	 */
	public native Sound pause()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.pause();
		return this;
	}-*/;

	/**
	 * Resumes the currently-paused sound. Does not affect currently-playing sounds.
	 */
	public native Sound resume()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.resume();
		return this;
	}-*/;

	/**
	 * Sets the stereo pan (left/right bias) of the given sound. Accepted values: -100 to 100 (L/R, 0 = center.) Affects pan property.
	 */
	public native Sound setPan(int value)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.setPan(value);
		return this;
	}-*/;

	/**
	 * Seeks to a given position within a sound, specified by miliseconds (1000 msec = 1 second.) Affects position property.
	 * <p>
	 * Can only seek within loaded sound data, as defined by the duration property.
	 */
	public native Sound setPosition(int value)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.setPosition(value);
		return this;
	}-*/;

	/**
	 * Sets the volume of the given sound. Accepted values: 0-100. Affects volume property.
	 */
	public native Sound setVolume(int value)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.setVolume(value);
		return this;
	}-*/;

	/**
	 * Stops playing the given sound.
	 */
	public native Sound stop()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.stop();
		return this;
	}-*/;

	/**
	 * Mutes/unmutes the given sound. Affected muted property (boolean.)
	 */
	public native Sound toggleMute()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.toggleMute();
		return this;
	}-*/;

	/**
	 * Pauses/resumes play of the given sound. Will also start a sound if it is has not yet been played.
	 */
	public native Sound togglePause()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.togglePause();
		return this;
	}-*/;

	/**
	 * Stops loading the given sound, canceling any current HTTP request.
	 * <p>
	 * Note that for Flash 8, SoundManager does this by pointing the sound object to about:blank, which replaces the current one from loading.
	 */
	public native Sound unLoad()/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.unload();
		return this;
	}-*/;

	/**
	 * Fires when a sound's reported buffering state has changed while playing and/or loading. The current state is reflected in the boolean isBuffering property. Flash 9+ only.
	 */
	public void onBufferChange(SoundEventHandler handler) {
		_addHandler("onbufferchange", handler);
	}

	/**
	 * Fires when a sound using an RTMP serverURL property has attempted to connect, and has either succeeded or failed. Affects connected property. Once connected, a stream can be
	 * requested from the RTMP server.
	 */
	public void onConnect(SoundEventHandler handler) {
		_addHandler("onconnect", handler);
	}

	/**
	 * Fires at least once per sound play instance when Flash encounters a security error when trying to call computeSpectrum() internally. This typically happens when sounds are
	 * 'inaccessible' due to another Flash movie (eg. YouTube) in another tab which has loaded, and may (or may not be) playing sound. Flash attempts to read the "combined" output
	 * to the sound card, and must have security permissions for all sounds as a result. See areSoundsInaccessible() on Adobe for more info.
	 * <p>
	 * If the offending resource causing the security error is closed or becomes inactive(?), the data will become available again. Intermittent availability will result in
	 * intermittent calls to ondataerror().
	 */

	public void onDataError(SoundEventHandler handler) {
		_addHandler("ondataerror", handler);
	}

	/**
	 * Fires when a playing sound has reached its end. By this point, relevant properties like playState will have been reset to non-playing status.
	 */
	public void onFinish(SoundEventHandler handler) {
		_addHandler("onfinish", handler);
	}

	/**
	 * Fires on sound load. Boolean reflects successful load (true), or fail/load from cache (false).
	 * <p>
	 * False value should seemingly only be for failure, but appears to be returned for load from cache as well. This strange behaviour comes from Flash. More detail may be
	 * available from the Flash 8 sound object documentation.
	 * <p>
	 * Failure can occur if the Flash sandbox (security) model is preventing access, for example loading SoundManager 2 on the local file system and trying to access an MP3 on a
	 * network (or internet) URL. (Security can be configured in the Flash security panel.)
	 */
	public native void onLoad(SoundLoadHandler handler)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer.onload = function(s) {
			var s = this;
			var sound = @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
			handler.@com.ait.toolkit.soundmanager.client.SoundLoadHandler::onLoad(Lcom/ait/toolkit/soundmanager/client/Sound;Z)(sound,s);
		}
	}-*/;

	/**
	 * Fires when a sound pauses, eg. via sound.pause().
	 */
	public void onPause(SoundEventHandler handler) {
		_addHandler("onpause", handler);
	}

	/**
	 * Fires when sound.play() is called.
	 */
	public void onPlay(SoundEventHandler handler) {
		_addHandler("onplay", handler);
	}

	/**
	 * Fires when a sound resumes playing, eg. via sound.resume().
	 */
	public void onResume(SoundEventHandler handler) {
		_addHandler("onresume", handler);
	}

	public void onStop(SoundEventHandler handler) {
		_addHandler("onstop", handler);
	}

	public void onId3(SoundEventHandler handler) {
		_addHandler("onid3", handler);
	}

	public void whileLoading(SoundEventHandler handler) {
		_addHandler("whileloading", handler);
	}

	public void whilePlaying(SoundEventHandler handler) {
		_addHandler("whileplaying", handler);
	}

	private native void _addHandler(String event, SoundEventHandler handler)/*-{
		var peer = this.@com.ait.toolkit.core.client.JsObject::getJsObj()();
		peer[event] = function() {
			var s = this;
			var sound = @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
			handler.@com.ait.toolkit.soundmanager.client.SoundEventHandler::onEvent(Lcom/ait/toolkit/soundmanager/client/Sound;)(sound);
		}
	}-*/;

}
