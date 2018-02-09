package com.noname.srpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


class MainMenu implements Screen {
	   private Skin skin;
	   private Stage stage;
	   private Table table;
	   private TextButton startButton;
	   private TextButton quitButton;
	   private Screen scr;
	   private ScreenViewport viewport;

	public MainMenu () {
	      skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	      viewport = new ScreenViewport();
	      stage = new Stage(viewport);
	      table = new Table();
	      table.setWidth(stage.getWidth());
	      table.align(Align.center | Align.top);

	      table.setPosition(0,Gdx.graphics.getHeight());
	      startButton = new TextButton("          Begin          ",skin);
	      quitButton = new TextButton ("          Quit          ",skin);

	      table.padTop(130);

	      table.add(startButton).padBottom(30);

	      table.row();
	      table.add(quitButton);

	      stage.addActor(table);
	      Gdx.input.setInputProcessor(stage);
	      
	      startButton.addListener(new ClickListener() {
	          @Override
	          public void clicked(InputEvent event, float x, float y) {
	             Game game = (Game)Gdx.app.getApplicationListener();
	             scr = game.getScreen();
	             game.setScreen(new Root());
	             stage.dispose();
	             event.stop();
	          }
	       });
	      quitButton.addListener(new ClickListener() {
	    	  @Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		Gdx.app.exit();
	    	}
	      });
	}

	@Override
	public void render (float dt) {
	      Gdx.gl.glClearColor(0.8f, 1, 0.4f, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      stage.act(dt);
	      stage.draw();
	}
	
	@Override
	public void dispose () {
	     stage.dispose();
	     scr.dispose();
	}

	@Override
	public void show() {
	}


	@Override
	public void resize(int width, int height) {		
	
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}
}
