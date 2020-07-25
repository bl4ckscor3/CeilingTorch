package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class JellyCeilingTorchBlock extends BlockJellyTorch
{
	protected final JellyTorchType torchType;

	public JellyCeilingTorchBlock(Properties properties, JellyTorchType torchType)
	{
		super(properties, torchType);

		this.torchType = torchType;
		setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.get(WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : state;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return hasEnoughSolidSide(world, pos.up(), Direction.DOWN);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		switch(torchType)
		{
			default: case PINK:
				return UABlocks.PINK_JELLY_TORCH.get().getLootTable();
			case PURPLE:
				return UABlocks.PURPLE_JELLY_TORCH.get().getLootTable();
			case BLUE:
				return UABlocks.BLUE_JELLY_TORCH.get().getLootTable();
			case GREEN:
				return UABlocks.GREEN_JELLY_TORCH.get().getLootTable();
			case YELLOW:
				return UABlocks.YELLOW_JELLY_TORCH.get().getLootTable();
			case ORANGE:
				return UABlocks.ORANGE_JELLY_TORCH.get().getLootTable();
			case RED:
				return UABlocks.RED_JELLY_TORCH.get().getLootTable();
			case WHITE:
				return UABlocks.WHITE_JELLY_TORCH.get().getLootTable();
		}
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		switch(torchType)
		{
			default: case PINK:
				return new ItemStack(UABlocks.PINK_JELLY_TORCH.get());
			case PURPLE:
				return new ItemStack(UABlocks.PURPLE_JELLY_TORCH.get());
			case BLUE:
				return new ItemStack(UABlocks.BLUE_JELLY_TORCH.get());
			case GREEN:
				return new ItemStack(UABlocks.GREEN_JELLY_TORCH.get());
			case YELLOW:
				return new ItemStack(UABlocks.YELLOW_JELLY_TORCH.get());
			case ORANGE:
				return new ItemStack(UABlocks.ORANGE_JELLY_TORCH.get());
			case RED:
				return new ItemStack(UABlocks.RED_JELLY_TORCH.get());
			case WHITE:
				return new ItemStack(UABlocks.WHITE_JELLY_TORCH.get());
		}
	}
}
