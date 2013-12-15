package com.kemoiz.decisions;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InputProc implements InputProcessor {

	public static char actualKey = '[';
	public static boolean taken = false;
	public static boolean enter = false;

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.ENTER:
			enter = true;
			break;
		case Keys.Q:
			actualKey = 'q';

			break;
		case Keys.W:
			actualKey = 'w';

			break;
		case Keys.E:
			actualKey = 'e';

			break;
		case Keys.R:
			actualKey = 'r';

			break;
		case Keys.T:
			actualKey = 't';

			break;
		case Keys.Y:
			actualKey = 'y';

			break;
		case Keys.U:
			actualKey = 'u';

			break;
		case Keys.I:
			actualKey = 'i';

			break;
		case Keys.O:
			actualKey = 'o';

			break;
		case Keys.P:
			actualKey = 'p';

			break;
		case Keys.A:
			actualKey = 'a';

			break;
		case Keys.S:
			actualKey = 's';

			break;
		case Keys.D:
			actualKey = 'd';

			break;
		case Keys.F:
			actualKey = 'f';

			break;
		case Keys.G:
			actualKey = 'g';

			break;
		case Keys.H:
			actualKey = 'h';

			break;
		case Keys.J:
			actualKey = 'j';

			break;
		case Keys.K:
			actualKey = 'k';

			break;
		case Keys.L:
			actualKey = 'l';
			break;
		case Keys.Z:
			actualKey = 'z';
			break;
		case Keys.X:
			actualKey = 'x';
			break;
		case Keys.C:
			actualKey = 'c';
			break;
		case Keys.V:
			actualKey = 'v';
			break;
		case Keys.B:
			actualKey = 'b';
			break;
		case Keys.N:
			actualKey = 'n';
			break;
		case Keys.M:
			actualKey = 'm';
			break;
		case Keys.BACKSPACE:
			actualKey = ']';

			break;
		case Keys.SPACE:
			actualKey = ' ';

			break;
		case Keys.CONTROL_RIGHT:
			break;

		default:
			actualKey = '[';
			taken = false;
			break;

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		actualKey = '[';
		taken = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
