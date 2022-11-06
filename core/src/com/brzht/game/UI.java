package com.brzht.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.craftingTable.CraftingTable;
import com.brzht.game.entity.Item;
import com.brzht.game.entity.particles.OneFrameSprite;
import com.brzht.game.support.Globals;
import com.brzht.game.support.Utils;
import com.brzht.game.world.World;

public class UI {
    World world;
    CraftingTable craftingTable;
    public UI(World world){
        craftingTable = new CraftingTable();
        this.world = world;
    }
    public void update(){

        Item item = world.player.inventory.getActiveItem();
        if (item != null && item.canBePlaced) {
            Vector2 pixel = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            Vector2 coords = Utils.floorVector2(Globals.camSupport.pixelToCoords(pixel));
            if (coords.cpy().sub(world.player.pos).len() <= 5) {
                if (world.getBlock((int) coords.y, (int) coords.x).canPlaceAtTop && world.getCell((int) coords.y, (int) coords.x).entities.isEmpty())  {
                    world.addPartice(new OneFrameSprite(Assets.getRegion("allow_sprite.png"), coords));
                    world.addPartice(new OneFrameSprite(item.texture, coords).setTransparency(0.5f).setPriority(10));
                } else {
                    world.addPartice(new OneFrameSprite(Assets.getRegion("ban_sprite.png"), coords));
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        if (craftingTable != null){
            craftingTable.render(batch, world.player.inventory);
        }
    }
}
