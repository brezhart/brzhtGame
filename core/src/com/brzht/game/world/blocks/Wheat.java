package com.brzht.game.world.blocks;

import com.brzht.game.entity.DroppedItem;
import com.brzht.game.items.WheatItem;
import com.brzht.game.world.Block;

import java.util.Vector;


public class Wheat extends Block {
    public Wheat(){
        super("Wheat.png");
        id = 8;
        isDistracteable = true;
        dropAfterDestruction = true;
        isSolid = false;
    }
    @Override
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> toDrop = new Vector<DroppedItem>();
        toDrop.add(new DroppedItem(new WheatItem()));
        return toDrop;
    }
}
