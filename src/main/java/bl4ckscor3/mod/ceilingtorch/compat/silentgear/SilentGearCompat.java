package bl4ckscor3.mod.ceilingtorch.compat.silentgear;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
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
import net.minecraftforge.event.RegistryEvent.Register;
import net.silentchaos512.gear.init.ModBlocks;

public class SilentGearCompat implements ICeilingTorchCompat
{
	public static Block stoneCeilingTorch;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(stoneCeilingTorch = new BlockCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0).lightValue(14).sound(SoundType.STONE)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return ModBlocks.STONE_TORCH.asBlock().getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(ModBlocks.STONE_TORCH.asItem());
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "silentgear_stone_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(ModBlocks.STONE_TORCH.asItem().getRegistryName(), stoneCeilingTorch);
	}
}
