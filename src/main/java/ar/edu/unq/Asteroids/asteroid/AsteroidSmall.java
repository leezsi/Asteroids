package ar.edu.unq.Asteroids.asteroid;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.americana.appearances.Sprite;

public class AsteroidSmall extends Asteroid {

	public AsteroidSmall(final double x, final double y) {
		super(x, y);
		this.setZ(Integer.MAX_VALUE);
	}

	@Override
	protected Sprite getSprite() {
		return Asteroids.SMALL_ASTEROID;
	}

	@Override
	protected Asteroid[] getChildren() {
		return new Asteroid[0];
	}

}
