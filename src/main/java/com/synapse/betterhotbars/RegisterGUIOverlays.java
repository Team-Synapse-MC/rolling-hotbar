package com.synapse.betterhotbars;

import com.synapse.betterhotbars.hotbars.HotbarOverlay;
import com.synapse.betterhotbars.hud.HUDReplacementOverlay;
import com.synapse.betterhotbars.inventory_peek.InventoryPeekOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterHotbars.MODID)
public class RegisterGUIOverlays {

    @Mod.EventBusSubscriber(modid = BetterHotbars.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(ResourceLocation.withDefaultNamespace("item_name"), "rolling_hotbar", HotbarOverlay.ROLLING_HOTBAR);
            event.registerAbove(ResourceLocation.withDefaultNamespace("item_name"),"inventory_peek", InventoryPeekOverlay.INVENTORY_PEEK);
            // hotbar is above hud
            // peek inventory is under hotbar and above hud
            event.registerBelow(ResourceLocation.withDefaultNamespace("player_health"), "hud_replacement", HUDReplacementOverlay.HUDReplacement);
        }
    }
}
