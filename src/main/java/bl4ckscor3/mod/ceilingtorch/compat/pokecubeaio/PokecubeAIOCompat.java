package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import pokecube.legends.init.BlockInit;

public class PokecubeAIOCompat implements ICeilingTorchCompat
{
	public static Block infectedCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(infectedCeilingTorch = new InfectedCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel(state -> 10)
				.sound(SoundType.WOOD))
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "pokecube_legends_infected_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(BlockInit.INFECTED_TORCH.get().getRegistryName(), infectedCeilingTorch);

		return placeEntries;
	}
}
