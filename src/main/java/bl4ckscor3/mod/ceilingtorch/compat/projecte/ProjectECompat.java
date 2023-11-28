package bl4ckscor3.mod.ceilingtorch.compat.projecte;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import moze_intel.projecte.gameObjs.block_entities.InterdictionTorchBlockEntity;
import moze_intel.projecte.gameObjs.registries.PEBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.RegistryObject;

public class ProjectECompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<Block> INTERDICTION_CEILING_TORCH = CeilingTorch.BLOCKS.register("projecte_interdiction_torch", () -> new InterdictionCeilingTorchBlock(BlockBehaviour.Properties.of()
			.pushReaction(PushReaction.DESTROY)
			.noCollission()
			.instabreak()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.randomTicks(), ParticleTypes.SOUL_FIRE_FLAME, () -> PEBlocks.INTERDICTION_TORCH.getBlock()));
	//@formatter:on
	public static final RegistryObject<BlockEntityType<? extends InterdictionTorchBlockEntity>> INTERDICTION_CEILING_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("projecte_interdiction_torch", () -> BlockEntityType.Builder.of(InterdictionCeilingTorchBlockEntity::new, INTERDICTION_CEILING_TORCH.get()).build(null));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(PEBlocks.INTERDICTION_TORCH.getBlock()), INTERDICTION_CEILING_TORCH.get());

		return placeEntries;
	}
}
