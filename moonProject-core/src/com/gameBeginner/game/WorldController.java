package com.gameBeginner.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.InputAdapter;
import com.gameBeginner.object.TestSprites;
import com.gameBeginner.util.CameraHelper;

public class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();

	// public Level level;
	public int lives;
	public int score;

	public CameraHelper cameraHelper;
	public TestSprites testSprites;

	public WorldController() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {

		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		testSprites = new TestSprites();
		Assets.getInstance().init(new AssetManager());
		testSprites.initAssetsInstance();
		// lives=Constants.LIVES_START;
		Gdx.app.debug(TAG, "world contorller inited");
	}

	private void initLevel() {
		score = 0;
		// level=new Level(Constants.LEVELS_01);
	}

	public void update(float deltaTime) {
//		Gdx.app.debug(TAG, "update time: " + deltaTime);
		handleDebugInput(deltaTime);
		testSprites.update(deltaTime);
		cameraHelper.update(deltaTime);
		// Gdx.app.debug(TAG, "handle update");
	}

	private void handleDebugInput(float deltaTime) {
		// TODO Auto-generated method stub
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;

		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveCamera(-camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveCamera(camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveCamera(0, -camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
			cameraHelper.setPosition(0, 0);

		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA))
			cameraHelper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD))
			cameraHelper.addZoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH))
			cameraHelper.setZoom(1);
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.R) {
			this.init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		return super.keyUp(keycode);
		// return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return super.mouseMoved(screenX, screenY);
	}

}
