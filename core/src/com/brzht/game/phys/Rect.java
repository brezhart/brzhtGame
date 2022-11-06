package com.brzht.game.phys;

import com.badlogic.gdx.math.Vector2;

public class Rect {
    public Vector2 pos;
    public Vector2 size;
    public Rect(Vector2 pos, Vector2 size){
        this.pos = pos.cpy();
        this.size = size.cpy();
    }
    public Rect cpy(){
        return new Rect(pos, size);
    }
    public Rect add(Vector2 coordsToAdd){
        pos.add(coordsToAdd);
        return this;
    }

    public Rect scale(Vector2 scl){
        scl = scl.cpy();
        pos.sub((size.cpy().scl(scl).sub(size)).scl(0.5f));
        size.scl(scl);
        return this;
    }

    public Rect setCenter(Vector2 center){
        center = center.cpy();
        pos = center.sub(size.cpy().scl(0.5f));
        return this;
    }
    public boolean intersect(Rect R2){
        float l = Math.max(pos.x, R2.pos.x);
        float r = Math.min(pos.x + size.x, R2.pos.x+R2.size.x);
        float b = Math.max(pos.y, R2.pos.y);
        float u = Math.min(pos.y + size.y, R2.pos.y + R2.size.y);
        if (b >= u && l >= r){
            return false;
        }
        return true;
    }
}
