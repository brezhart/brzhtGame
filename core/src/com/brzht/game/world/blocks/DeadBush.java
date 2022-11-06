package com.brzht.game.world.blocks;

import com.brzht.game.Assets;
import com.brzht.game.world.Block;

import static com.badlogic.gdx.math.MathUtils.random;

public class DeadBush extends Block {
    public DeadBush(){
        super("stone.png");
        id = 11;
        if (random.nextInt(1000)%2 == 0){
            texture = Assets.getRegion("DeadBush1.png");
        } else {
            texture = Assets.getRegion("DeadBush2.png");
        }
        isSolid = false;
        isDistracteable = true;
        dropAfterDestruction = false;
    }
}
