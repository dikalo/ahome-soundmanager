ahome-soundmanager
==================

Easy to use cross-platform sound capabilities for (GWT) projects.


##Getting started with ahome-soundmananger
1) Download the jar from our opensource repository : opensource.ahome-it.com

2) Inherit the SoundManager module

```xml
   <inherits name="com.ait.toolkit.soundmanager.SoundManager" />
```

3) Start playing sound and enjoy

```java
package com.ait.demo;

import com.ait.toolkit.soundmanager.client.SoundManager;
import com.ait.toolkit.soundmanager.client.SoundReadyHandler;

/**
 * This is the entry point to the client portion of the web application.
 */
@EntryPoint
public class Demo {

	@PostConstruct
	public void init() {
		SoundManager.start(new SoundReadyHandler() {
			@Override
			public void onReady() {
			   SoundManager.createSound("pathToMySoundFile").play();
			}
		});
	}

}

```
