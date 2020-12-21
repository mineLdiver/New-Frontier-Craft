package net.newfrontiercraft.nfc.gui;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.modificationstation.stationloader.api.client.event.gui.GuiHandlerRegister;
import net.modificationstation.stationloader.api.common.gui.GuiHandlerRegistry;
import net.modificationstation.stationloader.api.common.gui.GuiHelper;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.modificationstation.stationloader.api.common.registry.ModID;
import net.newfrontiercraft.nfc.NFC;
import net.newfrontiercraft.nfc.gui.screen.container.BrickOven;
import uk.co.benjiweber.expressions.tuples.BiTuple;

public class GUIs {

    @Subscribe
    public void onGuiHandlersRegister(GuiHandlerRegister.Data data) {
        GuiHandlerRegistry registry = data.getRegistry();
        ModID modID = data.getModID();
        registry.registerValueNoMessage(Identifier.of(modID, "brick_oven"), BiTuple.of(this::openBrickOven, net.newfrontiercraft.nfc.tileentity.BrickOven::new));
    }

    public static void open(Level level, int x, int y, int z, PlayerBase player, Class<? extends TileEntityBase> expected) {
        TileEntityBase tileEntity = expected.cast(level.getTileEntity(x, y, z));
        if (tileEntity != null) {
            Identifier identifier = null;
            InventoryBase inventory = (InventoryBase) tileEntity;
            ContainerBase container = null;
            if (tileEntity instanceof net.newfrontiercraft.nfc.tileentity.BrickOven) {
                identifier = Identifier.of(NFC.MOD_ID, "brick_oven");
                container = new net.newfrontiercraft.nfc.container.BrickOven(player.inventory, (net.newfrontiercraft.nfc.tileentity.BrickOven) tileEntity);
            }
            GuiHelper.INSTANCE.openGUI(player, identifier, inventory, container);
        }
    }

    public ScreenBase openBrickOven(PlayerBase player, InventoryBase inventory) {
        return new BrickOven(player.inventory, (net.newfrontiercraft.nfc.tileentity.BrickOven) inventory);
    }
}
