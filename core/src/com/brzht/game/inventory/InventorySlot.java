package com.brzht.game.inventory;

import com.badlogic.gdx.utils.Null;
import com.brzht.game.entity.Item;

public class InventorySlot {
    Item item = null;
    int amount = 0;
    Inventory inventory;
    InventorySlot(Item item, int amount, Inventory inventory){
        this.item = item;
        this.inventory = inventory;
        this.amount = amount;
    }
    InventorySlot(Inventory inventory){
        this.inventory = inventory;
    }
    InventorySlot(){}

    public boolean empty(){
        return item == null;
    }
    public boolean canPut(Item itemToPut){
        if (item == null){
            return true;
        }
        return false;
    }

}
