package com.gameBeginner.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Rectangle;
import com.gameBeginner.object.BunnyHead;
import com.gameBeginner.object.BunnyHead.JUMP_STATE;
import com.gameBeginner.object.Feather;
import com.gameBeginner.object.GoldCoin;
import com.gameBeginner.object.Rock;
import com.gameBeginner.object.TestSprites;
import com.gameBeginner.util.CameraHelper;
import com.gameBeginner.util.Constants;

class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();

	// public Level level;
	public int lives;
	public int score;
	public Level level1;

	public CameraHelper cameraHelper;
	public TestSprites testSprites;

	// Rectangles for collision detection
	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();

	public WorldController() {
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {

		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		Assets.getInstance().init(new AssetManager());
		// testSprites = new TestSprites();
		// testSprites.initAssetsInstance();

		// lives=Constants.LIVES_START;
		initLevel();
		Gdx.app.debug(TAG, "world contorller inited");
	}

	private void initLevel() {
		score = 0;
		level1 = new Level(Constants.LEVEL_01);
		if (cameraHelper == null)
			Gdx.app.debug(TAG, "cameraHelper not instantiated");
		else
			cameraHelper.setTarget(level1.bunnyHead);
		// level=new Level(Constants.LEVELS_01);
	}

	public void update(float deltaTime) {
		testCollisions();
		level1.update(deltaTime);
		// Gdx.app.debug(TAG, "update time: " + deltaTime);
		handleInputGame(deltaTime);
		handleDebugInput(deltaTime);
		// testSprites.update(deltaTime);
		cameraHelper.update(deltaTime);
		if (isPlayerInWater()) {
			initLevel();
		}
		// Gdx.app.debug(TAG, "handle update");
	}

	private void handleInputGame(float deltaTime) {
		if (cameraHelper.hasTarget(level1.bunnyHead)) {
			// Player Movement
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				level1.bunnyHead.velocity.x = -level1.bunnyHead.terminalVelocity.x;
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				level1.bunnyHead.velocity.x = level1.bunnyHead.terminalVelocity.x;
			} else {
				// Execute auto-forward movement on non-desktop platform
				if (Gdx.app.getType() != ApplicationType.Desktop) {
					level1.bunnyHead.velocity.x = level1.bunnyHead.terminalVelocity.x;
				}
			}

			// Bunny Jump
			// if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE))
			// level1.bunnyHead.setJumping(true);
			// else
			// level1.bunnyHead.setJumping(false);
		}
	}

	public boolean isPlayerInWater() {
		return level1.bunnyHead.position.y < -5;
	}

	private void handleDebugInput(float deltaTime) {
		// TODO Auto-generated method stub
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;

		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (!cameraHelper.hasTarget()) {
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
		}
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

	private void testCollisions() {
		r1.set(level1.bunnyHead.position.x, level1.bunnyHead.position.y, level1.bunnyHead.bounds.width,
				level1.bunnyHead.bounds.height);

		// Test collision: Bunny Head <-> Rocks
		for (Rock rock : level1.rocks) {
			r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionBunnyHeadWithRock(rock);
			// IMPORTANT: must do all collisions for valid edge testing on
			// rocks.
		}

		// Test collision: Bunny Head <-> Gold Coins
		for (GoldCoin goldcoin : level1.goldcoins) {
			if (goldcoin.isCollected())
				continue;
			r2.set(goldcoin.position.x, goldcoin.position.y, goldcoin.bounds.width, goldcoin.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionBunnyWithGoldCoin(goldcoin);
			break;
		}

		// Test collision: Bunny Head <-> Feathers
		for (Feather feather : level1.feathers) {
			if (feather.isCollected())
				continue;
			r2.set(feather.position.x, feather.position.y, feather.bounds.width, feather.bounds.height);
			if (!r1.overlaps(r2))
				continue;
			onCollisionBunnyWithFeather(feather);
			break;
		}
	}

	private void onCollisionBunnyHeadWithRock(Rock rock) {
		BunnyHead bunnyHead = level1.bunnyHead;
		float heightDifference = Math.abs(bunnyHead.position.y - (rock.position.y + rock.bounds.height));
		if (heightDifference > 0.25f) {
			boolean hitRightEdge = bunnyHead.position.x > (rock.position.x + rock.bounds.width / 2.0f);
			if (hitRightEdge) {
				bunnyHead.position.x = rock.position.x + rock.bounds.width;
			} else {
				bunnyHead.position.x = rock.position.x - bunnyHead.bounds.width;
			}
			return;
		}

		switch (bunnyHead.jumpState) {
		case GROUNDED:
			break;
		case FALLING:
		case JUMP_FALLING:
			bunnyHead.position.y = rock.position.y + bunnyHead.bounds.height + bunnyHead.origin.y;
			bunnyHead.jumpState = JUMP_STATE.GROUNDED;
			break;
		case JUMP_RISING:
			bunnyHead.position.y = rock.position.y + bunnyHead.bounds.height + bunnyHead.origin.y;
			break;
		}
	}

	private void onCollisionBunnyWithGoldCoin(GoldCoin goldcoin) {
		goldcoin.setCollected(true);
		Gdx.app.log(TAG, "Gold coin collected");
	}

	private void onCollisionBunnyWithFeather(Feather feather) {
		feather.setCollected(true);
		score += feather.getScore();
		// level1.bunnyHead.setFeatherPowerup(true);
		Gdx.app.log(TAG, "Feather collected");
	}

}
