package com.brzht.game.entity.particles;

import com.badlogic.gdx.math.Vector2;
import com.brzht.game.entity.Particle;

public class hittedParticle extends Particle {
    public hittedParticle(Vector2 pos){
        super(pos);
        this.lifeSpan = 20;
        this.animationName = "hitted";
    }
    public hittedParticle(Vector2 pos, Vector2 ang){
        super(pos, ang);
        this.lifeSpan = 20;
        this.animationName = "hitted";
    }
}
