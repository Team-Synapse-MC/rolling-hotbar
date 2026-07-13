package com.synapse.betterhotbars;

import com.synapse.betterhotbars.hotbars.AnimatedHotbarTypes;
import com.synapse.betterhotbars.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterHotbars.MODID, value = Dist.CLIENT)
public class RenderCanceller {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
            event.setCanceled(true);
        }

        if (BetterHotbarsConfig.HOTBAR_TYPE.get() == AnimatedHotbarTypes.RADIAL_HOTBAR) {
            if (event.getOverlay().id().equals(VanillaGuiOverlay.ITEM_NAME.id()) || Util.hudBars.contains(event.getOverlay().id())) {
                event.setCanceled(true);
            }
        }
    }
}