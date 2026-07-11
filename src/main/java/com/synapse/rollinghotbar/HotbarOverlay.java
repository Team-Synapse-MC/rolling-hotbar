package com.synapse.rollinghotbar;

import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HotbarOverlay {

    public static final IGuiOverlay ROLLING_HOTBAR = ((gui, poseStack, partialTick, width, height) -> {
        if (!gui.getMinecraft().options.hideGui) {
            gui.setupOverlayRenderState(true, false);
            if (gui.getMinecraft().gameMode == null) return;

            if (gui.getMinecraft().gameMode.getPlayerMode() != GameType.SPECTATOR) {

                RollingHotbarConfig.HOTBAR_TYPE.get().getHotbar().drawHotbar(gui, poseStack, partialTick, width, height);

            }
        }
    });
}
