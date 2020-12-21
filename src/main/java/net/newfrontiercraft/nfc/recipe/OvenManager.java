package net.newfrontiercraft.nfc.recipe;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.newfrontiercraft.nfc.tileentity.BrickOven;

import java.util.ArrayList;
import java.util.List;

public class OvenManager {

    // TODO: add back recipes
    public OvenManager() {
        recipes = new ArrayList<>();
//        addOvenRecipe(new ItemInstance(NFC.brass, 6),
//                new Object[] { new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.copper, 1),
//                        new ItemInstance(NFC.zinc, 1), new ItemInstance(NFC.zinc, 1), new ItemInstance(NFC.zinc, 1) },
//                1600);
//        addOvenRecipe(new ItemInstance(NFC.bronze, 6),
//                new Object[] { new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.copper, 1),
//                        new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.copper, 1), new ItemInstance(NFC.tin, 1) },
//                1600);
//        addOvenRecipe(new ItemInstance(NFC.steel, 8),
//                new Object[] { new ItemInstance(NFC.chrome, 1), new ItemInstance(ItemBase.ironIngot, 1),
//                        new ItemInstance(ItemBase.ironIngot, 1), new ItemInstance(ItemBase.ironIngot, 1),
//                        new ItemInstance(ItemBase.ironIngot, 1), new ItemInstance(ItemBase.ironIngot, 1),
//                        new ItemInstance(ItemBase.ironIngot, 1), new ItemInstance(ItemBase.ironIngot, 1) },
//                6400);
//        addOvenRecipe(new ItemInstance(NFC.tungsten, 1), new Object[] { new ItemInstance(NFC.tungstenore, 1) }, 200);
//        addOvenRecipe(new ItemInstance(NFC.titanium, 1), new Object[] { new ItemInstance(NFC.titaniumore, 1) }, 200);
//        addOvenRecipe(new ItemInstance(NFC.osmium, 1), new Object[] { new ItemInstance(NFC.osmiumore, 1) }, 200);

        int o = 15;
        for (int i = 0; i < 16; i++) {
            addOvenRecipe(new ItemInstance(BlockBase.LOCKED_CHEST, 8, i),
                    new Object[] { new ItemInstance(ItemBase.dyePowder, 1, o), new ItemInstance(BlockBase.GLASS, 1, 0),
                            new ItemInstance(BlockBase.GLASS, 1, 0), new ItemInstance(BlockBase.GLASS, 1, 0),
                            new ItemInstance(BlockBase.GLASS, 1, 0), new ItemInstance(BlockBase.GLASS, 1, 0),
                            new ItemInstance(BlockBase.GLASS, 1, 0), new ItemInstance(BlockBase.GLASS, 1, 0),
                            new ItemInstance(BlockBase.GLASS, 1, 0) },
                    100);
            o--;
        }
    }

    void addOvenRecipe(ItemInstance itemstack, Object[] aobj, int time) {
        List<ItemInstance> arraylist = new ArrayList<>();
        for (Object obj : aobj) {
            if (obj instanceof ItemInstance) {
                arraylist.add(((ItemInstance) obj).copy());
                continue;
            }
            if (obj instanceof ItemBase) {
                arraylist.add(new ItemInstance((ItemBase) obj));
                continue;
            }
            if (obj instanceof BlockBase)
                arraylist.add(new ItemInstance((BlockBase) obj));
            else
                throw new RuntimeException("Invalid shapeless recipe!");
        }
        recipes.add(new OvenRecipes(itemstack, arraylist, time));
    }

    public ItemInstance findMatchingRecipe(ItemInstance[] itemstacks, BrickOven joe) {
        // removed a lot of extra stuff
        for (OvenRecipes var12 : this.recipes) {
            if (var12.matches(itemstacks)) {
                joe.setTime(var12.getTime());
                return var12.getCraftingResult();
            }
        }

        return null;
    }

    public List<OvenRecipes> getRecipeList() {
        return recipes;
    }

    public static OvenManager smelting() {
        return instance;
    }

    private static final OvenManager instance = new OvenManager();
    private final List<OvenRecipes> recipes;

}