package de.dafuqs.spectrum.compat.REI.plugins;

import com.google.common.collect.*;
import de.dafuqs.spectrum.compat.REI.*;
import de.dafuqs.spectrum.registries.*;
import me.shedaniel.math.*;
import me.shedaniel.rei.api.client.gui.*;
import me.shedaniel.rei.api.client.gui.widgets.*;
import me.shedaniel.rei.api.client.registry.display.*;
import me.shedaniel.rei.api.common.category.*;
import me.shedaniel.rei.api.common.util.*;
import net.fabricmc.api.*;
import net.minecraft.text.*;
import org.jetbrains.annotations.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class InkConvertingCategory implements DisplayCategory<InkConvertingDisplay> {

	@Override
	public CategoryIdentifier<? extends InkConvertingDisplay> getCategoryIdentifier() {
		return SpectrumPlugins.INK_CONVERTING;
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(SpectrumBlocks.COLOR_PICKER);
	}

	@Override
	public Text getTitle() {
		return Text.translatable("container.spectrum.rei.ink_converting.title");
	}


	@Override
	public List<Widget> setupDisplay(@NotNull InkConvertingDisplay display, @NotNull Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 74, bounds.getCenterY() - 48);
		List<Widget> widgets = Lists.newArrayList();

		widgets.add(Widgets.createRecipeBase(bounds));

		if (!display.isUnlocked()) {
			widgets.add(Widgets.createLabel(new Point(startPoint.x + 8, startPoint.y + 38), Text.translatable("container.spectrum.rei.pedestal_crafting.recipe_not_unlocked_line_1")).leftAligned().color(0x3f3f3f).noShadow());
			widgets.add(Widgets.createLabel(new Point(startPoint.x + 8, startPoint.y + 48), Text.translatable("container.spectrum.rei.pedestal_crafting.recipe_not_unlocked_line_2")).leftAligned().color(0x3f3f3f).noShadow());
		} else {
			// input slot
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 8, startPoint.y + 40)).markInput().entries(display.getInputEntries().get(0)));

			// output arrow
			widgets.add(Widgets.createArrow(new Point(startPoint.x + 30, startPoint.y + 40)));

			// output amount & required time
			Text colorText = Text.translatable("container.spectrum.rei.ink_converting.color", display.color.getName());
			Text amountText = Text.translatable("container.spectrum.rei.ink_converting.amount", display.amount);
			widgets.add(Widgets.createLabel(new Point(startPoint.x + 58, startPoint.y + 40), colorText).leftAligned().color(0x3f3f3f).noShadow());
			widgets.add(Widgets.createLabel(new Point(startPoint.x + 58, startPoint.y + 50), amountText).leftAligned().color(0x3f3f3f).noShadow());
		}
		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 40;
	}

}
