package com.noname.trpg.scenecontroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class SceneController implements InputProcessor {

	private InventoryWindow window;
	private Actor character;
	private Stage stage;
	
	public class InventaryTarget extends Target
	{
		public InventaryTarget(Actor actor) {
			super(actor);
		}

		@Override
		public void reset(Source source, Payload payload) {
			super.reset(source, payload);
			getActor().setColor(Color.WHITE);
			System.out.println("reset");
		}
		@Override
		public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
			getActor().setColor(Color.GREEN);

			if(getActor()!= null)
				return true;
			else
				return false;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y, int pointer) {
			window.addActor(source.getActor());
			source.getActor().setPosition(getActor().getX() , getActor().getY());
			stage.draw();
		}
		
	};
	public class InventaryDrop extends Target
	{
		public InventaryDrop(Actor actor) {
			super(actor);
		}

		@Override
		public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y, int pointer) {
			stage.addActor(source.getActor());
			source.getActor().setPosition(character.getX() , character.getY());
			stage.draw();
			System.out.println(getActor().toString());
		}
		
	};
	public class InventoryWindow extends Window implements Comparable<Actor>
	{
		private Vector2 localPos = new Vector2();
		
		public InventoryWindow(String title, Skin skin) {
			super(title, skin);
	
			}
		@Override
		public void act(float delta) {
			super.act(delta);
			if(!isDragging())
			{
				setPosition(character.getX() - localPos.x, character.getY() - localPos.y);
			}
			else
			{
				localPos.set(character.getX() - getX(),character.getY() - getY());
			}
		}
		@Override
		public int compareTo(Actor o) {
			return (int) (-getY() + o.getY() + 10000);
		}
		
	};
	
	public SceneController( final Stage stage , Skin skin1 , final Actor character,DragAndDrop dragAndDrop)
	{
		this.stage = stage;
		Skin skin = new Skin();
		this.character = character;
		
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("cell", new Texture("cell_.png"));
		skin.add("drop", new Texture("drop_.png"));
		
		window = new InventoryWindow("Inventary",skin1);
		window.setSize(250, 250);
		window.setResizable(true);
		window.setKeepWithinStage(true);
		window.setClip(true);
		window.setColor(1, 1, 1, 0.8f);

		Image[][] cells = new Image[4][4];
		InventaryTarget[][] targets = new InventaryTarget[4][4];
		
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
		drop.setBounds(200, 0, 50, 200);
		window.addActor(drop);
		stage.addActor(window);
	
		for(int i = 0 ; i < 4 ; i++)
			for(int j = 0 ; j < 4 ; j++)
			{
				targets[i][j] = new InventaryTarget(cells[i][j]);
				dragAndDrop.addTarget(targets[i][j]);
			}
		
		dragAndDrop.addTarget(new InventaryDrop(drop));
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public boolean keyDown(int keycode) {

		System.out.println(keycode);
		if(keycode == Keys.I)
		{
			window.setVisible(!window.isVisible());
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}


