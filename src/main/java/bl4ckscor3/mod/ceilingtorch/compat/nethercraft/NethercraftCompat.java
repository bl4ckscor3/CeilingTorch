package bl4ckscor3.mod.ceilingtorch.compat.nethercraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.legacy.nethercraft.block.NetherBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent.Register;

public class NethercraftCompat implements ICeilingTorchCompat
{
	public static Block charcoalCeilingTorch;
	public static Block fouliteCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(charcoalCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return NetherBlocks.charcoal_torch.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(NetherBlocks.charcoal_torch);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "nethercraft_charcoal_torch")));
		event.getRegistry().register(fouliteCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return NetherBlocks.foulite_torch.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(NetherBlocks.foulite_torch);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "nethercraft_foulite_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(NetherBlocks.charcoal_torch.getRegistryName(), charcoalCeilingTorch, NetherBlocks.foulite_torch.getRegistryName(), fouliteCeilingTorch);

		return placeEntries;
	}
}
