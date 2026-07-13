package com.synapse.betterhotbars.inventory_peek.peek_types;

import com.synapse.betterhotbars.OverlayOption;
import com.synapse.betterhotbars.keybinding.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class PeekFromTop extends OverlayOption {

    private static final ResourceLocation INVENTORY_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/inventory.png");

    private static final int hiddenY = -60;
    public static int renderY = 33;
    private static int currentY = hiddenY;

    public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
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

        graphics.setColor(1, 1, 1, 1f);

        graphics.blit(INVENTORY_TEXTURE, startX-2, currentY-2, 6, 82,
                164, 56, 256, 256);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {

                int slot = 9 + row * 9 + col;

                int x = startX + col * 18;
                int y = currentY + row * 18;

                graphics.renderItem(inventory.getItem(slot), x, y);
                graphics.renderItemDecorations(mc.font, inventory.getItem(slot), x, y);
            }
        }
        graphics.setColor(1, 1, 1, 1f);
    }
}
