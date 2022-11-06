package com.brzht.game.entity.particles;

import com.badlogic.gdx.math.Vector2;
import com.brzht.game.entity.Particle;

public class deathParticle extends Particle {
    public deathParticle(Vector2 pos){
        super(pos);
        this.lifeSpan = 27;
        this.animationName = "death_effect";
    }
    public deathParticle(Vector2 pos, Vector2 ang){
        super(pos, ang);
        this.lifeSpan = 27;
        this.animationName = "death_effect";
    }
}
