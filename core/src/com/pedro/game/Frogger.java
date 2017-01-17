package com.pedro.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Frogger extends Game implements ApplicationListener{

	private Pantalla pantalla;
	private GameOver gameOver;
	private PantallaPrincipal pantallaPrincipal;

	@Override
	public void create() {
        setSplashScreen();
	}

	public void setGameScreen()
	{
		pantalla = new Pantalla(this);
		setScreen(pantalla);
	}

	public void setGameOverScreen()
	{
		gameOver = new GameOver(this);
		setScreen(gameOver);
	}

	public void setSplashScreen() {
		pantallaPrincipal = new PantallaPrincipal(this);
		setScreen(pantallaPrincipal);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}