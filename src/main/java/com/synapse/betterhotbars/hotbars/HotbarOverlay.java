package com.synapse.betterhotbars.hotbars;

import com.synapse.betterhotbars.BetterHotbarsConfig;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HotbarOverlay {

    public static final IGuiOverlay ROLLING_HOTBAR = ((gui, poseStack, partialTick, width, height) -> {
        if (!gui.getMinecraft().options.hideGui) {
            gui.setupOverlayRenderState(true, false);
            if (gui.getMinecraft().gameMode == null) return;

            if (gui.getMinecraft().gameMode.getPlayerMode() != GameType.SPECTATOR) {

                BetterHotbarsConfig.HOTBAR_TYPE.get().getHotbar().render(gui, poseStack, partialTick, width, height);

            }
        }
    });
}
