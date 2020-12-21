package net.newfrontiercraft.nfc.gui.screen.container;

import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class BrickOven extends ContainerBase {

    public BrickOven(PlayerInventory inventoryplayer, net.newfrontiercraft.nfc.tileentity.BrickOven brickOven) {
        super(new net.newfrontiercraft.nfc.container.BrickOven(inventoryplayer, brickOven));
        entity = brickOven;
        containerHeight = 202;
    }

    @Override
    protected void renderForeground() {
        TranslationStorage translation = TranslationStorage.getInstance();
        textManager.drawText(translation.translate("gui.nfc:brickOven"), 60, 6, 0x404040);
        textManager.drawText(translation.translate("gui.inventory.inventory"), 8, (containerHeight - 96) + 2, 0x404040);
    }

    @Override
    protected void renderContainerBackground(float f) {
        int i = minecraft.textureManager.getTextureId("/assets/nfc/textures/gui/oven.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.textureManager.bindTexture(i);
        int j = (width - containerWidth) / 2;
        int k = (height - containerHeight) / 2;
        blit(j, k, 0, 0, containerWidth, containerHeight);
        if (entity.isBurning()) {
            int l = entity.method_1285(12);
            blit(j + 56, (k + 72 + 12) - l, 176, 12 - l, 14, l + 2);
        }
        int i1 = entity.method_1284(24);
        blit(j + 79, k + 70, 176, 14, i1 + 1, 16);
    }

    private final net.newfrontiercraft.nfc.tileentity.BrickOven entity;
}