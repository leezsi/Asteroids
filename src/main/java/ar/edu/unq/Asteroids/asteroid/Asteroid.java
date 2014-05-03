package ar.edu.unq.Asteroids.asteroid;

import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Asteroid extends GameComponent<Level> {

	private final Vector2D vector;

	public Asteroid(final double x, final double y, final Vector2D direction) {
		this.setAppearance(this.getSprite());
		this.setX(x);
		this.setY(y);
		vector = direction;
	}

	public Asteroid(final double x, final double y) {
		this(x, y, AsteroidUtils.getDirection(x, y));
	}

	protected abstract Sprite getSprite();

	protected abstract Asteroid[] getChildren();

	protected void onDie() {
		this.getScene().replace(this, this.getChildren());
	}

	@Events.Update
	public void update(final double delta) {
		final Vector2D newPos = vector.asVersor().producto(delta * 100);
		this.move(newPos);

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
