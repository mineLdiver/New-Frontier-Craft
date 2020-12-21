package net.newfrontiercraft.nfc.texture;

import com.google.common.eventbus.Subscribe;
import net.modificationstation.stationloader.api.client.event.texture.TextureRegister;
import net.modificationstation.stationloader.api.client.texture.TextureFactory;
import net.modificationstation.stationloader.api.client.texture.TextureRegistry;

public class Textures {

    @Subscribe
    public void onTexturesRegister(TextureRegister.Data data) {
        TextureFactory factory = TextureFactory.INSTANCE;
        TextureRegistry terrain = TextureRegistry.getRegistry(TextureRegistry.Vanilla.TERRAIN);
        Blocks.BRICK_OVEN_FACE = factory.addTexture(terrain, "/assets/nfc/textures/blocks/brick_oven_face.png");
        Blocks.BRICK_OVEN = factory.addTexture(terrain, "/assets/nfc/textures/blocks/brick_oven.png");
        Blocks.BRICK_OVEN_FACE_LIT = factory.addTexture(terrain, "/assets/nfc/textures/blocks/brick_oven_face_lit.png");
        net.newfrontiercraft.nfc.block.Blocks.setDefaultTextures();
    }

    public static final class Blocks {

        public static int
                BRICK_OVEN_FACE,
                BRICK_OVEN,
                BRICK_OVEN_FACE_LIT;
    }
}
