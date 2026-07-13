package com.synapse.betterhotbars.hotbars.hotbar_types;

import com.synapse.betterhotbars.hotbars.AnimatedHotbar;
import com.synapse.betterhotbars.hotbars.HotbarUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class SmoothDefaultHotbar extends AnimatedHotbar {

    private static final ResourceLocation WIDGETS =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/widgets.png");

    private boolean initialized = false;
    private float displayedSelection;
    private float targetSelection;
    private int lastSelection;

    public void drawHotbar(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {

        Minecraft mc = gui.getMinecraft();

        if (mc.player == null)
            return;

        Inventory inventory = mc.player.getInventory();
        int selected = inventory.selected;

        if (!initialized) {
            initialized = true;
            displayedSelection = selected;
            targetSelection = selected;
            lastSelection = selected;
        }

        if (selected != lastSelection) {
            targetSelection = selected;
            lastSelection = selected;
        }

        // smooth animation
        float speed = 1f;
        displayedSelection += (targetSelection - displayedSelection) * speed * mc.getDeltaFrameTime();

        // snap when close
        if (Math.abs(displayedSelection - targetSelection) < 0.01f) {
            displayedSelection = targetSelection;
        }

        int left = width / 2 - 91;
        int y = height - 22;

        // draw hotbar background
        graphics.blit(
                WIDGETS,
                left,
                y,
                0,
                0,
                182,
                22,
                256,
                256
        );

        HotbarUtil.drawOffhandNormal(gui, graphics, partialTick, width, height);

        // draw items in hotbar
        for (int slot = 0; slot < 9; slot++) {

            int x = left + slot * 20 + 3;

            ItemStack stack = inventory.getItem(slot);

            graphics.renderItem(stack, x, y + 3);
            graphics.renderItemDecorations(mc.font, stack, x, y + 3);
        }

        // draw animated selector
        float selectorX = left - 1 + displayedSelection * 20.0f;
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 200);

        graphics.blit(
                WIDGETS,
                Math.round(selectorX),
                y - 1,
                0,
                22,
                24,
                24,
                256,
                256
        );
        graphics.pose().popPose();
    }
}