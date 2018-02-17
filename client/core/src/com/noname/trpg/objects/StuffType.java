package com.noname.trpg.objects;

import java.util.Random;

public enum StuffType {
 medkit , bow;
 
 public static StuffType randomStuff()  {
     Random random = new Random();
		return StuffType.values()[random.nextInt(StuffType.values().length)];
	}
}
