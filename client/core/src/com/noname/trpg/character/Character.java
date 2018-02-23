package com.noname.trpg.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor implements Comparable<Actor> {

	private TextureRegion[][] chatAnimAtlas;
	private Animation<?>[] animations;
	private Animation<?> currentAnimation;
	private TextureRegion currentRegion;

	private Texture charAtlas;
	private CharacterController characterController;
	private float stateTime=0;
	
	public Character(Camera camera , float x , float y) {
		characterController = new CharacterController(camera , this);
		charAnimationInit();
		setX(x);
		setY(y);
	}

	@Override
	public void act(float dt) {
		stateTime+=dt;
		if(stateTime > 1000)
			stateTime = 0;
		switch (characterController.getState()) {
		case up:
			currentAnimation = animations[3];
			currentRegion = (TextureRegion) currentAnimation.getKeyFrame(stateTime);

			break;
		case down:
			currentAnimation = animations[0];
			currentRegion = (TextureRegion) currentAnimation.getKeyFrame(stateTime);

			break;
		case right:
			currentAnimation = animations[1];
			currentRegion = (TextureRegion) currentAnimation.getKeyFrame(stateTime);

			break;
		case left:
			currentAnimation = animations[2];
			currentRegion = (TextureRegion) currentAnimation.getKeyFrame(stateTime);

			break;
		case none:
			if(currentAnimation!=null)
			currentRegion = (TextureRegion) currentAnimation.getKeyFrame(2);
			break;
		default:
			break;
		}

		characterController.update(dt);

		Vector2 pos = new Vector2(getX() , getY());
		pos.lerp(new Vector2( getX()+characterController.getInput().x ,getY()+characterController.getInput().y), 0.5f);
		
		setX(pos.x);
		setY(pos.y);
		
		getStage().getCamera().position.set( pos , 0 );

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		characterController.draw(batch);
		batch.draw(currentRegion, getX() -  currentRegion.getRegionWidth()/2, getY());		
		//bitmapFont.draw(batch ,model.getState().getName() , model.getTransform().getX()-20 , model.getTransform().getY() + 75);
		//bitmapFont.draw(batch ,model.getState().getHealth()+" hp" , model.getTransform().getX()-20 , model.getTransform().getY() + 95);
	
	}

	private void charAnimationInit()
	{

			int animCount = 5;
			int animInAtlas = 8;
			int texSize = 57;
			charAtlas = new Texture("HumanMaleSpritePageA.png");
			chatAnimAtlas = new TextureRegion[animCount][];
			for(int i = 0 ; i < animCount ; i++)
			{
				chatAnimAtlas[i] = new TextureRegion[animInAtlas];
				for(int j = 0; j < animInAtlas ; j++)
				{
					
					chatAnimAtlas[i][j] = new TextureRegion(charAtlas,j*texSize , i*texSize, texSize , texSize);
				}
			} 
			animations = new Animation[animCount];
			for( int i = 0 ; i < animCount ; i++)
			{
				animations[i]  = new Animation(0.1f,chatAnimAtlas[i]);
				animations[i] .setPlayMode(Animation.PlayMode.LOOP);
			}
			currentRegion = (TextureRegion) animations[0].getKeyFrame(2);
	}

	@Override
	public int compareTo(Actor o) {
		return (int) (-getY() + o.getY());
	}
	
	
}
