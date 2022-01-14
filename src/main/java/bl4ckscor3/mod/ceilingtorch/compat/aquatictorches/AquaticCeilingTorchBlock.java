package bl4ckscor3.mod.ceilingtorch.compat.aquatictorches;

import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import realmayus.aquatictorches.AquaticTorchBlock;

public class AquaticCeilingTorchBlock extends CeilingTorchBlock implements SimpleWaterloggedBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty FLOWING_WATER = AquaticTorchBlock.FLOWING_WATER;

	public AquaticCeilingTorchBlock(Properties properties, ParticleOptions particleOptions, Supplier<Block> originalBlock)
	{
		super(properties, particleOptions, originalBlock);
		registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
		boolean isFlowing = fluidState.getType() == Fluids.FLOWING_WATER;
		boolean isWater = fluidState.getType() == Fluids.WATER || isFlowing;

		return super.getStateForPlacement(ctx).setValue(WATERLOGGED, isWater).setValue(FLOWING_WATER, isFlowing ? fluidState.getAmount() : 8);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.getValue(WATERLOGGED))
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

		return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		if(state.getValue(AquaticTorchBlock.WATERLOGGED))
		{
			if(state.getValue(AquaticTorchBlock.FLOWING_WATER) == 8)
				return Fluids.WATER.getSource(false);
			else
				return Fluids.WATER.getFlowing(state.getValue(AquaticTorchBlock.FLOWING_WATER), false);
		}

		return super.getFluidState(state);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random random)
	{
		super.animateTick(blockState, level, pos, random);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block,BlockState> builder)
	{
		builder.add(WATERLOGGED, FLOWING_WATER);
	}
}
