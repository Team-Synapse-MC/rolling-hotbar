package com.synapse.betterhotbars;

import com.synapse.betterhotbars.hotbars.AnimatedHotbarTypes;
import com.synapse.betterhotbars.inventory_peek.InventoryPeekTypes;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HotbarConfigScreen {

    public static Screen create(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("Rolling Hotbar"))
                .setSavingRunnable(() -> {
                    AnimatedHotbarTypes hotbar = BetterHotbarsConfig.HOTBAR_TYPE.get();
                    InventoryPeekTypes peeker = BetterHotbarsConfig.PEEKER_TYPE.get();

                    // invalid combination: can't have peek from hotbar with radial hotbar
                    if (hotbar == AnimatedHotbarTypes.RADIAL_HOTBAR &&
                            peeker == InventoryPeekTypes.PEEK_FROM_HOTBAR) {

                        BetterHotbarsConfig.PEEKER_TYPE.set(InventoryPeekTypes.PEEK_FROM_TOP);
                    }

                    BetterHotbarsConfig.SPEC.save();
                });

        ConfigCategory general = builder.getOrCreateCategory(
                Component.literal("General")
        );

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(
                entryBuilder.startEnumSelector(
                                Component.literal("Hotbar Style"),
                                AnimatedHotbarTypes.class,
                                BetterHotbarsConfig.HOTBAR_TYPE.get()
                        )
                        .setDefaultValue(AnimatedHotbarTypes.SMOOTH_DEFAULT_HOTBAR)
                        .setSaveConsumer(BetterHotbarsConfig.HOTBAR_TYPE::set)
                        .build()
        );
        general.addEntry(
                entryBuilder.startEnumSelector(
                                Component.literal("Inventory Peek Style"),
                                InventoryPeekTypes.class,
                                BetterHotbarsConfig.PEEKER_TYPE.get()
                        )
                        .setDefaultValue(InventoryPeekTypes.PEEK_FROM_HOTBAR)
                        .setSaveConsumer(BetterHotbarsConfig.PEEKER_TYPE::set)
                        .build()
        );

        return builder.build();
    }
}