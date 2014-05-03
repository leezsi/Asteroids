package ar.edu.unq.Asteroids.asteroid;

import java.util.ArrayList;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.americana.appearances.Sprite;

public class AsteroidMedium extends Asteroid {

	public AsteroidMedium(final double x, final double y) {
		super(x, y);
		this.setZ(Integer.MAX_VALUE - 1);
	}

	@Override
	protected Asteroid[] getChildren() {
		final Asteroid child1 = new AsteroidSmall(this.getX(), this.getY());
		final Asteroid child2 = new AsteroidSmall(this.getX(), this.getY());
		return new Asteroid[] { child1, child2 };
	}

	@Override
	protected Sprite[] getSprites() {
		final ArrayList<Sprite> list = Asteroids.MEDIUM_ASTEROID_SPRITES;
		return list.toArray(new Sprite[list.size()]);
	}

}
