package de.dafuqs.spectrum.compat.REI.plugins;

import com.google.common.collect.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.compat.REI.*;
import de.dafuqs.spectrum.registries.*;
import me.shedaniel.math.*;
import me.shedaniel.rei.api.client.gui.*;
import me.shedaniel.rei.api.client.gui.widgets.*;
import me.shedaniel.rei.api.client.registry.display.*;
import me.shedaniel.rei.api.common.category.*;
import me.shedaniel.rei.api.common.entry.*;
import me.shedaniel.rei.api.common.util.*;
import net.fabricmc.api.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class EnchanterCategory implements DisplayCategory<EnchanterDisplay> {

	public final static Identifier BACKGROUND_TEXTURE = SpectrumCommon.locate("textures/gui/container/enchanter.png");
	public static final EntryIngredient ENCHANTER = EntryIngredients.of(SpectrumBlocks.ENCHANTER);

	@Override
	public CategoryIdentifier getCategoryIdentifier() {
		return SpectrumPlugins.ENCHANTER;
	}

	@Override
	public Text getTitle() {
		return Text.translatable("container.spectrum.rei.enchanting.title");
	}

	@Override
	public Renderer getIcon() {
		return EntryStacks.of(SpectrumBlocks.ENCHANTER);
	}

	@Override
	public List<Widget> setupDisplay(@NotNull EnchanterDisplay display, @NotNull Rectangle bounds) {
		Point startPoint = new Point(bounds.getCenterX() - 58 - 7, bounds.getCenterY() - 49);
		List<Widget> widgets = Lists.newArrayList();

		widgets.add(Widgets.createRecipeBase(bounds));

		if (!display.isUnlocked()) {
			widgets.add(Widgets.createLabel(new Point(startPoint.x, startPoint.y + 38), Text.translatable("container.spectrum.rei.pedestal_crafting.recipe_not_unlocked_line_1")).leftAligned().color(0x3f3f3f).noShadow());
			widgets.add(Widgets.createLabel(new Point(startPoint.x, startPoint.y + 48), Text.translatable("container.spectrum.rei.pedestal_crafting.recipe_not_unlocked_line_2")).leftAligned().color(0x3f3f3f).noShadow());
		} else {
			// enchanter structure background					            destinationX	 destinationY   sourceX, sourceY, width, height
			widgets.add(Widgets.createTexturedWidget(BACKGROUND_TEXTURE, startPoint.x + 12, startPoint.y + 21, 0, 0, 54, 54));

			// Knowledge Gem and Enchanter
			List<EntryIngredient> inputs = display.getInputEntries();
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 111, startPoint.y + 14)).markInput().entries(inputs.get(9)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 111, startPoint.y + 60)).entries(ENCHANTER).disableBackground());

			// center input slot
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 31, startPoint.y + 40)).markInput().entries(inputs.get(0)));

			// surrounding input slots
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 9)).markInput().entries(inputs.get(1)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 44, startPoint.y + 9)).markInput().entries(inputs.get(2)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 62, startPoint.y + 27)).markInput().entries(inputs.get(3)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 62, startPoint.y + 53)).markInput().entries(inputs.get(4)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 44, startPoint.y + 71)).markInput().entries(inputs.get(5)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 18, startPoint.y + 71)).markInput().entries(inputs.get(6)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 53)).markInput().entries(inputs.get(7)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y + 27)).markInput().entries(inputs.get(8)));

			// output arrow and slot
			List<EntryIngredient> output = display.getOutputEntries();
			widgets.add(Widgets.createArrow(new Point(startPoint.x + 80, startPoint.y + 40)).animationDurationTicks(display.craftingTime));
			widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 111, startPoint.y + 40)));
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 111, startPoint.y + 40)).markOutput().disableBackground().entries(output.get(0)));

			// duration and XP requirements
			// special handling for "1 second". Looks nicer
			Text text;
			if (display.craftingTime == 20) {
				text = Text.translatable("container.spectrum.rei.enchanting.crafting_time_one_second", 1);
			} else {
				text = Text.translatable("container.spectrum.rei.enchanting.crafting_time", (display.craftingTime / 20));
			}
			widgets.add(Widgets.createLabel(new Point(startPoint.x + 70, startPoint.y + 85), text).leftAligned().color(0x3f3f3f).noShadow());
		}
		return widgets;
	}

	@Override
	public int getDisplayHeight() {
		return 100;
	}

}
