package com.brzht.game.world.blocks;

import com.brzht.game.entity.DroppedItem;
import com.brzht.game.items.Apple;
import com.brzht.game.world.Block;

import java.util.Vector;

public class Sunflower extends Block {
    public Sunflower(){
        super("sunflower.png");
        id = 7;
        isDistracteable = true;
        isSolid = true;
        dropAfterDestruction = true;
    }
    @Override
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> toDrop = new Vector<DroppedItem>();
        toDrop.add(new DroppedItem(new Apple()));
        return toDrop;
    }
}
