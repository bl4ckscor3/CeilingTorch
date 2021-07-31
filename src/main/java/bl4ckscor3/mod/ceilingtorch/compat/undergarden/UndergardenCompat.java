package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import quek.undergarden.registry.UGBlocks;

public class UndergardenCompat implements ICeilingTorchCompat
{
	public static Block shardCeilingTorch;
	public static final RegistryObject<BlockEntityType<?>> ETHER_CEILING_TORCH = CeilingTorch.TILE_ENTITIES.register("undergarden_shard_torch", () -> BlockEntityType.Builder.of(CeilingShardTorchTileEntity::new, shardCeilingTorch).build(null));
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(shardCeilingTorch = new ShardCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH)
				.lightLevel(state -> 6))
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "undergarden_shard_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(UGBlocks.SHARD_TORCH.get().getRegistryName(), shardCeilingTorch);

		return placeEntries;
	}
}
