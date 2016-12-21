package com.gameBeginner.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BunnyGame extends ApplicationAdapter {

	private static final String TAG = BunnyGame.class.getName();

	private WorldController worldController;
	private WorldRender worldRender;

	private boolean paused;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		worldController = new WorldController();
		worldRender = new WorldRender(worldController);
	}

	@Override
	public void render() {
		if (!paused) {
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		
		Gdx.gl.glClearColor(0 / 255.0f, 0 / 255.0f, 255 / 255.0f, 1 / 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldRender.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		worldRender.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		paused = true;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		paused = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		worldRender.dispose();

	}

}
