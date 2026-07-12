package com.synapse.rollinghotbar;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RollingHotbar.MODID)
public class RegisterGUIOverlays {

    @Mod.EventBusSubscriber(modid = RollingHotbar.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerBelowAll("rolling_hotbar", HotbarOverlay.ROLLING_HOTBAR);
        }
    }
}
