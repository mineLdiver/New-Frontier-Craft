package net.newfrontiercraft.nfc;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.common.event.mod.Init;
import net.modificationstation.stationloader.api.common.lang.I18n;
import net.modificationstation.stationloader.api.common.mod.entrypoint.ModIDField;
import net.modificationstation.stationloader.api.common.registry.ModID;

public class NFC {

    @ModIDField
    public static ModID MODID;

    @Subscribe
    public void onInitialization(Init.Data data) {
        I18n.INSTANCE.addLangFolder("/assets/nfc/minecraft/lang/");
    }
}
