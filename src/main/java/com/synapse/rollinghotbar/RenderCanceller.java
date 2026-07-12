package com.synapse.rollinghotbar;

import com.synapse.rollinghotbar.hotbars.AnimatedHotbarTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RollingHotbar.MODID, value = Dist.CLIENT)
public class RenderCanceller {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
            event.setCanceled(true);
        }

        if (RollingHotbarConfig.HOTBAR_TYPE.get() == AnimatedHotbarTypes.RADIAL_HOTBAR) {
            if (event.getOverlay().id().equals(VanillaGuiOverlay.ITEM_NAME.id())
                    || event.getOverlay().id().equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())
                    || event.getOverlay().id().equals(VanillaGuiOverlay.PLAYER_HEALTH.id())
                    || event.getOverlay().id().equals(VanillaGuiOverlay.FOOD_LEVEL.id())) {
                event.setCanceled(true);
            }
        }
    }
}