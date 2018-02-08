package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FpsShower {

	SpriteBatch batch;
	
	public void renderFps()
	{
		if(batch == null)
			batch = new SpriteBatch();
		
		BitmapFont font = RenderKit.getBF();
		font.setColor(Color.WHITE);
		
		batch.begin();
		font.draw(batch, ((int)(1f/Gdx.graphics.getDeltaTime()))+"", 100, 100);
		batch.end();
	}
	
}
