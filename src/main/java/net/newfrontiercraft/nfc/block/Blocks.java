package net.newfrontiercraft.nfc.block;

import com.google.common.eventbus.Subscribe;
import net.minecraft.block.BlockBase;
import net.modificationstation.stationloader.api.common.block.BlockRegistry;
import net.modificationstation.stationloader.api.common.event.block.BlockRegister;
import net.modificationstation.stationloader.api.common.registry.Identifier;
import net.modificationstation.stationloader.api.common.registry.ModID;
import net.newfrontiercraft.nfc.texture.Textures;

public class Blocks {

    @Subscribe
    public void onBlocksRegister(BlockRegister.Data data) {
        BlockRegistry registry = data.getRegistry();
        ModID modID = data.getModID();
        BRICK_OVEN = registry.register(ID -> new BrickOven(ID, false), Identifier.of(modID, "brick_oven")).setHardness(5).sounds(BlockBase.STONE_SOUNDS).setName("brickOven").disableNotifyOnMetaDataChange();
        BRICK_OVEN_LIT = registry.register(ID -> new BrickOven(ID, true), Identifier.of(modID, "brick_oven_lit")).setHardness(5).sounds(BlockBase.STONE_SOUNDS).setName("brickOven");
        FAKE_BEDROCK = registry.register(ID -> new Ore(ID, BlockBase.BEDROCK.texture), Identifier.of(modID, "fake_bedrock")).setHardness(1).setBlastResistance(10).setName("fakeBedrock");
        RECORD_CROP = registry.register(ID -> new RecordCrops(ID, 0), Identifier.of(modID, "record_crop")).setHardness(2).setBlastResistance(10).setName("recordPlant");
    }

    public static void setDefaultTextures() {
        BRICK_OVEN.texture = BRICK_OVEN_LIT.texture = Textures.Blocks.BRICK_OVEN;
        RECORD_CROP.texture = Textures.Blocks.RECORD_CROP_STAGE_0;
    }

    public static BlockBase
            BRICK_OVEN,
            BRICK_OVEN_LIT,
            FAKE_BEDROCK,
            RECORD_CROP;
}
