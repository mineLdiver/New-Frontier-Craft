package net.newfrontiercraft.nfc.texture;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.client.event.texture.TextureRegister;
import net.modificationstation.stationloader.api.client.texture.TextureFactory;
import net.modificationstation.stationloader.api.client.texture.TextureRegistry;
import net.newfrontiercraft.nfc.NFC;

public class Textures {

    @Subscribe
    public void onTexturesRegister(TextureRegister.Data data) {
        TextureFactory factory = TextureFactory.INSTANCE;
        TextureRegistry terrain = TextureRegistry.getRegistry(TextureRegistry.Vanilla.TERRAIN);
        Blocks.BRICK_OVEN_FACE = factory.addTexture(terrain, Blocks.path + "brick_oven_face.png");
        Blocks.BRICK_OVEN = factory.addTexture(terrain, Blocks.path + "brick_oven.png");
        Blocks.BRICK_OVEN_FACE_LIT = factory.addTexture(terrain, Blocks.path + "brick_oven_face_lit.png");
        Blocks.RECORD_CROP_STAGE_0 = factory.addTexture(terrain, Blocks.path + "record_crop_stage_0.png");
        Blocks.RECORD_CROP_STAGE_1 = factory.addTexture(terrain, Blocks.path + "record_crop_stage_1.png");
        Blocks.PEBBLE = factory.addTexture(terrain, Blocks.path + "pebble.png");
        net.newfrontiercraft.nfc.block.Blocks.setDefaultTextures();
    }

    public static final class Blocks {

        public static final String path = "/assets/" + NFC.MODID + "/textures/blocks/";

        public static int
                BRICK_OVEN_FACE,
                BRICK_OVEN,
                BRICK_OVEN_FACE_LIT,
                RECORD_CROP_STAGE_0,
                RECORD_CROP_STAGE_1,
                PEBBLE;
    }
}
