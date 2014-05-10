package ar.edu.unq.Asteroids;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.components.LifeCounter;
import ar.edu.unq.americana.components.Score;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.utils.ResourcesUtils;

@Bean
public class Asteroids extends Game {

	private static final int WIDTH = 800;

	private static final int HEIGHT = 600;

	private Dimension dimension;

	private int currentLevel;

	private Score<Level> score;

	private LifeCounter<Level> lifeCounter;

	private boolean finish;

	public static final Font font = ResourcesUtils.getFont("pixelated.ttf",
			Font.TRUETYPE_FONT, Font.BOLD, 50);

	public static ArrayList<Sprite> LARGE_ASTEROID_SPRITES;

	public static ArrayList<Sprite> MEDIUM_ASTEROID_SPRITES;

	public static ArrayList<Sprite> SMALL_ASTEROID_SPRITES;

	@Property("ship.sprite")
	public static Sprite SHIP_SPRITE;

	@Property("background.sprite")
	public static Sprite BACKGROUND_SPRITE;

	@Property("bullet.sprite")
	public static Sprite BULLET_SPRITE;

	@Property("asteroid.sprite.animation.basePath")
	public static String ANIMATION_BASE_PATH;

	@Property("asteroid.sprite.animation")
	public static String ANIMATION_PATH;

	@Property("game.levels")
	public static int LEVELS;

	@Override
	protected String[] properties() {
		return new String[] { "asteroids.properties" };
	}

	@Override
	protected void initializeResources() {
		BULLET_SPRITE = BULLET_SPRITE.rotate(Math.PI / 2);
		this.initializeBackgroundSprite();
		this.initializeAsteroidAnimation();

	}

	private void initializeBackgroundSprite() {
		BACKGROUND_SPRITE = BACKGROUND_SPRITE.scale(
				WIDTH / BACKGROUND_SPRITE.getWidth(), HEIGHT
						/ BACKGROUND_SPRITE.getHeight());
	}

	private void initializeAsteroidAnimation() {
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
		this.start();
	}

	public void start() {
		this.currentLevel = 0;
		this.finish = false;
		this.score = new Score<Level>(10, Asteroids.font, Color.white);
		this.lifeCounter = new LifeCounter<Level>(3, SHIP_SPRITE.scale(0.5));
		this.nextLevel();
	}

	public void nextLevel() {
		if (this.currentLevel < LEVELS) {
			this.score.setDestroyPending(false);
			this.lifeCounter.setDestroyPending(false);
			this.setCurrentScene(new Level(++this.currentLevel, this.score,
					this.lifeCounter));
			this.finish = this.currentLevel == LEVELS;
		}
	}

	@Override
	public Dimension getDisplaySize() {
		if (this.dimension == null) {
			this.dimension = new Dimension(WIDTH, HEIGHT);
		}
		return this.dimension;
	}

	@Override
	public String getTitle() {
		return "Asteroids";
	}

	public boolean isFinish() {
		return this.finish;
	}

}
