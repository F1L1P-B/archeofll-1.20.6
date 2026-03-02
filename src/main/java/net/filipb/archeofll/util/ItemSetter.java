package net.filipb.archeofll.util;

import net.minecraft.item.Item;

public interface ItemSetter {
    default void archeofll$setItem(Item item) {
        
    }

    default boolean archeofll$canSetItem() {
        return false;
    }
}
