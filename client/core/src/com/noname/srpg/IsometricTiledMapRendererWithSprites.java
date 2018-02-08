package com.noname.srpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

import array.ModelsArray;
import array.ThingsArray;
import things.ThingRenderer;
import tools.RenderKit;


public class IsometricTiledMapRendererWithSprites extends IsometricTiledMapRenderer {
    private Array<SceneRender> models;
    private Array<ThingRenderer> things;
    private OrthographicCamera camera;
    
    private int drawSpritesAfterLayer = 1;

    public IsometricTiledMapRendererWithSprites() {
        super(RenderKit.getMap());
        models = ModelsArray.getArray();
        things = ThingsArray.getArray();
    }


    @Override
    public void render() {

    	float dt = Gdx.graphics.getDeltaTime();
    		
    	camera = RenderKit.getOC();
    	models = ModelsArray.getArray();
        things = ThingsArray.getArray();
 
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
                    if(currentLayer == drawSpritesAfterLayer)
                    {
                    	for(ThingRenderer thing : things)
                    	{
                            thing.render(this.getBatch(), dt ,camera );
                    	}
                    	models.sort();
                        for(SceneRender model : models)
                        {
                            model.render(this.getBatch(), dt ,camera );
                        }
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }
        endRender();

    }
    public static void bubbleSort(int[] arr){  
        for(int i = arr.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( arr[j] > arr[j+1] ){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}