package bl4ckscor3.mod.ceilingtorch.compat.midnight;

import java.util.Map;

import com.crypticmushroom.minecraft.midnight.common.registry.MnBlocks;
import com.crypticmushroom.minecraft.midnight.common.registry.MnParticleTypes;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public class MidnightCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> NIGHTSHROOM_CEILING_SPORCH = CeilingTorch.BLOCKS.register(
		"midnight_nightshroom_sporch",
		() -> new CeilingSporchBlock(
			BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel(state -> 13),
			MnParticleTypes.SPORCH_NIGHTSHROOM,
			MnBlocks.NIGHTSHROOM_SPORCH
		)
	);
	public static final RegistryObject<Block> DEWSHROOM_CEILING_SPORCH = CeilingTorch.BLOCKS.register(
		"midnight_dewshroom_sporch",
		() -> new CeilingSporchBlock(
			BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel(state -> 13),
			MnParticleTypes.SPORCH_DEWSHROOM,
			MnBlocks.DEWSHROOM_SPORCH
		)
	);
	public static final RegistryObject<Block> VIRIDSHROOM_CEILING_SPORCH = CeilingTorch.BLOCKS.register(
		"midnight_viridshroom_sporch",
		() -> new CeilingSporchBlock(
			BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel(state -> 13),
			MnParticleTypes.SPORCH_VIRIDSHROOM,
			MnBlocks.VIRIDSHROOM_SPORCH
		)
	);
	public static final RegistryObject<Block> BOGSHROOM_CEILING_SPORCH = CeilingTorch.BLOCKS.register(
		"midnight_bogshroom_sporch",
		() -> new CeilingSporchBlock(
			BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel(state -> 13),
			MnParticleTypes.SPORCH_BOGSHROOM,
			MnBlocks.BOGSHROOM_SPORCH
		)
	);
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = ImmutableMap.of(
				getRegistryName(MnBlocks.NIGHTSHROOM_SPORCH.get()), NIGHTSHROOM_CEILING_SPORCH.get(),
				getRegistryName(MnBlocks.DEWSHROOM_SPORCH.get()), DEWSHROOM_CEILING_SPORCH.get(),
				getRegistryName(MnBlocks.VIRIDSHROOM_SPORCH.get()), VIRIDSHROOM_CEILING_SPORCH.get(),
				getRegistryName(MnBlocks.BOGSHROOM_SPORCH.get()), BOGSHROOM_CEILING_SPORCH.get()
			);
		}

		return placeEntries;
	}
}
