/*
 Copyright (c) 2014 Ahomé Innovation Technologies. All rights reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.ait.toolkit.soundmanager.client;

import com.ait.toolkit.core.client.Util;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;

/**
 * SoundManager 2 makes it easier to play audio in your GWT application
 */
public final class SoundManager {

	private SoundManager() {

	}

	public static final String SWF_URL = Util.getModuleBaseUrl() + "soundmanager/swf/";
	private static final SoundManagerResources resources = GWT.create(SoundManagerResources.class);
	private static final SoundManagerConfig DEFAULT_CONFIG = new SoundManagerConfig();

	static {
		Util.injectJs(resources.js());
		DEFAULT_CONFIG.setUrl(SWF_URL);
		DEFAULT_CONFIG.setOnTimeout(new SoundTimeOutHandler() {
			@Override
			public void onTimeOut(SoundStatus status) {
				Window.alert(status.getStatus() + ", " + status.getErrorType());
			}
		});

	}

	public static void start(SoundReadyHandler handler) {
		DEFAULT_CONFIG.setOnReady(handler);
		setup(DEFAULT_CONFIG.getJsObj());
	}

	/**
	 * Normalized method which checks canPlayMIME() and canPlayURL() as needed to estimate the playability of an HTML link; this means both the href and type attributes, if
	 * provided, are checked for matching file extension and/or MIME type patterns.
	 */
	public static boolean canPlayLink(Element link) {
		if (link.getTagName().equalsIgnoreCase(AnchorElement.TAG)) {
			return _canPlayLink(link);
		}
		return false;
	}

	/**
	 * Returns a boolean indicating whether soundManager can play the given MIME type - eg., audio/mp3. The types supported vary based on Flash version and MPEG4 (MovieStar mode)
	 * options.
	 * 
	 * MIME type patterns are as follows:
	 * <ul>
	 * <li/>Defaults: /^audio\/(?:x-)?(?:mp(?:eg|3))\s*;?/i; - eg. audio/mp3 or audio/mpeg
	 * <li>
	 * <li/>MovieStar-only formats: /^audio\/(?:x-)?(?:mp(?:eg|3)|mp4a-latm|aac|speex)\s*;?/i; - eg. audio/m4a or audio/aac
	 * <li>
	 * </ul>
	 */
	public static boolean canPlayMime(Element link) {
		if (link.getTagName().equalsIgnoreCase(AnchorElement.TAG)) {
			return _canPlayMime(link);
		}
		return false;
	}

	/**
	 * Returns a boolean indicating whether soundManager can play the given URL. Playability is determined by a matching URL pattern set at runtime, based on Flash version and
	 * MPEG4 (MovieStar mode) support.
	 */
	public static native boolean canPlayUrl(String url)/*-{
		return $wnd.soundManager.canPlayUrl(url);
	}-*/;

	/**
	 * Clears the event listener set via onPosition(), in the same way it was registered. If the callback is omitted, any and all callbacks registered for the given offset will be
	 * cleared.
	 */
	public static void clearPosition(Sound sound, int offset) {
		clearOnPosition(sound.getId(), offset);
	}

	/**
	 * Clears the event listener set via onPosition(), in the same way it was registered. If the callback is omitted, any and all callbacks registered for the given offset will be
	 * cleared.
	 */
	public static void clearPosition(Sound sound, int offset, SoundPositionHandler handler) {
		clearOnPosition(sound.getId(), offset, handler);
	}

	/**
	 * Clears the event listener set via onPosition(), in the same way it was registered. If the callback is omitted, any and all callbacks registered for the given offset will be
	 * cleared.
	 */
	public static native void clearOnPosition(String id, int offset)/*-{
		return $wnd.soundManager.clearOnPosition(id, offset);
	}-*/;

	/**
	 * Clears the event listener set via onPosition(), in the same way it was registered. If the callback is omitted, any and all callbacks registered for the given offset will be
	 * cleared.
	 */
	public static native void clearOnPosition(String id, int offset, SoundPositionHandler cb)/*-{
		return $wnd.soundManager
				.clearOnPosition(
						id,
						offset,
						function(position) {
							cb.@com.ait.toolkit.soundmanager.client.SoundPositionHandler::onPosition(I)(position);
						});
	}-*/;

	/**
	 * Creates a sound object, supporting an arbitrary number of optional arguments.<br/>
	 * Returns a {@link Sound} object instance. At minimum, a url parameter is required.
	 */
	public static native Sound createSound(String soundUrl)/*-{
		var o = $wnd.soundManager.createSound({
			url : soundUrl
		});
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Creates a sound with the specified ID and URL (simple two-parameter method.)
	 */
	public static native Sound createSound(String id, String soundUrl)/*-{
		var o = $wnd.soundManager.createSound(id, soundUrl);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Stops, unloads and destroys a sound specified by ID.
	 */
	public static native void destroySound(String id)/*-{
		$wnd.soundManager.destroySound(id);
	}-*/;

	/**
	 * Returns the total number of bytes allocated to the Adobe Flash player or Adobe AIR, or 0 if unsupported (Flash 9+ only.) This number may include memory use across all tabs,
	 * browsers etc. See system.totalMemory (livedocs.adobe.com)
	 */
	public static native double getMemoryUse()/*-{
		return $wnd.soundManager.getMemoryUse();
	}-*/;

	/**
	 * Returns the total number of bytes allocated to the Adobe Flash player or Adobe AIR, or 0 if unsupported (Flash 9+ only.) This number may include memory use across all tabs,
	 * browsers etc. See system.totalMemory (livedocs.adobe.com)
	 */
	public static native double getMemoryUseFixed(int fix)/*-{
		return $wnd.soundManager.getMemoryUse().toFixed(fix);
	}-*/;

	/**
	 * Returns an {@link Sound} object specified by ID, or null if a sound with that ID is not found.
	 */
	public static native Sound getSoundById(String id)/*-{
		var o = $wnd.soundManager.getSoundById(id);
		if (!o) {
			return null;
		}
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Starts loading the sound specified by ID, with options if specified. Returns the related sound object.
	 */
	public static native Sound load(String id)/*-{
		var o = $wnd.soundManager.load(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Mutes the sound specified by ID and returns that sound object. If no ID specified, all sounds will be muted and null is returned. Affects muted property
	 */
	public static native Sound mute(String id)/*-{
		var o = $wnd.soundManager.mute(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Mutes the sound specified by ID and returns that sound object. If no ID specified, all sounds will be muted and null is returned. Affects muted property
	 */
	public static native void mute()/*-{
		$wnd.soundManager.mute();
	}-*/;

	/**
	 * Returns a boolean indicating whether soundManager has attempted to and succeeded in initialising. This function will return false if called before initialisation has
	 * occurred, and is useful when you want to create or play a sound without knowing SM2's current state.
	 */
	public static native boolean isOk()/*-{
		return $wnd.soundManager.ok();
	}-*/;

	/**
	 * 
	 * Registers an event listener, fired when a sound reaches or passes a certain position while playing. Position being "listened" for is passed back to event handler. Will also
	 * fire if a sound is "rewound" (eg. via setPosition() to an earlier point) and the given position is reached again. Listeners will be removed if a sound is unloaded. An
	 * optional scope can be passed as well. Note that for multiShot cases, only the first play instance's position is tracked in Flash; therefore, subsequent "shots" will not have
	 * onPosition() events being fired.
	 */
	public static native void onPosition(String id, int offset, SoundPositionHandler callback)/*-{
		$wnd.soundManager
				.onPosition(
						id,
						offset,
						function(p) {
							callback.@com.ait.toolkit.soundmanager.client.SoundPositionHandler::onPosition(I)(p);
						});
	}-*/;

	/**
	 * 
	 * Registers an event listener, fired when a sound reaches or passes a certain position while playing. Position being "listened" for is passed back to event handler. Will also
	 * fire if a sound is "rewound" (eg. via setPosition() to an earlier point) and the given position is reached again. Listeners will be removed if a sound is unloaded. An
	 * optional scope can be passed as well. Note that for multiShot cases, only the first play instance's position is tracked in Flash; therefore, subsequent "shots" will not have
	 * onPosition() events being fired.
	 */
	public static void onPosition(Sound sound, int offset, SoundPositionHandler handler) {
		onPosition(sound.getId(), offset, handler);
	}

	/**
	 * Pauses the sound specified by ID. Does not toggle. Affects paused property (boolean.) Returns the given sound object.
	 */
	public static native Sound pause(String id)/*-{
		var o = $wnd.soundManager.pause(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Pauses all sounds whose playState is >0. Affects paused property (boolean.)
	 */
	public static native void pauseAll()/*-{
		$wnd.soundManager.pauseAll();
	}-*/;

	/**
	 * Pauses the sound specified. Does not toggle. Affects paused property (boolean.) Returns the given sound object.
	 */
	public static Sound pause(Sound sound) {
		return pause(sound.getId());
	}

	/**
	 * Starts playing the sound specified by ID. (Will start loading if applicable, and will play ASAP.) Returns an SMSound (sound object) instance. Example:
	 * soundManager.play('mySound'); Note that the second parameter, options object, is not required and can take almost any argument from the object literal format (eg. volume.)
	 * It is convenient when you wish to override the sound defaults for a single instance.
	 */
	public static native Sound play(String id)/*-{
		var o = $wnd.soundManager.play(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Starts playing the sound specified. (Will start loading if applicable, and will play ASAP.) Returns an SMSound (sound object) instance. Example:
	 * soundManager.play('mySound'); Note that the second parameter, options object, is not required and can take almost any argument from the object literal format (eg. volume.)
	 * It is convenient when you wish to override the sound defaults for a single instance.
	 */
	public static Sound play(Sound sound) {
		return play(sound.getId());
	}

	/**
	 * Destroys any created Sound objects, unloads the flash movie (removing it from the DOM) and restarts the SM2 init process, retaining all currently-set properties.
	 */
	public static native void reboot()/*-{
		$wnd.soundManager.flashLoadTimeout = 0; // When restarting, wait indefinitely for flash
		$wnd.soundManager.onerror = {}; // Prevent an infinite loop, in case it's not flashblock
		$wnd.soundManager.reboot();
	}-*/;

	/**
	 * Resumes and returns the currently-paused sound specified by ID.
	 */
	public static native Sound resume(String id)/*-{
		var o = $wnd.soundManager.resume(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Resumes and returns the currently-paused sound.
	 */
	public static Sound resume(Sound sound) {
		return resume(sound.getId());
	}

	/**
	 * Resumes all currently-paused sounds.
	 */
	public static native void resumeAll()/*-{
		$wnd.soundManager.resumeAll();
	}-*/;

	/**
	 * Sets the stereo pan (left/right bias) of the sound specified by ID, and returns the related sound object. Accepted values: -100 to 100 (L/R, 0 = center.) Affects pan
	 * property.
	 */
	public static native Sound setPan(String id, int volume)/*-{
		var o = $wnd.soundManager.setPan(id, volume);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Sets the stereo pan (left/right bias) of the sound specified by ID, and returns the related sound object. Accepted values: -100 to 100 (L/R, 0 = center.) Affects pan
	 * property.
	 */
	public static Sound setPan(Sound sound, int volume) {
		return setPan(sound.getId(), volume);
	}

	/**
	 * Seeeks to a given position within a sound, specified by miliseconds (1000 msec = 1 second) and returns the related sound object. Affects position property. Can only seek
	 * within loaded sound data, as defined by the duration property.
	 */
	public static native Sound setPosition(String id, int volume)/*-{
		var o = $wnd.soundManager.setPosition(id, volume);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Seeeks to a given position within a sound, specified by miliseconds (1000 msec = 1 second) and returns the related sound object. Affects position property. Can only seek
	 * within loaded sound data, as defined by the duration property.
	 */
	public static Sound setPosition(Sound sound, int volume) {
		return setPosition(sound.getId(), volume);
	}

	/**
	 * Sets the volume of the sound specified by ID and returns the related sound object. Accepted values: 0-100. Affects volume property.
	 */
	public static native Sound setVolume(String id, int volume)/*-{
		var o = $wnd.soundManager.setVolume(id, volume);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Sets the volume of the sound specified and returns the related sound object. Accepted values: 0-100. Affects volume property.
	 */
	public static Sound setVolume(Sound sound, int volume) {
		return setVolume(sound.getId(), volume);
	}

	/**
	 * Starts loading the sound specified by ID, with options if specified. Returns the related sound object.
	 */
	public static native Sound load(String id, SoundConfig config)/*-{
		var o = $wnd.soundManager.load(id,
				config.@com.ait.toolkit.core.client.JsObject::getJsObj()());
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Stops, unloads and destroys a sound specified by ID.
	 */
	public static void destroySound(Sound sound) {
		destroySound(sound.getId());
	}

	/**
	 * Creates a sound object, supporting an arbitrary number of optional arguments.<br/>
	 * Returns a {@link Sound} object instance. At minimum, a url parameter is required.
	 */
	public static Sound createSound(SoundConfig config) {
		return new Sound(createSound(config.getJsObj()));
	}

	/**
	 * Stops playing the sound specified by ID. Returns the related sound object.
	 */
	public static native Sound stop(String id)/*-{
		var o = $wnd.soundManager.stop(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Stops playing the sound specified. Returns the related sound object.
	 */
	public static Sound stop(Sound sound) {
		return stop(sound.getId());
	}

	/**
	 * Stops any currently-playing sounds.
	 */
	public static native void stopAll()/*-{
		$wnd.soundManager.stopAll();
	}-*/;

	/**
	 * Mutes/unmutes the sound specified by ID. Returns the related sound object.
	 */
	public static native Sound toggleMute(String id)/*-{
		var o = $wnd.soundManager.toggleMute(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Mutes/unmutes the sound specified. Returns the related sound object.
	 */
	public static Sound toggleMute(Sound sound) {
		return toggleMute(sound.getId());
	}

	/**
	 * Pauses/resumes play on the sound specified by ID. Returns the related sound object.
	 */
	public static native Sound togglePause(String id)/*-{
		var o = $wnd.soundManager.togglePause(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Pauses/resumes play on the sound specified by ID. Returns the related sound object.
	 */
	public static Sound togglePause(Sound sound) {
		return togglePause(sound.getId());
	}

	/**
	 * Stops loading the sound specified by ID, canceling any current HTTP request. Returns the related sound object. Note that for Flash 8, SoundManager does this by pointing the
	 * sound object to about:blank, which replaces the current one from loading.
	 */
	public static native Sound unload(String id)/*-{
		var o = $wnd.soundManager.unload(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	/**
	 * Stops loading the sound specified, canceling any current HTTP request. Returns the related sound object. Note that for Flash 8, SoundManager does this by pointing the sound
	 * object to about:blank, which replaces the current one from loading.
	 */
	public static Sound unload(Sound sound) {
		return unload(sound.getId());
	}

	public static native Sound unmute(String id)/*-{
		var o = $wnd.soundManager.unmute(id);
		return @com.ait.toolkit.soundmanager.client.Sound::new(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
	}-*/;

	public static Sound unmute(Sound sound) {
		return unmute(sound.getId());
	}

	public static native void unmute()/*-{
		$wnd.soundManager.unmute();
	}-*/;

	private static native boolean _canPlayLink(Element link)/*-{
		return $wnd.soundManager.canPlayLink(link);
	}-*/;

	private static native JavaScriptObject createSound(JavaScriptObject config)/*-{
		return $wnd.soundManager.createSound(config);
	}-*/;

	private static native boolean _canPlayMime(Element link)/*-{
		return $wnd.soundManager.canPlayMIME(link);
	}-*/;

	public static void setUp(SoundManagerConfig config) {
		setup(config.getJsObj());
	}

	private static native void setup(JavaScriptObject config)/*-{
		$wnd.soundManager.setup(config);
	}-*/;

}
