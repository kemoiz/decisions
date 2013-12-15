package com.kemoiz.decisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Fonts {

	public BitmapFont white;
	private LabelStyle headingStyle;
	private Stage stage;
	private Label crsr;
	private Label line;

	public Fonts() {
		white = new BitmapFont(Gdx.files.internal("data/white.fnt"), false);
		BitmapFont whiteSmall = new BitmapFont(
				Gdx.files.internal("data/white.fnt"), false);
		headingStyle = new LabelStyle(white, Color.WHITE);
		newLine();
	}

	public void newLine() {
		line = new Label(">", headingStyle);
	}

	public void renderText(float x, float y, float size, String cont,
			Stage stage) {
		Label label = new Label(cont, headingStyle);
		label.setPosition(x, y);
		label.setFontScale(0.2f);
		this.stage = stage;
		stage.addActor(label);

	}

	public void renderLine(int x, int y, float size, String cont, Stage stage) {
		line.setText(cont);
		line.setPosition(64, y);
		line.setFontScale(0.2f);
		this.stage = stage;
		stage.addActor(line);

	}

	public void dumpLine(String lineString) {
		renderText(line.getX(), line.getY(), line.getFontScaleX(), lineString,
				stage);
	}

	public void doCrsr(int x, int y, float size, String cont, Stage stage) {
		crsr = new Label(cont, headingStyle);
		crsr.setPosition(x, y);
		crsr.setFontScale(size);
		this.stage = stage;
		stage.addActor(crsr);
	}

	public void blinkCrsr(int x, int y, boolean m) {
		crsr.setPosition(x, y);
		crsr.setVisible(m);

	}

	public void refreshCrsr(int actualX, int actualY) {
		crsr.setPosition(actualX + 13, actualY);

	}

}