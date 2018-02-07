package com.noname.srpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

public class Root extends ApplicationAdapter {
	private IsometricTiledMapRendererWithSprites ort;
	private TiledMap map;
	private OrthographicCamera camera;
	private ModelRenderer modelRenderer;
	private ThingRenderer thingRenderer;
	
	private FpsShower fpsShower;
	
	@Override
	public void create () {
		camera = RenderKit.getOC();

		ModelBuilder.build();	
		
		thingRenderer = new ThingRenderer(ThingBuilder.build());
		
		map = new TmxMapLoader().load("untitled.tmx");
		ort = new IsometricTiledMapRendererWithSprites(map,camera);
		
		ThingsArray.addModel(thingRenderer);
		TreeBuilder.build();
		
		fpsShower = new FpsShower();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera = RenderKit.getOC(width, height);
		camera.update();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		ort.render();	
		ort.setView(camera);
		camera.update();
		fpsShower.renderFps();

	}
	
	@Override
	public void dispose () {

	}
}
