package models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import things.ThingRenderer;
import tools.RenderKit;

public class ModelInventory {

	private Cell[][] cellArray;
	private final int size = 10 , cellLen = 20;
	private float x , y;
	private ShapeRenderer shapeRender;
	
	public ModelInventory()
	{
		shapeRender = RenderKit.getShapeRender();
		setCellArray(new Cell[size][size]);
	}

	public void addItem(int i , int j , ThingRenderer thingRenderer)
	{
		cellArray[i][j].setThingRenderer(thingRenderer);
	}
	
	public ThingRenderer removeItem(int i , int j)
	{
		ThingRenderer ren = cellArray[i][j].getThingRenderer();
		cellArray[i][j].setThingRenderer(null);
		return ren;
	}
	
	public Cell[][] getCellArray() {
		return cellArray;
	}

	public void setCellArray(Cell[][] cellArray) {
		this.cellArray = cellArray;
	}
	
	public void render()
	{
		shapeRender.begin(ShapeType.Line);
		for(int i = 0 ; i < size ; i++)
			for(int j = 0 ; j < size ; j++)
			{
				shapeRender.rect(x + i*cellLen, y + j*cellLen, cellLen, cellLen);
			}
		shapeRender.end();
	}
	
}
