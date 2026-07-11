package com.synapse.rollinghotbar;

import com.synapse.rollinghotbar.hotbars.AnimatedHotbarTypes;
import net.minecraftforge.common.ForgeConfigSpec;

public class RollingHotbarConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.EnumValue<AnimatedHotbarTypes> HOTBAR_TYPE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("general");

        HOTBAR_TYPE = builder
                .comment("The hotbar style to use")
                .defineEnum(
                        "hotbarStyle",
                        AnimatedHotbarTypes.ROLLING_HOTBAR
                );

        builder.pop();

        SPEC = builder.build();
    }
}