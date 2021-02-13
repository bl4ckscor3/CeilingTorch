package bl4ckscor3.mod.ceilingtorch.compat.wildnature;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.matez.wildnature.common.registry.particles.ParticleRegistry;
import com.matez.wildnature.util.lists.WNBlocks;
import com.matez.wildnature.util.lists.WNItems;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.RedstoneCeilingTorchBlock;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent.Register;

public class WildNatureCompat implements ICeilingTorchCompat
{
	public static Block ceilingDungeonTorch;
	public static Block ceilingCrystalTorch;
	public static Block ceilingDungeonRedstoneTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		//dungeon torch
		event.getRegistry().register(ceilingDungeonTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).sound(SoundType.LANTERN).lightValue(12)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return WNBlocks.DUNGEON_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(WNItems.DUNGEON_TORCH);
			}
		}.setRegistryName("wildnature_dungeon_torch"));
		//crystal torch
		event.getRegistry().register(ceilingCrystalTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).sound(SoundType.LANTERN).lightValue(15)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
			{
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.15D;
				double z = pos.getZ() + 0.5D;

				world.addParticle(ParticleRegistry.CRYSTAL_SPARK, x, y, z, 0.0D, 0.01D, 0.0D);
			}

			@Override
			public ResourceLocation getLootTable()
			{
				return WNBlocks.CRYSTAL_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(WNItems.CRYSTAL_TORCH);
			}
		}.setRegistryName("wildnature_crystal_torch"));
		//dungeon redstone torch
		event.getRegistry().register(ceilingDungeonRedstoneTorch = new RedstoneCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).sound(SoundType.LANTERN).lightValue(6)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return WNBlocks.DUNGEON_REDSTONE_TORCH.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(WNItems.DUNGEON_REDSTONE_TORCH);
			}
		}.setRegistryName("wildnature_dungeon_redstone_torch"));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(
					WNItems.DUNGEON_TORCH.getRegistryName(), ceilingDungeonTorch,
					WNItems.CRYSTAL_TORCH.getRegistryName(), ceilingCrystalTorch,
					WNItems.DUNGEON_REDSTONE_TORCH.getRegistryName(), ceilingDungeonRedstoneTorch);
		}

		return placeEntries;
	}
}