package ar.edu.unq.Asteroids.extrascenes;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.Asteroids.Asteroids;
import ar.edu.unq.Asteroids.levels.Level;
import ar.edu.unq.americana.appearances.Shape;
import ar.edu.unq.americana.components.Score;
import ar.edu.unq.americana.configs.Bean;
import ar.edu.unq.americana.scenes.MenuScene;

public class LevelWinScene extends MenuScene {
	@Bean
	private Asteroids game;

	public LevelWinScene(final Score<Level> score) {
		super(Asteroids.BACKGROUND_SPRITE);
	}

	@Override
	protected void addButtons(final MenuButtonBuilder buttonBuilder) {
		if (this.game.isFinish()) {
			buttonBuilder.text("new_game").onClick(new Runnable() {
				@Override
				public void run() {
					LevelWinScene.this.game.start();
				}
			}).build();
		} else {
			buttonBuilder.text("next_level").onClick(new Runnable() {
				@Override
				public void run() {
					LevelWinScene.this.game.nextLevel();
				}
			}).build();
		}
		buttonBuilder.text("exit").onClick(new Runnable() {
			@Override
			public void run() {
				LevelWinScene.this.game.closeGame();
			}
		}).build();
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
	protected Shape getMouseSprite() {
		return Asteroids.SHIP_SPRITE.rotate(-Math.PI / 4);
	}

	@Override
	protected Color disclamerColor() {
		return Color.green;
	}

	@Override
	protected String disclamer() {
		return this.game.getLocaleBoundle().getString("level_win_disclaimer");
	}
}
