package com.pedro.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Frogger implements ApplicationListener {
	private SpriteBatch batch;
	private Texture jugador;
	private Sprite spriteJugador;
	private float velocidadJugador = 7;
	private OrthographicCamera camera;
	private StickDireccion analogico;
	private Stage stage;
	private TextureRegion backgroundTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10f * aspectRatio, 10f);

		analogico = new StickDireccion(65,65);
		backgroundTexture = new TextureRegion(new Texture("background2.png"), 0, 0, 1920, 1080);

		jugador = new Texture(Gdx.files.internal("0.png"));
		spriteJugador = new Sprite(jugador);
		spriteJugador.setPosition(Gdx.graphics.getWidth() / 2 - spriteJugador.getWidth() / 2,
				Gdx.graphics.getHeight()/2-spriteJugador.getHeight() / 2);
		spriteJugador.setSize(100,100);
		stage = new Stage(new ScreenViewport(), batch);
		stage.addActor(analogico);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		spriteJugador.setX(spriteJugador.getX() + analogico.getKnobPercentX()*velocidadJugador);
		spriteJugador.setY(spriteJugador.getY() + analogico.getKnobPercentY()*velocidadJugador);

		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		spriteJugador.draw(batch);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		jugador.dispose();
	}
}