package com.noname.trpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class DragAndDropTest extends Game {
	Stage stage;
	Window window;

	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		final Skin skin = new Skin();
		Skin skin1 = new Skin(Gdx.files.internal("data/uiskin.json"));

		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("cell", new Texture("cell_.png"));
		skin.add("bow", new Texture("bow.png"));
		skin.add("drop",new Texture("drop_.png"));
		
		window = new Window("Inventary",skin1);
		window.setSize(250, 200);
		window.setResizable(true);
		window.setKeepWithinStage(true);
		window.setClip(true);
		window.setColor(1, 1, 1, 0.8f);

		
		Image sourceImage = new Image(skin, "bow");
		sourceImage.setBounds(50, 50, 50, 50);
		stage.addActor(sourceImage);
		
		Image sourceImage1 = new Image(skin, "bow");
		sourceImage1.setBounds(100, 150, 50, 50);
		stage.addActor(sourceImage1);
		
		Image[][] cells = new Image[4][4];	
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				cells[i][j] = new Image(skin, "cell");
				cells[i][j].setBounds(i*50, j*50, 50, 50);
				window.addActor(cells[i][j]);
			}
		}
		Image drop = new Image(skin,"drop");
		drop.setBounds(200, 50, 50, 100);
		window.addActor(drop);
		stage.addActor(window);
		
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(sourceImage) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("Some payload!");

				payload.setDragActor(new Label("Some payload!", skin));

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = new Label("Some payload!", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);

				return payload;
			}
		});
		
		dragAndDrop.addSource(new Source(sourceImage1) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("Some payload!");

				payload.setDragActor(new Label("Some payload!", skin));

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = new Label("Some payload!", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);

				return payload;
			}
		});
		
		for(int i = 0 ; i < 4 ; i++)
		for(int j = 0 ; j < 4 ; j++)
		dragAndDrop.addTarget(new Target(cells[i][j]) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
				//stage.addActor(source.getActor());
				
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				window.addActor(source.getActor());
				source.getActor().setPosition(getActor().getX() , getActor().getY());
				render();

			}
		});
		
		dragAndDrop.addTarget(new Target(drop) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
				return true;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
				//stage.addActor(source.getActor());
				
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				stage.addActor(source.getActor());
				render();
				source.getActor().setBounds(50, 50, 50, 50);
				source.getActor().setPosition(getActor().getX() , getActor().getY());
				System.out.println(getActor().getX());
			}
		});
		
	}

	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
	}
}