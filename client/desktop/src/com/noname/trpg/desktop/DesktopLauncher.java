package com.noname.trpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.noname.trpg.PointOfEntry;
import com.noname.trpg.test.Test;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 640;
		//config.height = 480;
		config.title = "Classic RPG Game";
		new LwjglApplication(new PointOfEntry(), config);
	}
}
