package de.dafuqs.spectrum.registries;

import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.blocks.amphora.*;
import de.dafuqs.spectrum.blocks.block_flooder.*;
import de.dafuqs.spectrum.blocks.bottomless_bundle.*;
import de.dafuqs.spectrum.blocks.chests.*;
import de.dafuqs.spectrum.blocks.cinderhearth.*;
import de.dafuqs.spectrum.blocks.crystallarieum.*;
import de.dafuqs.spectrum.blocks.enchanter.*;
import de.dafuqs.spectrum.blocks.ender.*;
import de.dafuqs.spectrum.blocks.energy.*;
import de.dafuqs.spectrum.blocks.fusion_shrine.*;
import de.dafuqs.spectrum.blocks.item_bowl.*;
import de.dafuqs.spectrum.blocks.item_roundel.*;
import de.dafuqs.spectrum.blocks.jade_vines.*;
import de.dafuqs.spectrum.blocks.memory.*;
import de.dafuqs.spectrum.blocks.mob_head.*;
import de.dafuqs.spectrum.blocks.particle_spawner.*;
import de.dafuqs.spectrum.blocks.pastel_network.nodes.*;
import de.dafuqs.spectrum.blocks.pedestal.*;
import de.dafuqs.spectrum.blocks.potion_workshop.*;
import de.dafuqs.spectrum.blocks.present.*;
import de.dafuqs.spectrum.blocks.redstone.*;
import de.dafuqs.spectrum.blocks.shooting_star.*;
import de.dafuqs.spectrum.blocks.spirit_instiller.*;
import de.dafuqs.spectrum.blocks.spirit_sallow.*;
import de.dafuqs.spectrum.blocks.structure.*;
import de.dafuqs.spectrum.blocks.titration_barrel.*;
import de.dafuqs.spectrum.blocks.upgrade.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.event.client.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class SpectrumBlockEntities<T extends BlockEntity> {
	
	public static BlockEntityType<OminousSaplingBlockEntity> OMINOUS_SAPLING;
	public static BlockEntityType<PedestalBlockEntity> PEDESTAL;
	public static BlockEntityType<FusionShrineBlockEntity> FUSION_SHRINE;
	public static BlockEntityType<EnchanterBlockEntity> ENCHANTER;
	
	//public static BlockEntityType<StonesettingWorkshopBlockEntity> STONESETTING_WORKSHOP;
	public static BlockEntityType<ItemBowlBlockEntity> ITEM_BOWL;
	public static BlockEntityType<ItemRoundelBlockEntity> ITEM_ROUNDEL;
	public static BlockEntityType<PreservationRoundelBlockEntity> PRESERVATION_ROUNDEL;
	public static BlockEntityType<EnderDropperBlockEntity> ENDER_DROPPER;
	public static BlockEntityType<EnderHopperBlockEntity> ENDER_HOPPER;
	public static BlockEntityType<ParticleSpawnerBlockEntity> PARTICLE_SPAWNER;
	public static BlockEntityType<CreativeParticleSpawnerBlockEntity> CREATIVE_PARTICLE_SPAWNER;
	public static BlockEntityType<UpgradeBlockEntity> UPGRADE_BLOCK;
	public static BlockEntityType<SpectrumSkullBlockEntity> SKULL;
	public static BlockEntityType<ShootingStarBlockEntity> SHOOTING_STAR;
	public static BlockEntityType<BottomlessBundleBlockEntity> BOTTOMLESS_BUNDLE;
	public static BlockEntityType<PotionWorkshopBlockEntity> POTION_WORKSHOP;
	public static BlockEntityType<CrystallarieumBlockEntity> CRYSTALLARIEUM;
	public static BlockEntityType<CinderhearthBlockEntity> CINDERHEARTH;
	
	public static BlockEntityType<CrystalApothecaryBlockEntity> CRYSTAL_APOTHECARY;
	public static BlockEntityType<ColorPickerBlockEntity> COLOR_PICKER;
	
	public static BlockEntityType<CompactingChestBlockEntity> COMPACTING_CHEST;
	public static BlockEntityType<RestockingChestBlockEntity> RESTOCKING_CHEST;
	public static BlockEntityType<PrivateChestBlockEntity> PRIVATE_CHEST;
	public static BlockEntityType<SuckingChestBlockEntity> SUCKING_CHEST;
	public static BlockEntityType<TreasureChestBlockEntity> TREASURE_CHEST;
	public static BlockEntityType<AmphoraBlockEntity> AMPHORA;
	
	public static BlockEntityType<PlayerDetectorBlockEntity> PLAYER_DETECTOR;
	public static BlockEntityType<RedstoneCalculatorBlockEntity> REDSTONE_CALCULATOR;
	public static BlockEntityType<RedstoneWirelessBlockEntity> REDSTONE_WIRELESS;
	public static BlockEntityType<BlockPlacerBlockEntity> BLOCK_PLACER;
	public static BlockEntityType<BlockFlooderBlockEntity> BLOCK_FLOODER;
	public static BlockEntityType<SpiritInstillerBlockEntity> SPIRIT_INSTILLER;
	public static BlockEntityType<MemoryBlockEntity> MEMORY;
	public static BlockEntityType<JadeVineRootsBlockEntity> JADE_VINE_ROOTS;
	public static BlockEntityType<PresentBlockEntity> PRESENT;
	public static BlockEntityType<TitrationBarrelBlockEntity> TITRATION_BARREL;
	public static BlockEntityType<PastelNodeBlockEntity> PASTEL_NODE;

	public static BlockEntityType<PreservationControllerBlockEntity> PRESERVATION_CONTROLLER;

	private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, SpectrumCommon.locate(id), FabricBlockEntityTypeBuilder.create(factory, blocks).build());
	}

	public static void register() {
		OMINOUS_SAPLING = register("ominous_sapling_block_entity", OminousSaplingBlockEntity::new, SpectrumBlocks.OMINOUS_SAPLING);
		PEDESTAL = register("pedestal_block_entity", PedestalBlockEntity::new, SpectrumBlocks.PEDESTAL_BASIC_AMETHYST, SpectrumBlocks.PEDESTAL_BASIC_TOPAZ, SpectrumBlocks.PEDESTAL_BASIC_CITRINE, SpectrumBlocks.PEDESTAL_ALL_BASIC, SpectrumBlocks.PEDESTAL_ONYX, SpectrumBlocks.PEDESTAL_MOONSTONE);
		FUSION_SHRINE = register("fusion_shrine_block_entity", FusionShrineBlockEntity::new, SpectrumBlocks.FUSION_SHRINE_BASALT, SpectrumBlocks.FUSION_SHRINE_CALCITE);
		ENCHANTER = register("enchanter_block_entity", EnchanterBlockEntity::new, SpectrumBlocks.ENCHANTER);
		ITEM_BOWL = register("item_bowl_block_entity", ItemBowlBlockEntity::new, SpectrumBlocks.ITEM_BOWL_BASALT, SpectrumBlocks.ITEM_BOWL_CALCITE);
		ITEM_ROUNDEL = register("item_roundel", ItemRoundelBlockEntity::new, SpectrumBlocks.ITEM_ROUNDEL);
		PRESERVATION_ROUNDEL = register("preservation_roundel", PreservationRoundelBlockEntity::new, SpectrumBlocks.PRESERVATION_ROUNDEL);
		ENDER_DROPPER = register("ender_dropper", EnderDropperBlockEntity::new, SpectrumBlocks.ENDER_DROPPER);
		ENDER_HOPPER = register("ender_hopper", EnderHopperBlockEntity::new, SpectrumBlocks.ENDER_HOPPER);
		PARTICLE_SPAWNER = register("particle_spawner", ParticleSpawnerBlockEntity::new, SpectrumBlocks.PARTICLE_SPAWNER);
		CREATIVE_PARTICLE_SPAWNER = register("creative_particle_spawner", CreativeParticleSpawnerBlockEntity::new, SpectrumBlocks.CREATIVE_PARTICLE_SPAWNER);
		COMPACTING_CHEST = register("compacting_chest", CompactingChestBlockEntity::new, SpectrumBlocks.COMPACTING_CHEST);
		RESTOCKING_CHEST = register("restocking_chest", RestockingChestBlockEntity::new, SpectrumBlocks.RESTOCKING_CHEST);
		PRIVATE_CHEST = register("private_chest", PrivateChestBlockEntity::new, SpectrumBlocks.PRIVATE_CHEST);
		SUCKING_CHEST = register("sucking_chest", SuckingChestBlockEntity::new, SpectrumBlocks.SUCKING_CHEST);
		TREASURE_CHEST = register("treasure_chest", TreasureChestBlockEntity::new, SpectrumBlocks.TREASURE_CHEST);
		AMPHORA = register("amphora", AmphoraBlockEntity::new, SpectrumBlocks.CHESTNUT_NOXWOOD_AMPHORA, SpectrumBlocks.EBONY_NOXWOOD_AMPHORA, SpectrumBlocks.SLATE_NOXWOOD_AMPHORA, SpectrumBlocks.IVORY_NOXWOOD_AMPHORA);
		PLAYER_DETECTOR = register("player_detector", PlayerDetectorBlockEntity::new, SpectrumBlocks.PLAYER_DETECTOR);
		REDSTONE_CALCULATOR = register("redstone_calculator", RedstoneCalculatorBlockEntity::new, SpectrumBlocks.REDSTONE_CALCULATOR);
		REDSTONE_WIRELESS = register("redstone_wireless", RedstoneWirelessBlockEntity::new, SpectrumBlocks.REDSTONE_WIRELESS);
		BLOCK_PLACER = register("block_placer", BlockPlacerBlockEntity::new, SpectrumBlocks.BLOCK_PLACER);
		BLOCK_FLOODER = register("block_flooder", BlockFlooderBlockEntity::new, SpectrumBlocks.BLOCK_FLOODER);
		SHOOTING_STAR = register("shooting_star", ShootingStarBlockEntity::new, SpectrumBlocks.COLORFUL_SHOOTING_STAR, SpectrumBlocks.FIERY_SHOOTING_STAR, SpectrumBlocks.GEMSTONE_SHOOTING_STAR, SpectrumBlocks.GLISTERING_SHOOTING_STAR, SpectrumBlocks.PRISTINE_SHOOTING_STAR);
		BOTTOMLESS_BUNDLE = register("bottomless_bundle", BottomlessBundleBlockEntity::new, SpectrumBlocks.BOTTOMLESS_BUNDLE);
		POTION_WORKSHOP = register("potion_workshop", PotionWorkshopBlockEntity::new, SpectrumBlocks.POTION_WORKSHOP);
		SPIRIT_INSTILLER = register("spirit_instiller", SpiritInstillerBlockEntity::new, SpectrumBlocks.SPIRIT_INSTILLER);
		MEMORY = register("memory", MemoryBlockEntity::new, SpectrumBlocks.MEMORY);
		JADE_VINE_ROOTS = register("jade_vine_roots", JadeVineRootsBlockEntity::new, SpectrumBlocks.JADE_VINE_ROOTS);
		CRYSTALLARIEUM = register("crystallarieum", CrystallarieumBlockEntity::new, SpectrumBlocks.CRYSTALLARIEUM);
		CRYSTAL_APOTHECARY = register("crystal_apothecary", CrystalApothecaryBlockEntity::new, SpectrumBlocks.CRYSTAL_APOTHECARY);
		COLOR_PICKER = register("color_picker", ColorPickerBlockEntity::new, SpectrumBlocks.COLOR_PICKER);
		CINDERHEARTH = register("cinderhearth", CinderhearthBlockEntity::new, SpectrumBlocks.CINDERHEARTH);
		PRESENT = register("present", PresentBlockEntity::new, SpectrumBlocks.PRESENT);
		TITRATION_BARREL = register("titration_barrel", TitrationBarrelBlockEntity::new, SpectrumBlocks.TITRATION_BARREL);
        PASTEL_NODE = register("pastel_node", PastelNodeBlockEntity::new, SpectrumBlocks.CONNECTION_NODE, SpectrumBlocks.PROVIDER_NODE, SpectrumBlocks.STORAGE_NODE, SpectrumBlocks.SENDER_NODE, SpectrumBlocks.GATHER_NODE);
        PRESERVATION_CONTROLLER = register("preservation_controller", PreservationControllerBlockEntity::new, SpectrumBlocks.PRESERVATION_CONTROLLER);

		// All the upgrades
		List<Block> upgradeBlocksList = UpgradeBlock.getUpgradeBlocks();
		Block[] upgradeBlocksArray = new Block[upgradeBlocksList.size()];
		upgradeBlocksArray = upgradeBlocksList.toArray(upgradeBlocksArray);
		UPGRADE_BLOCK = register("upgrade_block", UpgradeBlockEntity::new, upgradeBlocksArray);

		// All the skulls
		List<Block> skullBlocksList = new ArrayList<>();
		skullBlocksList.addAll(SpectrumBlocks.getMobHeads());
		skullBlocksList.addAll(SpectrumBlocks.getMobWallHeads());
		
		Block[] skullBlocksArray = new Block[skullBlocksList.size()];
		skullBlocksArray = skullBlocksList.toArray(skullBlocksArray);
		SKULL = register("skull", SpectrumSkullBlockEntity::new, skullBlocksArray);
	}
	
	public static void registerClient() {
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.PEDESTAL, PedestalBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.PRIVATE_CHEST, PrivateChestBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.COMPACTING_CHEST, CompactingChestBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.RESTOCKING_CHEST, RestockingChestBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.TREASURE_CHEST, SpectrumChestBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.SUCKING_CHEST, SuckingChestBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.UPGRADE_BLOCK, UpgradeBlockBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.FUSION_SHRINE, FusionShrineBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.ENCHANTER, EnchanterBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.ITEM_BOWL, ItemBowlBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.ITEM_ROUNDEL, ItemRoundelBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.PRESERVATION_ROUNDEL, ItemRoundelBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.SKULL, SpectrumSkullBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.SPIRIT_INSTILLER, SpiritInstillerBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.JADE_VINE_ROOTS, JadeVineRootsBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.CRYSTALLARIEUM, CrystallarieumBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.COLOR_PICKER, ColorPickerBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(SpectrumBlockEntities.PASTEL_NODE, PastelNodeBlockEntityRenderer::new);

		registerTextureAtlasCallback();
	}
	
	private static void registerTextureAtlasCallback() {
		// textures that are only referenced in code have to be added to the texture atlas manually
		
		//Register textures in chest atlas
		ClientSpriteRegistryCallback.event(TexturedRenderLayers.CHEST_ATLAS_TEXTURE).register((texture, registry) -> {
			registry.register(SpectrumCommon.locate("entity/private_chest"));
			registry.register(SpectrumCommon.locate("entity/treasure_chest"));
		});
		
		//Register textures in block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((texture, registry) -> {
			registry.register(SpectrumCommon.locate("entity/pedestal_upgrade_speed"));
		});
	}
	
}
