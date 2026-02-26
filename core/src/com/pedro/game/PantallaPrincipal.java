package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Antonio on 16/01/2017.
 */

public class PantallaPrincipal implements Screen{

    private Frogger pantallaPrincipal;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private TextureRegion backgroundTexture;
    private List<Textos> textosPantalla = new ArrayList<Textos>();

    public PantallaPrincipal(Frogger frogger) {
        pantallaPrincipal = frogger;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        backgroundTexture = new TextureRegion(new Texture("background2.png"), 0, 0, 1920, 1080);

        textosPantalla.add(new Textos("NUEVA PARTIDA", 715, 900));
        textosPantalla.add(new Textos("PUNTUACIONES", 715, 600));
        textosPantalla.add(new Textos("SALIR", 715, 300));
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        for (Textos font : textosPantalla)
        {
            font.update(batch);
        }
        batch.end();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(touchPos);

            // Adjusted coordinates for bottom-up Y axis (1080 - Y)
            if (touchPos.x > 707 && touchPos.x < 1290 && touchPos.y > 825 && touchPos.y < 920) {
                pantallaPrincipal.setGameScreen();
            } else if (touchPos.x > 707 && touchPos.x < 1320 && touchPos.y > 495 && touchPos.y < 626) {
                // TODO: Pantalla de puntuaciones
            } else if (touchPos.x > 707 && touchPos.x < 950 && touchPos.y > 220 && touchPos.y < 330) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
