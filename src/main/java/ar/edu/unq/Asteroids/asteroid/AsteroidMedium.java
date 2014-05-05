package ar.edu.unq.Asteroids.asteroid;

import java.util.ArrayList;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.pools.AsteroidPool;
import ar.edu.unq.americana.appearances.Sprite;

public class AsteroidMedium extends Asteroid {

	public AsteroidMedium() {
		this.setZ(Integer.MAX_VALUE - 1);

	}

	@Override
	protected Asteroid[] getChildren() {
		final Asteroid child1 = AsteroidPool.getSmall().initialize(this);
		final Asteroid child2 = AsteroidPool.getSmall().initialize(this);
		return new Asteroid[] { child1, child2 };
	}

	@Override
	protected Sprite[] getSprites() {
		final ArrayList<Sprite> list = Asteroids.MEDIUM_ASTEROID_SPRITES;
		return list.toArray(new Sprite[list.size()]);
	}

	@Override
	public void returnToPool() {
		AsteroidPool.addAsteroid(this);
	}

}
