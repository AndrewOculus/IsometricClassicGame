package array;

import com.badlogic.gdx.utils.Array;

import models.ModelRenderer;
import things.ThingRenderer;

public class ThingsArray {

	private static Array<ThingRenderer> models;
	
	private static void Init()
	{
		if(models == null)
			models = new Array<ThingRenderer>();
	}
	
	public static void addModel(ThingRenderer modelRenderer)
	{
		Init();
		models.add(modelRenderer);
	}
		
	public static Array<ThingRenderer> getArray()
	{
		Init();
		return models;
	}
	
	
}
