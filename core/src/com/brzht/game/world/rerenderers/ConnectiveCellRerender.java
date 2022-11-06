package com.brzht.game.world.rerenderers;

import com.brzht.game.Assets;
import com.brzht.game.world.Block;

public class ConnectiveCellRerender extends CellRerenderer{
    boolean connectToConnectables;
    int connectivityState = 0;
    public ConnectiveCellRerender(Block block, String textureName, boolean connectToConnectables){
        super(block);
        this.connectToConnectables = connectToConnectables;
        this.textureName = textureName;
    }
    @Override
    public void rerender() {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int sum = 0;
        int multiplier = 1;
        for (int i = 0; i < 4; i += 1) {
            int nx = block.pos.x + dx[i];
            int ny = block.pos.y + dy[i];
            if ((block.world.getBlock(ny, nx).doesConnectWithFence && connectToConnectables) || block.world.getBlock(ny, nx).id == block.id) {
                sum += multiplier;
            }
            multiplier *= 2;
        }
        connectivityState = sum;
        block.texture = Assets.getAnimFrame(textureName, connectivityState);
    }
}
