package com.gameBeginner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.gameBeginner.util.Constants;

public class WorldRender implements Disposable {

	private static final String TAG = WorldRender.class.getName();

	private OrthographicCamera camera;
	// private OrthographicCamera cameraGUI;
	private SpriteBatch batch;
	private WorldController worldController;

	public WorldRender(WorldController worldController) {
		// TODO Auto-generated constructor stub
		this.worldController = worldController;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		Gdx.app.debug(TAG, "world render inited");
		/* cameraGUI */
	}

	public void render() {
		renderWorld(batch);
		renderGUI();
	}

	public void renderGUI() {
		// TODO Auto-generated method stub

	}

	public void renderWorld(SpriteBatch batch) {
		// TODO Auto-generated method stub
		// //Debug
		// Vector2 posit=new Vector2();
		// posit=worldController.cameraHelper.getPosition();
		// Gdx.app.debug(TAG, "update CameraHelper position: "+posit.x+" ,
		// "+posit.y);
		// //
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		// batch.begin();
		worldController.testSprites.render(batch);
		// batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		Assets.getInstance().dispose();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float) height) * (float) width;
		camera.update();
	}

}
