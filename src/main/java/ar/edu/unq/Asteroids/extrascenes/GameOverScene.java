package ar.edu.unq.Asteroids.extrascenes;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.scenes.MenuScene;

public class GameOverScene extends MenuScene {

	@Bean
	private Asteroids game;

	private final int score;

	public GameOverScene(final int score) {
		super(Asteroids.BACKGROUND_SPRITE);
		this.score = score;
	}

	@Override
	protected Color disclamerColor() {
		return Color.red;
	}

	@Override
	protected String disclamer() {
		return "You loose, Try again\nyour score is " + this.score;
	}

	@Override
	protected Shape getMouseSprite() {
		return Asteroids.SHIP_SPRITE.rotate(-Math.PI / 4);
	}

	@Override
	public Color buttonTextColor() {
		return Color.black;
	}

	@Override
	public Font getFont() {
		return Asteroids.font;
	}

	@Override
	protected void addButtons(final MenuButtonBuilder buttonBuilder) {
		buttonBuilder.text("new_game").onClick(new Runnable() {
			@Override
			public void run() {
				GameOverScene.this.game.start();
			}
		}).build();
		buttonBuilder.text("exit").onClick(new Runnable() {
			@Override
			public void run() {
				GameOverScene.this.game.closeGame();
			}
		}).build();
	}

}
