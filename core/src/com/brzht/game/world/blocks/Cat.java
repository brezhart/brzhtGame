package com.brzht.game.world.blocks;

import com.brzht.game.world.Block;

public class Cat extends Block {
    public Cat(){
        super("cat.png");
        id = 14;
        isDistracteable = true;
        dropAfterDestruction = true;
        isSolid = true;
    }
}