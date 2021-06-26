package bl4ckscor3.mod.ceilingtorch.compat.bambooblocks;

import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BambooCeilingTorchBlock extends CeilingTorchBlock
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.5D, 2.0D, 5.5D, 10.5D, 16.0D, 10.5D);

	public BambooCeilingTorchBlock(Block.Properties properties, IParticleData particleData, Supplier<Block> originalBlock)
	{
		super(properties, particleData, originalBlock);
	}

	@Override
	public OffsetType getOffsetType()
	{
		return OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		Vector3d offset = state.getOffset(world, pos);

		return SHAPE.withOffset(offset.x, offset.y, offset.z);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return hasEnoughSolidSide(world, pos.up(), Direction.DOWN) || world.getBlockState(pos.up()).getBlock() instanceof LeavesBlock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		Vector3d offset = state.getOffset(world, pos);
		double x = pos.getX() + 0.5D + offset.x;
		double y = pos.getY() + 0.2D + offset.y;
		double z = pos.getZ() + 0.5D + offset.z;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(particleData, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
