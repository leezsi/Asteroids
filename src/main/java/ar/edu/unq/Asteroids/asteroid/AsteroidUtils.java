package ar.edu.unq.Asteroids.asteroid;

import java.util.Random;

import ar.edu.unq.americana.utils.Vector2D;

public class AsteroidUtils {

	public static Vector2D getDirection(final double x, final double y) {
		final Random rnd = new Random();
		final double dx = rnd.nextDouble() * (rnd.nextBoolean() ? 1 : -1);
		final double dy = rnd.nextDouble() * (rnd.nextBoolean() ? 1 : -1);
		return new Vector2D(dx, dy);
	}

}
