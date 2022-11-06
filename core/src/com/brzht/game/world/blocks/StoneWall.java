package com.brzht.game.world.blocks;

import com.brzht.game.world.Block;

public class StoneWall extends Block {
    public StoneWall() {
        super("stoneWall.png");
        id = 5;
        isSolid = true;
        isDistracteable = true;
        dropAfterDestruction = true;
        doesConnectWithFence = true;
    }
}