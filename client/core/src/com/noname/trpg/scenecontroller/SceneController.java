package com.noname.trpg.scenecontroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.noname.trpg.character.Character;
import com.noname.trpg.character.stats.CharacterStats;
import com.noname.trpg.objects.Stuff;
import com.noname.trpg.objects.StuffType;

public class SceneController implements InputProcessor {

	private InventoryWindow inventory;
	private CraftWindow craft;
	private StatsWindow stats;
	private Actor character;
	private Stage stage;
	private DragAndDrop dragAndDrop;
	
	public class StatsTarget extends Target
	{

		public StatsTarget(Actor actor) {
			super(actor);
		}

		@Override
		public void reset(Source source, Payload payload) {
			super.reset(source, payload);
			getActor().setColor(Color.WHITE);

		}
		@Override
		public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
			getActor().setColor(Color.GREEN);
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y, int pointer) {
			stats.addActor(source.getActor());
			source.getActor().setPosition(getActor().getX() , getActor().getY());
			((Stuff)source.getActor()).setInvSize();
			stage.draw();
		}
		
	}
	
	public class StatsWindow extends Window implements Comparable<Actor>{

		private Vector2 localPos = new Vector2();
		private TextButton axeButton;
		private TextButton bowButton;
		private Window craftWindow;
		
		public StatsWindow(String title, Skin skin) {
			super(title, skin);
			craftWindow = this;
			this.setSize(100, 140);
			this.setKeepWithinStage(true);
			this.setClip(true);
			this.setColor(1, 1, 1, 0.8f);
			
			CharacterStats stats_ = ((Character)character).getStats();
			
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
			
			for (Actor item : craftWindow.getChildren()) {
				if(item.getClass().getName().equals(Stuff.class.getName()))
				{
					if(((Stuff)item).getType() == StuffType.bow)
					{
						System.out.println("bow man");
					}
				}
			}
			
		}
		@Override
		public int compareTo(Actor o) {
			//allways top
			return (int) (-getY() + o.getY() + 10000);
		}
	};
	
	public class CraftTarget extends Target
	{
		public CraftTarget(Actor actor) {
			super(actor);
		}

		@Override
		public void reset(Source source, Payload payload) {
			super.reset(source, payload);
			getActor().setColor(Color.WHITE);

		}
		@Override
		public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
			getActor().setColor(Color.GREEN);
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y, int pointer) {
			craft.addActor(source.getActor());
			source.getActor().setPosition(getActor().getX() , getActor().getY());
			((Stuff)source.getActor()).setInvSize();
			stage.draw();
		}
		
	};
	
	public class CraftWindow extends Window implements Comparable<Actor>
	{
		private Vector2 localPos = new Vector2();
		private TextButton axeButton, bowButton , arrowButton;
		private Window craftWindow;
		
		public CraftWindow(String title, Skin skin) {
			super(title, skin);
			craftWindow = this;
			this.setSize(100, 140);
			this.setKeepWithinStage(true);
			this.setClip(true);
			this.setColor(1, 1, 1, 0.8f);
			
			axeButton = new TextButton("axe",skin);
			axeButton.setPosition(25, 60);
			axeButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Stuff isStone = null ;
					Stuff isStick = null ;

					for (Actor item : craftWindow.getChildren()) {

						if(item.getClass().getName().equals(Stuff.class.getName()))
						{
							if(((Stuff)item).getType()==StuffType.stone)
							{
								isStone = (Stuff)item;
							}
							if(((Stuff)item).getType()==StuffType.stick)
							{
								isStick = (Stuff)item;
							}
 						}
						
						if(isStick!=null&&isStone!=null)
						{
							isStick.remove();
							isStone.remove();
							Stuff axe = new Stuff(character.getX(), character.getY(), StuffType.axe, dragAndDrop);
							stage.addActor(axe);
						}
						
					}
				}
			});
			this.addActor(axeButton);
			
			bowButton = new TextButton("bow", skin);
			bowButton.setPosition(60, 60);
			bowButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					super.clicked(event, x, y);
					Stuff isRope = null ;
					Stuff isStick = null ;

					for (Actor item : craftWindow.getChildren()) {

						if(item.getClass().getName().equals(Stuff.class.getName()))
						{
							if(((Stuff)item).getType()==StuffType.rope)
							{
								isRope = (Stuff)item;
							}
							if(((Stuff)item).getType()==StuffType.stick)
							{
								isStick = (Stuff)item;
							}
 						}
						
						if(isStick!=null&&isRope!=null)
						{
							isStick.remove();
							isRope.remove();
							Stuff bow = new Stuff(character.getX(), character.getY(), StuffType.bow, dragAndDrop);
							stage.addActor(bow);
						}
						
					}
				}
			});
			this.addActor(bowButton);
			
			arrowButton = new TextButton("arrow", skin);
			arrowButton.setPosition(25, 90);
			arrowButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Stuff isStone = null ;
					Stuff isStick = null ;

					for (Actor item : craftWindow.getChildren()) {

						if(item.getClass().getName().equals(Stuff.class.getName()))
						{
							if(((Stuff)item).getType()==StuffType.stone)
							{
								isStone = (Stuff)item;
							}
							if(((Stuff)item).getType()==StuffType.stick)
							{
								isStick = (Stuff)item;
							}
 						}
						
						if(isStick!=null&&isStone!=null)
						{
							isStick.remove();
							isStone.remove();
							Stuff arrow = new Stuff(character.getX(), character.getY(), StuffType.arrow, dragAndDrop);
							stage.addActor(arrow);
						}
						
					}
				}
			});
			this.addActor(arrowButton);
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
			//allways top
			return (int) (-getY() + o.getY() + 10000);
		}
	}
	
	public class InventaryTarget extends Target
	{
		public InventaryTarget(Actor actor) {
			super(actor);
		}

		@Override
		public void reset(Source source, Payload payload) {
			super.reset(source, payload);
			getActor().setColor(Color.WHITE);

		}
		@Override
		public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
			getActor().setColor(Color.GREEN);
			return true;
		}

		@Override
		public void drop(Source source, Payload payload, float x, float y, int pointer) {
			inventory.addActor(source.getActor());
			source.getActor().setPosition(getActor().getX() , getActor().getY());
			
			((Stuff)source.getActor()).setInvSize();

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
			((Stuff)source.getActor()).setSceneSize();

			System.out.println(getActor().toString());
		}
		
	};
	public class InventoryWindow extends Window implements Comparable<Actor>
	{
		private Vector2 localPos = new Vector2();
		
		public InventoryWindow(String title, Skin skin) {
			super(title, skin);
			this.setSize(250, 220);
			this.setKeepWithinStage(true);
			this.setClip(true);
			this.setColor(1, 1, 1, 0.8f);
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
			//allways top
			return (int) (-getY() + o.getY() + 10000);
		}
		
	};
	
	public SceneController( final Stage stage , Skin skin1 , final Actor character,DragAndDrop dragAndDrop)
	{
		this.dragAndDrop = dragAndDrop;
		this.stage = stage;
		Skin skin = new Skin();
		this.character = character;
		
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("cell", new Texture("cell_.png"));
		skin.add("drop", new Texture("drop_.png"));
		
		inventory = new InventoryWindow("Inventory",skin1);
		craft = new CraftWindow("Craft", skin1);
		stats = new StatsWindow("Stats", skin1);

		Image[][] cells = new Image[4][4];
		InventaryTarget[][] targets = new InventaryTarget[4][4];
		
		for(int i = 0 ; i < 4 ; i++)
		{
			for(int j = 0 ; j < 4 ; j++)
			{
				cells[i][j] = new Image(skin, "cell");
				cells[i][j].setBounds(i*50, j*50, 50, 50);
				inventory.addActor(cells[i][j]);
			}
		}
		
		Image[] craftCells = new Image[2];
		
		for(int i = 0 ; i < 2 ; i++)
		{
			craftCells[i] = new Image(skin, "cell");
			craftCells[i].setBounds(i*50, 0, 50, 50);
			craft.addActor(craftCells[i]);
		}
		
		Image drop = new Image(skin,"drop");
		drop.setBounds(200, 0, 50, 200);
		inventory.addActor(drop);
		
		Image[] statsCells = new Image[2];
		
		for(int i = 0 ; i < 2 ; i++)
		{
			statsCells[i] = new Image(skin, "cell");
			statsCells[i].setBounds(i*50, 0, 50, 50);
			stats.addActor(statsCells[i]);
		}
		
		stage.addActor(inventory);
		stage.addActor(craft);
		stage.addActor(stats);
	
		for(int i = 0 ; i < 4 ; i++)
			for(int j = 0 ; j < 4 ; j++)
			{
				targets[i][j] = new InventaryTarget(cells[i][j]);
				dragAndDrop.addTarget(targets[i][j]);
			}
		
		dragAndDrop.addTarget(new InventaryDrop(drop));
		
		for(int i = 0 ; i < 2 ; i++)
		{
			dragAndDrop.addTarget(new CraftTarget(craftCells[i]));
		}
		
		for(int i = 0 ; i < 2 ; i++)
		{
			dragAndDrop.addTarget(new StatsTarget(statsCells[i]));
		}
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Keys.I)
		{
			inventory.setVisible(!inventory.isVisible());
		}
		if(keycode == Keys.S)
		{
			craft.setVisible(!craft.isVisible());
		}
		if(keycode == Keys.C)
		{
			stats.setVisible(!stats.isVisible());
		}		
		if(keycode == Keys.A)
		{

		}	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}


