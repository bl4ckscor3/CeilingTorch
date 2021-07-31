package bl4ckscor3.mod.ceilingtorch.compat.bambooblocks;

import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;

public class BambooCeilingTorchBlock extends CeilingTorchBlock
{
	protected static final VoxelShape SHAPE = Block.box(5.5D, 2.0D, 5.5D, 10.5D, 16.0D, 10.5D);

	public BambooCeilingTorchBlock(Block.Properties properties, ParticleOptions particleData, Supplier<Block> originalBlock)
	{
		super(properties, particleData, originalBlock);
	}

	@Override
	public OffsetType getOffsetType()
	{
		return OffsetType.XZ;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		Vec3 offset = state.getOffset(world, pos);

		return SHAPE.move(offset.x, offset.y, offset.z);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		return canSupportCenter(world, pos.above(), Direction.DOWN) || world.getBlockState(pos.above()).getBlock() instanceof LeavesBlock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		Vec3 offset = state.getOffset(world, pos);
		double x = pos.getX() + 0.5D + offset.x;
		double y = pos.getY() + 0.2D + offset.y;
		double z = pos.getZ() + 0.5D + offset.z;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
