package objects;

import java.util.Random;

import array.ModelsArray;

public class TreeBuilder {

	public static void build()
	{
		for(int i = 0 ; i < 100 ; i++)
		{
			Random rand = new Random();
			
			Tree tree = new Tree(Trees.Tree,900+(int)(rand.nextFloat()*1000),-900+ (int)(rand.nextFloat()*1000));
			ModelsArray.addModel(tree);
		}
	}
	
}
