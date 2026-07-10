package com.synapse.rollinghotbar;

import com.synapse.rollinghotbar.hotbars.AnimatedHotbarTypes;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HotbarOverlay {
    public static AnimatedHotbarTypes animatedHotbarType = AnimatedHotbarTypes.SMOOTH_DEFAULT_HOTBAR;

    public static final IGuiOverlay ROLLING_HOTBAR = ((gui, poseStack, partialTick, width, height) -> {
        if (!gui.getMinecraft().options.hideGui) {
            gui.setupOverlayRenderState(true, false);
            if (gui.getMinecraft().gameMode == null) return;

            if (gui.getMinecraft().gameMode.getPlayerMode() != GameType.SPECTATOR) {

                animatedHotbarType.getHotbar().drawHotbar(gui, poseStack, partialTick, width, height);

            }
        }
    });
}
