package com.brzht.game.craftingTable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brzht.game.Assets;
import com.brzht.game.entity.Item;
import com.brzht.game.inventory.Inventory;
import com.brzht.game.support.PairInt;
import com.brzht.game.support.Utils;

import java.util.Vector;

public class CraftRecipe {
    Vector<PairInt>ingridients;
    PairInt result;
    Vector<Item> ingridientItems;
    Item resultItem;
    public CraftRecipe(){
        ingridients = new Vector<>();
        ingridientItems = new Vector<>();
        result = new PairInt(0,0);
    }
    public CraftRecipe addIngridient(int id, int amount){
        ingridients.add(new PairInt(id, amount));
        ingridientItems.add(Utils.getItemById(id));
        return this;
    }
    public CraftRecipe addResult(int id, int amount){
        result = new PairInt(id, amount);
        resultItem = Utils.getItemById(id);
        return this;
    }

    public boolean canCraft(Inventory sourse){
        for (PairInt ingridient: ingridients){
            if (sourse.itemsAmount.get(ingridient.f) < ingridient.s){
                return false;
            }
        }
        return true;
    }
    public void craft(Inventory sourse){
        for (PairInt ingridient: ingridients){
            sourse.subItems(Utils.getItemById(ingridient.f), ingridient.s);
        }
        Item item = Utils.getItemById(result.f);
        int amount = result.s;
        sourse.putItem(item, amount);
    }

}
