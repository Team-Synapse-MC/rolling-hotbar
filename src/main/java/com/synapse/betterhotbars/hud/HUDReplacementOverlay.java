package com.synapse.betterhotbars.hud;

import com.synapse.betterhotbars.BetterHotbarsConfig;
import com.synapse.betterhotbars.hotbars.AnimatedHotbarTypes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HUDReplacementOverlay {
    public static final IGuiOverlay HUDReplacement = ((gui, poseStack, partialTick, width, height) -> {
        if (!gui.getMinecraft().options.hideGui) {
            gui.setupOverlayRenderState(true, false);
            if (gui.getMinecraft().gameMode == null) return;
            if (gui.getMinecraft().gameMode.getPlayerMode() != GameType.SPECTATOR) {
                if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {

                    if (BetterHotbarsConfig.HOTBAR_TYPE.get() == AnimatedHotbarTypes.RADIAL_HOTBAR) {
                        drawRadialBars(gui, poseStack, partialTick, width, height);
                    }
                }
            }
        }
    });

    /**
     * draws bars at the top of the screen (when player's radial hotbar is taking up bottom of the screen)
     */
    private static void drawRadialBars(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
        graphics.pose().pushPose();
        graphics.pose().translate(0, -height + 55, 0);
        gui.renderHealth(width, height, graphics);
        gui.renderFood(width, height, graphics);
        graphics.pose().popPose();

        graphics.pose().pushPose();
        graphics.pose().translate(0, -height + 37, 0);
        gui.renderExperienceBar(graphics, width / 2 - 91);
        graphics.pose().popPose();
    }
}
