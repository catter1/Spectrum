package de.dafuqs.spectrum.status_effects;

import de.dafuqs.spectrum.helpers.*;
import de.dafuqs.spectrum.items.trinkets.*;
import de.dafuqs.spectrum.networking.*;
import de.dafuqs.spectrum.particle.*;
import de.dafuqs.spectrum.progression.*;
import de.dafuqs.spectrum.registries.*;
import net.fabricmc.loader.api.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.*;
import net.minecraft.server.network.*;

public class DivinityStatusEffect extends SpectrumStatusEffect {
	
	public DivinityStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
		super(statusEffectCategory, color);
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity player) {
			if(!player.world.isClient) {
				SpectrumAdvancementCriteria.DIVINITY_TICK.trigger((ServerPlayerEntity) player);
			}
			player.getHungerManager().add(1, 0.25F);
		}
		if (entity.getHealth() < entity.getMaxHealth()) {
			entity.heal(amplifier / 2F);
		}
		if (entity.world.isClient) {
			if (entity.world.getTime() % 4 == 0) {
				ParticleHelper.playParticleWithPatternAndVelocityClient(entity.world, entity.getPos(), SpectrumParticleTypes.RED_CRAFTING, ParticlePattern.EIGHT, 0.2);
			}
		} else {
			WhispyCircletItem.removeSingleHarmfulStatusEffect(entity);
		}
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onApplied(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity) {
			if (entity instanceof ServerPlayerEntity player && entity.getStatusEffect(SpectrumStatusEffects.DIVINITY) == null) {
				SpectrumS2CPacketSender.playDivinityAppliedEffects(player);
			} else if (entity.world.isClient) {
				FabricLoader.getInstance().getObjectShare().put("healthoverlay:forceHardcoreHearts", true);
			}
		}
	}

	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity && entity.world.isClient) {
			FabricLoader.getInstance().getObjectShare().put("healthoverlay:forceHardcoreHearts", false);
		}
	}

}