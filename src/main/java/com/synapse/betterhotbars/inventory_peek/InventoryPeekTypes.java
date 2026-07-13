package com.synapse.betterhotbars.inventory_peek;

import com.synapse.betterhotbars.OverlayOption;
import com.synapse.betterhotbars.inventory_peek.peek_types.PeekFromHotbar;
import com.synapse.betterhotbars.inventory_peek.peek_types.PeekFromTop;

public enum InventoryPeekTypes {
    PEEK_FROM_HOTBAR(new PeekFromHotbar()),
    PEEK_FROM_TOP(new PeekFromTop());

    private final OverlayOption peeker;

    InventoryPeekTypes(OverlayOption peeker) {
        this.peeker = peeker;
    }

    public OverlayOption getPeeker() {
        return this.peeker;
    }
}
