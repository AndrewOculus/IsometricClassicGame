package com.noname.trpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.trpg.character.Character;
import com.noname.trpg.map.IsometricTiledMapRendererAdapter;
import com.noname.trpg.objects.Tree;
import com.noname.trpg.objects.TreeType;


public class PointOfEntry extends Game {
	
	private Skin skin;
	private Stage stage;
	private IsometricTiledMapRendererAdapter isoTMX;
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	    stage = new Stage(new ScreenViewport());
	    
	    isoTMX = new IsometricTiledMapRendererAdapter(stage.getCamera(), stage.getBatch(), "untitled.tmx");

	    stage.addActor(new Character(stage.getCamera() , 0 , 0));
	    for(int i = -20 ; i < 20 ; i++)
	    	for(int j = -20 ; j < 20 ; j++)
	    		stage.addActor(new Tree(i*200, j*200 ,TreeType.randomTree() ));
	    
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1 , 1 , 1 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
	    isoTMX.render();
		stage.draw();
	    stage.getActors().sort();
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
