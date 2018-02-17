package com.noname.trpg.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class IsometricTiledMapRendererAdapter extends IsometricTiledMapRenderer {

    public IsometricTiledMapRendererAdapter(Batch batch , String tmxPath ) {
        super(new TmxMapLoader().load(tmxPath));
        super.batch = batch;
    }
    @Override
    public void render() {
    	 beginRender();
         int currentLayer = 0;
         for (MapLayer layer : map.getLayers()) {
             if (layer.isVisible()) {
                 if (layer instanceof TiledMapTileLayer) {
                     renderTileLayer((TiledMapTileLayer)layer);
                     currentLayer++;
                    
                 } else {
                     for (MapObject object : layer.getObjects()) {
                         renderObject(object);
                     }
                 }
             }
         }
         endRender();
    }

}