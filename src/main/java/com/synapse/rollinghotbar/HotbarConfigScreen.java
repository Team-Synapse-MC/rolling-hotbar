package com.synapse.rollinghotbar;

import com.synapse.rollinghotbar.hotbars.AnimatedHotbarTypes;
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
                    System.out.println("Config saved!");
                });

        ConfigCategory general = builder.getOrCreateCategory(
                Component.literal("General")
        );

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(
                entryBuilder.startEnumSelector(
                                Component.literal("Hotbar Style"),
                                AnimatedHotbarTypes.class,
                                RollingHotbarConfig.HOTBAR_TYPE.get()
                        )
                        .setDefaultValue(AnimatedHotbarTypes.ROLLING_HOTBAR)
                        .setSaveConsumer(RollingHotbarConfig.HOTBAR_TYPE::set)
                        .build()
        );

        return builder.build();
    }
}