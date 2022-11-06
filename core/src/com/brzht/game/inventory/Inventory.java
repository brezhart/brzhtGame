package com.brzht.game.inventory;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brzht.game.Assets;
import com.brzht.game.entity.DroppedItem;
import com.brzht.game.entity.Item;
import com.brzht.game.items.HardwoodPlank;
import com.brzht.game.items.StoneItem;
import com.brzht.game.support.DefaultHashMap;
import com.brzht.game.support.Globals;
import com.brzht.game.support.Utils;
import com.brzht.game.world.World;

import java.util.Map;


public class Inventory {
    InventorySlot[] field = new InventorySlot[9];
    public int pickedSlot = 0;
    public Map<Integer, Integer> itemsAmount = new DefaultHashMap<>(0);

    public Inventory() {
        for (int i = 0; i < field.length; i+=1){
            field[i] = new InventorySlot();
        }

        putItem(new StoneItem(), 40);
        putItem(new HardwoodPlank(), 39);
//        putItem(new AbstractCellItem(new PavWooden()), 100);
//        putItem(new AbstractCellItem(new PavStone()), 100);
//        putItem(new AbstractCellItem(new Door()), 100);
    }
    public Item getActiveItem(){
        return field[pickedSlot].item;
    }
    public void render(SpriteBatch batch){
//        batch.draw(Assets.getRegion("inventory.png"), 120, 0);
        for (int i = 0; i < field.length; i++){
            if (i != pickedSlot){
                batch.draw(Assets.getRegion("slot.png"), 120 + i*44, 6);
            }
        }

        for (int i = 0; i < field.length; i++){
            if (i == pickedSlot){
                batch.draw(Assets.getRegion("slotNew.png"), 120 + i*44 - 2, 6 - 2);
            }
            if (field[i].item != null){
                batch.draw(field[i].item.texture, 126+i*44, 12, 32, 32);
                if (field[i].amount > 1) {
                    Globals.font.draw(batch, String.valueOf(field[i].amount), 126 + i * 44, 12 + 8);
                }
            }
        }

        //batch.draw(Assets.getRegion("craft_inventory.png"), 200, 316);
        //batch.draw(Assets.getRegion("craft_inventory.png"), 200, 200);
        //batch.draw(Assets.getRegion("picked_craft_inventory.png"), 198, 256);

    }
    public int howMuchCanPut(Item toPut){
        int amount = 0;
        for (InventorySlot inventorySlot : field) {
            if (inventorySlot.item == null) {
                amount+=toPut.maxStackSize;
            } else if (inventorySlot.item.id == toPut.id){
                amount+=toPut.maxStackSize-inventorySlot.amount;
            }
        }
        return amount;
    }
    public void putItem(Item toPut) {
        putItem(toPut, 1);
    }
    public void putItem(Item toPut, int amount){
        itemsAmount.merge(toPut.id, amount, Integer::sum);
        for (InventorySlot inventorySlot : field) {
            if (inventorySlot.item != null && (inventorySlot.item.id == toPut.id && inventorySlot.amount < inventorySlot.item.maxStackSize)){
                int canPut = Math.min(inventorySlot.item.maxStackSize - inventorySlot.amount, amount);
                inventorySlot.amount+=canPut;
                amount-=canPut;
            }
        }
        for (InventorySlot inventorySlot : field) {
            if (inventorySlot.item == null){
                int canPut = Math.min(toPut.maxStackSize, amount);
                if (canPut != 0) {
                    inventorySlot.amount = canPut;
                    amount-=canPut;
                    inventorySlot.item = toPut;
                }
            }
        }
        assert amount != 0;
    }

    public void movePickedSlotRight() {
        pickedSlot+=1;
        pickedSlot%=field.length;
    }
    public void movePickedSlotLeft() {
        pickedSlot-=1;
        pickedSlot+=field.length;
        pickedSlot%=field.length;
    }
    public void dropActiveItem(World world){
        if (field[pickedSlot].item != null) {
            DroppedItem dropped = field[pickedSlot].item.getDroppedItem( world.player.pos.cpy() );
            dropped.pos.add(world.player.angle.cpy().scl(1));
            dropped.speed.add(world.player.angle.cpy().scl(5));
            world.addEnitity(dropped);
            subOneItemFromActiveSlot();
        }
    }
    public void subOneItemFromActiveSlot(){
        subFromActiveSlot(1);
    }
    public void subFromActiveSlot(int amount){
        Utils.decrementWithErasion(itemsAmount,getActiveItem().id, amount);
        field[pickedSlot].amount-=amount;
        if (field[pickedSlot].amount == 0) {
            field[pickedSlot].item = null;
        }
        assert field[pickedSlot].amount >= 0;
    }

    public void subOneItem(Item toTake){
        subItems(toTake, 1);
    }
    public void subItems(Item toTake, int amount){
        Utils.decrementWithErasion(itemsAmount,toTake.id, amount);
        // сначала берём из неполных стаков
        for (InventorySlot inventorySlot : field) {
            if (inventorySlot.item != null && inventorySlot.item.id == toTake.id && inventorySlot.amount != toTake.maxStackSize){
                int maxCanSub = Math.min(amount, inventorySlot.amount);
                inventorySlot.amount-=maxCanSub;
                amount-=maxCanSub;
                if (inventorySlot.amount == 0){
                    inventorySlot.item = null;
                }
            }
        }

        for (InventorySlot inventorySlot : field) {
            if (inventorySlot.item != null && inventorySlot.item.id == toTake.id){
                int maxCanSub = Math.min(amount, inventorySlot.amount);
                inventorySlot.amount-=maxCanSub;
                amount-=maxCanSub;
                if (inventorySlot.amount == 0){
                    inventorySlot.item = null;
                }
            }
        }
        assert amount == 0;

    }
}

