package net.newfrontiercraft.nfc.container;

import net.minecraft.class_633;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;

public class BrickOven extends ContainerBase {

    private final net.newfrontiercraft.nfc.tileentity.BrickOven brickOven;
    private int cookTime = 0;
    private int burnTime = 0;
    private int fuelTime = 0;
    private int requiredTime;

    public BrickOven(PlayerInventory playerInventory, net.newfrontiercraft.nfc.tileentity.BrickOven brickOven) {
        this.brickOven = brickOven;
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                addSlot(new Slot(brickOven, k + i * 3, 38 + k * 18, 17 + i * 18));
        addSlot(new Slot(brickOven, 9, 56, 89));
        addSlot(new FurnaceOutput(playerInventory.player, brickOven, 10, 116, 71));
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                addSlot(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 120 + i * 18));
        for (int j = 0; j < 9; j++)
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 178));
    }

    @Override
    public void method_2076(class_633 arg) {
        super.method_2076(arg);
        arg.method_2099(this, 0, brickOven.cookTime);
        arg.method_2099(this, 1, brickOven.burnTime);
        arg.method_2099(this, 2, brickOven.fuelTime);
        arg.method_2099(this, 3, brickOven.requiredTime);
    }

    @Override
    public void method_2075() {
        super.method_2075();
        for (Object o : field_2736) {
            class_633 var2 = (class_633) o;
            if (cookTime != brickOven.cookTime)
                var2.method_2099(this, 0, brickOven.cookTime);
            if (burnTime != brickOven.burnTime)
                var2.method_2099(this, 1, brickOven.burnTime);
            if (fuelTime != brickOven.fuelTime)
                var2.method_2099(this, 2, brickOven.fuelTime);
            if (requiredTime != brickOven.requiredTime)
                var2.method_2099(this, 3, brickOven.requiredTime);
        }
        cookTime = brickOven.cookTime;
        burnTime = brickOven.burnTime;
        fuelTime = brickOven.fuelTime;
        requiredTime = brickOven.requiredTime;
    }

    public void setProperty(int id, int value) {
        if (id == 0)
            brickOven.cookTime = value;
        if (id == 1)
            brickOven.burnTime = value;
        if (id == 2)
            brickOven.fuelTime = value;
        if (id == 3)
            brickOven.requiredTime = value;
    }

    @Override
    public boolean canUse(PlayerBase player) {
        return brickOven.canPlayerUse(player);
    }

    @Override
    public ItemInstance transferSlot(int index) {
        ItemInstance itemInstance = null;
        Slot slot = (Slot) slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemInstance itemInstance1 = slot.getItem();
            itemInstance = itemInstance1.copy();
            if (index == 10)
                insertItem(itemInstance1, 11, 47, true);
            else if (index >= 11 && index < 38)
                insertItem(itemInstance1, 38, 39, false);
            else if (index >= 38 && index < 47)
                insertItem(itemInstance1, 11, 38, false);
            else
                insertItem(itemInstance1, 11, 47, false);
            if (itemInstance1.count == 0)
                slot.setStack(null);
            else
                slot.markDirty();
            if (itemInstance1.count != itemInstance.count)
                slot.onCrafted(itemInstance1);
            else
                return null;
        }
        return itemInstance;
    }
}
