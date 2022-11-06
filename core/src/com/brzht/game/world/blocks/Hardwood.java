package com.brzht.game.world.blocks;

import com.brzht.game.entity.DroppedItem;
import com.brzht.game.items.HardwoodPlank;
import com.brzht.game.world.Block;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.random;

public class Hardwood extends Block {
    public Hardwood(){
        super("hardWood.png");
        id = 2;
        isSolid = true;
        isDistracteable = true;
        dropAfterDestruction = true;
    }

    @Override
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> toDrop = new Vector<DroppedItem>();
        for (int i = 0; i < 8; i++) {
            toDrop.add(new DroppedItem(new HardwoodPlank()));
        }
        return toDrop;
    }
}