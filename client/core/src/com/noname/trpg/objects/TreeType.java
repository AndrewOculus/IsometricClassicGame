package com.noname.trpg.objects;

import java.util.Random;

public enum TreeType {
	tree1,tree2,tree3,tree4,tree5;
	
	public static TreeType randomTree()  {
        Random random = new Random();
		return TreeType.values()[random.nextInt(TreeType.values().length)];
	}
}
