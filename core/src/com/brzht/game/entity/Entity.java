package com.brzht.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.brzht.game.Assets;
import com.brzht.game.phys.Rect;
import com.brzht.game.support.Globals;
import com.brzht.game.support.PosDouble;
import com.brzht.game.support.SizeFloat;
import com.brzht.game.support.Utils;
import com.brzht.game.world.Cell;
import com.brzht.game.world.World;

import java.util.Iterator;

public class Entity {
    public Vector2 pos;
    public Vector2 speed;
    Vector3 acceleration;
    public Vector2 size = new Vector2(0.7f,0.7f);
    public Vector2 angle = new Vector2(1,0);
    public int SpritePriority = 1;
    public World world;
    public boolean IS_REMOVED = false;
    public Entity(Vector2 pos){
        this.pos = pos.cpy();
        this.speed = new Vector2(0,0);
    }
    Entity(){
        this.pos = new Vector2(0,0);
        this.speed = new Vector2(0,0);
    }

    public void update(){

    }
    public void updatePos(float deltaTime){
        speed.x *= 0.8;
        speed.y *= 0.8;
        if (Math.abs(speed.x) < 0.01 ){
            speed.x = 0;
        }
        if (Math.abs(speed.y) < 0.01){
            speed.y = 0;
        }
        float mulx = 1;
        float muly = 1;
        if (world.checkCellCollision(pos.cpy().mulAdd(speed.cpy().scl(0,1), deltaTime), size)) {
            muly = 0;
        }
        if (world.checkCellCollision(pos.cpy().mulAdd(speed.cpy().scl(1,0), deltaTime), size)) {
            mulx = 0;
        }
        if (mulx*muly == 1) {
            if (world.checkCellCollision(pos.cpy().mulAdd(speed, deltaTime), size)) {
                mulx = 0;
                muly = 0;
            }
        }
        speed.scl(mulx, muly);
        addPos(speed.cpy().scl( deltaTime));
    }

    public void REMOVE(){
        IS_REMOVED = true;
        removeFromCellEntityContainer();
    }

    public void removeFromCellEntityContainer(){
        int left = (int)Math.floor((pos.x - size.x/2f));
        int right = (int)Math.floor((pos.x + size.x/2f ));
        int top = (int)Math.floor((pos.y + size.y/2));
        int bottom = (int)Math.floor((pos.y - size.y/2));
        for (int i = Math.max(0, bottom); i <= Math.min(top, world.WIDTH - 1); i+=1){
            for (int g = Math.max(0,left); g <=  Math.min(right, world.HEIGHT - 1); g+=1){

                world.getCell(i,g).entities.remove(this);
            }
        }
    }
    public float getSortingCoords(){
        return pos.y - size.y/2;
    }

    public void addToCellEntityContainer(){
        int left = (int)Math.floor((pos.x - size.x/2f));
        int right = (int)Math.floor((pos.x + size.x/2f ));
        int top = (int)Math.floor((pos.y + size.y/2));
        int bottom = (int)Math.floor((pos.y - size.y/2));
        for (int i = Math.max(0, bottom); i <= Math.min(top, world.WIDTH - 1); i+=1){
            for (int g = Math.max(0,left); g <=  Math.min(right, world.HEIGHT - 1); g+=1){
                world.getCell(i,g).entities.add(this);
            }
        }
    }

    public Vector2 addPos(Vector2 vectorToAdd){
        removeFromCellEntityContainer();
        pos.add(vectorToAdd);
        addToCellEntityContainer();
        return pos;
    }

    public Vector2 setPos(Vector2 vectorToSet){
        if (pos != null) {
            removeFromCellEntityContainer();
        }
        pos = vectorToSet.cpy();
        addToCellEntityContainer();
        return pos;
    }

    public void render(SpriteBatch batch, float time){
       // batch.draw(Assets.getRegion("slot.png"), (pos.x - size.x/2)*32 , (pos.y - size.y/2)*32, size.x*32, size.y*32);

    }
}
