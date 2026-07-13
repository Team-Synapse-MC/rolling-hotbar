package com.synapse.betterhotbars.hotbars;

import com.synapse.betterhotbars.OverlayOption;
import com.synapse.betterhotbars.hotbars.hotbar_types.RadialHotbar;
import com.synapse.betterhotbars.hotbars.hotbar_types.RollingHotbar;
import com.synapse.betterhotbars.hotbars.hotbar_types.SmoothDefaultHotbar;

public enum AnimatedHotbarTypes {

    RADIAL_HOTBAR(new RadialHotbar()),
    ROLLING_HOTBAR(new RollingHotbar()),
    SMOOTH_DEFAULT_HOTBAR(new SmoothDefaultHotbar());

    private final OverlayOption hotbar;

    AnimatedHotbarTypes(OverlayOption hotbar) {
        this.hotbar = hotbar;
    }
    public OverlayOption getHotbar() {
        return this.hotbar;
    }
}
