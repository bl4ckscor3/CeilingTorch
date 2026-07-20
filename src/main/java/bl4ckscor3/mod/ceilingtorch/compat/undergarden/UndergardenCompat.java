package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import quek.undergarden.registry.UGBlocks;

public class UndergardenCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<Block> SHARD_CEILING_TORCH = CeilingTorch.BLOCKS.registerBlock("undergarden_shard_torch", ShardCeilingTorchBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(_ -> 6));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = Map.of(UGBlocks.SHARD_TORCH.getId(), SHARD_CEILING_TORCH.get());

		return placeEntries;
	}
}
