package ar.edu.unq.Asteroids.levels;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.Asteroids.asteroid.pools.AsteroidPool;
import ar.edu.unq.Asteroids.bullet.Bullet;
import ar.edu.unq.Asteroids.bullet.BulletDieEvent;
import ar.edu.unq.Asteroids.bullet.BulletPool;
import ar.edu.unq.Asteroids.ship.Ship;
import ar.edu.unq.Asteroids.ship.ShipFireEvent;
import ar.edu.unq.Asteroids.ship.ShipLossLiveEvent;
import ar.edu.unq.americana.Game;
import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.GameScene;
import ar.edu.unq.americana.appearances.Appearance;
import ar.edu.unq.americana.components.LifeCounter;
import ar.edu.unq.americana.components.Score;
import ar.edu.unq.americana.events.annotations.Events;
import ar.edu.unq.americana.utils.Tuning;

public class Level extends GameScene {

	private final Set<Asteroid> asteroids;
	private final BulletPool bulletPool;
	private final int level;
	private Ship ship;
	private Score<Level> score;
	private final LifeCounter<Level> lifeCounter;

	public Level(final int level, final Score<Level> score,
			final LifeCounter<Level> lifeCounter) {
		this.addComponent(this.score = score);
		this.addComponent(lifeCounter);
		this.align(score, lifeCounter);
		this.lifeCounter = lifeCounter;
		this.asteroids = new HashSet<Asteroid>();
		this.bulletPool = new BulletPool();
		this.level = level;
		this.addComponent(this.ship = new Ship());
	}

	private void align(final Score<Level> score,
			final LifeCounter<Level> lifeCounter) {
		final Appearance appearance = score.getAppearance();
		lifeCounter.alignBottomTo(score.getY() + (appearance.getHeight() / 2));
		lifeCounter.alignRightTo(score.getX() - appearance.getWidth());
	}

	private void addLargeAsteroids(final int level) {
		final Integer amount = Tuning.getInteger("level." + level + ".large");
		for (int i = 0; i < amount; i++) {
			this.addAsteroid(AsteroidPool.getLarge());
		}
	}

	@Override
	public void setGame(final Game game) {
		super.setGame(game);
		this.addBackground(game);
		this.addLargeAsteroids(this.level);
		this.addMediumAsteroids(this.level);
		this.addSmallAsteroids(this.level);
	}

	private void addSmallAsteroids(final int level2) {
		final Integer amount = Tuning.getInteger("level." + this.level
				+ ".small");
		for (int i = 0; i < amount; i++) {
			this.addAsteroid(AsteroidPool.getSmall());
		}
	}

	private void addMediumAsteroids(final int level2) {
		final Integer amount = Tuning.getInteger("level." + this.level
				+ ".medium");
		for (int i = 0; i < amount; i++) {
			this.addAsteroid(AsteroidPool.getMedium());
		}
	}

	private void addBackground(final Game game) {

		final int width = game.getDisplayWidth();
		final int height = game.getDisplayHeight();
		this.addComponent(new GameComponent<Level>() {
			{
				this.setX(width / 2);
				this.setY(height / 2);
				this.setZ(0);
				this.setAppearance(Asteroids.BACKGROUND_SPRITE);
			}

			@Override
			protected boolean isCollisionable() {
				return false;
			}
		});
	}

	@Events.Fired(ShipFireEvent.class)
	private void bulletFireRequest(final ShipFireEvent event) {
		final Bullet bullet = this.bulletPool.get();
		if (bullet != null) {
			bullet.initializeFrom(this.ship);
			this.addComponent(bullet);
		}
	}

	@Events.Fired(BulletDieEvent.class)
	public void bulletDie(final BulletDieEvent event) {
		final Bullet bullet = event.getBullet();
		this.removeComponent(bullet);
		this.bulletPool.add(bullet);
	}

	@Events.Fired(ShipLossLiveEvent.class)
	private void shipLoosLive(final ShipLossLiveEvent event) {
		event.getAsteroid().onDie();
		this.lifeCounter.lossLife();
		this.replaceShip();
	}

	private void replaceShip() {
		this.ship.destroy();
		this.addComponent(this.ship = new Ship());
		this.ship.center();
	}

	protected Set<Asteroid> getAsteroids() {
		return this.asteroids;
	}

	public void addAsteroid(final Asteroid asteroid) {
		this.asteroids.add(asteroid);
		this.addComponent(asteroid);
	}

	public void replace(final Asteroid asteroid, final Asteroid... children) {
		asteroid.destroy();
		asteroid.returnToPool();
		for (final Asteroid child : children) {
			this.addAsteroid(child);
		}
	}

	public Score<Level> getScore() {
		return this.score;
	}

}
