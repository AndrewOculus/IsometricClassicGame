package com.noname.trpg.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.trpg.objects.StuffType;
import com.noname.trpg.objects.TreeType;

public class RenderKit {

	private static Sprite tree1 , tree2 , tree3, tree4, tree5, aid, bow;
	private static Skin skin;
	private static BitmapFont bitmapFont;
	
	public static Sprite getTree(TreeType type)
	{
		switch (type) {
		case tree1:
			if(tree1==null)
				tree1 = new Sprite(new Texture("tree_1.png"));
			return tree1;
		case tree2:
			if(tree2==null)
				tree2 = new Sprite(new Texture("tree_2.png"));
			return tree2;
		case tree3:
			if(tree3==null)
				tree3 = new Sprite(new Texture("tree_3.png"));
			return tree3;
		case tree4:
			if(tree4==null)
				tree4 = new Sprite(new Texture("tree_4.png"));
			return tree4;
		case tree5:
			if(tree5==null)
				tree5 = new Sprite(new Texture("tree_5.png"));
			return tree5;
			
		}
		return null;
	}
	public static Skin getSkin() {
		if(skin == null)
			skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		return skin;
	}
	public BitmapFont getBitmapFont()
	{
		if(bitmapFont==null)
			bitmapFont = new BitmapFont();
		return bitmapFont;
	}
	public static Sprite getStuff(StuffType type)
	{
		switch (type) {
		case medkit:
			if(aid==null)
				aid = new Sprite(new Texture("aid.png"));
			return aid;
		case bow:
			if(bow==null)
				bow = new Sprite(new Texture("bow.png"));
			return bow;
			
		}
		return null;
	}
	
}
