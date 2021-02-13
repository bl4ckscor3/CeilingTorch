package bl4ckscor3.mod.ceilingtorch.compat.druidcraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.vulp.druidcraft.registry.ItemRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class DruidcraftCompat implements ICeilingTorchCompat
{
	public static Block fieryCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(fieryCeilingTorch = new FieryCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).setLightLevel(state -> 15).sound(SoundType.BAMBOO)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "druidcraft_fiery_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ItemRegistry.fiery_torch.getRegistryName(), fieryCeilingTorch);

		return placeEntries;
	}
}
