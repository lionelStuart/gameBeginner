package com.gameBeginner.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gameBeginner.object.AbstractGameObject;

public class CameraHelper {
	private static final String TAG = CameraHelper.class.getName();
	
	private final float MAX_ZOOM_IN=0.25f;
	private final float MAX_ZOOM_OUT=10.f;
	
	private Vector2 position;
	private float zoom;
	private AbstractGameObject target;
	
	public CameraHelper() {
		// TODO Auto-generated constructor stub
		position=new Vector2();
		zoom=1.0f;
	}
	
	public void update(float deltaTime){
		if (!hasTarget()) return;

		position.x = target.position.x + target.origin.x;
		position.y = target.position.y + target.origin.y;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(float x,float y) {
		this.position.set(x, y);
	}

	public void addZoom(float amount){
		this.setZoom(zoom+amount);
	}
	
	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}

	public AbstractGameObject getTarget() {
		return target;
	}

	public void setTarget(AbstractGameObject target) {
		this.target = target;
	}
	
	public boolean hasTarget() {
		return target!=null;
	}
	
	public boolean hasTarget(AbstractGameObject target) {
		return hasTarget() &&this.target.equals(target);
	}
	
	public void applyTo(OrthographicCamera camera) {
		camera.position.x=this.position.x;
		camera.position.y=this.position.y;
		camera.zoom=this.zoom;
		camera.update();
	}
	
}
