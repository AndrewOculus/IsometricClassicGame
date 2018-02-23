package com.noname.trpg.scenecontroller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class SceneController {

	private InventoryWindow window;
	private Actor character;
	
	public class InventoryWindow extends Window implements Comparable<Actor>
	{
		Vector2 vec = new Vector2();
		public InventoryWindow(String title, Skin skin) {
			super(title, skin);
		}
		@Override
		public void act(float delta) {
			super.act(delta);
			if(!isDragging())
			{
				setPosition(character.getX()- vec.x, character.getY()- vec.y);
			}
			else
			{
				vec.set(character.getX() - getX(),character.getY() - getY());
			}
		}
		@Override
		public int compareTo(Actor o) {
			return (int) (-getY() + o.getY()*10);
		}
		
	};
	
	public SceneController( final Stage stage , Skin skin1 , final Actor character,DragAndDrop dragAndDrop)
	{
		final Skin skin = new Skin();
		this.character = character;

		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("cell", new Texture("cell_.png"));
		skin.add("drop", new Texture("drop_.png"));
		
		window = new InventoryWindow("Inventary",skin1);
		window.setSize(250, 200);
		window.setResizable(true);
		window.setKeepWithinStage(true);
		window.setClip(true);
		window.setColor(1, 1, 1, 0.8f);

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
		final Image drop = new Image(skin,"drop");
		drop.setBounds(200, 0, 50, 200);
		window.addActor(drop);
		stage.addActor(window);
	
		for(int i = 0 ; i < 4 ; i++)
		for(int j = 0 ; j < 4 ; j++)
		dragAndDrop.addTarget(new Target(cells[i][j]) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				window.addActor(source.getActor());
				source.getActor().setPosition(getActor().getX() , getActor().getY());
				stage.draw();
			}
		});
		
		dragAndDrop.addTarget(new Target(drop) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.WHITE);
				return true;
			}
			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);				
			}
			

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
				stage.addActor(source.getActor());
				source.getActor().setPosition(character.getX() , character.getY());
				stage.draw();
			}
		});
		
	}
}


