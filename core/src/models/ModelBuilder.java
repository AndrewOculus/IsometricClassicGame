package models;

import array.ModelsArray;
import stats.State;
import stats.Transform;

public class ModelBuilder {

	public static Model build()
	{
		State state = new State();
		Transform transform = new Transform();
		Model model = new Model();
		state.setExperience(0);
		state.setHealth(100);
		state.setName("admin");
		transform.setAngle(0);
		transform.setX(1500);
		transform.setY(-400);

		model.setState(state);
		model.setTransform(transform);
		
		
		
		ModelsArray.addModel( new ModelRenderer( model));
		
		return model;
	}
	
}
