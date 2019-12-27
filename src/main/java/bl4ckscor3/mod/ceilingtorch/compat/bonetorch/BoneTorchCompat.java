package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import com.builtbroken.bonetorch.BoneTorchMod;

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
import net.minecraftforge.event.RegistryEvent;

public class BoneTorchCompat implements ICeilingTorchCompat
{
	public static Block ceilingBoneTorch;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ceilingBoneTorch = new BlockCeilingTorch(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD)) {
			@Override
			public ResourceLocation getLootTable()
			{
				return BoneTorchMod.blockTorch.getLootTable();
			}

			@Override
			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
			{
				return new ItemStack(BoneTorchMod.blockTorch);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bonetorch_bonetorch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(new ResourceLocation("bonetorch", "bonetorch"), ceilingBoneTorch);
	}
}
