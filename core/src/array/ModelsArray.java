package array;

import com.badlogic.gdx.utils.Array;
import com.noname.srpg.SceneRender;

public class ModelsArray{

	private static Array<SceneRender> models;
	
	private static void Init()
	{
		if(models == null)
			models = new Array<SceneRender>();
	}
	
	public static void addModel(SceneRender modelRenderer)
	{
		Init();
		models.add(modelRenderer);
	}
		
	public static Array<SceneRender> getArray()
	{
		Init();
		return models;
	}
	
}
