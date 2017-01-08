package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Pedro Antonio on 08/01/2017.
 */

public class Vehicle extends Sprite {

    private float speed = 55;
    private Vector2 velocity = new Vector2();
    private TiledMapTileLayer collisionLayer;

    public Vehicle(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        setSize(getWidth(), getHeight() * 1.5f);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {
        if(getX() < 1088) {
            velocity.x += speed;
        }

        else if(getX() > -1) {
            velocity.x += -speed;
        }

        setX(getX() + velocity.x * delta);
    }
}