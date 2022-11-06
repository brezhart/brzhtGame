package com.brzht.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brzht.game.craftingTable.CraftingTable;
import com.brzht.game.support.Globals;
import com.brzht.game.world.World;

public class GameScreen implements Screen {
    final BrzhtGame game;
    public World world;




    public GameScreen(final BrzhtGame game) {
        Globals.camSupport.viewport = new FitViewport(640, 480, Globals.camSupport.camera);
        InputProcessor inputProcessor = new InputProcessor() {

            @Override
            public boolean keyDown(int keycode) {

                switch (keycode){
                    case 51:
                        world.player.GO_UP = true;
                        break;
                    case 47:
                        world.player.GO_DOWN = true;
                        break;
                    case 29:
                        world.player.GO_LEFT = true;
                        break;
                    case 32:
                        world.player.GO_RIGHT = true;
                        break;
                    case 45:
                        world.player.inventory.dropActiveItem(world);
                        break;
                    case 69:
                        Globals.camSupport.camera.zoom-=0.1;
                        Globals.camSupport.camera.zoom = Math.max(0.3f, Globals.camSupport.camera.zoom);
                        break;
                    case 70:
                        Globals.camSupport.camera.zoom+=0.1;
                        Globals.camSupport.camera.zoom = Math.min(3f, Globals.camSupport.camera.zoom);
                        break;
                    case 49:
                        Vector2 pixel = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                        Vector2 coords = Globals.camSupport.pixelToCoords(pixel);
                        int x = (int) coords.x;
                        int y = (int) coords.y;
                        world.getBlock(y,x).onUse();
                        break;
                    case 44:
                        break;
                    case 37:
                        if (world.ui.craftingTable == null){
                            world.ui.craftingTable = new CraftingTable();
                        } else {
                            world.ui.craftingTable = null;
                        }
                        break;
                    case 66:
                        if (world.ui.craftingTable != null) {
                            world.ui.craftingTable.tryCraft(world.player.inventory);
                        }
                        break;
                    case 20:
                        if (world.ui.craftingTable != null) {
                            world.ui.craftingTable.toNextReceipe();
                        }
                        break;
                    case 19:
                        if (world.ui.craftingTable != null) {
                            world.ui.craftingTable.toLastReceipe();
                        }
                        break;

                }
                if (8 <= keycode && keycode <= 16){
                    world.player.inventory.pickedSlot = keycode-8;
                }

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
//                Gdx.app.log("TEST", String.valueOf(keycode));
                switch (keycode){
                    case 51:
                        world.player.GO_UP = false;
                        break;
                    case 47:
                        world.player.GO_DOWN = false;
                        break;
                    case 29:
                        world.player.GO_LEFT = false;
                        break;
                    case 32:
                        world.player.GO_RIGHT = false;
                        break;
                    case 33:
                        break;

                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT){
                    world.player.hit();
                } else {
                    Vector2 pixel = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                    Vector2 coords = Globals.camSupport.pixelToCoords(pixel);
                    int x = (int) coords.x;
                    int y = (int) coords.y;
                    world.player.placeBlock(y,x);
                }
                return true;
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

                screenY = Gdx.graphics.getHeight() - screenY;
                Vector2 playerScreenPos = Globals.camSupport.coordsToPixel(world.player.pos);
                world.player.angle = new Vector2(screenX, screenY).sub(playerScreenPos);
                world.player.angle.nor();
                return true;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                if (amountY > 0){
                    world.player.inventory.movePickedSlotRight();
                } else if (amountY < 0) {
                    world.player.inventory.movePickedSlotLeft();
                }
                return true;
            }

        };
        Gdx.input.setInputProcessor(inputProcessor);
        this.game = game;
        world = new World(48,60, game, this);



    }

    @Override
    public void render(float delta) {
        update(delta);
        world.render();
        //game.batch.end();

    }

    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;
        world.update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

}