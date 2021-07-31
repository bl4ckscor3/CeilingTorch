package bl4ckscor3.mod.ceilingtorch.compat.druidcraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.vulp.druidcraft.registry.BlockRegistry;
import com.vulp.druidcraft.registry.ItemRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class DruidcraftCompat implements ICeilingTorchCompat
{
	public static Block fieryCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(fieryCeilingTorch = new FieryCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.lootFrom(() -> BlockRegistry.fiery_torch)
				.noCollission()
				.strength(0.0F)
				.lightLevel(state -> 15)
				.sound(SoundType.BAMBOO)).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "druidcraft_fiery_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ItemRegistry.fiery_torch.getRegistryName(), fieryCeilingTorch);

		return placeEntries;
	}
}
