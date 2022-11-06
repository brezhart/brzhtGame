package com.brzht.game.world.blocks;

import com.brzht.game.Assets;
import com.brzht.game.entity.DroppedItem;
import com.brzht.game.items.StoneItem;
import com.brzht.game.world.Block;

import java.util.Vector;

import static com.badlogic.gdx.math.MathUtils.random;

public class Stone extends Block {
    public Stone(){
        super("stone.png");
        id = 2;

        isSolid = true;
        if (random.nextInt(1000)%2 == 0){
            texture = Assets.getRegion("Stone1.png");
        } else {
            texture = Assets.getRegion("Stone2.png");
        }
        isDistracteable = true;
        dropAfterDestruction = true;
    }

    @Override
    public Vector<DroppedItem> getDrop(){
        Vector<DroppedItem> toDrop = new Vector<DroppedItem>();
        for (int i = 0; i < 5; i++) {
            toDrop.add(new DroppedItem(new StoneItem()));
        }
        return toDrop;
    }
}
