package net.newfrontiercraft.nfc.block;

import net.minecraft.block.BlockSounds;
import net.minecraft.block.Sand;
import net.minecraft.level.Level;

import java.util.Random;

public class Pebble extends Sand {

    public Pebble(int i, int j) {
        super(i, j);
    }

    @Override
    protected Pebble setHardness(float hardness) {
        return (Pebble) super.setHardness(hardness);
    }

    @Override
    protected Pebble setBlastResistance(float resistance) {
        return (Pebble) super.setBlastResistance(resistance);
    }

    @Override
    protected Pebble sounds(BlockSounds sounds) {
        return (Pebble) super.sounds(sounds);
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        if (rand.nextInt(8) == 0)
            super.onScheduledTick(level, x, y, z, rand);
    }
}
