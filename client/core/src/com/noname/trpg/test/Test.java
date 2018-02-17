package com.noname.trpg.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class Test extends Game {

	IsometricTiledMapRenderer isoTMX;
	OrthographicCamera camera;
	
	@Override
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
		isoTMX = new IsometricTiledMapRenderer(new TmxMapLoader().load("untitled1.tmx"));
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1 , 1 , 1 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		isoTMX.render();
		isoTMX.setView(camera);
	}

	
}
