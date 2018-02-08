package com.noname.srpg;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class SceneRender implements Comparable<SceneRender> {
	public abstract void render(Batch batch , float dt , OrthographicCamera camera);
	public abstract float getX();
	public abstract float getY();
}
