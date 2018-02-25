package com.noname.trpg.hitlogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.noname.trpg.tools.RenderKit;

public class HitLogic implements InputProcessor {

	private Stage stage;
	private Actor pointerActor = null , characterActor = null;
	private Actor inventory;
	
	
	public HitLogic(Stage stage , Actor chActor) {
		this.stage = stage;
		this.characterActor = chActor;
		this.inventory = new Inventory("Inventory",RenderKit.getSkin());
		stage.addActor(inventory);

	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor( stage );
		multiplexer.addProcessor( this ); // Your screen
		Gdx.input.setInputProcessor( multiplexer );
	}
	
	public void update()
	{
		if(pointerActor!=null)
		{
			Vector2 unp = unproject(Gdx.input.getX() , Gdx.input.getY());
			pointerActor.setPosition(unp.x, unp.y);
		}

		Actor actor = hit();
		
		if(Gdx.input.justTouched())
		{
			if(pointerActor == null && actor != null)
			{
				float len =( new Vector2(characterActor.getX() - actor.getX() , characterActor.getY() - actor.getY())).len();
				
				if(len < 10)
				{
					actor.setVisible(false);
					((Inventory) inventory).addItem(actor);
				}

			}
		}
	}
	
	private Actor hit()
	{
		Vector2 unp = unproject(Gdx.input.getX() , Gdx.input.getY());
		return stage.hit(unp.x , unp.y, true);
	}
	private Vector2 unproject(float x , float y)
	{
		Vector3 picupVec = new Vector3(x , y , 0);
		picupVec = stage.getCamera().unproject(picupVec);
		return new Vector2(picupVec.x, picupVec.y);
	}

	@Override
	public boolean keyDown(int keycode) {

		switch (keycode) {
		case Keys.A:
			((Inventory) inventory).removeAllItem(characterActor);
			break;

		default:
			break;
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
