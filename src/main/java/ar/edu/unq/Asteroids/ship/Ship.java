package ar.edu.unq.Asteroids.ship;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.utils.ShipUtils;
import ar.edu.unq.americana.DeltaState;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.constants.Key;
import ar.edu.unq.americana.events.annotations.EventType;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.utils.Vector2D;

public class Ship extends GameComponent<Level> {

	@Property("ship.rotation_delta")
	private static double ROTATION_DELTA;
	@Property("ship.max_speed")
	private static double MAX_SPEED;
	@Property("ship.acceleration")
	private static double ACCELERATION;

	private double angle = 0;
	private double acceleration = 0;
	private double speed = 0;
	private Vector2D vector;

	public Vector2D getVector() {
		return vector;
	}

	public void setVector(final Vector2D vector) {
		this.vector = vector;
	}

	public Ship() {
		this.setAppearance(Asteroids.SHIP_SPRITE);
		vector = new Vector2D(0, -1);
	}

	@Override
	public void onSceneActivated() {
		super.onSceneActivated();
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() / 2);
	}

	@Events.Keyboard(key = Key.D, type = EventType.BeingHold)
	public void rotateRight(final DeltaState state) {
		angle = angle + (ROTATION_DELTA * state.getDelta());
		ShipUtils.rotate(this, angle);
	}

	@Events.Keyboard(key = Key.A, type = EventType.BeingHold)
	public void rotateLeft(final DeltaState state) {
		angle = angle - (ROTATION_DELTA * state.getDelta());
		ShipUtils.rotate(this, angle);
	}

	@Events.Keyboard(key = Key.W, type = EventType.BeingHold)
	public void goUp(final DeltaState state) {
		acceleration = ACCELERATION;
	}

	@Events.Keyboard(key = Key.W, type = EventType.Released)
	public void goDown(final DeltaState state) {
		acceleration = -ACCELERATION;
	}

	@Events.Update
	public void update(final double delta) {
		speed = Math.min((acceleration * delta) + speed, MAX_SPEED);
		if (speed > 0) {
			final Vector2D newPos = vector.asVersor().producto(speed);
			this.move(newPos);
		} else {
			speed = 0;
		}

		final double width = this.getAppearance().getWidth() / 2;
		final double height = this.getAppearance().getHeight() / 2;
		final int displayWidth = this.getGame().getDisplayWidth();
		final int displayHeight = this.getGame().getDisplayHeight();
		if ((this.getX() - width) >= displayWidth) {
			this.setX(-width + 1);

		}
		if ((this.getX() + width) <= 0) {
			this.setX((displayWidth + width) - 1);

		}
		if ((this.getY() - height) >= displayHeight) {
			this.setY(-height + 1);

		}
		if ((this.getY() + height) <= 0) {
			this.setY((displayHeight + height) - 1);

		}

	}

}
