package com.brzht.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;
import com.brzht.game.inventory.Inventory;
import com.brzht.game.items.Hand;
import com.brzht.game.phys.Rect;
import com.brzht.game.support.Utils;

import java.util.Iterator;
import java.util.Vector;

public class Player extends Entity {
    public Boolean GO_UP = false;
    public Boolean GO_LEFT = false;
    public Boolean GO_RIGHT = false;
    public Boolean GO_DOWN = false;
    public Vector2 hitArea = new Vector2(1f, 1f);
    public Inventory inventory = new Inventory();
    public String textureName = "";
    Hand hand = new Hand();

    public Player(Vector2 pos){
        super.size = new Vector2(0.3f, 0.3f);
        super.pos = pos;
        this.textureName = "player.png";
    }
    @Override
    public void update(){



        speed.setZero();
//        if (GO_UP){
//            speed.add(new Vector2(0,1));
//        }
//        if (GO_DOWN){
//            speed.add(new Vector2(0,-1));
//        }
//        if (GO_LEFT){
//
//            speed.add(new Vector2(-1,0));
//        }
//        if (GO_RIGHT){
//
//            speed.add(new Vector2(1,0));
//        }
//        speed.nor().scl(3);

        if (GO_UP){
            speed = angle.cpy().scl(3);
        }
        if (GO_DOWN){
            speed = angle.cpy().scl(-3);

        }
        if (GO_LEFT){
            speed = (new Vector2(-angle.y, angle.x));
        }
        if (GO_RIGHT){
            speed  = (new Vector2(angle.y, -angle.x));
        }
        speed.nor().scl(3);

        Vector<Entity> entities = world.getEntitiesInRadius(pos, 0.8f);
        for (Entity entity: entities){
            if (entity instanceof DroppedItem){
                if (!((DroppedItem)entity).canPick()) continue;
                float dist = pos.cpy().sub(entity.pos).len();
                int howMuchCanPut = inventory.howMuchCanPut(((DroppedItem)entity).item);
                if (dist > 0.4 && howMuchCanPut != 0){
                    double force = 1 / (dist * dist);
                    Vector2 coef = pos.cpy().sub(entity.pos).scl(1/dist);
                    entity.speed.add(coef.scl((float) (force*0.5f)));
                } else if (howMuchCanPut != 0) {
                    inventory.putItem(((DroppedItem)entity).item);
                    entity.REMOVE();
                }
            }
        }


    }

    Item getActiveWeapon(){
        if (inventory.getActiveItem() == null){
            return hand;
        }
        return inventory.getActiveItem();
    }

    public void hit(){
        //world.addPartice(new hitParticle(pos.cpy().add(angle), angle.cpy()));
        Rect hitRect = new Rect(pos, hitArea);
        hitRect.setCenter(pos);
        hitRect.add(angle.cpy().scl(1));

        int left = (int)Math.floor((hitRect.pos.x));
        int right = (int)Math.floor((hitRect.pos.x + hitRect.size.x ));
        int top = (int)Math.floor((hitRect.pos.y + hitRect.size.y));
        int bottom = (int)Math.floor((hitRect.pos.y));
        for (int i = bottom; i <= top; i+=1){
            for (int g = left; g <= right; g+=1){
                world.getBlock(i,g).hitted(getActiveWeapon());
            }
        }
    }


    @Override
    public void render(SpriteBatch batch, float time){
        super.render(batch, time);
        //Rect hitRect = new Rect(pos, hitArea);
        //hitRect.setCenter(pos);
        //hitRect.add(angle.cpy().scl(1));

        float angSpeed = (float) ((float)Math.atan2(speed.y, speed.x) * 180.0 / Math.PI);
        if (angSpeed < 0){
            angSpeed = 360 + angSpeed;
        }
        float angAng = (float) ((float)Math.atan2(angle.y, angle.x) * 180.0 / Math.PI);
        if (angAng < 0){
            angAng = 360 + angAng;
        }

        int animAngSpeed = ((int)(angSpeed + 22.5f)%360)/45;
        int animAngAng = ((int)(angAng + 22.5f)%360)/45;
        //batch.draw(Assets.getRegion("slot.png"), (int) (hitRect.pos.x*32), (int) (hitRect.pos.y*32), 0, 0, hitRect.size.x*32, hitRect.size.y*32, 1, 1, 0 );
        //batch.draw(Assets.getRegion("slot.png"), (int) (pos.x*32), (int) (pos.y*32), 0, 0, 2, 2, 1, 1, 0 );
        if (GO_UP || GO_LEFT || GO_DOWN || GO_RIGHT) {
            TextureRegion region= Assets.getAnimFrame("player_running_" + String.valueOf(animAngAng), time);
            batch.draw(region, (int) ((pos.x) * 32 - region.getRegionWidth()/2), (int) ((pos.y - size.y/2) * 32));
        } else {
            TextureRegion region= Assets.getAnimFrame("player_running_" + String.valueOf(animAngAng), 0);
            batch.draw(region, (int) ((pos.x ) * 32 - region.getRegionWidth()/2), (int) ((pos.y - size.y/2) * 32));
        }



    }
    public void placeBlock(int y, int x){
        float dist = pos.cpy().sub(x,y).len();
        if (dist > 5) {
            return;
        }
        Item item = inventory.getActiveItem();
        if (item != null && item.canBePlaced && world.getBlock(y,x).canPlaceAtTop && world.getCell(y, x).entities.isEmpty()){
            inventory.subOneItemFromActiveSlot();
            world.addBlock(y, x, Utils.getBlockById(item.id));
        }

    }

}
