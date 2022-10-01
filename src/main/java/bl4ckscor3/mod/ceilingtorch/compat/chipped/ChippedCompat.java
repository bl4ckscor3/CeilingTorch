package bl4ckscor3.mod.ceilingtorch.compat.chipped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.grimbo.chipped.block.ChippedBlockTypes;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.RedstoneCeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.RegistryObject;

public class ChippedCompat implements ICeilingTorchCompat
{
	public static final List<CeilingTorchBlock> SUPPORTED_TORCHES = new ArrayList<>();
	public static final List<RedstoneCeilingTorchBlock> SUPPORTED_REDSTONE_TORCHES = new ArrayList<>();
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(RegistryObject<TorchBlock> block : ChippedBlockTypes.TORCHES)
		{
			CeilingTorchBlock ceilingBlock = new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME, block);

			ceilingBlock.setRegistryName(block.getId().toString().replace(":", "_"));
			event.getRegistry().register(ceilingBlock);
			SUPPORTED_TORCHES.add(ceilingBlock);
		}

		for(RegistryObject<RedstoneTorchBlock> block : ChippedBlockTypes.REDSTONE_TORCHES)
		{
			RedstoneCeilingTorchBlock ceilingBlock = new RedstoneCeilingTorchBlock(Block.Properties.copy(Blocks.REDSTONE_TORCH), block);

			ceilingBlock.setRegistryName(block.getId().toString().replace(":", "_"));
			event.getRegistry().register(ceilingBlock);
			SUPPORTED_REDSTONE_TORCHES.add(ceilingBlock);
		}
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			ImmutableMap.Builder<ResourceLocation,Block> builder = ImmutableMap.builder();

			SUPPORTED_TORCHES.forEach(torch -> builder.put(torch.getOriginalBlock().getRegistryName(), torch));
			SUPPORTED_REDSTONE_TORCHES.forEach(torch -> builder.put(torch.getOriginalBlock().getRegistryName(), torch));
			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
