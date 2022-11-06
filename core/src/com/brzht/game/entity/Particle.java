package com.brzht.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;
import com.brzht.game.world.World;

import java.util.Iterator;

public class Particle extends Entity{
    protected int lifeSpan;
    int lived = 0;
    protected String animationName = "";
    boolean isStatic = false;
    int SpritePrioirity = -1;
    protected Particle(Vector2 pos){
        super(pos);
    }
    protected Particle(Vector2 pos, Vector2 angle){
        super(pos);
        this.angle = angle.cpy();
    }
    @Override
    public void update(){
        if (lived >= lifeSpan){
            REMOVE();
        }
        lived++;
    }
    @Override
    public void render(SpriteBatch batch,float time){
        //Gdx.app.log("SS", String.valueOf(lived) + " " +String.valueOf( Assets.animations.get(animationName).getFrameDuration())+" " +  String.valueOf(Assets.animations.get(animationName).getKeyFrameIndex((float)lived)));
        //float ang = (float) ((float)Math.atan2(angle.y, angle.x) * 180.0 / Math.PI);
        //batch.draw(Assets.getAnimFrame(animationName, lived), (int) pos.x*32 , (int) pos.y*32, 0, 0, 16, 16, 1, 1, ang );
        batch.draw(Assets.getAnimFrame(animationName, lived), (int) pos.x*32 , (int) pos.y*32 );
    }

    @Override
    public float getSortingCoords() {
        return pos.y;
    }
}
