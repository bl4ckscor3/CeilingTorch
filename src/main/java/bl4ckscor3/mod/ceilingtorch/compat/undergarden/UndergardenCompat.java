package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import quek.undergarden.registry.UGBlocks;

public class UndergardenCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<Block> SHARD_CEILING_TORCH = CeilingTorch.BLOCKS.register("undergarden_shard_torch", () -> new ShardCeilingTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(state -> 6)));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = Map.of(UGBlocks.SHARD_TORCH.getId(), SHARD_CEILING_TORCH.get());

		return placeEntries;
	}
}
