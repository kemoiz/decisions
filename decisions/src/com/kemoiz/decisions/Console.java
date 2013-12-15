package com.kemoiz.decisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Console {

	private Fonts fonts;
	public boolean isCursorBlinking = true, newLinePlease = false;
	public int actualX = 64, actualY = 300;
	private float fsize = 0.2f, tickTime;
	private Stage stage;
	private String line = "";
	public Story story;
	public int newlinehow = 0;
	private SoundEngine sound;

	public Console(Stage stage) {
		sound = new SoundEngine();
		Gdx.input.setInputProcessor(new InputProc());
		this.stage = stage;
		fonts = new Fonts();
		fonts.doCrsr(actualX, actualY, fsize, "|", stage);
		story = new Story(fonts, stage);
		story.command(line, actualX, actualY);
		newLine(false);
	}

	public void tick() {
		if (Story.iNeedANewLine2) {
			actualX = 64;

			newLinePlease = true;
			story.command("", actualX, actualY);
			newLine(true);

		}
		if (Story.iNeedANewLine) {
			actualX = 64;

			newLinePlease = true;
			story.command("", actualX, actualY);
			newLine(true);

		} else {

			drawFromBuffer();

			refreshCursor();
			tickTime++;
			if (tickTime > 20) {
				tickTime = 0;
				if (isCursorBlinking) {
					blink();
					isCursorBlinking = false;
				} else {
					fonts.blinkCrsr(actualX, actualY, false);
					isCursorBlinking = true;
				}
			}

			if (InputProc.enter) {
				InputProc.enter = false;
				newLinePlease = true;
				story.command(line, actualX, actualY - 32);
				newLine(false);
				newLine(true);
			}
		}
	}

	private void refreshCursor() {
		fonts.refreshCrsr(actualX, actualY);

	}

	private void drawFromBuffer() {

		if (InputProc.actualKey != '[' && !InputProc.taken) {
			sound.playTick();
			if (InputProc.actualKey == ']') {
				line = removeLastChar(line);
				InputProc.taken = true;
				if (actualX > 0) {
					actualX = actualX - 13;
				}

			} else {
				line = line + InputProc.actualKey;
				actualX = actualX + 13;
			}
			fonts.renderLine(actualX + 64, actualY, fsize, line, stage);

			InputProc.taken = true;
			if (actualX > 760) {
				newLine(true);
			}

		}

	}

	public void newLine(boolean withLine) {

		fonts.dumpLine(line);
		actualX = 64;
		actualY = actualY - 32;
		newLinePlease = true;
		if (withLine) {

			line = ">";
		} else
			line = "";

		fonts.renderLine(actualX + 64, actualY, fsize, line, stage);

	}

	private void blink() {
		fonts.blinkCrsr(actualX, actualY, true);

	}

	private String removeLastChar(String s) {
		if (s == null || s.length() < 1) {
			return s;
		}
		return s.substring(0, s.length() - 1);
	}

}
