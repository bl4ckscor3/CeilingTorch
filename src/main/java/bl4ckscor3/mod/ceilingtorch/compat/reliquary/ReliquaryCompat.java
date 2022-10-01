package bl4ckscor3.mod.ceilingtorch.compat.reliquary;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent.Register;
import reliquary.init.ModBlocks;

public class ReliquaryCompat implements ICeilingTorchCompat
{
	public static Block interdictionCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(interdictionCeilingTorch = new InterdictionCeilingTorchBlock().setRegistryName("reliquary_interdiction_torch"));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ModBlocks.INTERDICTION_TORCH.get().getRegistryName(), interdictionCeilingTorch);

		return placeEntries;
	}
}
