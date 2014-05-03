package ar.edu.unq.Asteroids;

import java.awt.Dimension;
import java.util.ArrayList;

import ar.edu.unq.Asteroids.asteroid.AsteroidLarge;
import ar.edu.unq.Asteroids.asteroid.AsteroidMedium;
import ar.edu.unq.Asteroids.asteroid.AsteroidSmall;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.ship.Ship;
import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.configs.Property;

public class Asteroids extends Game {

	private static final int WIDTH = 800;

	private static final int HEIGHT = 600;

	private Dimension dimension;

	public static ArrayList<Sprite> LARGE_ASTEROID_SPRITES;

	public static ArrayList<Sprite> MEDIUM_ASTEROID_SPRITES;

	public static ArrayList<Sprite> SMALL_ASTEROID_SPRITES;

	@Property("ship.sprite")
	public static Sprite SHIP_SPRITE;

	@Property("asteroid.sprite.animation.basePath")
	public static String ANIMATION_BASE_PATH;

	@Property("asteroid.sprite.animation")
	public static String ANIMATION_PATH;

	@Override
	protected String[] properties() {
		return new String[] { "asteroids.properties" };
	}

	@Override
	protected void initializeResources() {
		dimension = new Dimension(WIDTH, HEIGHT);
		final String[] spritePaths = ANIMATION_PATH.split(",");
		LARGE_ASTEROID_SPRITES = new ArrayList<Sprite>();
		MEDIUM_ASTEROID_SPRITES = new ArrayList<Sprite>();
		SMALL_ASTEROID_SPRITES = new ArrayList<Sprite>();
		for (final String path : spritePaths) {
			final Sprite spriteBase = Sprite.fromImage(ANIMATION_BASE_PATH
					+ "/" + path);
			final Sprite large = spriteBase
					.scale(((spriteBase.getWidth() / SHIP_SPRITE.getWidth()) * 0.5));
			LARGE_ASTEROID_SPRITES.add(large);
			final Sprite medium = large.scale(0.5);
			MEDIUM_ASTEROID_SPRITES.add(medium);
			SMALL_ASTEROID_SPRITES.add(medium.scale(0.5));
		}

	}

	@Override
	protected void setUpScenes() {
		final Level level = new Level();
		level.addComponent(new Ship());
		level.addAsteroid(new AsteroidSmall(50, 150));
		level.addAsteroid(new AsteroidMedium(50, 100));
		level.addAsteroid(new AsteroidLarge(50, 50));
		this.setCurrentScene(level);
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Asteroids";
	}

}
