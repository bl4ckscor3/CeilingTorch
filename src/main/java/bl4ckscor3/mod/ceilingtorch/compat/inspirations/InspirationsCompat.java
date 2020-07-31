package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import knightminer.inspirations.utility.InspirationsUtility;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class InspirationsCompat implements ICeilingTorchCompat
{
	public static Block ceilingTorchLever;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ceilingTorchLever = new CeilingTorchLeverBlock().setRegistryName(new ResourceLocation(CeilingTorch.MODID, "inspirations_torch_lever")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		System.out.println(InspirationsUtility.torchLeverFloor.getRegistryName());
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(InspirationsUtility.torchLeverFloor.getRegistryName(), ceilingTorchLever);

		return placeEntries;
	}
}
