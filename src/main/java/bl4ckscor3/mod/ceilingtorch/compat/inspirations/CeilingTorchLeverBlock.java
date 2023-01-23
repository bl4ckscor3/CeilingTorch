package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import knightminer.inspirations.utility.block.TorchLeverBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CeilingTorchLeverBlock extends TorchLeverBlock {
	private static final DirectionProperty SWING = DirectionProperty.create("swing", dir -> dir != Direction.DOWN);

	public CeilingTorchLeverBlock(Properties properties, ParticleOptions particleData) {
		super(properties, particleData);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.UP && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.above(), Direction.DOWN);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		Direction swing = state.getValue(SWING);
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offsetX = 0.0D;
		double offsetY = -0.25D;
		double offsetZ = 0.0D;

		if (swing != Direction.UP) {
			offsetX = 0.23D * swing.getStepX();
			offsetY += 0.05D;
			offsetZ = 0.23D * swing.getStepZ();
		}

		level.addParticle(flameParticle, x + offsetX, y + offsetY, z + offsetZ, 0.0D, 0.0D, 0.0D);
		level.addParticle(ParticleTypes.SMOKE, x + offsetX, y + offsetY, z + offsetZ, 0.0D, 0.0D, 0.0D);
	}
}
