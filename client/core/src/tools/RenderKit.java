package tools;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class RenderKit
{
	private static ShapeRenderer sr;
	private static SpriteBatch sb;
	private static BitmapFont bf;
	private static Texture charAtlas;
	private static OrthographicCamera camera;
	private static TextureRegion[][] chatAnimAtlas;
	private static Animation[] charAnimation;
	private static Sprite aidSprite ,pointerSprite , treeSprite;
	private static ShapeRenderer shapeRenderer;
	private static TiledMap map;
	
	public static OrthographicCamera getOC(int w , int h)
	{
		if(camera == null)
			camera = new OrthographicCamera(w , h);
		if(camera.viewportHeight != h || camera.viewportWidth != w)
		{
			camera = new OrthographicCamera(w , h);
		}

		return camera;
	}
	public static OrthographicCamera getOC()
	{
		if(camera == null)
			camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());

		return camera;
	}
	public static ShapeRenderer getSR()
	{
		if(sr == null)
			sr = new ShapeRenderer();
		return sr;
	}
	public static SpriteBatch getSB()
	{
		if(sb == null)
			sb = new SpriteBatch();
		return sb;
	}
	public static BitmapFont getBF()
	{
		if(bf == null)
			bf = new BitmapFont();
		return bf;
	}
	private static void charAnimationInit()
	{
		if(charAnimation==null || charAtlas==null || chatAnimAtlas==null)
		{
			int animCount = 5;
			int animInAtlas = 8;
			int texSize = 57;
			charAnimation = new Animation[animCount];
			charAtlas = new Texture("HumanMaleSpritePageA.png");
			chatAnimAtlas = new TextureRegion[animCount][];
			for(int i = 0 ; i < animCount ; i++)
			{
				chatAnimAtlas[i] = new TextureRegion[animInAtlas];
				for(int j = 0; j < animInAtlas ; j++)
				{
					
					chatAnimAtlas[i][j] = new TextureRegion(charAtlas,j*texSize , i*texSize, texSize , texSize);
				}
			} 
			charAnimation = new Animation[animCount];
			for( int i = 0 ; i < animCount ; i++)
			{
				charAnimation[i]  = new Animation(0.1f,chatAnimAtlas[i]);
				charAnimation[i] .setPlayMode(Animation.PlayMode.LOOP);
			}
		}
	}
	public static Animation[] getCharAnim()
	{
		charAnimationInit();
		return charAnimation;
	}
	public static Sprite getAidSprite()
	{
		if(aidSprite == null)
		{
			Texture texture = new Texture("aid.png");
			aidSprite = new Sprite (texture);
			aidSprite.setScale(0.15f);
			aidSprite.setOrigin(0, 0);
		}
		return aidSprite;
	}
	public static Sprite getPointerSprite()
	{
		if(pointerSprite == null)
		{
			Texture texture =new Texture("pointer.png");
			pointerSprite = new Sprite (texture);
			float scale = 0.7f;
			pointerSprite.setScale(scale);
			pointerSprite.setOrigin(-pointerSprite.getWidth(),-pointerSprite.getWidth()*scale);
		}
		return pointerSprite;
	}
	public static ShapeRenderer getShapeRender() {
		if(shapeRenderer==null)
			shapeRenderer = new ShapeRenderer();
		return shapeRenderer;
	}
	public static Sprite getTreeSprite() {
		if(treeSprite == null)
		{
			Texture texture = new Texture("tree_1.png");
			treeSprite = new Sprite (texture);
			float scale = 1f;
			treeSprite.setScale(scale);
			treeSprite.setOrigin(-treeSprite.getWidth(),-treeSprite.getWidth()*scale);
		}
		return treeSprite;
	}
	public static TiledMap getMap() {
		if(map == null)
		{
			map = new TmxMapLoader().load("untitled1.tmx");
		}
		return map;
	}
}
