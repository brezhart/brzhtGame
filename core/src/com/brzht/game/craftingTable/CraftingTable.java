package com.brzht.game.craftingTable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brzht.game.Assets;
import com.brzht.game.inventory.Inventory;
import com.brzht.game.support.Globals;

import java.util.Vector;
import com.brzht.game.support.Utils;

public class CraftingTable {
    Vector<CraftRecipe> recipes = new Vector<>();
    public int pickedRecipe = 0 ;
    public int topRecipe = 0;
    int SIZE = 4;
    public CraftingTable(){
        recipes.add(new CraftRecipe().addIngridient(132, 2).addResult(5, 1));
        recipes.add(new CraftRecipe().addIngridient(132, 1).addResult(3, 2));
        recipes.add(new CraftRecipe().addIngridient(133, 1).addIngridient(132, 1).addResult(9, 1));
        recipes.add(new CraftRecipe().addIngridient(133, 2).addResult(10, 1));
        recipes.add(new CraftRecipe().addIngridient(133, 2).addResult(4, 2));
        recipes.add(new CraftRecipe().addIngridient(132, 1).addResult(3, 2));
        recipes.add(new CraftRecipe().addIngridient(132, 2).addResult(5, 1));
        recipes.add(new CraftRecipe().addIngridient(132, 1).addResult(3, 2));
        recipes.add(new CraftRecipe().addIngridient(132, 2).addResult(5, 1));
        recipes.add(new CraftRecipe().addIngridient(132, 1).addResult(3, 2));

    }
    public void render(SpriteBatch batch, Inventory source){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1);
        int offsety = Gdx.graphics.getHeight()/2 + Assets.getRegion("crafting_table_frame.png").getRegionHeight()/2;
        int offsetx = Gdx.graphics.getWidth()/2 - Assets.getRegion("crafting_table_frame.png").getRegionWidth()/2;
        batch.draw(Assets.getRegion("crafting_table_frame.png"), offsetx - 4, offsety - 56*3 - 4);

        for (int i = topRecipe; i < Math.min(recipes.size(), topRecipe + SIZE); i++){

            if (i == pickedRecipe){
                batch.draw(Assets.getRegion("picked_craft_inventory.png"), offsetx-2, offsety-2);
            } else {
                batch.draw(Assets.getRegion("craft_inventory.png"), offsetx, offsety);
            }
            for (int g = 0; g < recipes.get(i).ingridients.size(); g+=1){
                batch.draw(recipes.get(i).ingridientItems.get(g).texture, offsetx + 11 + g*46, offsety + 11, 32, 32);
                int have = source.itemsAmount.get(recipes.get(i).ingridients.get(g).f);
                int need = recipes.get(i).ingridients.get(g).s;
                if (have < need){
                    font.setColor(Color.RED);
                }
                font.draw(batch, Utils.str(have) + "/" +Utils.str(need),offsetx + 11 + g*46,offsety + 19);
                font.setColor(Color.WHITE);
            };
            batch.draw(recipes.get(i).resultItem.texture, offsetx + 19 + 4*46, offsety + 11, 32, 32);
            font.draw(batch, Utils.str(recipes.get(i).result.s),offsetx + 19 + 4*46,offsety + 19);
            offsety -= 56;
        }
    }
    public void tryCraft(Inventory sourse){
        if (recipes.get(pickedRecipe).canCraft(sourse)){
            recipes.get(pickedRecipe).craft(sourse);
        }
    }
    public void updateTopRecipe(){
        topRecipe = Math.min(topRecipe, pickedRecipe);
        topRecipe = Math.max(topRecipe, pickedRecipe - SIZE + 1);
    }
    public void toNextReceipe(){
        if (pickedRecipe + 1 != recipes.size()){
            pickedRecipe+=1;
        }
        updateTopRecipe();
        Gdx.app.log("SS", Utils.str(topRecipe) + " " + Utils.str(pickedRecipe));
    }
    public void toLastReceipe(){
        if (pickedRecipe != 0){
            pickedRecipe-=1;
        }
        updateTopRecipe();
    }
}
