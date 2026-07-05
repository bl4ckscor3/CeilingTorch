package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import knightminer.inspirations.utility.block.TorchLeverBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CeilingTorchLeverBlock extends TorchLeverBlock {
	private static final DirectionProperty SWING = DirectionProperty.create("swing", dir -> dir != Direction.DOWN);

	public CeilingTorchLeverBlock(BlockBehaviour.Properties properties, ParticleOptions particles) {
		super(properties, particles);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	private boolean isPowered(BlockState state) {
		return state.getValue(SWING) != Direction.UP;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.UP && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.above(), Direction.DOWN);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		Direction swing = state.getValue(SWING);
		double x = pos.getX() + 0.5;
		double y = pos.getY() + 0.7;
		double z = pos.getZ() + 0.5;
		double offsetX = 0.0;
		double offsetY = -0.25;
		double offsetZ = 0.0;

		if (isPowered(state)) {
			offsetX = 0.23 * swing.getStepX();
			offsetY += 0.05;
			offsetZ = 0.23 * swing.getStepZ();
		}

		world.addParticle(flameParticle, x + offsetX, y + offsetY, z + offsetZ, 0.0, 0.0, 0.0);
		world.addParticle(ParticleTypes.SMOKE, x + offsetX, y + offsetY, z + offsetZ, 0.0, 0.0, 0.0);

	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return isPowered(state) && side == Direction.UP ? 15 : 0;
	}
}
