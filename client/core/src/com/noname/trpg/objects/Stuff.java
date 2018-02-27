package com.noname.trpg.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.noname.trpg.tools.RenderKit;

public class Stuff extends Actor implements Comparable<Actor> {

	private Sprite stuffSprite;
	private 	float scale = 0.1f;
	
	public Stuff(float x , float y , StuffType type, DragAndDrop dragAndDrop) {
		super.setX(x);
		super.setY(y);
		
		stuffSprite = new Sprite(RenderKit.getStuff(type));
		stuffSprite.setScale(scale);
		stuffSprite.setOrigin(-stuffSprite.getWidth()*scale/2,-stuffSprite.getWidth()*scale/2);
		stuffSprite.setPosition(x, y);

		dragAndDrop.addSource(new Source(this) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				return payload;
			}
		});
	}	
	@Override
	public void act(float delta) {
		super.act(delta);
		stuffSprite.setPosition(getX() + stuffSprite.getWidth()*scale/2, getY() + stuffSprite.getHeight()*scale/2);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		stuffSprite.draw(batch);
		setBounds(getX(), getY(), stuffSprite.getWidth()*scale, stuffSprite.getHeight()*scale);
	}

	@Override
	public int compareTo(Actor o) {
		return (int) (-getY() + o.getY());
	}
	public void setScale(float sclae)
	{
		stuffSprite.setScale(scale);
	}
}
