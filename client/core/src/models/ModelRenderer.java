package models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.noname.srpg.SceneRender;

import tools.RenderKit;

public class ModelRenderer extends SceneRender  {

	private Model model;
	private Animation[] animations;
	private Animation currentAnimation;
	private ModelController modelController;
	private ModelInventory modelInventory;
	private float stateTime;
	private TextureRegion currentRegion;
	private BitmapFont bitmapFont;

	public ModelRenderer( Model model )
	{
		animations = RenderKit.getCharAnim();
		modelController = new ModelController(this);
		currentRegion = animations[0].getKeyFrame(stateTime);
		bitmapFont = RenderKit.getBF();
		this.model = model;
		modelInventory = new ModelInventory();
		setModelInventory(modelInventory);
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	@Override
	public void render(Batch batch ,float dt , OrthographicCamera camera)
	{
		stateTime+=dt;
		if(stateTime > 1000)
			stateTime = 0;
		switch (modelController.getState()) {
		case up:
			currentAnimation = animations[3];
			currentRegion = currentAnimation.getKeyFrame(stateTime);

			break;
		case down:
			currentAnimation = animations[0];
			currentRegion = currentAnimation.getKeyFrame(stateTime);

			break;
		case right:
			currentAnimation = animations[1];
			currentRegion = currentAnimation.getKeyFrame(stateTime);

			break;
		case left:
			currentAnimation = animations[2];
			currentRegion = currentAnimation.getKeyFrame(stateTime);

			break;
		case none:
			if(currentAnimation!=null)
			currentRegion = currentAnimation.getKeyFrame(2);
			break;
		default:
			break;
		}

		modelController.update(dt , batch);
		
		model.getTransform().addX(modelController.getInput().x);
		model.getTransform().addY(modelController.getInput().y);
		camera.position.set(model.getTransform().getX(), model.getTransform().getY(),0);

		batch.draw(currentRegion, model.getTransform().getX() -  currentRegion.getRegionWidth()/2, model.getTransform().getY());		
		bitmapFont.draw(batch ,model.getState().getName() , model.getTransform().getX()-20 , model.getTransform().getY() + 75);
		bitmapFont.draw(batch ,model.getState().getHealth()+" hp" , model.getTransform().getX()-20 , model.getTransform().getY() + 95);
	}

	public ModelController getModelController() {
		return modelController;
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}

	public void inventoryRender() {
		if(modelInventory!=null)
		modelInventory.render();
	}

	public void setModelInventory(ModelInventory modelInventory) {
		this.modelInventory = modelInventory;
	}

	@Override
	public float getX() {
		return model.getTransform().getX();
	}

	@Override
	public float getY() {
		return model.getTransform().getY();
	}

	@Override
	public int compareTo(SceneRender o) {
		return -(int) getY() + (int)o.getY();
	}

}
