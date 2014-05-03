package ar.edu.unq.Asteroids.asteroid;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.americana.appearances.Sprite;

public class AsteroidLarge extends Asteroid {

	public AsteroidLarge(final double x, final double y) {
		super(x, y);
		this.setZ(Integer.MAX_VALUE - 2);
	}

	@Override
	protected Sprite getSprite() {
		return Asteroids.LARGE_ASTEROID;
	}

	@Override
	protected Asteroid[] getChildren() {
		final Asteroid child1 = new AsteroidMedium(this.getX(), this.getY());
		final Asteroid child2 = new AsteroidMedium(this.getX(), this.getY());
		return new Asteroid[] { child1, child2 };

	}

}
