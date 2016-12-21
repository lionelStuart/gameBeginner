package com.gameBeginner.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.gameBeginner.game.Assets;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

public class TestSprites extends AbstractGameObject {
	private static final String TAG = TestSprites.class.getName();

	private static final int spritesAmount = 5;

	private Sprite[] sprites;
	private int selectSprite;

	public void initTestInstances() {
		sprites = new Sprite[spritesAmount];
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width, height);
		Texture texture = new Texture(pixmap);
		for (int i = 0; i != spritesAmount; i++) {
			sprites[i] = createRandomSprite(texture);
		}
		selectSprite = 0;
		Gdx.app.debug(TAG, "TestSprites inited by Test");
	}

	public void initAssetsInstance() {
		// TODO Test Instance created by Assets, to replace the
		// initTestInstances methods above
		sprites = new Sprite[spritesAmount];
		Array<TextureRegion> regions = new Array<TextureRegion>();
		regions.add(Assets.getInstance().bunny.head);
		regions.add(Assets.getInstance().feather.feather);
		regions.add(Assets.getInstance().goldCoin.goldCoin);
		regions.add(Assets.getInstance().rock.edge);
		regions.add(Assets.getInstance().levelDecoration.cloud01);
		for (int i = 0; i != spritesAmount; i++) {
			sprites[i]=createRandomSprite(regions.random());
		}
		selectSprite = 0;
		Gdx.app.debug(TAG, "TestSprites inited by initAssetsInstance()");
	}

	public int getSelectSprite() {
		return selectSprite;
	}

	public void setSelectSprite(int selectSprite) {
		this.selectSprite = MathUtils.clamp(selectSprite, 0, spritesAmount - 1);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		updateSelectedSprite(deltaTime);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.begin();
		for (Sprite sprite : sprites) {
			sprite.draw(batch);
		}
		batch.end();
	}

	private void updateSelectedSprite(float deltaTime) {
		float rotation = sprites[selectSprite].getRotation();
		rotation += 30 * deltaTime;
		rotation %= 360;
		sprites[selectSprite].setRotation(rotation);
	}

	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(255 / 255.0f, 0 / 255.0f, 0 / 255.0f, 0.5f / 1.0f);
		pixmap.fill();
		pixmap.setColor(255 / 255.0f, 255 / 255.0f, 0 / 255.0f, 1 / 1.0f);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		pixmap.setColor(255 / 255.0f, 255 / 255.0f, 0 / 255.0f, 1 / 1.0f);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}

	private Sprite createRandomSprite(Texture texture) {
		Sprite spr = new Sprite(texture);
		spr.setSize(1, 1);
		spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
		float randomX = MathUtils.random(-2.0f, 2.0f);
		float randomY = MathUtils.random(-2.0f, 2.0f);
		spr.setPosition(randomX, randomY);
		return spr;
	}

	private Sprite createRandomSprite(TextureRegion region) {
		Sprite spr = new Sprite(region);
		spr.setSize(1, 1);
		spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
		float randomX = MathUtils.random(-2.0f, 2.0f);
		float randomY = MathUtils.random(-2.0f, 2.0f);
		spr.setPosition(randomX, randomY);
		return spr;
	}
}
