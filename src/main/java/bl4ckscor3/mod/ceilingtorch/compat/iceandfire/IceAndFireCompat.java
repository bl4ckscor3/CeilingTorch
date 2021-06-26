package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Map;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class IceAndFireCompat implements ICeilingTorchCompat
{
	public static Block burntCeilingTorch;
	public static Block dreadCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(burntCeilingTorch = new BurntCeilingTorchBlock(Block.Properties.create(Material.WOOD)
				.lightValue(0)
				.sound(SoundType.WOOD)
				.notSolid()
				.variableOpacity())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ice_and_fire_burnt_torch")));
		event.getRegistry().register(dreadCeilingTorch = new DreadCeilingTorchBlock(Block.Properties.create(Material.WOOD)
				.lightValue(7)
				.sound(SoundType.STONE)
				.notSolid()
				.variableOpacity())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ice_and_fire_dread_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(IafBlockRegistry.BURNT_TORCH.getRegistryName(), burntCeilingTorch,
					IafBlockRegistry.DREAD_TORCH.getRegistryName(), dreadCeilingTorch);
		}

		return placeEntries;
	}
}
