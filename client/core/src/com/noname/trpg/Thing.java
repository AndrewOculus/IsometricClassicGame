package com.noname.trpg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Thing extends Actor {

	private ThingsType type;
	private Sprite sprite;
	
	public Thing(float x , float y , ThingsType type) {
		setX(x);
		setY(y);
		this.type = type;
		sprite =new Sprite( new Texture("aid.png"));

	}
	public Thing() {
		setX(100);
		setY(100);

		sprite =new Sprite( new Texture("aid.png"));
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
		//sprite.setOrigin(-sprite.getWidth()/2, -sprite.getHeight()/2);
		//sprite.setCenter(-sprite.getWidth()/2, -sprite.getHeight()/2);
		//sprite.setOriginBasedPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}
	@Override
	public void act(float delta) {
		sprite.setX(getX() - sprite.getWidth()/2);
		sprite.setY(getY() - sprite.getHeight()/2);
		super.act(delta);
	}
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return x >= -sprite.getWidth()/2 && x <super.getWidth()-sprite.getWidth()/2 && y >= -sprite.getHeight()/2 && y < super.getHeight()-sprite.getHeight()/2 ? this : null;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//batch.draw(texture,super.getX(), super.getY());
		sprite.draw(batch);
	}
	
}
