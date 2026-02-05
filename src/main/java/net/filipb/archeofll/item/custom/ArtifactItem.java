package net.filipb.archeofll.item.custom;

import java.util.List;

import net.minecraft.client.item.TooltipType;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ArtifactItem extends Item {
    public ArtifactItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        String baseKey = this.getTranslationKey().replaceFirst("^item", "tooltip");
        for (int i = 1;; i++) {
            String key = baseKey + "." + i;
            if (!I18n.hasTranslation(key)) break;
            tooltip.add(Text.translatable(key).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}
