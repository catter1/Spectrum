package de.dafuqs.spectrum.progression;

import de.dafuqs.spectrum.SpectrumCommon;
import de.dafuqs.spectrum.Support;
import de.dafuqs.spectrum.enums.PedestalRecipeTier;
import de.dafuqs.spectrum.recipe.pedestal.PedestalCraftingRecipe;
import de.dafuqs.spectrum.toast.RecipeToast;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class ClientPedestalRecipeToastManager {

    public static final HashMap<Identifier, List<PedestalCraftingRecipe>> unlockablePedestalRecipes = new HashMap<>();

    public static void registerUnlockablePedestalRecipe(PedestalCraftingRecipe recipe) {
        List<Identifier> requiredAdvancementIdentifiers = recipe.getRequiredAdvancementIdentifiers();
        if(requiredAdvancementIdentifiers.size() > 0) {
            for(Identifier requiredAdvancementIdentifier : requiredAdvancementIdentifiers) {
                if (unlockablePedestalRecipes.containsKey(requiredAdvancementIdentifier)) {
                    if(!unlockablePedestalRecipes.get(requiredAdvancementIdentifier).contains(recipe)) {
                        unlockablePedestalRecipes.get(requiredAdvancementIdentifier).add(recipe);
                    }
                } else {
                    List<PedestalCraftingRecipe> recipes = new ArrayList<>();
                    recipes.add(recipe);
                    unlockablePedestalRecipes.put(requiredAdvancementIdentifier, recipes);
                }
            }
        }
    }

    public static void process(List<Identifier> doneAdvancements, boolean showToast) {
        if(showToast) {
            for (Identifier doneAdvancement : doneAdvancements) {
                showToastsForAllRecipesWithAdvancement(doneAdvancement);
            }
        }
    }

    /**
     *
     * @param advancementIdentifier The advancement the player got
     */
    private static void showToastsForAllRecipesWithAdvancement(Identifier advancementIdentifier) {
        if (unlockablePedestalRecipes.containsKey(advancementIdentifier)) {
            for (PedestalCraftingRecipe unlockedRecipe : unlockablePedestalRecipes.get(advancementIdentifier)) {
                if (unlockedRecipe.shouldShowToastOnUnlock() && unlockedRecipe.canPlayerCraft(MinecraftClient.getInstance().player)) {
                    RecipeToast.showRecipeToast(MinecraftClient.getInstance(), new ItemStack(unlockedRecipe.getOutput().getItem()));
                }
            }
        }

        Optional<PedestalRecipeTier> newlyUnlockedRecipeTier = hasJustUnlockedANewRecipeTier(advancementIdentifier);
        if(newlyUnlockedRecipeTier.isPresent()) {
            showToastsForAllNewlyUnlockedRecipesForTier(newlyUnlockedRecipeTier.get());
        }
    }

    /**
     * When the player upgraded their pedestal and built the new structure
     * show toasts for all recipes that he already meets the requirements for
     * @param pedestalRecipeTier The new pedestal recipe tier the player unlocked
     */
    private static void showToastsForAllNewlyUnlockedRecipesForTier(PedestalRecipeTier pedestalRecipeTier) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        List<PedestalCraftingRecipe> alreadyUnlockedRecipesAtNewTier = new ArrayList<>();
        for(List<PedestalCraftingRecipe> recipes : unlockablePedestalRecipes.values()) {
            for(PedestalCraftingRecipe recipe : recipes) {
                if(recipe.getTier() == pedestalRecipeTier
                        && recipe.shouldShowToastOnUnlock()
                        && !alreadyUnlockedRecipesAtNewTier.contains(recipe)
                        && recipe.hasUnlockedRequiredAdvancements(player)) {

                    alreadyUnlockedRecipesAtNewTier.add(recipe);
                    RecipeToast.showRecipeToast(MinecraftClient.getInstance(), new ItemStack(recipe.getOutput().getItem()));
                }
            }
        }
    }

    private static Optional<PedestalRecipeTier> hasJustUnlockedANewRecipeTier(@NotNull Identifier advancementIdentifier) {
        if(advancementIdentifier.equals(new Identifier(SpectrumCommon.MOD_ID, "place_pedestal"))) {
            return Optional.of(PedestalRecipeTier.BASIC);
        } else if(advancementIdentifier.equals(new Identifier(SpectrumCommon.MOD_ID, "build_basic_pedestal_structure"))) {
            return Optional.of(PedestalRecipeTier.SIMPLE);
        } else if(advancementIdentifier.equals(new Identifier(SpectrumCommon.MOD_ID, "build_advanced_pedestal_structure"))) {
            return Optional.of(PedestalRecipeTier.ADVANCED);
        } else if(advancementIdentifier.equals(new Identifier(SpectrumCommon.MOD_ID, "build_complex_pedestal_structure"))) {
            return Optional.of(PedestalRecipeTier.COMPLEX);
        }
        return Optional.empty();
    }

}