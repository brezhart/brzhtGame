package com.brzht.game.entity.particles;

import com.badlogic.gdx.math.Vector2;
import com.brzht.game.entity.Particle;

public class hitParticle extends Particle {
    hitParticle(Vector2 pos){
        super(pos);
        this.lifeSpan = 30;
        this.animationName = "hit";
    }
    public hitParticle(Vector2 pos, Vector2 ang){
        super(pos, ang);
        this.lifeSpan = 30;
        this.animationName = "player_running_0";
    }
}
