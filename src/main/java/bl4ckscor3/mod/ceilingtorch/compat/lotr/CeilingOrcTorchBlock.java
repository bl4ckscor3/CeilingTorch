package bl4ckscor3.mod.ceilingtorch.compat.lotr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import lotr.common.init.LOTRItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class CeilingOrcTorchBlock extends Block
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final VoxelShape UPPER_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape LOWER_SHAPE = Block.makeCuboidShape(7.0D, 7.0D, 7.0D, 9.0D, 16.0D, 9.0D);

	public CeilingOrcTorchBlock(Block.Properties properties)
	{
		super(properties);
		setDefaultState(stateContainer.getBaseState().with(HALF, DoubleBlockHalf.UPPER));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return state.get(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE : UPPER_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		DoubleBlockHalf half = state.get(HALF);

		if(facing.getAxis() != Axis.Y || half == DoubleBlockHalf.UPPER != (facing == Direction.DOWN) || facingState.getBlock() == this && facingState.get(HALF) != half)
		{
			if(half == DoubleBlockHalf.UPPER && facing == Direction.UP && !state.isValidPosition(world, currentPos))
				return Blocks.AIR.getDefaultState();

			if(state.isValidPosition(world, currentPos))
				return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		}

		return Blocks.AIR.getDefaultState();
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockPos pos = ctx.getPos();

		return pos.getY() > 0 && ctx.getWorld().getBlockState(pos.down()).isReplaceable(ctx) ? super.getStateForPlacement(ctx) : null;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		if(state.get(HALF) == DoubleBlockHalf.LOWER)
		{
			if(state.getBlock() != this)
				return true;
			else
			{
				BlockState aboveState = world.getBlockState(pos.up());

				return aboveState.getBlock() == this && aboveState.get(HALF) == DoubleBlockHalf.UPPER;
			}
		}
		else
			return hasEnoughSolidSide(world, pos.up(), Direction.DOWN);
	}

	@Override
	public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack)
	{
		super.harvestBlock(world, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{
		DoubleBlockHalf half = state.get(HALF);
		BlockPos otherHalfPos = half == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
		BlockState otherHalfState = world.getBlockState(otherHalfPos);

		if(otherHalfState.getBlock() == this && otherHalfState.get(HALF) != half)
		{
			world.setBlockState(otherHalfPos, Blocks.AIR.getDefaultState(), 35);
			world.playEvent(player, 2001, otherHalfPos, Block.getStateId(otherHalfState));

			if(!world.isRemote && !player.isCreative())
			{
				spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());
				spawnDrops(otherHalfState, world, otherHalfPos, null, player, player.getHeldItemMainhand());
			}
		}

		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(state.get(HALF) == DoubleBlockHalf.LOWER)
		{
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void fillStateContainer(Builder<Block,BlockState> builder)
	{
		builder.add(HALF);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		return state.get(HALF) == DoubleBlockHalf.LOWER ? Arrays.asList(new ItemStack(LOTRItems.ORC_TORCH.get())) : new ArrayList<>();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(LOTRItems.ORC_TORCH.get());
	}
}