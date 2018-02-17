package com.noname.trpg.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.trpg.tools.RenderKit;

public class Stuff extends Actor implements Comparable<Actor> {

	private Sprite stuffSprite;
	
	public Stuff(float x , float y , StuffType type) {
		super.setX(x);
		super.setY(y);
		
		stuffSprite = new Sprite(RenderKit.getStuff(type));
		float scale = 0.3f;
		stuffSprite.setScale(scale);
		stuffSprite.setOrigin(-stuffSprite.getWidth()*scale,-stuffSprite.getWidth()*scale);
		stuffSprite.setPosition(x, y);
		
		super.setWidth(stuffSprite.getWidth());
		super.setHeight(stuffSprite.getHeight());
	}	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		stuffSprite.draw(batch);
		//System.out.println(getX() + " " + getY());
	}
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return x >= 0 && x < super.getWidth() && y >= 0 && y < super.getHeight() ? this : null;
	}
	@Override
	public int compareTo(Actor o) {
		return -(int) getY() + (int)o.getY();
	}

}
