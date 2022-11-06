package com.brzht.game.world.blocks;

import com.badlogic.gdx.Gdx;
import com.brzht.game.Assets;
import com.brzht.game.world.Block;

import javax.xml.stream.FactoryConfigurationError;

public class Door extends Block {
    boolean isOpen = false;
    public Door(){
        super("doorClosed.png");
        id = 10;
        isSolid = true;
        isOpen = false;
        isDistracteable = false;
        dropAfterDestruction = false;
    }
    @Override
    public void onUse(){
        if (!isOpen || world.getCell(pos.y, pos.x).entities.isEmpty()){
            isOpen^=true;
            isSolid^=true;
        }
        if (isOpen){
            texture = Assets.getRegion("doorOpen.png");
        } else{
            texture = Assets.getRegion("doorClosed.png");
        }
        //world.screen.world = new World(10,10, world.game, world.screen);
    }
}
