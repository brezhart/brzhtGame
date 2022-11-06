package com.brzht.game.world.blocks;

import com.brzht.game.world.Block;

public class Sand extends Block {
    public Sand(){
        super("Sand.png");
        id = 1;
        isSolid = false;
        canPlaceAtTop = true;
    }
}
