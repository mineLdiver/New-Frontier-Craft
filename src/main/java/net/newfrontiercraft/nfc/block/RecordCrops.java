package net.newfrontiercraft.nfc.block;

import net.minecraft.block.BlockBase;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import net.minecraft.level.Level;
import net.minecraft.level.TileView;
import net.modificationstation.stationloader.api.client.model.BlockWithWorldRenderer;
import net.modificationstation.stationloader.api.common.preset.block.Plant;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.newfrontiercraft.nfc.texture.Textures;

import java.util.Random;

public class RecordCrops extends Plant implements BlockWithWorldRenderer {

    public RecordCrops(Identifier identifier, int texture) {
        super(identifier, texture);
        float f = 0.5F;
        setBoundingBox(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    @Override
    protected boolean canPlantOnTopOf(int id) {
        return id == BlockBase.FARMLAND.id;
    }

    @Override
    public void onScheduledTick(Level level, int x, int y, int z, Random rand) {
        super.onScheduledTick(level, x, y, z, rand);
        if (level.getLightLevel(x, y + 1, z) >= 9) {
            int var6 = level.getTileMeta(x, y, z);
            if (var6 < 9) {
                float var7 = growCropStage(level, x, y, z);
                if (rand.nextInt((int)(100.0F / var7)) == 0 && rand.nextInt(3) == 0) {
                    ++var6;
                    level.setTileMeta(x, y, z, var6);
                }
            }
        }
    }

    private float growCropStage(Level arg, int x, int y, int z) {
        float var5 = 1.0F;
        int var6 = arg.getTileId(x, y, z - 1);
        int var7 = arg.getTileId(x, y, z + 1);
        int var8 = arg.getTileId(x - 1, y, z);
        int var9 = arg.getTileId(x + 1, y, z);
        int var10 = arg.getTileId(x - 1, y, z - 1);
        int var11 = arg.getTileId(x + 1, y, z - 1);
        int var12 = arg.getTileId(x + 1, y, z + 1);
        int var13 = arg.getTileId(x - 1, y, z + 1);
        boolean var14 = var8 == this.id || var9 == this.id;
        boolean var15 = var6 == this.id || var7 == this.id;
        boolean var16 = var10 == this.id || var11 == this.id || var12 == this.id || var13 == this.id;
        for(int var17 = x - 1; var17 <= x + 1; ++var17)
            for(int var18 = z - 1; var18 <= z + 1; ++var18) {
                int var19 = arg.getTileId(var17, y - 1, var18);
                float var20 = 0.0F;
                if (var19 == BlockBase.FARMLAND.id) {
                    var20 = 1.0F;
                    if (arg.getTileMeta(var17, y - 1, var18) > 0)
                        var20 = 3.0F;
                }
                if (var17 != x || var18 != z)
                    var20 /= 4.0F;
                var5 += var20;
            }
        if (var16 || var14 && var15)
            var5 /= 2.0F;
        return var5;
    }

    @Override
    public int getTextureForSide(int side, int meta) {
        return meta < 9 ? texture : Textures.Blocks.RECORD_CROP_STAGE_1;
    }

    @Override
    public void renderWorld(TileRenderer tileRenderer, TileView tileView, int x, int y, int z, int meta) {
        Tessellator tessellator = Tessellator.INSTANCE;
        int q = tileView.getTileMeta(x, y, z);
        float r = 0.0625f;
        tessellator.method_1700(0, -r * (10 - q), 0);
        tileRenderer.method_73(this, x, y, z);
        tessellator.method_1700(0, r * (10 - q), 0);
    }

    // TODO: add the drops
    @Override
    public int getDropId(int meta, Random rand) {
        return super.getDropId(meta, rand);
    }

    @Override
    public int getDropCount(Random rand) {
        return 1;
    }
}
