package com.brzht.game.world.blocks;

import com.brzht.game.Assets;
import com.brzht.game.world.Block;
import com.brzht.game.world.rerenderers.ConnectiveCellRerender;

public class PavWooden extends Block {
    public PavWooden(){
        super(Assets.getAnimFrame("PavWooden", 15));
        id = 4;
        isDistracteable = true;
        isSolid = false;
        dropAfterDestruction = true;
        canPlaceAtTop = true;
        rerenderer = new ConnectiveCellRerender(this,"PavWooden", false);
    }
}
