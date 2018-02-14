package com.noname.trpg.tools;

import com.badlogic.gdx.graphics.Texture;
import com.noname.trpg.objects.TreeType;

public class RenderKit {

	private static Texture tree1 , tree2 , tree3, tree4, tree5;
	
	public static Texture getTree(TreeType type)
	{
		switch (type) {
		case tree1:
			if(tree1==null)
			tree1 = new Texture("tree_1.png");
			return tree1;
		case tree2:
			if(tree2==null)
			tree2 = new Texture("tree_2.png");
			return tree2;
		case tree3:
			if(tree3==null)
			tree3 = new Texture("tree_3.png");
			return tree3;
		case tree4:
			if(tree4==null)
			tree4 = new Texture("tree_4.png");
			return tree4;
		case tree5:
			if(tree5==null)
			tree5 = new Texture("tree_5.png");
			return tree5;
			
		}
		return null;
	}
	
}
