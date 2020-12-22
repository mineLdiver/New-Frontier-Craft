package net.newfrontiercraft.nfc.block;

import net.minecraft.block.BlockBase;
import net.minecraft.block.BlockSounds;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.level.TileView;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationloader.api.common.gui.GuiHelper;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.modificationstation.stationloader.api.common.util.BlockFaces;
import net.newfrontiercraft.nfc.NFC;
import net.newfrontiercraft.nfc.texture.Textures;

import java.util.Random;

public class BrickOven extends BlockWithEntity {

    protected BrickOven(int ID, boolean lit) {
        super(ID, Material.STONE);
        rand = new Random();
        this.lit = lit;
    }

    @Override
    public BrickOven setHardness(float hardness) {
        return (BrickOven) super.setHardness(hardness);
    }

    @Override
    public BrickOven sounds(BlockSounds sounds) {
        return (BrickOven) super.sounds(sounds);
    }

    @Override
    public BrickOven setName(String string) {
        return (BrickOven) super.setName(string);
    }

    @Override
    public BrickOven disableNotifyOnMetaDataChange() {
        return (BrickOven) super.disableNotifyOnMetaDataChange();
    }

    @Override
    public int getDropId(int meta, Random rand) {
        return net.newfrontiercraft.nfc.block.Blocks.BRICK_OVEN.id;
    }

    @Override
    public void onBlockPlaced(Level level, int x, int y, int z) {
        super.onBlockPlaced(level, x, y, z);
        setDefaultRotation(level, x, y, z);
    }

    private void setDefaultRotation(Level level, int x, int y, int z) {
        if (!level.isClient) {
            int var5 = level.getTileId(x, y, z - 1);
            int var6 = level.getTileId(x, y, z + 1);
            int var7 = level.getTileId(x - 1, y, z);
            int var8 = level.getTileId(x + 1, y, z);
            byte var9 = 3;

            if (BlockBase.FULL_OPAQUE[var6] && !BlockBase.FULL_OPAQUE[var5]) {
                var9 = 2;
            }

            if (BlockBase.FULL_OPAQUE[var7] && !BlockBase.FULL_OPAQUE[var8]) {
                var9 = 5;
            }

            if (BlockBase.FULL_OPAQUE[var8] && !BlockBase.FULL_OPAQUE[var7]) {
                var9 = 4;
            }

            level.setTileMeta(x, y, z, var9);
        }
    }

    @Override
    public int method_1626(TileView tileView, int x, int y, int z, int side) {
        return tileView.getTileMeta(x, y, z) == side ? lit ? Textures.Blocks.BRICK_OVEN_FACE_LIT : Textures.Blocks.BRICK_OVEN_FACE : texture;
    }

    @Override
    public void randomDisplayTick(Level level, int x, int y, int z, Random rand) {
        if (this.lit) {
            int var6 = level.getTileMeta(x, y, z);
            float var7 = (float)x + 0.5F;
            float var8 = (float)y + 0.25F + rand.nextFloat() * 6.0F / 16.0F;
            float var9 = (float)z + 0.5F;
            float var10 = 0.52F;
            float var11 = rand.nextFloat() * 0.6F - 0.3F;
            switch (var6) {
                case 4: {
                    level.addParticle("smoke", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
                    level.addParticle("flame", var7 - var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case 5: {
                    level.addParticle("smoke", var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
                    level.addParticle("flame", var7 + var10, var8, var9 + var11, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case 2: {
                    level.addParticle("smoke", var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
                    level.addParticle("flame", var7 + var11, var8, var9 - var10, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case 3: {
                    level.addParticle("smoke", var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
                    level.addParticle("flame", var7 + var11, var8, var9 + var10, 0.0D, 0.0D, 0.0D);
                    break;
                }
            }
        }
    }

    @Override
    public int getTextureForSide(int side) {
        if (BlockFaces.values()[side] == BlockFaces.WEST)
            return Textures.Blocks.BRICK_OVEN_FACE;
        else
            return super.getTextureForSide(side);
    }

    @Override
    public boolean canUse(Level level, int x, int y, int z, PlayerBase player) {
        net.newfrontiercraft.nfc.tileentity.BrickOven brickOven = (net.newfrontiercraft.nfc.tileentity.BrickOven) level.getTileEntity(x, y, z);
        GuiHelper.INSTANCE.openGUI(player, Identifier.of(NFC.MODID, "brick_oven"), brickOven, new net.newfrontiercraft.nfc.container.BrickOven(player.inventory, brickOven));
        return true;
    }

    public static void method_1403(boolean b, Level level, int x, int y, int z) {
        int var5 = level.getTileMeta(x, y, z);
        TileEntityBase var6 = level.getTileEntity(x, y, z);
        SETTING_TILE = true;
        if (b)
            level.setTile(x, y, z, net.newfrontiercraft.nfc.block.Blocks.BRICK_OVEN_LIT.id);
        else
            level.setTile(x, y, z, net.newfrontiercraft.nfc.block.Blocks.BRICK_OVEN.id);
        SETTING_TILE = false;
        level.setTileMeta(x, y, z, var5);
        var6.validate();
        level.setTileEntity(x, y, z, var6);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new net.newfrontiercraft.nfc.tileentity.BrickOven();
    }

    @Override
    public void afterPlaced(Level arg, int i, int j, int k, Living arg1) {
        switch (MathHelper.floor((double)(arg1.yaw * 4.0F / 360.0F) + 0.5D) & 3) {
            case 0: {
                arg.setTileMeta(i, j, k, 2);
                break;
            }
            case 1: {
                arg.setTileMeta(i, j, k, 5);
                break;
            }
            case 2: {
                arg.setTileMeta(i, j, k, 3);
                break;
            }
            case 3: {
                arg.setTileMeta(i, j, k, 4);
                break;
            }
        }
    }

    @Override
    public void onBlockRemoved(Level level, int x, int y, int z) {
        if (!SETTING_TILE) {
            net.newfrontiercraft.nfc.tileentity.BrickOven var5 = (net.newfrontiercraft.nfc.tileentity.BrickOven) level.getTileEntity(x, y, z);
            for(int var6 = 0; var6 < var5.getInventorySize(); ++var6) {
                ItemInstance var7 = var5.getInventoryItem(var6);
                if (var7 != null) {
                    float var8 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float var9 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float var10 = this.rand.nextFloat() * 0.8F + 0.1F;
                    while(var7.count > 0) {
                        int var11 = this.rand.nextInt(21) + 10;
                        if (var11 > var7.count)
                            var11 = var7.count;
                        var7.count -= var11;
                        Item var12 = new Item(level, (float)x + var8, (float)y + var9, (float)z + var10, new ItemInstance(var7.itemId, var11, var7.getDamage()));
                        float var13 = 0.05F;
                        var12.velocityX = (float)this.rand.nextGaussian() * var13;
                        var12.velocityY = (float)this.rand.nextGaussian() * var13 + 0.2F;
                        var12.velocityZ = (float)this.rand.nextGaussian() * var13;
                        level.spawnEntity(var12);
                    }
                }
            }
        }

        super.onBlockRemoved(level, x, y, z);
    }

    private final Random rand;
    private final boolean lit;
    private static boolean SETTING_TILE = false;
}
