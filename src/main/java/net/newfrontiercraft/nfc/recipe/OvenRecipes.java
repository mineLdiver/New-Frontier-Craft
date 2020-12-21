package net.newfrontiercraft.nfc.recipe;

import net.minecraft.item.ItemInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OvenRecipes {

    public OvenRecipes(ItemInstance itemstack, List<ItemInstance> list, int time) {
        recipeOutput = itemstack;
        recipeItems = list;
        this.time = time;
    }

    public ItemInstance getRecipeOutput() {
        return recipeOutput;
    }

    public int getTime() {
        return time;
    }

    public boolean matches(ItemInstance[] itemstacks) {
        List<ItemInstance> arraylist = new ArrayList<>(recipeItems);
        int i = 0;
        while (i < 3) {
            for (int j = 0; j < 3; j++) {
                ItemInstance itemstack = itemstacks[j + (i * 3)];
                if (itemstack == null)
                    continue;
                boolean flag = false;
                Iterator<ItemInstance> iterator = arraylist.iterator();
                do {
                    if (!iterator.hasNext())
                        break;
                    ItemInstance itemstack1 = iterator.next();
                    if (itemstack.itemId != itemstack1.itemId || itemstack1.getDamage() != -1 && itemstack.getDamage() != itemstack1.getDamage())
                        continue;
                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                } while (true);
                if (!flag)
                    return false;
            }

            i++;
        }
        return arraylist.isEmpty();
    }

    public ItemInstance getCraftingResult() {
        return recipeOutput.copy();
    }

    public int getRecipeSize() {
        return recipeItems.size();
    }

    private final ItemInstance recipeOutput;
    private final List<ItemInstance> recipeItems;
    private final int time;
}