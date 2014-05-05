package ar.edu.unq.Asteroids.bullet;

import ar.edu.unq.americana.events.ioc.fired.FiredEvent;

public class BulletDieEvent extends FiredEvent {

	private final Bullet bullet;

	public BulletDieEvent(final Bullet bullet) {
		this.bullet = bullet;
	}

	public Bullet getBullet() {
		return this.bullet;
	}

}
