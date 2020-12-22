package net.newfrontiercraft.nfc.tileentity;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.common.event.block.TileEntityRegister;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.newfrontiercraft.nfc.NFC;

public class TileEntities {

    @Subscribe
    public void onTileEntitiesRegister(TileEntityRegister.Data data) {
        data.register(BrickOven.class, Identifier.of(NFC.MODID, "brick_oven").toString());
    }
}
