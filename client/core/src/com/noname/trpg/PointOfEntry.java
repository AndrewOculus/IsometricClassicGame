package com.noname.trpg;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.trpg.character.Character;
import com.noname.trpg.map.IsometricTiledMapRendererAdapter;
import com.noname.trpg.objects.Stuff;
import com.noname.trpg.objects.StuffType;
import com.noname.trpg.objects.Tree;
import com.noname.trpg.objects.TreeType;


public class PointOfEntry extends Game {
	
	private Skin skin;
	private Stage stage;
	private IsometricTiledMapRenderer isoTMX;
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	    stage = new Stage(new ScreenViewport());
	    
	    isoTMX = new IsometricTiledMapRendererAdapter(stage.getBatch(), "untitled1.tmx");

	    Random random = new Random();
	    stage.addActor(new Character(stage.getCamera() , 0 , 0));
	    for(int i = -20 ; i < 20 ; i++)
	    	for(int j = -20 ; j < 20 ; j++)
	    	{
	    		stage.addActor(new Tree(i*200 + random.nextFloat()*100, j*200 + random.nextFloat()*100 ,TreeType.randomTree()));
	    	}
	    
		stage.addActor(new Stuff(0, 0 ,StuffType.randomStuff()));

	    
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1 , 1 , 1 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
	    isoTMX.render();
	    isoTMX.setView((OrthographicCamera)stage.getCamera());
		stage.draw();
	    stage.getActors().sort();
	    
	    System.out.println(stage.hit(Gdx.input.getX() + stage.getCamera().position.x, Gdx.graphics.getHeight() - Gdx.input.getY() + stage.getCamera().position.y, true));
	}
	
	@Override
	public void dispose () {
		isoTMX.dispose();
		//stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getCamera().viewportWidth = Gdx.graphics.getWidth();
	    stage.getCamera().viewportHeight = Gdx.graphics.getHeight();
	}
}
