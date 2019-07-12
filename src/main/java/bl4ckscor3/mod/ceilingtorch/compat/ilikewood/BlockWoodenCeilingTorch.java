package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
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
import yamahari.ilikewood.blocks.torch.WoodenTorchBlock;
import yamahari.ilikewood.util.WoodType;

public class BlockWoodenCeilingTorch extends WoodenTorchBlock
{
	protected static final VoxelShape CEILING_SHAPE = Block.makeCuboidShape(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);

	public BlockWoodenCeilingTorch(WoodType woodType)
	{
		super(woodType);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return func_220055_a(world, pos.up(), Direction.DOWN);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.1D;
		double d2 = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
