package de.dafuqs.spectrum.mixin.compat.healthoverlay.present;

import de.dafuqs.spectrum.config.*;
import de.dafuqs.spectrum.mixin.accessors.*;
import de.dafuqs.spectrum.render.*;
import net.fabricmc.api.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.hud.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import terrails.healthoverlay.config.*;
import terrails.healthoverlay.render.*;

@Environment(EnvType.CLIENT)
@Mixin(HeartRenderer.class)
public abstract class InGameHudMixin {

	// Execute the display after HealthOverlay did its own, to avoid conflict
	@Inject(method = "renderPlayerHearts", at = @At("TAIL"), remap = false, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private void renderPlayerHeartsAzureDikeInjector(MatrixStack poseStack, PlayerEntity player, CallbackInfo ci) {
		// Get config for
        CompatibilitySettingAccessors.INSTANCE.register(
                "healthoverlay", Boolean.class,
                "absorptionOverHealth", Configuration.ABSORPTION.renderOverHealth.get());

		InGameHud hud = MinecraftClient.getInstance().inGameHud;
		int scaledWidth = ((InGameHudAccessor) hud).getWidth();
		int scaledHeight = ((InGameHudAccessor) hud).getHeight();
		HudRenderers.renderAzureDike(poseStack, scaledWidth, scaledHeight, player);
	}
}