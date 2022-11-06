package com.brzht.game.world.blocks;

import com.brzht.game.Assets;
import com.brzht.game.world.Block;
import com.brzht.game.world.rerenderers.ConnectiveCellRerender;

public class FenceWooden extends Block {

    public FenceWooden() {
        super(Assets.getAnimFrame("wooden_fence", 10));
        id = 9;
        rerenderer = new ConnectiveCellRerender(this,"wooden_fence", true);
        isSolid = true;
        doesConnectWithFence = true;
        isDistracteable = true;
        dropAfterDestruction = true;
    }
}
