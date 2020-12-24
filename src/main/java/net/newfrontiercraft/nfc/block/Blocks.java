package net.newfrontiercraft.nfc.block;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationloader.api.common.event.block.BlockRegister;
import net.modificationstation.stationloader.api.common.preset.block.Ore;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.modificationstation.stationloader.api.common.registry.ModID;
import net.newfrontiercraft.nfc.texture.Textures;

public class Blocks {

    @Subscribe
    public void onBlocksRegister(BlockRegister.Data data) {
        ModID modID = data.getModID();
        BRICK_OVEN = new BrickOven(Identifier.of(modID, "brick_oven"), false).setHardness(5).sounds(BlockBase.STONE_SOUNDS).setName("brickOven").disableNotifyOnMetaDataChange();
        BRICK_OVEN_LIT = new BrickOven(Identifier.of(modID, "brick_oven_lit"), true).setHardness(5).sounds(BlockBase.STONE_SOUNDS).setName("brickOven");
        FAKE_BEDROCK = new Ore(Identifier.of(modID, "fake_bedrock"), BlockBase.BEDROCK.texture).setHardness(1).setBlastResistance(10).setName("fakeBedrock");
        RECORD_CROP = new RecordCrops(Identifier.of(modID, "record_crop"), 0).setHardness(2).setBlastResistance(10).setName("recordPlant");
        PEBBLE = new Pebble(Identifier.of(modID, "pebble"), 0).setHardness(3).setBlastResistance(3).sounds(BlockBase.GRAVEL_SOUNDS).setName("pebble");
    }

    public static void setDefaultTextures() {
        BRICK_OVEN.texture = BRICK_OVEN_LIT.texture = Textures.Blocks.BRICK_OVEN;
        RECORD_CROP.texture = Textures.Blocks.RECORD_CROP_STAGE_0;
        PEBBLE.texture = Textures.Blocks.PEBBLE;
    }

    public static BlockBase
            BRICK_OVEN,
            BRICK_OVEN_LIT,
            FAKE_BEDROCK,
            RECORD_CROP,
            PEBBLE;
}
