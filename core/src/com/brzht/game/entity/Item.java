package com.brzht.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;

public class Item {


    public TextureRegion texture;
    // WEAPON
    public float blockDamage = 5;
    public float entityDamage = 5;
    public int id;
    public boolean canBePlaced = false;
    public int cellToPlace = 0;

    //
    public int maxStackSize = 64;


    //
    public Item(String textureName) {
        this.texture = Assets.getRegion(textureName);

    }
    public Item(TextureRegion texture){
        this.texture = texture;
    }

//    @Override
//    public void render(SpriteBatch batch, float time){
//        float ang = (float) ((float)Math.atan2(angle.y, angle.x) * 180.0 / Math.PI);
//        batch.draw(Assets.getRegion(textureName), (int) (pos.x*32), (int) (pos.y*32), size.y/2, size.y/2, size.x, size.y, 1, 1, ang );
//    }
    public DroppedItem getDroppedItem(Vector2 pos){
        return new DroppedItem(this, pos);
    }
}
