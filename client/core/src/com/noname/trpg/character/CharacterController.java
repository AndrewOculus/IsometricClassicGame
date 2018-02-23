package com.noname.trpg.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class CharacterController {
	private Vector2 input;
	private Vector3 followPoint = null;
	private OrthographicCamera camera;
	private Character character;
	private Sprite debugSprite;

	public CharacterController(Camera camera2 , Character character)
	{
		this.character = character;
		this.camera = (OrthographicCamera) camera2;
		
		Texture texture = new Texture("pointer.png");
		debugSprite = new Sprite (texture);
		float scale = 0.7f;
		debugSprite.setScale(scale);
		debugSprite.setOrigin(-debugSprite.getWidth(),-debugSprite.getWidth()*scale);
		
		input = new Vector2(0,0);		
	}
	public void update(float dt)
	{		
		if(Gdx.input.isButtonPressed(1))
		{
			followPoint = new Vector3(Gdx.input.getX() ,Gdx.input.getY(), 0);
			followPoint = camera.unproject(followPoint);
		}
		
		if(followPoint!=null)
		{
			Vector2 personPosition = new Vector2(character.getX() ,character.getY() );
			Vector2 autoWay = new Vector2(-personPosition.x + followPoint.x , -personPosition.y + followPoint.y);
			if(autoWay.len() > 1)
				input = autoWay.nor();
			else
				input.set(0, 0);

			debugSprite.setPosition(followPoint.x, followPoint.y);
		}

	}

	public void draw(Batch batch) {
		debugSprite.draw(batch);
	}
	
	public Vector2 getInput()
	{
		return input;
	}
	public STATE getState()
	{
		if(input.x > 0.45)
			return STATE.left;
		if(input.x < -0.45)
			return STATE.right;
		if(input.y < -0.45)
			return STATE.down;
		if(input.y > 0.45)
			return STATE.up;
		return STATE.none;
	}
}
