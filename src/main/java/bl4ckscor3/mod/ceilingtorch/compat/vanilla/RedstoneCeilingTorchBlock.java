package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedstoneCeilingTorchBlock extends RedstoneTorchBlock
{
	protected RedstoneCeilingTorchBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return hasEnoughSolidSide(world, pos.up(), Direction.DOWN);
	}

	@Override
	public int getWeakPower(BlockState state, IBlockReader access, BlockPos pos, Direction side)
	{
		return state.get(LIT) && Direction.DOWN != side ? 15 : 0;
	}

	@Override
	public int getStrongPower(BlockState state, IBlockReader access, BlockPos pos, Direction side)
	{
		return side == Direction.UP ? state.getWeakPower(access, pos, side) : 0;
	}

	@Override
	protected boolean shouldBeOff(World world, BlockPos pos, BlockState state)
	{
		return world.isSidePowered(pos.up(), Direction.UP);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(state.get(LIT))
		{
			double x = pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double y = pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D;
			double z = pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;

			world.addParticle(RedstoneParticleData.REDSTONE_DUST, x, y - 0.25D, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(Items.REDSTONE_TORCH);
	}
}
