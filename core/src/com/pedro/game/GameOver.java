package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Pedro Antonio on 08/01/2017.
 */

public class GameOver implements Screen {

    private Frogger pantallaGameOver;
    private SpriteBatch batch;
    private TextureRegion backgroundTexture;
    private OrthographicCamera camera;
    private Textos texto = new Textos("VOLVER A JUGAR", 715, 400),
            textoSalir = new Textos("SALIR", 715, 250);

    public GameOver(Frogger frogger) {
        pantallaGameOver = frogger;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 10f * aspectRatio, 10f);
        backgroundTexture = new TextureRegion(new Texture("gameOver.jpg"), 0, 0, 1920, 1080);

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        texto.update(batch);
        textoSalir.update(batch);
        batch.end();

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 707 && Gdx.input.getX() < 1320 && Gdx.input.getY() > 650 && Gdx.input.getY() < 740) {
                pantallaGameOver.setGameScreen();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}