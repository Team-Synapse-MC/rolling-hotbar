package com.synapse.betterhotbars.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static final List<ResourceLocation> hudBars = new ArrayList<>();
    static {
        hudBars.add(VanillaGuiOverlay.PLAYER_HEALTH.id());
        hudBars.add(VanillaGuiOverlay.ARMOR_LEVEL.id());
        hudBars.add(VanillaGuiOverlay.FOOD_LEVEL.id());
        hudBars.add(VanillaGuiOverlay.AIR_LEVEL.id());
        hudBars.add(VanillaGuiOverlay.MOUNT_HEALTH.id());
        hudBars.add(VanillaGuiOverlay.JUMP_BAR.id());
        hudBars.add(VanillaGuiOverlay.EXPERIENCE_BAR.id());
        hudBars.add(VanillaGuiOverlay.ITEM_NAME.id());
    }

    /**
     * Frame-rate independent exponential smoothing.
     *
     * @param current current position
     * @param target target position
     * @param speed higher = faster
     * @param deltaFrames {@link net.minecraft.client.Minecraft#getDeltaFrameTime()}.
     */
    public static float smooth(float current, float target, float speed, float deltaFrames) {
        float t = 1.0f - (float) Math.exp(-speed * deltaFrames / 20.0f);
        return current + (target - current) * t;
    }

    /**
     * Same as smooth() except it
     * snaps when close enough so it doesn't spend forever approaching the last fraction of a pixel.
     */
    public static float smoothSnap(float current, float target, float speed, float deltaFrames, float snapDistance) {
        current = smooth(current, target, speed, deltaFrames);

        if (Math.abs(current - target) < snapDistance) {
            return target;
        }

        return current;
    }
}
