package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.Map;

import org.cyclops.integrateddynamics.RegistryEntries;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class IntegratedDynamicsCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<Block> MENRIL_CEILING_TORCH = CeilingTorch.BLOCKS.registerBlock("integrateddynamics_menril_torch", p -> new MenrilCeilingTorchBlock(p, RegistryEntries.BLOCK_MENRIL_TORCH), () -> getProperties(SoundType.WOOD));
	public static final DeferredBlock<Block> MENRIL_CEILING_TORCH_STONE = CeilingTorch.BLOCKS.registerBlock("integrateddynamics_menril_torch_stone", p -> new MenrilCeilingTorchBlock(p, RegistryEntries.BLOCK_MENRIL_TORCH_STONE), () -> getProperties(SoundType.STONE));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(RegistryEntries.ITEM_MENRIL_TORCH.getId(), MENRIL_CEILING_TORCH.get(),
					RegistryEntries.ITEM_MENRIL_TORCH_STONE.getId(), MENRIL_CEILING_TORCH_STONE.get());
			//@formatter:on
		}

		return placeEntries;
	}

	private static BlockBehaviour.Properties getProperties(SoundType soundType) {
		return BlockBehaviour.Properties.of().noCollision().strength(0.0F).lightLevel(state -> 14).sound(soundType);
	}
}
