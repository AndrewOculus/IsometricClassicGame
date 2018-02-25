package com.noname.trpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.noname.trpg.DragAndDropTest;
import com.noname.trpg.PointOfEntry;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		config.title = "Classic RPG Game";
		new LwjglApplication(new DragAndDropTest(), config);
	}
}
