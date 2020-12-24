package net.newfrontiercraft.nfc.block;

import net.minecraft.level.Level;
import net.modificationstation.stationloader.api.common.preset.block.Sand;
import net.modificationstation.stationloader.api.common.registry.Identifier;

import java.util.Random;

public class Pebble extends Sand {

    public Pebble(Identifier identifier, int j) {
        super(identifier, j);
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        if (rand.nextInt(8) == 0)
            super.onScheduledTick(level, x, y, z, rand);
    }
}
