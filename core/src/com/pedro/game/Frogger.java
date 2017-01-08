package com.pedro.game;

import com.badlogic.gdx.Game;

public class Frogger extends Game {

	@Override
	public void create () {
		// Para usar un mapa de tiles y mejorar las colisiones con los elementos del mapa.
		////////////////////////
		setScreen(new Pantalla());
		////////////////////////
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}