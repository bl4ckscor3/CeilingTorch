package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;
import java.util.Random;

import com.sammy.malum.common.blocks.lighting.IColor;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CeilingEtherTorchBlock extends CeilingTorchBlock implements IColor, IWaterLoggable
{
	private final Color color;

	public CeilingEtherTorchBlock(Properties properties, Color color)
	{
		super(properties, null);

		setDefaultState(getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
		this.color = color;
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.get(BlockStateProperties.WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.WATERLOGGED);
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new CeilingLightingTileEntity();
	}

	@Override
	public Color getColor()
	{
		return color;
	}
}
