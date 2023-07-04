package de.dafuqs.spectrum.compat.REI;

import de.dafuqs.revelationary.api.advancements.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.recipe.*;
import me.shedaniel.rei.api.common.display.basic.*;
import me.shedaniel.rei.api.common.entry.*;
import me.shedaniel.rei.api.common.util.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public abstract class GatedSpectrumDisplay extends BasicDisplay implements GatedRecipeDisplay {
	
	private final Identifier requiredAdvancementIdentifier;
	private final boolean secret;
	
	public GatedSpectrumDisplay(GatedRecipe recipe, List<EntryIngredient> inputs, ItemStack output) {
		this(recipe, inputs, Collections.singletonList(EntryIngredients.of(output)));
	}
	
	public GatedSpectrumDisplay(GatedRecipe recipe, List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
		super(inputs, outputs);
		this.secret = recipe.isSecret();
		this.requiredAdvancementIdentifier = recipe.getRequiredAdvancementIdentifier();
	}
	
	public GatedSpectrumDisplay(Identifier requiredAdvancementIdentifier, boolean secret, List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
		super(inputs, outputs);
		this.secret = secret;
		this.requiredAdvancementIdentifier = requiredAdvancementIdentifier;
	}
	
	@Override
	public List<EntryIngredient> getInputEntries() {
		if (this.isUnlocked()) {
			return super.getInputEntries();
		} else {
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<EntryIngredient> getOutputEntries() {
		if (this.isUnlocked() || SpectrumCommon.CONFIG.REIListsRecipesAsNotUnlocked) {
			return super.getOutputEntries();
		} else {
			return new ArrayList<>();
		}
	}
	
	@Override
	public boolean isUnlocked() {
		return AdvancementHelper.hasAdvancement(MinecraftClient.getInstance().player, this.requiredAdvancementIdentifier);
	}
	
	@Override
	public boolean isSecret() {
		return secret;
	}
	
}