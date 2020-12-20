package net.newfrontiercraft.nfc;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.common.event.mod.Init;

public class NFC {

    @Subscribe
    public void onInitialization(Init.Data data) {
        System.out.println("Hello NFC!");
    }
}
