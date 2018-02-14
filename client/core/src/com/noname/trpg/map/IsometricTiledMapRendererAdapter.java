package com.noname.trpg.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class IsometricTiledMapRendererAdapter extends IsometricTiledMapRenderer {

    public IsometricTiledMapRendererAdapter(Camera camera , Batch batch , String tmxPath ) {
        super(new TmxMapLoader().load(tmxPath));
        super.setView((OrthographicCamera)camera);
        super.batch = batch;
    }

}