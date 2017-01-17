package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Antonio on 16/01/2017.
 */

public class PantallaPrincipal implements Screen{

    private Frogger pantallaPrincipal;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureRegion backgroundTexture;
    private List<Textos> textosPantalla = new ArrayList<Textos>();

    public PantallaPrincipal(Frogger frogger) {
        pantallaPrincipal = frogger;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 10f * aspectRatio, 10f);
        backgroundTexture = new TextureRegion(new Texture("background2.png"), 0, 0, 1920, 1080);

        textosPantalla.add(new Textos("NUEVA PARTIDA", 715, 900));
        textosPantalla.add(new Textos("PUNTUACIONES", 715, 600));
        textosPantalla.add(new Textos("SALIR", 715, 300));
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        for (Textos font : textosPantalla)
        {
            font.update(batch);
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 707 && Gdx.input.getX() < 1290 && Gdx.input.getY() > 160 && Gdx.input.getY() < 255) {
                pantallaPrincipal.setGameScreen();
            } else if (Gdx.input.getX() > 707 && Gdx.input.getX() < 1320 && Gdx.input.getY() > 454 && Gdx.input.getY() < 585) {
                // TODO: Pantalla de puntuaciones
            } else if (Gdx.input.getX() > 707 && Gdx.input.getX() < 950 && Gdx.input.getY() > 750 && Gdx.input.getY() < 860) {
                Gdx.app.exit();
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