package com.synapse.betterhotbars.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import com.synapse.betterhotbars.BetterHotbars;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterHotbars.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBinding {

    private static final String KEY_CATEGORY_BETTERHOTBARS = "key.category.betterhotbars";
    private static final String KEY_PEEK_INVENTORY = "key.betterhotbars.peek_inventory";
    public static final KeyMapping PEEK_INVENTORY_KEY = new KeyMapping(KEY_PEEK_INVENTORY, KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_LALT, -1), KEY_CATEGORY_BETTERHOTBARS);

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(PEEK_INVENTORY_KEY);
    }
}
