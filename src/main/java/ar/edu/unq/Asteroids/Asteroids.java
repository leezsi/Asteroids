package ar.edu.unq.Asteroids;

import java.awt.Dimension;

import ar.edu.unq.Asteroids.asteroid.AsteroidLarge;
import ar.edu.unq.Asteroids.asteroid.AsteroidMedium;
import ar.edu.unq.Asteroids.asteroid.AsteroidSmall;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.ship.Ship;
import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.appearances.Sprite;

public class Asteroids extends Game {

	private static final int WIDTH = 800;

	private static final int HEIGHT = 600;

	private Dimension dimension;

	public static Sprite LARGE_ASTEROID;

	public static Sprite MEDIUM_ASTEROID;

	public static Sprite SMALL_ASTEROID;

	public static Sprite SHIP_SPRITE;

	@Override
	protected String[] properties() {
		return new String[] { "asteroids.properties" };
	}

	@Override
	protected void initializeResources() {
		dimension = new Dimension(WIDTH, HEIGHT);
		SHIP_SPRITE = Sprite.fromImage("assets/ship.png");
		final Sprite base = Sprite.fromImage("assets/asteroid.png");
		LARGE_ASTEROID = base.scale(0.75);
		MEDIUM_ASTEROID = LARGE_ASTEROID.scale(0.5);

		SMALL_ASTEROID = MEDIUM_ASTEROID.scale(0.5);
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
