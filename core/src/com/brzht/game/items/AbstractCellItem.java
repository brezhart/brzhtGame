package com.brzht.game.items;

import com.brzht.game.entity.Item;
import com.brzht.game.world.Block;

public class AbstractCellItem extends Item {
    public AbstractCellItem(Block block){
        super(block.texture);
        canBePlaced = true;
        id = block.id;
    }
    //@Override
    public void cell(){

    }
}
