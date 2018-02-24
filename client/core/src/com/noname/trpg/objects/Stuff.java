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
	private float size;
	private final float sceneSize = 20 , invSize = 50;
	
	public Stuff(float x , float y , StuffType type, DragAndDrop dragAndDrop) {
		super.setX(x);
		super.setY(y);
		setSceneSize();
		stuffSprite = new Sprite(RenderKit.getStuff(type));
		stuffSprite.setSize(size, size);
		stuffSprite.setOrigin(-size/2,-size/2);
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
		stuffSprite.setPosition(getX(), getY() );
		stuffSprite.setSize(size, size);

	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		stuffSprite.draw(batch);
		setBounds(getX(), getY(), size, size);
	}

	@Override
	public int compareTo(Actor o) {
		return (int) (-getY() + o.getY());
	}
	public void setSceneSize()
	{
		this.size = sceneSize;
	}
	public void setInvSize()
	{
		this.size = invSize;
	}
}
