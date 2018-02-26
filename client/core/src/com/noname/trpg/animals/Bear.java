package com.noname.trpg.animals;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Bear extends Actor implements Comparable<Actor> {

	private TextureRegion[][] chatAnimAtlas;
	private Animation<?>[] animations;
	private Animation<?> currentAnimation;
	private TextureRegion currentRegion;
	private Texture charAtlas;
	private float stateTime = 0;
	private Vector2 velocity = new Vector2();
	private Vector2 targetPoint = new Vector2();

	
	public Bear(float x, float y) {
		animationInit();
		targetPoint.set(x, y);
		setX(x);
		setY(y);
	}
	@Override
	public void act(float delta) {
		super.act(delta);

		stateTime+=delta;
		if(stateTime > 10)
		{
			stateTime = 0;
			Random rand = new Random();
			targetPoint.set((rand.nextFloat() - 1f)*10000, (rand.nextFloat() - 1f)*10000);
		}
		

		velocity.set(targetPoint.x - getX(), targetPoint.y - getY());
		if(velocity.len() > 4)
		{
			velocity.nor();
			setX(getX()+velocity.x);
			setY(getY()+velocity.y);
		}
		
		switch (getState()) {
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

		//setX(pos.x);
		//setY(pos.y);

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(currentRegion, getX() -  currentRegion.getRegionWidth()/2, getY());	
	}
	
	private void animationInit()
	{
		int animCount = 4;
		int animInAtlas = 3;
		int texSize = 48;
		charAtlas = new Texture("bear-atlas.png");
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
			animations[i].setPlayMode(Animation.PlayMode.LOOP);
		}
		currentRegion = (TextureRegion) animations[0].getKeyFrame(2);
	}
	@Override
	public int compareTo(Actor o) {
System.out.println(getY() );
		return (int)(-getY() + o.getY());
	}
	private AnimalState getState()
	{
		if(velocity.x > 0.45)
			return AnimalState.left;
		if(velocity.x < -0.45)
			return AnimalState.right;
		if(velocity.y < -0.45)
			return AnimalState.down;
		if(velocity.y > 0.45)
			return AnimalState.up;
		return AnimalState.none;
	}
}
