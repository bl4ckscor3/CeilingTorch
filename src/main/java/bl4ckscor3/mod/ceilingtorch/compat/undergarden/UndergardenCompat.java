package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import quek.undergarden.registry.UGBlocks;

public class UndergardenCompat implements ICeilingTorchCompat {
	public static Block shardCeilingTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(shardCeilingTorch = new ShardCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH)
				.lightLevel(state -> 6))
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "undergarden_shard_torch")));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(UGBlocks.SHARD_TORCH.get().getRegistryName(), shardCeilingTorch);

		return placeEntries;
	}
}
