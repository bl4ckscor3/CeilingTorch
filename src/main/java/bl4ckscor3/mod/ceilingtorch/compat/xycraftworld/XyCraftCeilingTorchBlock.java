package bl4ckscor3.mod.ceilingtorch.compat.xycraftworld;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class XyCraftCeilingTorchBlock extends CeilingTorchBlock implements SimpleWaterloggedBlock {
	private final ParticleOptions particleData;

	public XyCraftCeilingTorchBlock(Properties properties, ParticleOptions particleData, Supplier<? extends Block> originalBlock) {
		super(properties, ParticleTypes.SOUL_FIRE_FLAME, originalBlock);

		this.particleData = particleData;
		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
		if (state.getValue(BlockStateProperties.WATERLOGGED))
			tickAccess.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

		return super.updateShape(state, level, tickAccess, currentPos, facing, facingPos, facingState, random);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!(random.nextFloat() > 0.4F)) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(particleData, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
}
