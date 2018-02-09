package com.noname.srpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import array.ModelsArray;
import array.ThingsArray;
import models.ModelBuilder;
import models.ModelRenderer;
import objects.TreeBuilder;
import things.ThingBuilder;
import things.ThingRenderer;
import tools.FpsShower;
import tools.RenderKit;

public class Root implements Screen {
	private IsometricTiledMapRendererWithSprites ort;
	private TiledMap map;
	private OrthographicCamera camera;
	private ModelRenderer modelRenderer;
	private ThingRenderer thingRenderer;
	
	private FpsShower fpsShower;
	
	public Root () {

		camera = RenderKit.getOC();

		ModelBuilder.build();	
		
		thingRenderer = new ThingRenderer(ThingBuilder.build());
		ort = new IsometricTiledMapRendererWithSprites();
		
		ThingsArray.addModel(thingRenderer);
		TreeBuilder.build();
		
		fpsShower = new FpsShower();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		ort.render();	
		ort.setView(camera);
		camera.update();
		fpsShower.renderFps();		
	}

	@Override
	public void resize(int width, int height) {
		camera = RenderKit.getOC(width, height);
		camera.update();		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
