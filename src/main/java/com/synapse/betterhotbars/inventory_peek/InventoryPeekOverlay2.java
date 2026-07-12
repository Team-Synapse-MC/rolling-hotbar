package com.synapse.betterhotbars.inventory_peek;

import com.synapse.betterhotbars.keybinding.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class InventoryPeekOverlay2 {
    private static final ResourceLocation INVENTORY_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/inventory.png");

    private static final int hiddenY = -60;
    public static int renderY = 20;
    private static int currentY = hiddenY;

    public static final IGuiOverlay INVENTORY_PEEK = ((gui, poseStack, partialTick, width, height) -> {
        if (!gui.getMinecraft().options.hideGui) {
            gui.setupOverlayRenderState(true, false);
            if (gui.getMinecraft().gameMode == null) return;

            if (gui.getMinecraft().gameMode.getPlayerMode() != GameType.SPECTATOR) {

                if (!KeyBinding.PEEK_INVENTORY_KEY.isDown()) {
                    currentY = Mth.lerpInt(Minecraft.getInstance().getDeltaFrameTime(), currentY, hiddenY);
                } else {
                    currentY = Mth.lerpInt(Minecraft.getInstance().getDeltaFrameTime(), currentY, renderY);
                }

                Minecraft mc = gui.getMinecraft();

                if (mc.player == null)
                    return;

                Inventory inventory = mc.player.getInventory();

                int startX = width / 2 - 81;

                poseStack.setColor(1, 1, 1, 1f);

                poseStack.blit(INVENTORY_TEXTURE, startX-2, currentY-2, 6, 82,
                        164, 56, 256, 256);

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 9; col++) {

                        int slot = 9 + row * 9 + col;

                        int x = startX + col * 18;
                        int y = currentY + row * 18;

                        poseStack.renderItem(inventory.getItem(slot), x, y);
                        poseStack.renderItemDecorations(mc.font, inventory.getItem(slot), x, y);
                    }
                }
                poseStack.setColor(1, 1, 1, 1f);

            }
        }
    });
}
