package bl4ckscor3.mod.ceilingtorch.compat.inspirations;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import knightminer.inspirations.utility.block.TorchLeverBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CeilingTorchLeverBlock extends TorchLeverBlock
{
	private static final DirectionProperty SWING = DirectionProperty.create("swing", dir -> dir != Direction.DOWN);

	public CeilingTorchLeverBlock(Properties properties, IParticleData particleData)
	{
		super(properties, particleData);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
	{
		return canSupportCenter(world, pos.above(), Direction.DOWN);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		Direction swing = state.getValue(SWING);
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offsetX = 0.0D;
		double offsetY = -0.25D;
		double offsetZ = 0.0D;

		if(swing != Direction.UP)
		{
			offsetX = 0.23D * swing.getStepX();
			offsetY += 0.05D;
			offsetZ = 0.23D * swing.getStepZ();
		}

		world.addParticle(flameParticle, x + offsetX, y + offsetY, z + offsetZ, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.FLAME, x + offsetX, y + offsetY, z + offsetZ, 0.0D, 0.0D, 0.0D);
	}
}
