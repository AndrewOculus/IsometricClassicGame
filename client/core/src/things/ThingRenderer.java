package things;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.noname.srpg.SceneRender;

import tools.RenderKit;

public class ThingRenderer extends SceneRender {

	private Sprite thingSprite;
	private ThingState thingState;
	
	public ThingRenderer(ThingState thingState){
		
		this.thingState = thingState;
		switch (thingState.getThingClass()) {
		case AID:
			thingSprite = RenderKit.getAidSprite();
			break;

		default:
			break;
		}
	}
	@Override
	public void render(Batch batch,float dt,OrthographicCamera camera)
	{
	
		thingSprite.setPosition(thingState.getX(), thingState.getY());
		if(!thingState.isTaken())
			thingSprite.draw(batch);
	}

	public Sprite getThingSprite() {
		return thingSprite;
	}

	public void setThingSprite(Sprite thingSprite) {
		this.thingSprite = thingSprite;
	}

	public ThingState getThingState() {
		return thingState;
	}

	public void setThingState(ThingState thingState) {
		this.thingState = thingState;
	}
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return thingState.getX();
	}
	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return thingState.getY();
	}
	@Override
	public int compareTo(SceneRender o) {
		// TODO Auto-generated method stub
		return -(int) getY() + (int)o.getY();	}
	
}
