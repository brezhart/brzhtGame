package com.brzht.game.world;

import com.brzht.game.Assets;

public class FenceBlock extends Block {
    int connectivityState = 0;
    String animationName;
    protected FenceBlock(String animationName) {
        super(Assets.getAnimFrame(animationName, 10));
        this.animationName = animationName;
        isSolid = true;
        doesConnectWithFence = true;
        isDistracteable = true;
        dropAfterDestruction = true;

    }
    @Override
    public void SpecialAbilities(int deltaTicks){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int sum = 0;
        int multiplier = 1;
        for (int i = 0; i < 4; i+=1){
            int nx = pos.x + dx[i];
            int ny = pos.y + dy[i];
            if (world.getBlock(ny,nx).doesConnectWithFence){
                sum+=multiplier;
            }
            multiplier*=2;
        }
        connectivityState = sum;
        texture = Assets.getAnimFrame(animationName, connectivityState);
    }

}
