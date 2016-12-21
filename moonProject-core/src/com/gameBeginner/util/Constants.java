package com.gameBeginner.util;

public class Constants {
	// Visible game world is 5 meters wide
	public static final float VIEWPORT_WIDTH = 10.0f;

	// Visible game world is 5 meters tall
	public static final float VIEWPORT_HEIGHT = 10.0f;

	// GUI Width
	public static final float VIEWPORT_GUI_WIDTH = 800.0f;
	// GUI Height
	public static final float VIEWPORT_GUI_HEIGHT = 480.0f;

	// Location of description file for texture atlas
	public static final String DEFAULT_FONT = "images/arial-15.fnt";
	
	public static final String TEXTURE_ATLAS_OBJECTS = "images/canyonbunny.pack";
	
	public static final String TEXTURE_ATLAS_REGION_BUNNY_HEAD = "bunny_head";

	public static final String TEXTURE_ATLAS_REGION_ROCK_EDGE = "rock_edge";
	public static final String TEXTURE_ATLAS_REGION_ROCK_MIDDLE = "rock_middle";
	public static final String TEXTURE_ATLAS_REGION_GOLD_COIN = "item_gold_coin";
	public static final String TEXTURE_ATLAS_REGION_FEATHER = "item_feather";
	public static final String TEXTURE_ATLAS_REGION_CLOUD01 = "cloud01";
	public static final String TEXTURE_ATLAS_REGION_CLOUD02 = "cloud02";
	public static final String TEXTURE_ATLAS_REGION_CLOUD03 = "cloud03";
	public static final String TEXTURE_ATLAS_REGION_MOUNTAIN_LEFT = "mountain_left";
	public static final String TEXTURE_ATLAS_REGION_MOUNTAIN_RIGHT = "mountain_right";
	public static final String TEXTURE_ATLAS_REGION_WATER_OVERLAY = "water_overlay";

	// Location of image file for level 01
	public static final String LEVEL_01 = "levels/level-01.png";

	// Amount of extra lives at level start
	public static final int LIVES_START = 3;
}
