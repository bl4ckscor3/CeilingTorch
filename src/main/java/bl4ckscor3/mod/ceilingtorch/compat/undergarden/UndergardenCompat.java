package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.RegistryObject;
import quek.undergarden.registry.UGBlocks;

public class UndergardenCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> SHARD_CEILING_TORCH = CeilingTorch.BLOCKS.register("undergarden_shard_torch", () -> new ShardCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH).lightLevel(state -> 6)));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(UGBlocks.SHARD_TORCH.get()), SHARD_CEILING_TORCH.get());

		return placeEntries;
	}
}
