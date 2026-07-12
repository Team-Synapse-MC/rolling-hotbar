package com.synapse.betterhotbars.hotbars;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class RollingHotbar extends AnimatedHotbar {
    private static final ResourceLocation WIDGETS =
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/gui/widgets.png"
            );

    public boolean renderHotbarBackground = true;

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
            int diff = selected - lastSelection;

            if (diff > 4)
                diff -= 9;
            else if (diff < -4)
                diff += 9;

            targetSelection += diff;
            lastSelection = selected;
        }

        float speed = 1f;
        displayedSelection += (targetSelection - displayedSelection) * speed * mc.getDeltaFrameTime();

        if (Math.abs(displayedSelection - targetSelection) < 0.001f)
            displayedSelection = targetSelection;

        if (Math.abs(displayedSelection) > 1000) {
            float wraps = (float) Math.floor(displayedSelection / 9f);
            displayedSelection -= wraps * 9f;
            targetSelection -= wraps * 9f;
        }

        int centerX = width / 2;
        int centerY = height - 11;
        int spacing = 20;

        // draw hotbar background
        if (renderHotbarBackground) {
            graphics.blit(
                    WIDGETS,
                    width / 2 - 91,
                    height - 22,
                    0,
                    0,
                    182,
                    22,
                    256,
                    256
            );
        }

        // render offhand behind the selected item
//        ItemStack offhand = mc.player.getOffhandItem();
//
//        if (!offhand.isEmpty()) {
//            graphics.pose().pushPose();
//            graphics.pose().translate(centerX, centerY - 3, 0);
//            graphics.pose().scale(0.75f, 0.75f, 1);
//
//            RenderSystem.enableBlend();
//            graphics.setColor(1f, 1f, 1f, 0.65f);
//
//            graphics.renderItem(offhand, -8, -8);
//            graphics.renderItemDecorations(mc.font, offhand, -8, -8);
//
//            graphics.setColor(1f, 1f, 1f, 1f);
//            graphics.pose().popPose();
//        }

        for (int slot = 8; slot >= 0; slot--) {

            float offset = slot - displayedSelection;

            while (offset > 4.5f)
                offset -= 9f;

            while (offset < -4.5f)
                offset += 9f;

            float scale = 1;
            float alpha = 1;

            float x = centerX + offset * spacing;
            float y = centerY;

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
                    centerY + 18,
                    0xFFFFFF
            );
        }

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 400);

        graphics.blit(
                WIDGETS,
                centerX - 12,
                centerY - 12,
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
