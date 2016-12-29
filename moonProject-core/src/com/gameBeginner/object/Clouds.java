package com.gameBeginner.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gameBeginner.game.Assets;

public class Clouds extends AbstractGameObject {

	private float length;
	private Array<TextureRegion> regClouds;
	private Array<Cloud> clouds;

	private class Cloud extends AbstractGameObject {

		private TextureRegion regCloud;
		/**
		 * 
		 */
		public Cloud() {
			// TODO Auto-generated constructor stub
		}
		public void setRegion(TextureRegion region) {
			regCloud = region;
		}

		@Override
		public void render(SpriteBatch batch) {
			// TODO Auto-generated method stub
			TextureRegion region = regCloud;
			batch.draw(region.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y,
					dimension.x, dimension.y, scale.x, scale.y, rotation, regCloud.getRegionX(), regCloud.getRegionY(),
					regCloud.getRegionWidth(), regCloud.getRegionWidth(), false, false);
		}
	}
	
	public Clouds(float length) {
		// TODO Auto-generated constructor stub
		this.length=length;
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		dimension.set(3.0f, 1.5f);
		regClouds=new Array<TextureRegion>();
		regClouds.add(Assets.getInstance().levelDecoration.cloud01);

		regClouds.add(Assets.getInstance().levelDecoration.cloud02);
		regClouds.add(Assets.getInstance().levelDecoration.cloud03);
		int distFac=5;
		int numCounds=(int)(length/distFac);
		clouds = new Array<Cloud>(2 * numCounds);
		for(int i=0;i<numCounds;i++){
			Cloud cloud=spawnCloud();
			cloud.position.x=i*distFac;
			clouds.add(cloud);
		}
	}

	private Cloud spawnCloud(){
		Cloud cloud=new Cloud();
		cloud.dimension.set(this.dimension);
		cloud.setRegion(regClouds.random());
		Vector2 pos=new Vector2();
		pos.x=length+10;
		pos.y+=1.75;
		pos.y+=MathUtils.random(-0.2f, 0.2f);
		cloud.position.set(pos);
		return cloud;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		for (Cloud cloud : clouds) {
			cloud.render(batch);
		}
	}
}
