package objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.noname.srpg.SceneRender;

import tools.RenderKit;

public class Tree extends SceneRender {

	private Sprite treeSprite;
	private float x , y;
	
	public Tree(Trees trees , float x ,float y)
	{
		this.x = x;
		this.y = y;
		treeSprite = RenderKit.getTreeSprite();
	}

	@Override
	public void render(Batch batch, float dt, OrthographicCamera camera) {

		treeSprite.draw(batch);
		treeSprite.setPosition(x, y);
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public int compareTo(SceneRender o) {
		return -(int) getY() + (int)o.getY();
	}


}
