package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import pokecube.legends.init.BlockInit;

public class PokecubeAIOCompat implements ICeilingTorchCompat
{
	public static Block ultraCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ultraCeilingTorch = new UltraCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F)
				.noCollission())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "pokecube_legends_ultra_torch1")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(BlockInit.ULTRA_TORCH1.get().getRegistryName(), ultraCeilingTorch);

		return placeEntries;
	}
}
