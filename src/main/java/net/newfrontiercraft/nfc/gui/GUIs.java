package net.newfrontiercraft.nfc.gui;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationloader.api.client.event.gui.GuiHandlerRegister;
import net.modificationstation.stationloader.api.common.gui.GuiHandlerRegistry;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.modificationstation.stationloader.api.common.registry.ModID;
import net.newfrontiercraft.nfc.gui.screen.container.BrickOven;
import uk.co.benjiweber.expressions.tuples.BiTuple;

public class GUIs {

    @Subscribe
    public void onGuiHandlersRegister(GuiHandlerRegister.Data data) {
        GuiHandlerRegistry registry = data.getRegistry();
        ModID modID = data.getModID();
        registry.registerValueNoMessage(Identifier.of(modID, "brick_oven"), BiTuple.of(this::openBrickOven, net.newfrontiercraft.nfc.tileentity.BrickOven::new));
    }

    public ScreenBase openBrickOven(PlayerBase player, InventoryBase inventory) {
        return new BrickOven(player.inventory, (net.newfrontiercraft.nfc.tileentity.BrickOven) inventory);
    }
}
