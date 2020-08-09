package bl4ckscor3.mod.ceilingtorch.compat.dwarfcoal;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import sora.dwarfcoal.init.ModItems;

public class DwarfCoalCompat implements ICeilingTorchCompat
{
	public static Block dwarfTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(dwarfTorch = new DwarfCeilingTorchBlock(Block.Properties.from(Blocks.TORCH).lightValue(10)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_dwarf_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ModItems.DWARF_TORCH.getRegistryName(), dwarfTorch);

		return placeEntries;
	}
}
