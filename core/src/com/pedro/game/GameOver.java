package com.pedro.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Pedro Antonio on 08/01/2017.
 */

public class GameOver implements Screen {

    private Batch batch;
    private TextureRegion backgroundTexture;

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new TextureRegion(new Texture("gameOver.png"), 0, 0, 1920, 1080);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        batch.end();
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

    }
}
