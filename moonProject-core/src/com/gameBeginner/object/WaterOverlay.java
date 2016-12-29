package com.gameBeginner.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gameBeginner.game.Assets;

public class WaterOverlay extends AbstractGameObject {

	private TextureRegion regWaterOverlay;
	private float length;
	
	/**
	 * 
	 */
	public WaterOverlay(float length) {
		// TODO Auto-generated constructor stub
		this.length=length;
		init();
	}
	
	public void init() {
		dimension.set(length*10,3);
		regWaterOverlay=Assets.getInstance().levelDecoration.waterOverlay;
		
		origin.x=-dimension.x/2;
	}
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		TextureRegion reg = null;
		reg = regWaterOverlay;
		batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x, dimension.y,
			scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false,
			false);
	}

}
