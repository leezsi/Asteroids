package ar.edu.unq.Asteroids.ship;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.rules.BottomOutRule;
import ar.edu.unq.Asteroids.rules.LeftOutRule;
import ar.edu.unq.Asteroids.rules.RightOutRule;
import ar.edu.unq.Asteroids.rules.TopOutRule;
import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.collision.CollisionStrategy;
import ar.edu.unq.americana.physic.Physics;
import ar.edu.unq.americana.rules.IRule;
import ar.edu.unq.americana.utils.Vector2D;

public class Ship extends GameComponent<Level> {

	@Property("ship.rotation_delta")
	private static double ROTATION_DELTA;

	@Property("ship.max_speed")
	private static double MAX_SPEED;

	@Property("ship.acceleration")
	private static double ACCELERATION;

	@Property("ship.fire_sleep")
	private static double FIRE_SLEEP;

	private double angle = -Math.PI / 2;
	private Physics physics;
	private double shootSleep;

	public Ship() {
		this.setZ(2);
		this.shootSleep = 0;
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() / 2);
		this.physics = Physics.inersialEngine(this.angle);
		this.physics.friction(ACCELERATION * 0.01);
		this.setAppearance(Asteroids.SHIP_SPRITE.rotate(this.physics
				.spriteRotatationAngle()));
	}

	@Events.Keyboard(key = Key.D, type = EventType.BeingHold)
	public void rotateRight(final DeltaState state) {
		final double rotation = this.angularSpeed() * state.getDelta();
		this.rotateSprite(rotation);
		this.physics.rotate(rotation);
	}

	private double angularSpeed() {
		return Math.PI;
	}

	@Events.Keyboard(key = Key.A, type = EventType.BeingHold)
	public void rotateLeft(final DeltaState state) {
		final double rotation = -this.angularSpeed() * state.getDelta();
		this.rotateSprite(rotation);
		this.physics.rotate(rotation);
	}

	private void rotateSprite(final double rotation) {
		this.angle += rotation;
		final double alpha = this.physics.spriteRotatationAngle();
		this.setAppearance(Asteroids.SHIP_SPRITE.rotate(alpha));
	}

	@Events.Keyboard(key = Key.W, type = EventType.BeingHold)
	public void goUp(final DeltaState state) {
		this.physics.thrust(ACCELERATION);
	}

	@Events.Keyboard(key = Key.W, type = EventType.Released)
	public void goDown(final DeltaState state) {
		this.physics.thrust(0);
	}

	@Events.Keyboard(type = EventType.BeingHold, key = Key.SPACE)
	public void fire(final DeltaState state) {
		if (this.shootSleep <= 0) {
			this.shootSleep = Ship.FIRE_SLEEP;
			this.fire(new ShipFireEvent(this.physics));
		}
	}

	@Events.Update
	public void update(final double delta) {
		this.shootSleep -= delta;
		final Vector2D position = this.physics.getPosition(delta);
		this.move(position);
	}

	@Events.ColitionCheck.ForType(collisionStrategy = CollisionStrategy.PerfectPixel, type = Asteroid.class)
	private void checkAsteroidCollision(final Asteroid asteroid) {
		this.destroy();
		this.fire(new ShipLossLiveEvent(this, asteroid));
	}

	@Override
	protected IRule<?, ?>[] rules() {
		return new IRule<?, ?>[] { new TopOutRule(), new LeftOutRule(),
				new BottomOutRule(), new RightOutRule() };
	}

	public double getAngle() {
		return this.angle;
	}

}
