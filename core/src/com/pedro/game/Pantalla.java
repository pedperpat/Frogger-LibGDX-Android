package com.pedro.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

/**
 * Created by Pedro Antonio on 06/01/2017.
 */

public class Pantalla implements Screen, InputProcessor {

    private Random r = new Random();
    private int lifes = 3;
    private Sprite life;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;
    private Vehicle vehicle1, vehicle2, vehicle3, vehicle4;
    private RiverTrunk trunk1, trunk2;
    private Stage stage;
    private StickDireccion analogico;
    private String[] vehiculos = new String[] { "vehiculo0.png", "vehiculo1.png", "vehiculo2.png",
            "vehiculo3.png", "vehiculo4.png", "vehiculo5.png", "vehiculo6.png" };
    private Rectangle playerRect;
    private Rectangle vehicleRect1, vehicleRect2, vehicleRect3,vehicleRect4;

    public void update()
    {
        playerRect = new Rectangle(player.getX(), player.getY(),
                player.getWidth(),
                player.getHeight());
        vehicleRect1 = new Rectangle(vehicle1.getX(), vehicle1.getY(),
                vehicle1.getWidth(), vehicle1.getHeight());

        vehicleRect2 = new Rectangle(vehicle2.getX(), vehicle2.getY(),
                vehicle2.getWidth(), vehicle2.getHeight());

        vehicleRect3 = new Rectangle(vehicle3.getX(), vehicle3.getY(),
                vehicle3.getWidth(), vehicle3.getHeight());

        vehicleRect4 = new Rectangle(vehicle4.getX(), vehicle4.getY(),
                vehicle4.getWidth(), vehicle4.getHeight());

        // If player collides with some vehicle...
        if (isOverlapping(playerRect,vehicleRect1) || isOverlapping(playerRect,vehicleRect2)
                || isOverlapping(playerRect,vehicleRect3) || isOverlapping(playerRect,vehicleRect4))
        {
            player.setX(870);
            player.setY(50);
            if(lifes != 0 && lifes < 4) {
                lifes--;
            }
            if(lifes == 2) {
                life = new Sprite(new Texture("2vidas.png"));
                life.setPosition(20,1000);
                life.setSize(80,80);
            }
            else if(lifes == 1) {
                life = new Sprite(new Texture("1vida.png"));
                life.setPosition(20,1000);
                life.setSize(80,80);
            }
            else if(lifes == 0){
                life = new Sprite(new Texture("ultimaVida.png"));
                life.setPosition(20,1000);
                life.setSize(80,80);
            }
        }
    }

    public boolean isOverlapping(Rectangle player, Rectangle vehicle) {
        if(player.overlaps(vehicle)) {
            return true;
        }
        return false;
    }

    @Override
    public void show() {

        TmxMapLoader loader = new TmxMapLoader();
        mapa = loader.load("froggerMap.tmx");

        renderer = new OrthogonalTiledMapRenderer(mapa);
        camera = new OrthographicCamera();
        TiledMapTileLayer layer0 = (TiledMapTileLayer) mapa.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2,
                layer0.getHeight() * layer0.getTileHeight() / 2, 0);
        camera.position.set(center);

        life = new Sprite(new Texture("3vidas.png"));

        life.setPosition(20,1000);
        life.setSize(80,80);


        player = new Player(new Sprite(new Texture("0.png")), (TiledMapTileLayer)mapa.getLayers().get(0));
        player.setPosition(870,50);
        player.setSize(64,64);

        vehicle1 = new Vehicle(new Sprite(new Texture(vehiculos[r.nextInt(6)])),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        vehicle1.setPosition(0, r.nextInt((400 - 200) + 1) + 200);
        vehicle1.setSize(64,64);

        vehicle2 = new Vehicle(new Sprite(new Texture(vehiculos[r.nextInt(6)])),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        vehicle2.setPosition(20, r.nextInt((500 - 430) + 1) + 430);
        vehicle2.setSize(64,64);

        vehicle3 = new Vehicle(new Sprite(new Texture(vehiculos[r.nextInt(6)])),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        vehicle3.setPosition(1900, r.nextInt((550 - 500) + 1) + 500);
        vehicle3.setSize(64,64);

        vehicle4 = new Vehicle(new Sprite(new Texture(vehiculos[r.nextInt(6)])),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        vehicle4.setPosition(2000, r.nextInt((250 - 220) + 1) + 220);
        vehicle4.setSize(64,64);

        trunk1 = new RiverTrunk((new Sprite(new Texture("trunk.png"))),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        trunk1.setPosition(400,895);
        trunk1.setSize(64,64);

        trunk2 = new RiverTrunk((new Sprite(new Texture("trunk.png"))),
                (TiledMapTileLayer)mapa.getLayers().get(0));
        trunk2.setPosition(600,765);
        trunk2.setSize(64,64);

        analogico = new StickDireccion(65,65);
        stage = new Stage(new ScreenViewport());
        stage.addActor(analogico);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

        if(!player.collidesBottom() && !player.collidesLeft() && !player.collidesRight() && !player.collidesTop()) {
            player.setX(player.getX() + analogico.getKnobPercentX()*player.getSpeed());
            player.setY(player.getY() + analogico.getKnobPercentY()*player.getSpeed());
        }else {
            player.setX(870);
            player.setY(50);
        }
        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        vehicle1.draw(renderer.getBatch());
        vehicle2.draw(renderer.getBatch());
        vehicle3.draw(renderer.getBatch());
        vehicle4.draw(renderer.getBatch());
        trunk1.draw(renderer.getBatch());
        trunk2.draw(renderer.getBatch());
        life.draw(renderer.getBatch());
        renderer.getBatch().end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        update();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
        mapa.dispose();
        renderer.dispose();
        player.getTexture().dispose();
        vehicle1.getTexture().dispose();
        vehicle2.getTexture().dispose();
        vehicle3.getTexture().dispose();
        vehicle4.getTexture().dispose();
        trunk1.getTexture().dispose();
        trunk2.getTexture().dispose();
        life.getTexture().dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
