package ar.edu.unq.Asteroids.bullet;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.rules.BottomOutRule;
import ar.edu.unq.Asteroids.rules.LeftOutRule;
import ar.edu.unq.Asteroids.rules.RightOutRule;
import ar.edu.unq.Asteroids.rules.TopOutRule;
import ar.edu.unq.Asteroids.ship.Ship;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.rules.IRule;
import ar.edu.unq.americana.utils.Vector2D;

public class Bullet extends GameComponent<Level> {
	@Property("bullet.speed")
	private static double SPEED;
	private Vector2D vector;
	private double remainingLife;

	public Bullet() {
		this.setAppearance(Asteroids.BULLET_SPRITE.copy());
	}

	public void initializeFrom(final Ship ship) {
		this.setDestroyPending(false);
		this.setX(ship.getX());
		this.setY(ship.getY());
		this.setZ(ship.getZ() - 1);
		this.vector = ship.getVector();
		this.remainingLife = Math.max(ship.getGame().getDisplayWidth(), ship
				.getGame().getDisplayHeight());
		this.setAppearance(Asteroids.BULLET_SPRITE.rotate(ship.getAngle()));
	}

	@Events.Update
	public void update(final double delta) {
		final Vector2D currentVector = this.vector.asVersor().producto(
				delta * SPEED);
		this.remainingLife -= currentVector.getModule() * 1.25;
		if (this.remainingLife <= 0) {
			this.die();
		} else {
			this.move(currentVector);
		}

	}

	@Override
	protected IRule<?, ?>[] rules() {
		return new IRule<?, ?>[] { new TopOutRule(), new LeftOutRule(),
				new BottomOutRule(), new RightOutRule() };
	}

	public void die() {
		this.fire(new BulletDieEvent(this));
	}

}
