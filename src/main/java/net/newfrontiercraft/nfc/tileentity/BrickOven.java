package net.newfrontiercraft.nfc.tileentity;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.Bucket;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.newfrontiercraft.nfc.recipe.OvenManager;

public class BrickOven extends TileEntityBase implements InventoryBase {

    public BrickOven() {
        contents = new ItemInstance[11];
        burnTime = 0;
        fuelTime = 0;
        cookTime = 0;
    }

    @Override
    public int getInventorySize() {
        return contents.length;
    }

    @Override
    public ItemInstance getInventoryItem(int i) {
        return contents[i];
    }

    @Override
    public ItemInstance takeInventoryItem(int index, int count) {
        if (contents[index] != null) {
            if (contents[index].count <= count) {
                ItemInstance itemToTake = contents[index];
                contents[index] = null;
                return itemToTake;
            } else {
                ItemInstance splitItemToTake = contents[index].split(count);
                if (contents[index].count == 0)
                    contents[index] = null;
                return splitItemToTake;
            }
        } else
            return null;
    }

    @Override
    public void setInventoryItem(int slot, ItemInstance item) {
        contents[slot] = item;
        if (item != null && item.count > getMaxItemCount())
            item.count = getMaxItemCount();
    }

    @Override
    public String getContainerName() {
        return "Brick Oven";
    }

    @Override
    public void readIdentifyingData(CompoundTag tag) {
        super.readIdentifyingData(tag);
        ListTag var2 = tag.getListTag("Items");
        contents = new ItemInstance[getInventorySize()];
        for(int var3 = 0; var3 < var2.size(); ++var3) {
            CompoundTag var4 = (CompoundTag)var2.get(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < contents.length) {
                contents[var5] = new ItemInstance(var4);
            }
        }
        burnTime = tag.getShort("BurnTime");
        cookTime = tag.getShort("CookTime");
        fuelTime = getFuelTime(contents[9]);
    }

    @Override
    public void writeIdentifyingData(CompoundTag tag) {
        super.writeIdentifyingData(tag);
        tag.put("BurnTime", (short)burnTime);
        tag.put("CookTime", (short)cookTime);
        ListTag var2 = new ListTag();
        for(int var3 = 0; var3 < contents.length; ++var3) {
            if (contents[var3] != null) {
                CompoundTag var4 = new CompoundTag();
                var4.put("Slot", (byte)var3);
                contents[var3].toTag(var4);
                var2.add(var4);
            }
        }
        tag.put("Items", var2);
    }

    @Override
    public int getMaxItemCount() {
        return 64;
    }

    public int method_1284(int i) {
        return cookTime * i / requiredTime;
    }

    public int method_1285(int i) {
        if (fuelTime == 0)
            fuelTime = 200;
        return burnTime * i / fuelTime;
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    @Override
    public void tick() {
        boolean var1 = burnTime > 0;
        boolean var2 = false;
        if (burnTime > 0)
            --burnTime;
        if (!level.isClient) {
            if (burnTime == 0 && canAcceptRecipeOutput()) {
                fuelTime = burnTime = getFuelTime(contents[9]);
                if (burnTime > 0) {
                    var2 = true;
                    if (contents[9] != null) {
                        if (contents[9].getType().hasContainerItemType())
                            contents[9] = new ItemInstance(contents[9].getType().getContainerItemType());
                        else
                            --contents[9].count;
                        if (contents[9].getType() instanceof Bucket)
                            contents[9] = new ItemInstance(ItemBase.bucket);
                        else if (contents[9].count == 0)
                            contents[9] = null;
                    }
                }
            }
            if (isBurning() && canAcceptRecipeOutput()) {
                ++cookTime;
                if (cookTime == requiredTime) {
                    cookTime = 0;
                    craftRecipe();
                    var2 = true;
                }
            } else
                cookTime = 0;
            if (var1 != burnTime > 0) {
                var2 = true;
                net.newfrontiercraft.nfc.block.BrickOven.method_1403(burnTime > 0, level, x, y, z);
            }
        }

        if (var2)
            markDirty();
    }

    private boolean canAcceptRecipeOutput() {
        ItemInstance item = OvenManager.smelting().findMatchingRecipe(contents, this);
        if (item == null)
            return false;
        if (contents[10] == null)
            return true;
        if (!contents[10].isEqualIgnoreFlags(item))
            return false;
        if (contents[10].count < getMaxItemCount() && contents[10].count + item.count < contents[10].method_709())
            return true;
        return contents[10].count + item.count < item.method_709();
    }

    public void craftRecipe() {
        if (canAcceptRecipeOutput()) {
            ItemInstance item = OvenManager.smelting().findMatchingRecipe(contents, this);
            if (contents[10] == null)
                contents[10] = item.copy();
            else if (contents[10].itemId == item.itemId)
                contents[10].count += item.count;

            // Removed container item code
            for (int i = 0; i < 9; i++)
                if (contents[i] != null) {
                    contents[i].count--;
                    if (contents[i].count <= 0)
                        contents[i] = null;
                }
        }
    }

    // TODO: add the items
    private int getFuelTime(ItemInstance item) {
        if (item != null) {
            int i = item.getType().id;
            int j = item.getDamage();
            if (i < BlockBase.BY_ID.length && BlockBase.BY_ID[i].material == Material.WOOD)
                return 100;
//            if (i == NFC.fireMushroom.blockID)
//                return 200;
            if ((i == ItemBase.coal.id/* || i == NFC.nethercoal.id*/) && j == 0)
                return 1600;
            if (i == ItemBase.coal.id)
                return 800;
            if (/*i == NFC.anthracite.id || i == NFC.bucketOil.id || */i == ItemBase.lavaBucket.id)
                return 6400;
        }
        return 0;
    }

    @Override
    public boolean canPlayerUse(PlayerBase arg) {
        if (this.level.getTileEntity(x, y, z) != this)
            return false;
        else
            return !(arg.squaredDistanceTo((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D) > 64.0D);
    }

    public void setTime(int requiredTime) {
        this.requiredTime = requiredTime;
    }

    private ItemInstance[] contents;
    public int burnTime;
    public int fuelTime;
    public int cookTime;
    public int requiredTime = 200;
}
