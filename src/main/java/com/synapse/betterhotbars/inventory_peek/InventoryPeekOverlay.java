package com.synapse.betterhotbars.inventory_peek;

import com.synapse.betterhotbars.keybinding.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class InventoryPeekOverlay {

    private static float currentY = Float.NaN;

    private static final ResourceLocation WIDGETS =
            ResourceLocation.withDefaultNamespace("textures/gui/widgets.png");

    public static final IGuiOverlay INVENTORY_PEEK = ((gui, graphics, partialTick, width, height) -> {

        if (gui.getMinecraft().options.hideGui)
            return;

        gui.setupOverlayRenderState(true, false);

        if (gui.getMinecraft().gameMode == null)
            return;

        if (gui.getMinecraft().gameMode.getPlayerMode() == GameType.SPECTATOR)
            return;

        Minecraft mc = gui.getMinecraft();

        if (mc.player == null)
            return;

        Inventory inventory = mc.player.getInventory();

        float shownY = height - 82;
        float hiddenY = height + 70;

        int hotbarLeft = width / 2 - 91;
        int startX = hotbarLeft + 3;


        if (Float.isNaN(currentY))
            currentY = hiddenY;

        float targetY = KeyBinding.PEEK_INVENTORY_KEY.isDown() ? shownY : hiddenY;

        float speed = 15.0f;
        float t = 1.0f - (float) Math.exp(-speed * mc.getDeltaFrameTime() / 20.0f);
        currentY += (targetY - currentY) * t;

        int yBase = Math.round(currentY);

        // hotbar background behind each row
        for (int row = 0; row < 3; row++) {
            graphics.blit(
                    WIDGETS,
                    hotbarLeft,
                    yBase + row * 20 + (row == 0 ? -1 : 0),
                    0,
                    row == 0 ? 0 : 1,
                    182,
                    20 + (row == 0 ? 1 : 0),
                    256,
                    256
            );
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {

                int slot = 9 + row * 9 + col;

                int x = startX + col * 20;
                int y = yBase + row * 20 + 3;

                graphics.renderItem(inventory.getItem(slot), x, y);
                graphics.renderItemDecorations(mc.font, inventory.getItem(slot), x, y);
            }
        }
    });
}