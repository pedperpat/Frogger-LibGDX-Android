package com.pedro.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Pedro Antonio on 20/12/2016.
 */

public class StickDireccion extends Touchpad implements Disposable {

    private Skin skin;

    public StickDireccion(float x, float y) {
        super(10, new TouchpadStyle());

        skin = new Skin();
        skin.add("touchBackground", new Texture("touchBackground.png"));
        skin.add("touchKnob", new Texture("touchKnob.png"));

        TouchpadStyle style = new TouchpadStyle();
        style.background = skin.getDrawable("touchBackground");
        style.knob = skin.getDrawable("touchKnob");

        setStyle(style);

        setBounds(65, 65, 250, 250);
        setPosition(x,y);
    }

    @Override
    public void dispose() {
        skin.dispose();
    }
}
