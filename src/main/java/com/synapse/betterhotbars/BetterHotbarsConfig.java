package com.synapse.betterhotbars;

import com.synapse.betterhotbars.hotbars.AnimatedHotbarTypes;
import com.synapse.betterhotbars.inventory_peek.InventoryPeekTypes;
import net.minecraftforge.common.ForgeConfigSpec;

public class BetterHotbarsConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.EnumValue<AnimatedHotbarTypes> HOTBAR_TYPE;
    public static final ForgeConfigSpec.EnumValue<InventoryPeekTypes> PEEKER_TYPE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("general");

        HOTBAR_TYPE = builder
                .comment("The hotbar style to use")
                .defineEnum(
                        "hotbarStyle",
                        AnimatedHotbarTypes.SMOOTH_DEFAULT_HOTBAR
                );

        PEEKER_TYPE = builder
                .comment("The inventory peek style to use.")
                .defineEnum(
                        "peekerStyle",
                        InventoryPeekTypes.PEEK_FROM_HOTBAR
                );

        builder.pop();

        SPEC = builder.build();
    }
}