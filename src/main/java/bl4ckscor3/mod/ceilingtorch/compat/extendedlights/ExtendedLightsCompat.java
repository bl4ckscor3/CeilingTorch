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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
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
		event.getRegistry().register(modernCeilingTorchWhite = new ModernCeilingTorchBlock(Block.Properties.create(Material.IRON).lootFrom(() -> RegBlocks.light_modern_ground_torch_white).hardnessAndResistance(1.0F).setLightLevel(state -> 15).sound(SoundType.METAL)) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(RegBlocks.light_modern_ground_torch_white);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_white")));
		event.getRegistry().register(modernCeilingTorchBlack = new ModernCeilingTorchBlock(Block.Properties.create(Material.IRON).lootFrom(() -> RegBlocks.light_modern_ground_torch_black).hardnessAndResistance(1.0F).setLightLevel(state -> 15).sound(SoundType.METAL)) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(RegBlocks.light_modern_ground_torch_black);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "extended_lights_modern_torch_black")));
		event.getRegistry().register(glowstoneCeilingTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).lootFrom(() -> RegBlocks.light_torch_glowstone).hardnessAndResistance(0.0F).doesNotBlockMovement().setLightLevel(state -> 14).sound(SoundType.WOOD), null) {
			@Override
			public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(RegBlocks.light_torch_glowstone);
			}
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
