package com.synapse.betterhotbars.hotbars;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
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

        // draw offhand slot background
        ItemStack offhand = mc.player.getOffhandItem();
        HumanoidArm offhandSide = mc.player.getMainArm().getOpposite(); // draw slot on left for right-handers and vice versa

        if (!offhand.isEmpty()) {
            if (offhandSide == HumanoidArm.LEFT) {
                graphics.blit(
                        WIDGETS,
                        left - 29,
                        y - 1,
                        24,
                        22,
                        29,
                        24,
                        256,
                        256
                );
            } else {
                graphics.blit(
                        WIDGETS,
                        left + 182,
                        y - 1,
                        53,
                        22,
                        29,
                        24,
                        256,
                        256
                );
            }
        }

        // draw items in hotbar
        for (int slot = 0; slot < 9; slot++) {

            int x = left + slot * 20 + 3;

            ItemStack stack = inventory.getItem(slot);

            graphics.renderItem(stack, x, y + 3);
            graphics.renderItemDecorations(mc.font, stack, x, y + 3);
        }

        // draw offhand item
        if (!offhand.isEmpty()) {
            int offhandY = y + 3;

            int x;
            if (offhandSide == HumanoidArm.LEFT) {
                x = left - 26;

            } else {
                x = left + 192;

            }
            graphics.renderItem(offhand, x, offhandY);
            graphics.renderItemDecorations(mc.font, offhand, x, offhandY);
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