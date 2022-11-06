package com.brzht.game.support;

import com.badlogic.gdx.math.Vector2;

public class PosInt{
    public int x;
    public int y;
    public PosInt(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2 toVector2(){
        return new Vector2(x,y);
    }
}

