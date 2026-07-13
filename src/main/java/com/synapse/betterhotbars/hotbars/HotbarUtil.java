package com.synapse.betterhotbars.hotbars;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class HotbarUtil {
    private static final ResourceLocation WIDGETS =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/widgets.png");

    public static void drawOffhandNormal(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
        assert Minecraft.getInstance().player != null;

        int left = width / 2 - 91;
        int y = height - 22;

        // draw offhand slot background
        ItemStack offhand = Minecraft.getInstance().player.getOffhandItem();
        HumanoidArm offhandSide = Minecraft.getInstance().player.getMainArm().getOpposite(); // draw slot on left for right-handers and vice versa

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
            graphics.renderItemDecorations(Minecraft.getInstance().font, offhand, x, offhandY);
        }
    }
}
