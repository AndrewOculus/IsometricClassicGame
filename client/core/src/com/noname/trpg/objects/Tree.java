package com.noname.trpg.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.trpg.tools.RenderKit;

public class Tree extends Actor implements Comparable<Actor>  {

	private Sprite treeSprite;
	private float scale = 1f;
	
	public Tree(float x , float y , TreeType type) {
						
		treeSprite = new Sprite (RenderKit.getTree(type));
		treeSprite.setScale(scale);
		treeSprite.setOrigin(-treeSprite.getWidth()*scale/2,0);
		setX(x);
		setY(y);
		treeSprite.setPosition((int)getX(), (int)getY());
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		treeSprite.draw(batch);
	}
	@Override
	public int compareTo(Actor o) {
		return (int) (-getY() + o.getY());
	}
}
