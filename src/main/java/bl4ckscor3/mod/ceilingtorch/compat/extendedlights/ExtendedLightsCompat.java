package bl4ckscor3.mod.ceilingtorch.compat.extendedlights;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.polyvalord.extlights.blocks.RegBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class ExtendedLightsCompat implements ICeilingTorchCompat
{
	public static Block modernCeilingTorchWhite;
	public static Block modernCeilingTorchBlack;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(modernCeilingTorchWhite = new ModernCeilingTorchBlock(Block.Properties.create(Material.IRON)
				.hardnessAndResistance(1.0F)
				.lightValue(15)
				.sound(SoundType.METAL),
				() -> RegBlocks.light_modern_ground_torch_white).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_white")));
		event.getRegistry().register(modernCeilingTorchBlack = new ModernCeilingTorchBlock(Block.Properties.create(Material.IRON)
				.hardnessAndResistance(1.0F)
				.lightValue(15)
				.sound(SoundType.METAL),
				() -> RegBlocks.light_modern_ground_torch_black).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_black")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(RegBlocks.light_modern_ground_torch_white.getRegistryName(), modernCeilingTorchWhite,
					RegBlocks.light_modern_ground_torch_black.getRegistryName(), modernCeilingTorchBlack);
		}

		return placeEntries;
	}
}
