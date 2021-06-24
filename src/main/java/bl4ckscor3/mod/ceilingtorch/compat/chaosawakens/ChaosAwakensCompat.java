package bl4ckscor3.mod.ceilingtorch.compat.chaosawakens;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import io.github.chaosawakens.common.registry.CABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;

public class ChaosAwakensCompat implements ICeilingTorchCompat
{
	public static Block crystalCeilingTorch;
	public static Block sunstoneCeilingTorch;
	public static Block extremeCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(crystalCeilingTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).lootFrom(CABlocks.CRYSTAL_TORCH).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(CABlocks.CRYSTAL_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "chaosawakens_crystal_torch")));
		event.getRegistry().register(sunstoneCeilingTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).lootFrom(CABlocks.SUNSTONE_TORCH).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 12).sound(SoundType.WOOD), ParticleTypes.END_ROD) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(CABlocks.SUNSTONE_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "chaosawakens_sunstone_torch")));
		event.getRegistry().register(extremeCeilingTorch = new CeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).lootFrom(CABlocks.EXTREME_TORCH).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel(state -> 15).sound(SoundType.WOOD), ParticleTypes.FLAME) {
			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(CABlocks.EXTREME_TORCH.get());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "chaosawakens_extreme_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(CABlocks.CRYSTAL_TORCH.get().getRegistryName(), crystalCeilingTorch,
					CABlocks.SUNSTONE_TORCH.get().getRegistryName(), sunstoneCeilingTorch,
					CABlocks.EXTREME_TORCH.get().getRegistryName(), extremeCeilingTorch);
		}

		return placeEntries;
	}
}
