package ar.edu.unq.Asteroids.rules;

import ar.edu.unq.americana.GameComponent;
import ar.edu.unq.americana.rules.GenericOutOfBoundsRule;

public class LeftOutRule extends GenericOutOfBoundsRule<GameComponent<?>> {

	@Override
	protected boolean mustApply(final GameComponent<?> component,
			final double width, final double height, final int displayWidth,
			final int displayHeight) {
		return (component.getX() + width) <= 0;
	}

	@Override
	protected void apply(final GameComponent<?> component, final double width,
			final double height, final int displayWidth, final int displayHeight) {
		component.setX((displayWidth + width) - 1);
	}

}
