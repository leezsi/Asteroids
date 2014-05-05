package ar.edu.unq.Asteroids.ship;

import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class ShipLossLiveEvent extends FiredEvent {

	private final Ship ship;
	private final Asteroid asteroid;

	public ShipLossLiveEvent(final Ship ship, final Asteroid asteroid) {
		this.ship = ship;
		this.asteroid = asteroid;
	}

	public Ship getShip() {
		return this.ship;
	}

	public Asteroid getAsteroid() {
		return this.asteroid;
	}

}
