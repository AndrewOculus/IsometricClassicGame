package com.noname.trpg.hitlogic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;

public class Inventory extends Window implements Comparable<Actor> {


	private Array<Actor> inventoryArray;
	
	public Inventory(String title, Skin skin) {
		super(title, skin);
		inventoryArray = new Array<Actor>();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
	}
	
	public void addItem(Actor item)
	{
		item.setVisible(false);
		inventoryArray.add(item);
	}
	public void removeItem(int item, Actor charActor)
	{
		Actor actor = inventoryArray.removeIndex(item);
		actor.setPosition(charActor.getX(), charActor.getY());
		actor.setVisible(true);
	}
	public void removeAllItem(Actor charActor)
	{
		int size = inventoryArray.size;
		for(int i = 0 ; i < size ; i++)
		{
			removeItem(0,charActor);
			System.out.println("remove");

		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(int i = 0 ; i< inventoryArray.size ; i++)
		{
			Actor actor = inventoryArray.get(i);
			actor.setPosition(getX()+30*i, getY());
			actor.draw(batch, parentAlpha);
		}
	}
	@Override
	public int compareTo(Actor o) {
		return 99999;
	}
	
}
