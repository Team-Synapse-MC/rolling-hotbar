package com.synapse.betterhotbars.inventory_peek.peek_types;

import com.synapse.betterhotbars.BetterHotbars;
import com.synapse.betterhotbars.BetterHotbarsConfig;
import com.synapse.betterhotbars.OverlayOption;
import com.synapse.betterhotbars.inventory_peek.InventoryPeekTypes;
import com.synapse.betterhotbars.keybinding.KeyBinding;
import com.synapse.betterhotbars.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterHotbars.MODID, value = Dist.CLIENT)
public class PeekFromHotbar extends OverlayOption {
    private static float hudOffset = 0f;
    private static float peekProgress = 0f;

    private static final ResourceLocation WIDGETS =
            ResourceLocation.withDefaultNamespace("textures/gui/widgets.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
        Minecraft mc = gui.getMinecraft();

        if (mc.player == null)
            return;

        Inventory inventory = mc.player.getInventory();

        float shownY = height - 82;
        float hiddenY = height + 70;

        int hotbarLeft = width / 2 - 91;
        int startX = hotbarLeft + 3;

        float targetProgress = KeyBinding.PEEK_INVENTORY_KEY.isDown() ? 1f : 0f;

        peekProgress = Util.smoothSnap(peekProgress, targetProgress, 15.0f, mc.getDeltaFrameTime(), 0.001f);

        float currentY = hiddenY + (shownY - hiddenY) * peekProgress;

        hudOffset = -60f * peekProgress;

        int yBase = Math.round(currentY);

        // hotbar background behind each row
        for (int row = 0; row < 3; row++) {
            graphics.blit(
                    WIDGETS,
                    hotbarLeft,
                    yBase + row * 20 + (row == 0 ? -1 : 0),
                    0,
                    row == 0 ? 0 : 1,
                    182,
                    20 + (row == 0 ? 1 : 0),
                    256,
                    256
            );
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {

                int slot = 9 + row * 9 + col;

                int x = startX + col * 20;
                int y = yBase + row * 20 + 2;

                graphics.renderItem(inventory.getItem(slot), x, y);
                graphics.renderItemDecorations(mc.font, inventory.getItem(slot), x, y);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderGuiPre(RenderGuiOverlayEvent.Pre event) {
        ResourceLocation id = event.getOverlay().id();

        if (BetterHotbarsConfig.PEEKER_TYPE.get() == InventoryPeekTypes.PEEK_FROM_HOTBAR) {

            if (Util.hudBars.contains(id)) {
                event.getGuiGraphics().pose().pushPose();
                event.getGuiGraphics().pose().translate(0, PeekFromHotbar.hudOffset, 0);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderOverlayPost(RenderGuiOverlayEvent.Post event) {
        ResourceLocation id = event.getOverlay().id();

        if (BetterHotbarsConfig.PEEKER_TYPE.get() == InventoryPeekTypes.PEEK_FROM_HOTBAR) {

            if (Util.hudBars.contains(id)) {
                event.getGuiGraphics().pose().popPose();
            }
        }
    }
}
