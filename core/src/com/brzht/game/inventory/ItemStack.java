package com.brzht.game.inventory;

public class ItemStack {
    public String id = "Empty";
    public int amount = 1;
    ItemStack(String id){
        this.id = id;
    }
    public boolean canAdd(int amountToAdd){
        return amount + amountToAdd <= 10;
    }
}
