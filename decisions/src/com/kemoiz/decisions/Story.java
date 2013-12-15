package com.kemoiz.decisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Story {

	public static boolean iNeedANewLine = false;
	public static boolean iNeedANewLine2 = false;
	private int cmdDid = 0, talk = 0;
	private Fonts fonts;
	private float actualX, actualY;
	private Stage stage;
	private StoryStrings strs;
	private SoundEngine sound;
	private int level;
	private boolean ga = false;
	private boolean gb;
	public static boolean DONE = false;

	public Story(Fonts fonts, Stage stage) {
		this.fonts = fonts;
		this.stage = stage;
		strs = new StoryStrings();
		sound = new SoundEngine();

	}

	public void command(String line, float x, float y) {
		if (GameScreen.failure)
			sound.mute();
		sound.playEnter();
		actualX = x;
		actualY = y;
		if (gb) {
			text(18);
			gb = false;
			iNeedANewLine2 = false;
			line = "";
			return;
		}
		if (ga) {
			text(14);
			ga = false;
			iNeedANewLine2 = false;
			line = "";
			return;
		}
		switch (cmdDid) {
		case 0:
			text(0);
			iNeedANewLine = true;
			break;
		case 1:
			text(1);
			iNeedANewLine = false;
			break;
		}
		if (cmdDid > 1)
			fab(line);
		cmdDid++;
	}

	private void fab(String line) {

		System.out.println(line);
		if (line.length() > 0)
			line = line.substring(1);

		if (level == 21) {
			text(30);
			DONE = true;
			return;
		}

		if (level == 20) {

			switch (line) {
			case "yes":
				text(28);
				level = 21;
				return;
			case "ok":
				text(28);
				level = 21;
				return;

			case "okay":
				text(28);
				level = 21;
				return;

			default:
				text(125);
				iNeedANewLine2 = true;
				GameScreen.failure = true;

			}

		}

		if (level == 10) {
			text(100);
			level = 20;

		}

		if (level == 9) {

			switch (line) {
			case "i love you":
				text(100);
				level = 20;
				return;
			case "i like you":
				text(100);
				level = 20;
				return;
			case "please":
				text(27);
				return;
			default:
				text(125);
				iNeedANewLine2 = true;
				GameScreen.failure = true;
				break;

			}

		}

		if (level == 8) {

			switch (line) {
			case "yes":
				text(100);
				level = 20;
				return;
				// break;
			case "no":
				text(26);
				level = 9;
				break;

			default:
				text(25);
				break;

			}
		}
		if (level == 7) {
			text(24);
			level = 8;
			return;
		}
		if (level == 6) {
			text(22);
			sound.playMusic(1);
			level++;
			return;
		}
		if (level == 5) {

			switch (line) {
			case "yes":
				text(21);
				level = 6;
				break;
			default:
				iNeedANewLine2 = true;
				GameScreen.failure = true;

			}

		}
		if (level == 4) {
			if (line != "no") {
				text(20);
				level = 5;
				return;
			} else {
				iNeedANewLine2 = true;
				GameScreen.failure = true;
			}
		}
		if (level == 3) {

			switch (line) {
			case "yes":
				text(19);
				level = 4;
				break;
			case "no":
				text(125);
				iNeedANewLine2 = true;
				GameScreen.failure = true;
				break;
			}

		}

		if (level == 2) {

			switch (line) {

			case "whoareyou":
				text(16);
				return;
			case "yes":
				text(17);
				iNeedANewLine2 = true;
				gb = true;
				level = 3;
				sound.playMusic(3);
				break;
			case "no":
				text(125);
				iNeedANewLine = true;
				GameScreen.failure = true;
				break;

			}

		}
		if (level == 1 || level == 2) {

			switch (line) {
			case "whatyoudo":
				text(15);
				break;
			case "whoareyou":
				if (level == 2) {
				} else {
					text(13);
					level = 2;
				}
				break;
			case "whoyoulove":
				iNeedANewLine = true;
				GameScreen.failure = true;
				break;

			case "talk":
				if (talk == 8) {
					iNeedANewLine = true;
					GameScreen.failure = true;
				} else {

					text(10);
					talk++;
				}

				break;
			case "exit":
				Gdx.app.exit();
				break;
			case "talk please":
				text(11);
				iNeedANewLine2 = true;
				ga = true;
				break;
			case "please":
				text(11);
				iNeedANewLine2 = true;
				ga = true;
				break;
			case "help":
				text(12);
				break;
			default:
				text(127);
				break;
			}

		}

		if (level == 0) {
			switch (line) {
			case "talk":
				switch (talk) {
				case 0:
					text(127);
					break;
				case 1:
					text(4);
					break;
				case 2:
					text(5);
					break;
				case 3:
					text(127);
					break;
				case 4:
					sound.playMusic(0);
					text(7);
					break;
				case 5:
					text(8);
					break;
				case 6:
					text(9);
					level = 1;
					break;
				case 7:
					text(10);
					break;

				}
				talk++;

				break;

			case "help":
				text(2);
				break;
			case "exit":
				Gdx.app.exit();
				break;
			case "connect":
				text(126);

				break;
			default:
				text(127);
				break;

			}
		}
	}

	private void text(int i) {
		fonts.renderText(64, actualY, 0.2f, strs.str[i], stage);

	}

}
