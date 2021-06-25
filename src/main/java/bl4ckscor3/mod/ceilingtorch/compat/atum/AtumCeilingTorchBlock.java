package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.function.Supplier;

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

public class AtumCeilingTorchBlock extends CeilingTorchBlock
{
	private final Supplier<Block> originalBlock;

	public AtumCeilingTorchBlock(Supplier<Block> originalBlock)
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD));

		this.originalBlock = originalBlock;
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return originalBlock.get().getRegistryName();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(originalBlock.get());
	}
}
