package com.gameBeginner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.gameBeginner.util.Constants;


public class Assets implements Disposable, AssetErrorListener {

	private static final String TAG = Assets.class.getName();

	private static final Assets instance = new Assets();


	private AssetManager assetManager;

	public AssetFonts fonts;
	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetFeather feather;
	public AssetLevelDecoration levelDecoration;

	private Assets() {
		// TODO Auto-generated constructor stub

	}

	public static Assets getInstance() {
		return instance;
	}
	
	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultLarge;

		public AssetFonts() {
			defaultSmall = new BitmapFont(Gdx.files.internal(Constants.DEFAULT_FONT), true);
			defaultNormal = new BitmapFont(Gdx.files.internal(Constants.DEFAULT_FONT), true);
			defaultLarge = new BitmapFont(Gdx.files.internal(Constants.DEFAULT_FONT), true);

			defaultSmall.getData().setScale(0.75f);
			defaultNormal.getData().setScale(1.0f);
			defaultLarge.getData().setScale(2.0f);
		}
		
		public void dispose(){
			defaultSmall.dispose();
			defaultNormal.dispose();
			defaultLarge.dispose();
		}
	}

	public class AssetBunny {
		public final AtlasRegion head;

		public AssetBunny(TextureAtlas atlas) {
			// TODO Auto-generated constructor stub
			head = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_BUNNY_HEAD);
		}

	}

	public class AssetRock {
		public final AtlasRegion edge;
		public final AtlasRegion middle;

		public AssetRock(TextureAtlas atlas) {
			// TODO Auto-generated constructor stub
			edge = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_ROCK_EDGE);
			middle = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_ROCK_MIDDLE);
		}
	}

	public class AssetGoldCoin {
		public final AtlasRegion goldCoin;

		public AssetGoldCoin(TextureAtlas atlas) {
			// TODO Auto-generated constructor stub
			goldCoin = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_GOLD_COIN);
		}
	}

	public class AssetFeather {
		public final AtlasRegion feather;

		public AssetFeather(TextureAtlas atlas) {
			// TODO Auto-generated constructor stub
			feather = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_FEATHER);
		}
	}

	public class AssetLevelDecoration {
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion mountainRight;
		public final AtlasRegion waterOverlay;

		public AssetLevelDecoration(TextureAtlas atlas) {
			// TODO Auto-generated constructor stub
			cloud01 = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_CLOUD01);
			cloud02 = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_CLOUD01);
			cloud03 = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_CLOUD01);
			mountainLeft = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_MOUNTAIN_LEFT);
			mountainRight = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_MOUNTAIN_RIGHT);
			waterOverlay = atlas.findRegion(Constants.TEXTURE_ATLAS_REGION_WATER_OVERLAY);
		}
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,TextureAtlas.class);
		assetManager.finishLoading();

		debugLoading();
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for (Texture texture : atlas.getTextures()) {
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		fonts = new AssetFonts();
		bunny = new AssetBunny(atlas);
		rock = new AssetRock(atlas);
		goldCoin = new AssetGoldCoin(atlas);
		feather = new AssetFeather(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);

	}

	private void debugLoading() {
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String name : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, " asset: " + name);
		}
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error(TAG, "Cannot load asset " + asset.fileName);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		assetManager.dispose();
		fonts.dispose();
	}

}
