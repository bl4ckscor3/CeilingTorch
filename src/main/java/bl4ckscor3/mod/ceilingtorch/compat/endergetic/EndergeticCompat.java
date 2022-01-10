package bl4ckscor3.mod.ceilingtorch.compat.endergetic;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.endergetic.core.registry.EEBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class EndergeticCompat implements ICeilingTorchCompat
{
	public static Block enderCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(enderCeilingTorch = new EnderCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "endergetic_ender_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(EEBlocks.ENDER_TORCH.get().getRegistryName(), enderCeilingTorch);

		return placeEntries;
	}
}
