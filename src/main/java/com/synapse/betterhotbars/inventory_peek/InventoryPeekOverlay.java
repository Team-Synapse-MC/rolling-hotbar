package com.synapse.betterhotbars.inventory_peek;

import com.synapse.betterhotbars.BetterHotbarsConfig;
import net.minecraft.world.level.GameType;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class InventoryPeekOverlay {
    public static final IGuiOverlay INVENTORY_PEEK = ((gui, graphics, partialTick, width, height) -> {

        if (gui.getMinecraft().options.hideGui) return;

        gui.setupOverlayRenderState(true, false);

        if (gui.getMinecraft().gameMode == null) return;

        if (gui.getMinecraft().gameMode.getPlayerMode() == GameType.SPECTATOR) return;

        BetterHotbarsConfig.PEEKER_TYPE.get().getPeeker().render(gui, graphics, partialTick, width, height);
    });
}