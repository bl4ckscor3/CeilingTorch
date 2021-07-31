package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;
import java.util.Random;
import java.util.function.Supplier;

import com.sammy.malum.common.blocks.lighting.IColor;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CeilingEtherTorchBlock extends CeilingTorchBlock implements IColor, SimpleWaterloggedBlock
{
	private final Color color;

	public CeilingEtherTorchBlock(Properties properties, Color color, Supplier<Block> originalBlock)
	{
		super(properties, null, originalBlock);

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
		this.color = color;
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.getValue(BlockStateProperties.WATERLOGGED))
			world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.WATERLOGGED);
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public BlockEntity createTileEntity(BlockState state, BlockGetter world)
	{
		return new CeilingLightingTileEntity();
	}

	@Override
	public Color getColor()
	{
		return color;
	}
}
