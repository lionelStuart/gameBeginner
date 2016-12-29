package com.gameBeginner.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gameBeginner.game.Assets;

public class Rock extends AbstractGameObject {

	private TextureRegion regEdge;
	private TextureRegion regMiddle;

	private int length;

	/**
	 * 
	 */
	public Rock() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		dimension.set(1, 1.5f);
		regEdge = Assets.getInstance().rock.edge;
		regMiddle = Assets.getInstance().rock.middle;
		setLength(1);
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
		bounds.set(0, 0, dimension.x * length, dimension.y);
	}

	public void addLength(int amount) {
		setLength(this.length + amount);
	}
	private void drawRock(SpriteBatch batch) {
		TextureRegion reg = null;

		float relX = 0;
		float relY = 0;

		// Draw left edge
		reg = regEdge;
		relX -= dimension.x / 4;
		batch.draw(reg.getTexture(), position.x + relX, position.y + relY, origin.x, origin.y, dimension.x / 4, dimension.y,
			scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false,
			false);

		// Draw middle
		relX = 0;
        reg = regMiddle;
		for (int i = 0; i < length; i++) {
			batch.draw(reg.getTexture(), position.x + relX, position.y + relY, origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false,
				false);
			relX += dimension.x;
		}

		// Draw right edge
		reg = regEdge;
		batch.draw(reg.getTexture(), position.x + relX, position.y + relY, origin.x + dimension.x / 8, origin.y, dimension.x / 4,
			dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
			reg.getRegionHeight(), true, false);
	}
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		drawRock(batch);
	}

}