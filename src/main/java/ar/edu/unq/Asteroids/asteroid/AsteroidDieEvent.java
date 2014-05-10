package ar.edu.unq.Asteroids.asteroid;

import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class AsteroidDieEvent extends FiredEvent {
	private final Asteroid asteroid;

	public AsteroidDieEvent(final Asteroid asteroid) {
		this.asteroid = asteroid;
	}

	public Asteroid getAsteroid() {
		return this.asteroid;
	}

	public Asteroid[] children() {
		return this.asteroid.getChildren();
	}

}
