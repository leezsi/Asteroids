package ar.edu.unq.Asteroids.ship;

import java.util.List;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.Asteroids.bullet.Bullet;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.rules.BottomOutRule;
import ar.edu.unq.Asteroids.rules.LeftOutRule;
import ar.edu.unq.Asteroids.rules.RightOutRule;
import ar.edu.unq.Asteroids.rules.TopOutRule;
import ar.edu.unq.Asteroids.utils.ShipUtils;
import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.colissions.CollisionDetector;
import ar.edu.unq.americana.colissions.CollitionGroup;
import ar.edu.unq.americana.components.utils.ComponentUtils;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;
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

	private double angle = 0;
	private double acceleration = 0;
	private double speed = 0;
	private Vector2D vector;
	private double shootSleep;

	public Vector2D getVector() {
		return this.vector;
	}

	public void setVector(final Vector2D vector) {
		this.vector = vector;
	}

	public Ship() {
		CollitionGroup.setAs(this, Bullet.class);
		this.setAppearance(Asteroids.SHIP_SPRITE);
		this.setZ(2);
		this.vector = new Vector2D(0, -1);
		this.shootSleep = 0;
	}

	@Override
	public void onSceneActivated() {
		this.center();
	}

	public Ship center() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() / 2);
		return this;
	}

	@Events.Keyboard(key = Key.D, type = EventType.BeingHold)
	public void rotateRight(final DeltaState state) {
		this.angle = this.angle + (ROTATION_DELTA * state.getDelta());
		ShipUtils.rotate(this, this.angle);
	}

	@Events.Keyboard(key = Key.A, type = EventType.BeingHold)
	public void rotateLeft(final DeltaState state) {
		this.angle = this.angle - (ROTATION_DELTA * state.getDelta());
		ShipUtils.rotate(this, this.angle);
	}

	@Events.Keyboard(key = Key.W, type = EventType.BeingHold)
	public void goUp(final DeltaState state) {
		this.acceleration = ACCELERATION;
	}

	@Events.Keyboard(key = Key.W, type = EventType.Released)
	public void goDown(final DeltaState state) {
		this.acceleration = -ACCELERATION;
	}

	@Events.Keyboard(type = EventType.BeingHold, key = Key.SPACE)
	public void fire(final DeltaState state) {
		if (this.shootSleep <= 0) {
			this.fire(new ShipFireEvent());
			this.shootSleep = Ship.FIRE_SLEEP;
		}
	}

	@Events.Update
	public void update(final double delta) {
		this.shootSleep -= delta;
		this.speed = Math.min((this.acceleration * delta) + this.speed,
				MAX_SPEED);
		if (this.speed > 0) {
			final Vector2D newPos = this.vector.asVersor().producto(this.speed);
			this.move(newPos);
			this.checkCollisions();
		} else {
			this.speed = 0;
		}
	}

	private void checkCollisions() {
		final List<Asteroid> asteroids = ComponentUtils
				.filter(this.getScene().getComponents())
				.byClass(Asteroid.class).get();
		for (final Asteroid asteroid : asteroids) {
			if (CollisionDetector.perfectPixel(this, asteroid)) {
				this.fire(new ShipLossLiveEvent(this, asteroid));
			}
		}
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
