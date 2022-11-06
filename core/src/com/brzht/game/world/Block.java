package com.brzht.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;
import com.brzht.game.entity.DroppedItem;
import com.brzht.game.entity.Item;
import com.brzht.game.entity.particles.deathParticle;
import com.brzht.game.entity.particles.hittedParticle;
import com.brzht.game.items.AbstractCellItem;
import com.brzht.game.support.PosInt;
import com.brzht.game.support.Utils;
import com.brzht.game.world.rerenderers.CellRerenderer;

import java.util.Vector;

public class Block {
    public TextureRegion texture;
    public CellRerenderer rerenderer;
    public World world;
    public PosInt pos;
    public boolean isSolid = false;
    public boolean isDistracteable = false;
    public boolean dropAfterDestruction = false;
    public float hp = 20;
    public int id;

    // anim
    public boolean wasHit = false;
    public int ticksFromHit = 0;

    public boolean doesConnectWithFence = false;
    public boolean canPlaceAtTop = false;



    protected Block(String textureName){

        this.texture = Assets.getRegion(textureName);
        rerenderer = new CellRerenderer(this);
    }
    protected Block(TextureRegion texture){
        this.texture = texture;
        rerenderer = new CellRerenderer(this);
    }
    public Block setPos(PosInt pos){
        this.pos = pos;
        return this;

    }
    public void del(){
        this.world.field.get(pos.y).get(pos.x).blocks.remove(this.world.field.get(pos.y).get(pos.x).blocks.size() - 1);
    }
    public void destruct(){
        if (dropAfterDestruction) {
            Vector<DroppedItem> toDrop = getDrop();
            for (DroppedItem drop: toDrop) {
                drop.pos = pos.toVector2().add(new Vector2((float) Math.random(), (float) Math.random()));
                drop.speed.setToRandomDirection().scl(3);
                world.addEnitity(drop);
            }
        }
        world.addPartice(new deathParticle(Utils.toVector2(pos)));
        del();

    }
    public void hitted(Item weapon){
        if (isDistracteable) {
            wasHit = true;
            hp -= weapon.blockDamage;

            if (hp <= 0) {
                destruct();
            } else{
                world.addPartice(new hittedParticle(Utils.toVector2(pos)));
            }
        }
    }
    void render(SpriteBatch batch){
        if (wasHit){
            batch.draw(texture,pos.x * 32 - 1 + 2*((ticksFromHit/3 + (pos.x*31)^pos.y) % 2), pos.y * 32);
        } else {
            batch.draw(texture,pos.x * 32, pos.y * 32);
        }
    }
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> vec = new Vector<DroppedItem>();
        vec.add(new DroppedItem(new AbstractCellItem(this)));
        return vec;
    }
    public void update(int deltaTicks){
        updateAnim(deltaTicks);
        SpecialAbilities(deltaTicks);
        rerenderer.rerender();
    }
    public void SpecialAbilities(int deltaTicks){}
    public void updateAnim(int deltaTicks){

        if (wasHit){
            ticksFromHit += deltaTicks;
            if (ticksFromHit > 25) {
                wasHit = false;
                ticksFromHit = 0;
            }
        }
    }
    public void onUse(){
    }
}
