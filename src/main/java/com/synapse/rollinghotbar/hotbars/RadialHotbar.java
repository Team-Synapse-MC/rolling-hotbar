package com.synapse.rollinghotbar.hotbars;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class RadialHotbar extends AnimatedHotbar {
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

        // detect scroll direction
        if (selected != lastSelection) {
            int diff = selected - lastSelection;

            // choose the shortest path around the wheel
            if (diff > 4)
                diff -= 9;
            else if (diff < -4)
                diff += 9;

            targetSelection += diff;
            lastSelection = selected;
        }

        // smooth animation
        float speed = 12.0f; // higher = snappier
        float t = 1.0f - (float) Math.exp(-speed * mc.getDeltaFrameTime() / 20.0f);
        displayedSelection += (targetSelection - displayedSelection) * t;

        // Prevent values from growing forever
        if (Math.abs(displayedSelection) > 1000) {
            float wraps = (float) Math.floor(displayedSelection / 9f);
            displayedSelection -= wraps * 9f;
            targetSelection -= wraps * 9f;
        }

        int centerX = width / 2;
        int centerY = height - 32;
        int spacing = 30;

        // render offhand item
        ItemStack offhand = mc.player.getOffhandItem();

        if (!offhand.isEmpty()) {
            graphics.pose().pushPose();
            // normal selected slot position
            graphics.pose().translate(centerX, centerY - 20, 0);
            // smaller than main items
            graphics.pose().scale(0.75f, 0.75f, 1);
            RenderSystem.enableBlend();
            graphics.setColor(1f, 1f, 1f, 0.8f);
            graphics.renderItem(offhand, -8, -8);
            graphics.renderItemDecorations(mc.font, offhand, -8, -8);
            graphics.setColor(1f, 1f, 1f, 1f);
            graphics.pose().popPose();
        }

        for (int slot = 8; slot >= 0; slot--) {

            float offset = slot - displayedSelection;

            while (offset > 4.5f)
                offset -= 9f;

            while (offset < -4.5f)
                offset += 9f;

            float distance = Math.abs(offset);

            float scale = Math.max(0.55f, 1.0f - distance * 0.12f);

            // make the selected item a little bigger
            if (slot == selected) {
                scale = 1.3f;
            }

            float yOffset = distance * distance * 1.55f;
            float alpha = Math.max(0.35f, 1.0f - distance * 0.18f);

            float x = centerX + offset * spacing;
            float y = centerY + yOffset;

            graphics.pose().pushPose();

            graphics.pose().translate(x, y, 200);
            graphics.pose().scale(scale, scale, 1);

            RenderSystem.enableBlend();
            graphics.setColor(1f, 1f, 1f, alpha);

            ItemStack stack = inventory.getItem(slot);

            graphics.renderItem(stack, -8, -8);
            graphics.renderItemDecorations(mc.font, stack, -8, -8);

            graphics.setColor(1f, 1f, 1f, 1f);
            graphics.pose().popPose();
        }

        ItemStack selectedStack = inventory.getItem(selected);

        if (!selectedStack.isEmpty()) {
            String text = selectedStack.getHoverName().getString();

            int textWidth = mc.font.width(text);

            graphics.drawString(
                    mc.font,
                    text,
                    centerX - textWidth / 2,
                    centerY + 16,
                    0xFFFFFF
            );
        }
    }
}
