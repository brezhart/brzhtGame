package com.brzht.game.entity.particles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.brzht.game.Assets;
import com.brzht.game.entity.Particle;

public class OneFrameSprite extends Particle {
    TextureRegion texture;
    Sprite sprite;
    float transparency = 1;
    public OneFrameSprite(TextureRegion texture, Vector2 pos){
        super(pos);
        this.texture = texture;
        sprite = new Sprite(texture);
        lifeSpan = 1;
    }
    public OneFrameSprite setTransparency(float transparency){
        this.transparency = transparency;
        return this;
    }
    public OneFrameSprite setPriority(int priority){
        this.SpritePriority = priority;
        return this;
    }

    @Override
    public void render(SpriteBatch batch, float time){
        //Gdx.app.log("SS", String.valueOf(lived) + " " +String.valueOf( Assets.animations.get(animationName).getFrameDuration())+" " +  String.valueOf(Assets.animations.get(animationName).getKeyFrameIndex((float)lived)));
        //float ang = (float) ((float)Math.atan2(angle.y, angle.x) * 180.0 / Math.PI);
        //batch.draw(Assets.getAnimFrame(animationName, lived), (int) pos.x*32 , (int) pos.y*32, 0, 0, 16, 16, 1, 1, ang );
        batch.setColor(1,1,1,transparency);
        batch.draw(texture, (int) pos.x*32 , (int) pos.y*32 );
        batch.setColor(1,1,1,1);

    }
}
