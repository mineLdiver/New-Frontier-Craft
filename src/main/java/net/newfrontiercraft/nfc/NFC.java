package net.newfrontiercraft.nfc;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.common.event.mod.Init;
import net.modificationstation.stationloader.api.common.lang.I18n;
import net.modificationstation.stationloader.api.common.registry.ModID;

public class NFC {

    public static ModID MOD_ID;

    @Subscribe
    public void onInitialization(Init.Data data) {
        MOD_ID = data.getModID();
        I18n.INSTANCE.addLangFolder("/assets/nfc/minecraft/lang/");
    }
}
