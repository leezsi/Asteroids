package ar.edu.unq.Asteroids.utils;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.ship.Ship;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.utils.Vector2D;

public class ShipUtils {

	public static Sprite rotate(final double angle) {
		return Asteroids.SHIP_SPRITE.rotate(angle);
	}

	public static void rotate(final Ship ship, final double angle) {
		final Vector2D vector = ship.getVector().asVersor();

		ship.setVector(new Vector2D(Math.cos(angle - (Math.PI / 2)), Math
				.sin(angle - (Math.PI / 2))).asVersor().suma(vector));
		ship.setAppearance(rotate(angle));

	}
}
