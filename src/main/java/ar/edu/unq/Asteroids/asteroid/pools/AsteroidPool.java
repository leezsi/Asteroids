package ar.edu.unq.Asteroids.asteroid.pools;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.asteroid.Asteroid;
import ar.edu.unq.Asteroids.asteroid.AsteroidLarge;
import ar.edu.unq.Asteroids.asteroid.AsteroidMedium;
import ar.edu.unq.Asteroids.asteroid.AsteroidSmall;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.pooling.AbstractPool;

public abstract class AsteroidPool<A extends Asteroid> extends AbstractPool<A> {

	private static class AsteroidLargePool extends AsteroidPool<AsteroidLarge> {

		@Property("pool.large_asteorid.max")
		private static int max;

		@Override
		protected void initialize() {
			this.initialize(max);
		}

		@Override
		protected Class<AsteroidLarge> getType() {
			return AsteroidLarge.class;
		}

	}

	private static class AsteroidMediumPool extends
			AsteroidPool<AsteroidMedium> {

		@Property("pool.medium_asteorid.max")
		private static int max;

		@Override
		protected void initialize() {
			this.initialize(max);
		}

		@Override
		protected Class<AsteroidMedium> getType() {
			return AsteroidMedium.class;
		}

	}

	private static class AsteroidSmallPool extends AsteroidPool<AsteroidSmall> {

		@Property("pool.small_asteorid.max")
		private static int max;

		@Override
		protected void initialize() {
			this.initialize(max);
		}

		@Override
		protected Class<AsteroidSmall> getType() {
			return AsteroidSmall.class;
		}

	}

	private static AsteroidLargePool large = new AsteroidLargePool();
	private static AsteroidMediumPool medium = new AsteroidMediumPool();
	private static AsteroidSmallPool small = new AsteroidSmallPool();

	public static AsteroidLarge getLarge() {
		return large.get();
	}

	public static void addAsteroid(final AsteroidLarge asteroid) {
		large.add(asteroid);
	}

	public static void addAsteroid(final AsteroidMedium asteroid) {
		medium.add(asteroid);
	}

	public static void addAsteroid(final AsteroidSmall asteroid) {
		small.add(asteroid);
	}

	public static AsteroidMedium getMedium() {
		return medium.get();
	}

	public static AsteroidSmall getSmall() {
		return small.get();
	}

	@Bean
	private Asteroids game;

	@SuppressWarnings("unchecked")
	@Override
	protected A createNew() {
		final Asteroid instance = super.createNew();
		final int mediumWidth = this.game.getDisplayWidth() / 2;
		final int mediumHeight = this.game.getDisplayHeight() / 2;
		instance.initialize(mediumWidth, mediumHeight, mediumWidth,
				mediumHeight);
		return (A) instance;
	}

}
