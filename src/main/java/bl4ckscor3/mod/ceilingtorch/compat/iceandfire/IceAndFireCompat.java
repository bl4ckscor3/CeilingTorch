package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Map;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class IceAndFireCompat implements ICeilingTorchCompat
{
	public static Block burntCeilingTorch;
	public static Block dreadCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(burntCeilingTorch = new BurntCeilingTorchBlock(Block.Properties.of(Material.WOOD)
				.lightLevel(state -> 0)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.dynamicShape())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ice_and_fire_burnt_torch")));
		event.getRegistry().register(dreadCeilingTorch = new DreadCeilingTorchBlock(Block.Properties.of(Material.WOOD)
				.lightLevel(state -> 7)
				.sound(SoundType.STONE)
				.noOcclusion()
				.dynamicShape())
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
