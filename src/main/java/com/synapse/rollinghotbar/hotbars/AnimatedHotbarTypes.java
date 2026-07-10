package com.synapse.rollinghotbar.hotbars;

public enum AnimatedHotbarTypes {

    RADIAL_HOTBAR(new RadialHotbar()),
    ROLLING_HOTBAR(new RollingHotbar()),
    SMOOTH_DEFAULT_HOTBAR(new SmoothDefaultHotbar());

    private final AnimatedHotbar hotbar;

    AnimatedHotbarTypes(AnimatedHotbar hotbar) {
        this.hotbar = hotbar;
    }
    public AnimatedHotbar getHotbar() {
        return this.hotbar;
    }
}
