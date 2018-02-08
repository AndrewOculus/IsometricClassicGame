package models;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import tools.RenderKit;

public class ModelController {

	private Vector2 input;
	private Vector3 followPoint = null;
	private ModelRenderer modelRenderer;

	public ModelController(ModelRenderer modelRenderer)
	{
		this.modelRenderer = modelRenderer;
		input = new Vector2(0,0);
	}
	public void update(float dt , Batch batch)
	{
		/*
		int x = 0, y = 0;
		
		if(Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed(Keys.UP))
			y +=1;
		if(Gdx.input.isKeyPressed(Keys.S)||Gdx.input.isKeyPressed(Keys.DOWN))
			y -=1;
		if(Gdx.input.isKeyPressed(Keys.A)||Gdx.input.isKeyPressed(Keys.LEFT))
			x -=1;
		if(Gdx.input.isKeyPressed(Keys.D)||Gdx.input.isKeyPressed(Keys.RIGHT))
			x +=1;
		
		//input = input.set(x, y);
		//input.nor();
		*/
		Sprite debugSprite = RenderKit.getPointerSprite();
		
		OrthographicCamera camera = RenderKit.getOC();

		if(Gdx.input.isTouched())
		{
			followPoint = new Vector3(Gdx.input.getX() ,Gdx.input.getY(), 0);
			followPoint = camera.unproject(followPoint);
		}
		
		if(followPoint!=null)
		{
			Vector2 personPosition = new Vector2(modelRenderer.getModel().getTransform().getX() ,modelRenderer.getModel().getTransform().getY() );
			
			
			Vector2 autoWay = new Vector2(-personPosition.x + followPoint.x , -personPosition.y + followPoint.y);
			if(autoWay.len() > 1)
				input = autoWay.nor();
			else
				input.set(0, 0);

			debugSprite.setPosition(followPoint.x, followPoint.y);
			debugSprite.draw(batch);
		}

	}
	public Vector2 getInput()
	{
		return input;
	}
	public STATE getState()
	{
		if(input.x > 0.45)
			return STATE.left;
		if(input.x < -0.45)
			return STATE.right;
		if(input.y < -0.45)
			return STATE.down;
		if(input.y > 0.45)
			return STATE.up;
		return STATE.none;
	}
}
