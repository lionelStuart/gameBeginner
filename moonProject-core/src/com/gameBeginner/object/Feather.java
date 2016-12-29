package com.gameBeginner.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gameBeginner.game.Assets;

public class Feather extends AbstractGameObject{
	
	private TextureRegion regFeather;
	
	private int score;
	public int getScore() {
		return score;
	}

	private void setScore(int score) {
		this.score = score;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	private boolean collected;
	
	public Feather() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		setScore(250);
		setCollected(false);
		dimension.set(0.5f,0.5f);
		regFeather=Assets.getInstance().feather.feather;
		bounds.set(0, 0, dimension.x, dimension.y);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(isCollected())return;
		
		TextureRegion region=regFeather;
		batch.draw(region.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y,
				dimension.x, dimension.y, scale.x, scale.y, rotation, regFeather.getRegionX(), regFeather.getRegionY(),
				regFeather.getRegionWidth(), regFeather.getRegionWidth(), false, false);
	}

}
