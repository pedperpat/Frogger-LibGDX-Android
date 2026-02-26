package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Pedro Antonio on 08/01/2017.
 */

public class GameOver implements Screen {

    private Frogger pantallaGameOver;
    private SpriteBatch batch;
    private Texture texture;
    private TextureRegion backgroundTexture;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Sound gameOverAudio;
    private Textos texto = new Textos("VOLVER A JUGAR", 715, 400),
            textoSalir = new Textos("SALIR", 715, 250);

    public GameOver(Frogger frogger) {
        pantallaGameOver = frogger;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();

        texture = new Texture("gameOver.jpg");
        backgroundTexture = new TextureRegion(texture, 0, 0, 1920, 1080);

        gameOverAudio = Gdx.audio.newSound(Gdx.files.internal("gameOver.wav"));
        gameOverAudio.play();
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        texto.update(batch);
        textoSalir.update(batch);
        batch.end();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(touchPos);

            // VOLVER A JUGAR at (715, 400)
            if (touchPos.x > 707 && touchPos.x < 1320 && touchPos.y > 340 && touchPos.y < 460) {
                pantallaGameOver.setGameScreen();
            }
            // SALIR at (715, 250)
            else if (touchPos.x > 707 && touchPos.x < 950 && touchPos.y > 190 && touchPos.y < 310) {
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
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        texto.dispose();
        textoSalir.dispose();
        if (gameOverAudio != null) {
            gameOverAudio.dispose();
        }
    }
}
