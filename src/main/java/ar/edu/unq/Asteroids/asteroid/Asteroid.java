package ar.edu.unq.Asteroids.asteroid;

import ar.edu.unq.Asteroids.bullet.Bullet;
import ar.edu.unq.Asteroids.bullet.BulletDieEvent;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.Asteroids.rules.BottomOutRule;
import ar.edu.unq.Asteroids.rules.LeftOutRule;
import ar.edu.unq.Asteroids.rules.RightOutRule;
import ar.edu.unq.Asteroids.rules.TopOutRule;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.appearances.Animation;
import ar.edu.unq.americana.appearances.Sprite;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.events.ioc.collision.CollisionStrategy;
import ar.edu.unq.americana.rules.IRule;
import ar.edu.unq.americana.utils.TrigonometricsAndRandomUtils;
import ar.edu.unq.americana.utils.Vector2D;

public abstract class Asteroid extends GameComponent<Level> {

	@Property("asteroid.speed")
	private static double SPEED;
	private Vector2D vector;

	public Asteroid() {
		this.setAppearance(new Animation(0.1, this.getSprites()));
	}

	public Asteroid initialize(final Asteroid asteroid) {
		this.setX(asteroid.getX());
		this.setY(asteroid.getY());
		this.vector = AsteroidUtils.getDirection(this.getX(), this.getY());
		return this;
	}

	public void initialize(final double x, final double y,
			final int mediumWidth, final int mediumHeight) {
		final Vector2D position = this.generatePosition(x, y, mediumWidth,
				mediumHeight);
		this.setX(position.getX());
		this.setY(position.getY());
		this.vector = AsteroidUtils.getDirection(this.getX(), this.getY());
	}

	private Vector2D generatePosition(final double x, final double y,
			final int mediumWidth, final int mediumHeight) {
		final double alpha = TrigonometricsAndRandomUtils.angle();
		final int maxModule = Math.min(mediumWidth, mediumHeight);
		final double module = TrigonometricsAndRandomUtils.gausean(maxModule,
				maxModule / 2, 50, maxModule);
		return TrigonometricsAndRandomUtils.vector(alpha, module).suma(
				new Vector2D(x, y));
	}

	protected abstract Sprite[] getSprites();

	public abstract Asteroid[] getChildren();

	public void onDie(final Bullet bullet) {
		this.fire(new AsteroidDieEvent(this));
		this.fire(new BulletDieEvent(bullet));
	}

	@Events.Update
	public void update(final double delta) {
		final Vector2D newPos = this.vector.asVersor().multiply(delta * SPEED);
		this.move(newPos);
	}

	@Events.ColitionCheck.ForType(collisionStrategy = CollisionStrategy.PerfectPixel, type = Bullet.class)
	private void bulletCollision(final Bullet bullet) {
		this.onDie(bullet);
	}

	@Override
	protected IRule<?, ?>[] rules() {
		return new IRule<?, ?>[] { new LeftOutRule(), new TopOutRule(),
				new RightOutRule(), new BottomOutRule() };
	}

	public abstract void returnToPool();

}
