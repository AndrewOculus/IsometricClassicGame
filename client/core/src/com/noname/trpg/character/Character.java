package com.noname.trpg.character;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.trpg.character.stats.CharacterStats;
import com.noname.trpg.tools.RenderKit;

public class Character extends Actor implements Comparable<Actor> {

	private TextureRegion[][] chatAnimAtlas;
	private Animation<?>[] animations;
	private Animation<?> currentAnimation;
	private TextureRegion currentRegion;
	private BitmapFont bitmapFont;
	private Texture charAtlas;
	private CharacterController characterController;
	private float stateTime = 0;
	private CharacterStats characterStats;
	
	
	public Character(Camera camera , float x , float y) {
		characterStats = new CharacterStats("character");
		characterController = new CharacterController(camera , this);
		bitmapFont = RenderKit.getBitmapFont();
		charAnimationInit();
		setX(x);
		setY(y);
		camera.position.set(getX(), getY(), 0);
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
		pos.lerp(new Vector2( getX()+ characterController.getInput().x*dt*characterStats.getCharacterSpeed().x ,getY()+characterController.getInput().y*dt*characterStats.getCharacterSpeed().x), 0.5f);
		
		setX(pos.x);
		setY(pos.y);
		Vector3 campos = new Vector3(getX() , getY(),0);
		getStage().getCamera().position.set( getStage().getCamera().position.lerp(campos, 0.3f));

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		characterController.draw(batch);
		batch.draw(currentRegion, getX() -  currentRegion.getRegionWidth()/2, getY());		
		bitmapFont.draw(batch ,characterStats.getName() , getX()-20 , getY() + 75);
		bitmapFont.draw(batch ,characterStats.getHealth() +" hp" , getX()-20 , getY() + 95);
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
			animations[i].setPlayMode(Animation.PlayMode.LOOP);
		}
		currentRegion = (TextureRegion) animations[0].getKeyFrame(2);
	}

	public CharacterStats getStats()
	{
		return characterStats;
	}
	
	@Override
	public int compareTo(Actor o) {
		return (int) (-getY() + o.getY());
	}
	
	
}
