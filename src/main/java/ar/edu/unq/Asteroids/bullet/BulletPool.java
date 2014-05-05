package ar.edu.unq.Asteroids.bullet;

import ar.edu.unq.americana.configs.Property;
import ar.edu.unq.americana.pooling.StrictPool;

public class BulletPool extends StrictPool<Bullet> {
	@Property("pool.bullet.max")
	private static int maxBullets;

	@Override
	protected void initialize() {
		super.initialize(maxBullets);
	}

	@Override
	protected Class<Bullet> getType() {
		return Bullet.class;
	}

}
