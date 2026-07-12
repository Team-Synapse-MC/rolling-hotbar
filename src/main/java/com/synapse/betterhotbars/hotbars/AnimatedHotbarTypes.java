package com.synapse.betterhotbars.hotbars;

public enum AnimatedHotbarTypes {

    RADIAL_HOTBAR(new RadialHotbar()),
    ROLLING_HOTBAR(new RollingHotbar()),
    ROLLING_HOTBAR_2(new RollingHotbar2()),
    SMOOTH_DEFAULT_HOTBAR(new SmoothDefaultHotbar());

    private final AnimatedHotbar hotbar;

    AnimatedHotbarTypes(AnimatedHotbar hotbar) {
        this.hotbar = hotbar;
    }
    public AnimatedHotbar getHotbar() {
        return this.hotbar;
    }
}
