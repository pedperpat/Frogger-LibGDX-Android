package com.pedro.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Pedro Antonio on 17/01/2017.
 */

public class Textos {
    private GlyphLayout layout;
    private BitmapFont font;
    private String text;
    private int x;
    private int y;

    public Textos(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.getData().setScale(5, 5);
        layout = new GlyphLayout(font, text);
    }

    public void update(SpriteBatch batch) {
        font.draw(batch, layout, x, y);
    }
}
