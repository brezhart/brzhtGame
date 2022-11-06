package com.brzht.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.brzht.game.BrzhtGame;
import com.brzht.game.GameScreen;
import com.brzht.game.UI;
import com.brzht.game.entity.DroppedItem;
import com.brzht.game.entity.Entity;
import com.brzht.game.entity.Particle;
import com.brzht.game.entity.Player;
import com.brzht.game.support.Globals;
import com.brzht.game.support.PosInt;
import com.brzht.game.world.blocks.*;
import com.brzht.game.world.blocks.Void;

import java.util.*;

public class World {
    Vector<Vector<Cell>> field;
    public Set<Entity> enitities = new HashSet<Entity>();
    public Set<Particle> particles = new HashSet<Particle>();
    public int WIDTH, HEIGHT;
    public BrzhtGame game;
    public GameScreen screen;

    Void voidBlock = new Void();
    public Player player;
    float time = 0;
    OrthographicCamera camera;

    public UI ui;

    public World(int w, int h, BrzhtGame game, GameScreen screen) {
        player = new Player(new Vector2(2.5f, 2.5f));
        ui = new UI(this);
        addEnitity(player);
        this.game = game;
        this.screen = screen;
        WIDTH = w;
        HEIGHT = h;
        field = new Vector<Vector<Cell>>();
        for (int i = 0; i < HEIGHT; i++) {
            field.add(new Vector<Cell>());
            for (int g = 0; g < WIDTH; g++) {
                field.get(i).add(new Cell(new PosInt(g, i)));
            }
        }
        Random random = new Random();
//        for (int i = 0; i < HEIGHT; i++){
//            for (int g = 0; g < WIDTH; g++){
//                field.get(i).get(g).add(new Sand(this).setPos(new PosInt(g,i)));
//                if (random.nextInt(1000)%2 == 0 && i > 10) {
//                    if (random.nextInt(1000)%2 == 0) {
//                        field.get(i).get(g).add(new Wheat(this).setPos(new PosInt(g, i)));
//                    } else {
//                        field.get(i).get(g).add(new Stone(this).setPos(new PosInt(g, i)));
//                    }
//                }
//            }
//        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int g = 0; g < WIDTH; g++) {
                addBlock(i, g, new Sand());
            }
        }
        for (int i = 10; i < 12; i++) {
            for (int g = 5; g < WIDTH - 5; g++) {
                addBlock(i, g, new PavStone());
            }
        }

        for (int i = 12; i < 35; i++) {
            for (int g = 20; g < 21; g++) {
                addBlock(i, g, new PavStone());
            }
        }

        for (int i = 12; i < 40; i++) {
            for (int g = 0; g < 19; g++) {
                if (random.nextInt(100) < 50) {
                    if (random.nextInt(100) < 10) {
                        addBlock(i, g, new DeadBush());
                    } else {
                        addBlock(i, g, new Wheat());
                    }
                }
            }
        }

        for (int i = 13; i < 40; i++) {
            for (int g = 22; g < 48; g++) {
                if (random.nextInt(100) < 70) {
                    if (random.nextInt(100) < 10) {
                        addBlock(i, g, new DeadBush());
                    } else {
                        addBlock(i, g, new Corn());
                    }
                }
            }
        }
//        for (int i = 41; i < 46; i+=1){
//            for (int g = 18; g < 23; g+=1){
//                addCell(i, g, new PavWooden());
//            }
//        }
//        addCell(40, 20, new PavWooden());
//        addCell(40, 20, new Door());
//        for (int i = 40; i < 47; i+=1){
//            for (int g = 17; g < 24; g+=1){
//                if (getCell(i,g).getClass() == Sand.class) {
//                    addCell(i, g, new StoneWall());
//                }
//            }
//        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int g = 0; g < HEIGHT; g++) {
                if (getBlock(i, g).getClass() == Sand.class) {
                    if (random.nextInt(100) < 20) {
                        if (random.nextInt(100) < 50) {
                            addBlock(i, g, new Hardwood());
                        } else {
                            addBlock(i, g, new Stone());
                        }
                    }
                }
            }
        }

    }

    public Block getBlock(int i, int g) {
        if (i < 0 || g < 0 || i >= HEIGHT || g >= WIDTH) {
            return voidBlock;
        }
        return field.get(i).get(g).blocks.lastElement();
    }

    public Cell getCell(int i, int g) {
        if (i < 0 || g < 0 || i >= HEIGHT || g >= WIDTH) {
            return null;
        }
        return field.get(i).get(g);
    }

    public boolean checkCellCollision(Vector2 pos, Vector2 size) {
        int left = (int) Math.floor((double) (pos.x - size.x / 2));
        int right = (int) Math.floor((double) (pos.x + size.x / 2));
        int top = (int) Math.floor((double) (pos.y + size.y / 2));
        int bottom = (int) Math.floor((double) (pos.y - size.y / 2));
        for (int i = left; i <= right; i++) {
            if (getBlock(top, i).isSolid) {
                return true;
            }
            ;
        }
        for (int i = left; i <= right; i++) {
            if (getBlock(bottom, i).isSolid) {
                return true;
            }
            ;
        }
        for (int i = top; i <= bottom; i++) {
            if (getBlock(i, left).isSolid) {
                return true;
            }
        }
        for (int i = top; i <= bottom; i++) {
            if (getBlock(i, right).isSolid) {
                return true;
            }
            ;
        }
        return false;
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Globals.camSupport.camera.update();
        game.batch.setProjectionMatrix(Globals.camSupport.camera.combined);
        game.batch.begin();
        Globals.shapeRenderer.setProjectionMatrix(Globals.camSupport.camera.combined);
        Globals.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

//        for (int i = HEIGHT - 1; i >= 0; i--){
//            for (int g = 0; g < WIDTH; g++){
//                for (int j = 0; j < field.get(i).get(g).size(); j+=1) {
//                    field.get(i).get(g).get(j).render(game.batch);
//                }
//            }
//        }

        // sort doesnt work :(((((
        Vector<Entity> entityVec = new Vector<Entity>();
        for (Entity entity : enitities) {
            entityVec.add(entity);
        }
        for (Entity particle : particles) {
            entityVec.add(particle);
        }
        entityVec.sort((s1, s2) -> {
            if (s1.pos.y == s2.pos.y) {
                return Integer.compare(s1.SpritePriority, s2.SpritePriority);
            }
            return -1 * Double.compare(s1.getSortingCoords(), s2.getSortingCoords());
        });
        int last_not_rendered = HEIGHT - 1;
        for (Entity entity : entityVec) {
            int toRender = (int) Math.floor(entity.getSortingCoords());
            for (int i = Math.min(HEIGHT - 1, last_not_rendered); i >= Math.max(toRender, 0); i--) {
                for (int g = 0; g < WIDTH; g++) {
                    //if (field.get(i).get(g).entities.size() == 0) continue;
                    for (int j = 0; j < field.get(i).get(g).blocks.size(); j += 1) {
                        field.get(i).get(g).blocks.get(j).render(game.batch);
                    }
                }
            }
            last_not_rendered = toRender - 1;
            entity.render(game.batch, time);
        }

        for (int i = last_not_rendered; i >= 0; i--) {
            for (int g = 0; g < WIDTH; g++) {
                //if (field.get(i).get(g).entities.size() == 0) continue;
                for (int j = 0; j < field.get(i).get(g).blocks.size(); j += 1) {
                    field.get(i).get(g).blocks.get(j).render(game.batch);
                }
            }
        }

        Vector3 lastCameraPos = Globals.camSupport.camera.position.cpy();
        float lastCameraZoom = Globals.camSupport.camera.zoom;

        Globals.camSupport.camera.position.set(Globals.camSupport.camera.viewportWidth / 2, Globals.camSupport.camera.viewportHeight / 2, 0);
        Globals.camSupport.camera.zoom = 1;
        Globals.camSupport.camera.update();

        //
        game.batch.setProjectionMatrix(Globals.camSupport.camera.combined);
        Globals.shapeRenderer.setProjectionMatrix(Globals.camSupport.camera.combined);
        //

        // RENDER UI
        player.inventory.render(game.batch);
        ui.render(game.batch);

        Globals.camSupport.camera.position.set(lastCameraPos);
        Globals.camSupport.camera.zoom = lastCameraZoom;
        //
        game.batch.end();
        Globals.shapeRenderer.end();
        //
//        Gdx.app.log("MyTag", "-----------");
    }

    public void addEnitity(Entity entityToAdd) {
        entityToAdd.world = this;
        entityToAdd.addToCellEntityContainer();
        enitities.add(entityToAdd);
    }

    public Vector<Entity> getEntitiesInRadius(Vector2 pos, float r) {
        Vector<Entity> entities = new Vector<>();
        pos = pos.cpy();
        for (int y = Math.max(0, (int) Math.floor(pos.y - r)); y <= Math.min(Math.ceil(pos.y + r), HEIGHT - 1); y += 1) {
            for (int x = Math.max(0, (int) Math.floor(pos.x - r)); x <= Math.min(Math.ceil(pos.x + r), WIDTH - 1); x += 1) {
                double dist = pos.cpy().sub(x, y).len();
                if (dist < r - 2) {
                    entities.addAll(getCell(y, x).entities);
                } else if (dist <= r + 1) {
                    for (Entity entity : getCell(y, x).entities) {
                        if (pos.cpy().sub(entity.pos).len() <= r) {
                            entities.add(entity);
                        }
                    }
                }
            }
        }
        return entities;
    }

    public void addPartice(Particle particleToAdd) {
        particleToAdd.world = this;
        particles.add(particleToAdd);
    }

    public void addBlock(int y, int x, Block blockToAdd) {
        blockToAdd.world = this;
        blockToAdd.pos = new PosInt(x, y);
        field.get(y).get(x).blocks.add(blockToAdd);
    }

    public void update(float deltaTime) {
        ui.update();
        //Gdx.app.log("GG",String.valueOf(1/deltaTime));
        // MOVE CAMERA SO PLAYER ALWAYS VISIBLE;
        int keepInBounds = 220;
        Vector2 onScreen = Globals.camSupport.coordsToPixel(player.pos);
        if (onScreen.x > Globals.camSupport.camera.viewportWidth - keepInBounds) {
            Globals.camSupport.camera.translate(onScreen.x - (Globals.camSupport.WIDTH() - keepInBounds), 0);
        }
        if (onScreen.y > Globals.camSupport.camera.viewportHeight - keepInBounds) {
            Globals.camSupport.camera.translate(0, onScreen.y - (Globals.camSupport.HEIGHT() - keepInBounds));
        }

        if (onScreen.x < keepInBounds) {
            Globals.camSupport.camera.translate(onScreen.x - keepInBounds, 0);
        }
        if (onScreen.y < keepInBounds) {
            Globals.camSupport.camera.translate(0, onScreen.y - keepInBounds);
        }


        for (int i = 0; i < HEIGHT; i += 1) {
            for (int g = 0; g < WIDTH; g += 1) {
                getBlock(i, g).update(1);
            }
        }

        time += deltaTime;

        for (Iterator<Particle> iter = particles.iterator(); iter.hasNext(); ) {
            Particle particle = iter.next();
            particle.update();
        }

        for (Iterator<Entity> iter = enitities.iterator(); iter.hasNext(); ) {
            Entity entity = iter.next();
            entity.update();
        }

        for (Iterator<Entity> iter = enitities.iterator(); iter.hasNext(); ) {
            Entity entity = iter.next();
            entity.updatePos(deltaTime);
        }
        enitities.removeIf(entity -> entity.IS_REMOVED);
        particles.removeIf(particle -> particle.IS_REMOVED);
    }
}
