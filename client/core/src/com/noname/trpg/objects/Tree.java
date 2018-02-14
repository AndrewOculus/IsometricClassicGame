package com.noname.trpg.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.trpg.tools.RenderKit;

public class Tree extends Actor implements Comparable<Actor>  {

	private Sprite treeSprite;
	private TreeType type;
	
	public Tree(float x , float y , TreeType type) {
		
		this.type = type;
				
		Texture texture = RenderKit.getTree(TreeType.randomTree());
		
		treeSprite = new Sprite (texture);
		float scale = 1f;
		treeSprite.setScale(scale);
		treeSprite.setOrigin(-treeSprite.getWidth(),-treeSprite.getWidth()*scale);
		setX(x);
		setY(y);
		treeSprite.setPosition(x, y);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		treeSprite.draw(batch);
	}
	
	@Override
	public int compareTo(Actor o) {
		return -(int) getY() + (int)o.getY();
	}

	
}
