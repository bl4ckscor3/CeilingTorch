package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import knightminer.inspirations.utility.InspirationsUtility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;

public class InspirationsCompat implements ICeilingTorchCompat
{
	public static Block ceilingTorchLever;
	public static Block ceilingSoulTorchLever;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ceilingTorchLever = new CeilingTorchLeverBlock(Block.Properties.from(Blocks.TORCH).lootFrom(() -> InspirationsUtility.torchLeverFloor), ParticleTypes.FLAME) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(InspirationsUtility.torchLeverItem);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "inspirations_torch_lever")));
		event.getRegistry().register(ceilingSoulTorchLever = new CeilingTorchLeverBlock(Block.Properties.from(Blocks.SOUL_TORCH).lootFrom(() -> InspirationsUtility.soulLeverFloor), ParticleTypes.SOUL_FIRE_FLAME) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(InspirationsUtility.soulLeverItem);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "inspirations_soul_torch_lever")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(InspirationsUtility.torchLeverItem.getRegistryName(), ceilingTorchLever,
					InspirationsUtility.soulLeverItem.getRegistryName(), ceilingSoulTorchLever);
		}

		return placeEntries;
	}
}
