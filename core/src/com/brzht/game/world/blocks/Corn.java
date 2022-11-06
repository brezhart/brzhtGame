package com.brzht.game.world.blocks;

import com.brzht.game.entity.DroppedItem;
import com.brzht.game.items.CornItem;
import com.brzht.game.world.Block;

import java.util.Vector;

public class Corn extends Block {

    public Corn(){
        super("Corn.png");
        id = 12;
        isSolid = true;
        isDistracteable = true;
        dropAfterDestruction = true;
    }
    @Override
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> toDrop = new Vector<DroppedItem>();
        toDrop.add(new DroppedItem(new CornItem()));
        return toDrop;
    }
}
