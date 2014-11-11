package com.ait.toolkit.soundmanager.client;

public interface SoundManagerLoadCallback {

	public void onLoadSuccess();

	public void onLoadError(SoundStatus status);
}
