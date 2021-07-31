package bl4ckscor3.mod.ceilingtorch.compat.extendedlights;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.polyvalord.extlights.blocks.RegBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

public class ExtendedLightsCompat implements ICeilingTorchCompat
{
	public static Block modernCeilingTorchWhite;
	public static Block modernCeilingTorchBlack;
	public static Block glowstoneCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(modernCeilingTorchWhite = new ModernCeilingTorchBlock(Block.Properties.of(Material.METAL)
				.strength(1.0F)
				.lightLevel(state -> 15)
				.sound(SoundType.METAL),
				() -> RegBlocks.light_modern_ground_torch_white).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_white")));
		event.getRegistry().register(modernCeilingTorchBlack = new ModernCeilingTorchBlock(Block.Properties.of(Material.METAL)
				.strength(1.0F)
				.lightLevel(state -> 15)
				.sound(SoundType.METAL),
				() -> RegBlocks.light_modern_ground_torch_black).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_black")));
		event.getRegistry().register(glowstoneCeilingTorch = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F)
				.noCollission()
				.lightLevel(state -> 14)
				.sound(SoundType.WOOD),
				null, () -> RegBlocks.light_torch_glowstone) {
			@Override
			public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_glowstone_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(RegBlocks.light_modern_ground_torch_white.getRegistryName(), modernCeilingTorchWhite,
					RegBlocks.light_modern_ground_torch_black.getRegistryName(), modernCeilingTorchBlack,
					RegBlocks.light_torch_glowstone.getRegistryName(), glowstoneCeilingTorch);
		}

		return placeEntries;
	}
}
