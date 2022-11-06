package com.brzht.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;

public class DroppedItem extends Entity {
    public String textureName = "";
    public Item item;
    public int ticksFromDrop;
    public DroppedItem(Item item, Vector2 pos){
        super(pos);
        this.item = item;
        ticksFromDrop = 0;
    }
    public DroppedItem(Item item){
        super(new Vector2(0,0));
        this.item = item;
        ticksFromDrop = 0;
    }

    @Override
    public void update() {
        ticksFromDrop++;
    }
    public boolean canPick(){
        return ticksFromDrop >= 0;
    }

    @Override
    public void render(SpriteBatch batch, float time){
        super.render(batch, time);
        float ang = (float) ((float)Math.atan2(angle.y, angle.x) * 180.0 / Math.PI);

        batch.draw(item.texture, (int) ((pos.x - size.x/2.f)*32), (int) ((pos.y - size.y/2)*32), size.y*16, size.y*16, size.x*32, size.y*32, 1, 1, ang );
    }
}
