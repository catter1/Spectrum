package de.dafuqs.spectrum.blocks.mob_blocks;

import net.minecraft.block.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.server.world.*;
import net.minecraft.text.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class LineTeleportingMobBlock extends MobBlock {
	
	protected final int range;
	
	public LineTeleportingMobBlock(Settings settings, ParticleEffect particleEffect, int range) {
		super(settings, particleEffect);
		this.range = range;
	}
	
	public static Direction getLookDirection(@NotNull Entity entity, boolean mirrorVertical, boolean mirrorHorizontal) {
		double pitch = entity.getPitch();
		if (pitch < -60) {
			return mirrorVertical ? Direction.UP : Direction.DOWN;
		} else if (pitch > 60) {
			return mirrorVertical ? Direction.DOWN : Direction.UP;
		} else {
			return mirrorHorizontal ? entity.getMovementDirection().getOpposite() : entity.getMovementDirection();
		}
	}
	
	public static Optional<BlockPos> searchForBlock(World world, BlockPos pos, BlockState searchedState, Direction direction, int range) {
		BlockPos.Mutable mutable = pos.mutableCopy();
		for (int i = 1; i < range; i++) {
			BlockPos currPos = mutable.offset(direction, i);
			if (world.getBlockState(currPos) == searchedState) {
				return Optional.of(currPos);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		super.appendTooltip(stack, world, tooltip, options);
		tooltip.add(Text.translatable("block.spectrum.line_teleporting_mob_block.tooltip", range));
		tooltip.add(Text.translatable("block.spectrum.line_teleporting_mob_block.tooltip2", range));
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (!world.isClient && !hasCooldown(state)) {
			if (trigger((ServerWorld) world, pos, state, entity, getLookDirection(entity, true, false).getOpposite())) { // we want the movement direction here, instead of only "top"
				playTriggerParticles((ServerWorld) world, pos);
				playTriggerSound(world, pos);
				triggerCooldown(world, pos);
			}
		}
	}
	
	@Override
	public boolean trigger(ServerWorld world, BlockPos blockPos, BlockState state, @Nullable Entity entity, Direction side) {
		if (entity != null) {
			Optional<BlockPos> foundBlockPos = searchForBlock(world, blockPos, state, side.getOpposite(), this.range);
			if (foundBlockPos.isPresent()) {
				BlockPos targetPos = foundBlockPos.get();
				triggerCooldown(world, targetPos);
				RandomTeleportingMobBlock.teleportTo(world, entity, targetPos);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getCooldownTicks() {
		return 10;
	}
	
}
