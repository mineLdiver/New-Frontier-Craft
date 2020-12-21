package net.newfrontiercraft.nfc.block;

public class Ore extends net.minecraft.block.Ore {

    public Ore(int i, int j) {
        super(i, j);
    }

    @Override
    protected Ore setHardness(float hardness) {
        return (Ore) super.setHardness(hardness);
    }

    @Override
    protected Ore setBlastResistance(float resistance) {
        return (Ore) super.setBlastResistance(resistance);
    }
}
