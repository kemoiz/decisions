package com.kemoiz.decisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundEngine {

	Music music;
	Sound tick, enter;

	public SoundEngine() {
		enter = tick = Gdx.audio.newSound(Gdx.files
				.internal("sfx/decisions_02.wav"));
		tick = Gdx.audio.newSound(Gdx.files
				.internal("sfx/decisions_click1.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sfx/decisions_ms1.ogg"));
		music.setVolume(0.1f);

	}

	public void playTick() {
		tick.play(0.4f, 1f, 0.2f);
	}

	public void playEnter() {
		enter.play();
	}

	public void playMusic(int nr) {

		switch (nr) {
		case 0:

			break;
		case 1:
			music = Gdx.audio.newMusic(Gdx.files
					.internal("sfx/decisions_ms2.ogg"));
			break;
		case 2:
			music = Gdx.audio.newMusic(Gdx.files
					.internal("sfx/decisions_ms3.ogg"));
			break;
		case 3:
			music = Gdx.audio.newMusic(Gdx.files
					.internal("sfx/decisions_ms4.ogg"));
			break;

		}
		music.play();
	}

	public void mute() {
		music.setVolume(0);
	}
}
