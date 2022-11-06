package com.brzht.game.support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.brzht.game.entity.Item;
import com.brzht.game.items.*;
import com.brzht.game.world.Block;
import com.brzht.game.world.blocks.*;
import com.brzht.game.world.blocks.Void;

import java.util.Map;

public class Utils {
    public static Vector2 toVector2(Vector3 to){
        return new Vector2(to.x, to.y);
    }
    public static Vector2 toVector2(PosInt to){
        return new Vector2(to.x, to.y);
    }

    public static Vector2 floorVector2(Vector2 vec){
        return new Vector2((int)vec.x, (int)vec.y);
    }

    public static Item getItemById(int id){
        switch (id){
            case 128:
                return new Hand();
            case 129:
                return new Apple();
            case 130:
                return new CornItem();
            case 131:
                return new WheatItem();
            case 132:
                return new StoneItem();
            case 133:
                return new HardwoodPlank();
            default:
                return new AbstractCellItem(getBlockById(id));
        }

    }
    public static Block getBlockById(int id){
        switch (id) {
            case 0:
                return new Void();
            case 1:
                return new Sand();
            case 2:
                return new Stone();
            case 3:
                return new PavStone();
            case 4:
                return new PavWooden();
            case 5:
                return new StoneWall();
            case 7:
                return new Sunflower();
            case 8:
                return new Wheat();
            case 9:
                return new FenceWooden();
            case 10:
                return new Door();
            case 11:
                return new DeadBush();
            case 12:
                return new Corn();
            case 13:
                return new Hardwood();
            case 14:
                return new Cat();
        }
        Gdx.app.error("getCellById", "NO SUCH ID AS" + String.valueOf(id));
        System.exit(1);
        return null;
    }
    public static void decrementWithErasion(Map<Integer, Integer> map, int key, int amount){

        map.put(key, map.get(key) - amount);
        if (map.get(key) == 0){
            map.remove(key);
        }
    }
    public static String str(int a){
        return String.valueOf(a);
    }
}
