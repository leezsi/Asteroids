package ar.edu.unq.Asteroids.asteroid;

import java.util.ArrayList;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.pools.AsteroidPool;
import ar.edu.unq.americana.appearances.Sprite;

public class AsteroidSmall extends Asteroid {

	public AsteroidSmall() {
		this.setZ(Integer.MAX_VALUE);
	}

	@Override
	protected Asteroid[] getChildren() {
		return new Asteroid[0];
	}

	@Override
	protected Sprite[] getSprites() {
		final ArrayList<Sprite> list = Asteroids.SMALL_ASTEROID_SPRITES;
		return list.toArray(new Sprite[list.size()]);
	}

	@Override
	public void returnToPool() {
		AsteroidPool.addAsteroid(this);
	}

}
