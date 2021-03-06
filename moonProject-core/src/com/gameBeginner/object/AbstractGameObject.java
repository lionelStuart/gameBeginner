package com.gameBeginner.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
	
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	
	public AbstractGameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		position=new Vector2();
		dimension=new Vector2(1,1);
		origin=new Vector2();
		scale=new Vector2(1,1);
		rotation=0;
	}
	public abstract void update(float deltaTime) ;
	
	public abstract void render(SpriteBatch batch); 
	
}
