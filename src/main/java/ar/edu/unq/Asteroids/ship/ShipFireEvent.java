package ar.edu.unq.Asteroids.ship;

import ar.edu.unq.americana.events.ioc.fired.FiredEvent;
import ar.edu.unq.americana.utils.Vector2D;

public class ShipFireEvent extends FiredEvent {

	private final double shipSpeed;
	private final Vector2D direction;

	public ShipFireEvent(final double speed, final Vector2D direction) {
		this.shipSpeed = speed;
		this.direction = direction;
	}

	public Vector2D getDirection() {
		return this.direction;
	}

	public double getShipSpeed() {
		return this.shipSpeed;
	}
}
