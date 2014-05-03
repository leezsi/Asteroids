package ar.edu.unq.Asteroids.levels;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.americana.GameScene;

public class Level extends GameScene {

	private final Set<Asteroid> asteroids;

	public Level() {
		asteroids = new HashSet<Asteroid>();
	}

	protected Set<Asteroid> getAsteroids() {
		return asteroids;
	}

	public void addAsteroid(final Asteroid asteroid) {
		asteroids.add(asteroid);
		this.addComponent(asteroid);
	}

	public void replace(final Asteroid asteroid, final Asteroid... children) {
		asteroids.remove(asteroid);
		this.removeComponent(asteroid);
		for (final Asteroid child : children) {
			this.addAsteroid(child);
		}
	}
}
