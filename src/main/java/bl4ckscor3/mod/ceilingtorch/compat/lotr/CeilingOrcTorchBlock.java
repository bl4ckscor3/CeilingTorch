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
	private static final VoxelShape UPPER_SHAPE = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
	private static final VoxelShape LOWER_SHAPE = Block.box(7.0D, 7.0D, 7.0D, 9.0D, 16.0D, 9.0D);

	public CeilingOrcTorchBlock(Block.Properties properties)
	{
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return state.getValue(HALF) == DoubleBlockHalf.LOWER ? LOWER_SHAPE : UPPER_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		DoubleBlockHalf half = state.getValue(HALF);

		if(facing.getAxis() != Axis.Y || half == DoubleBlockHalf.UPPER != (facing == Direction.DOWN) || facingState.getBlock() == this && facingState.getValue(HALF) != half)
		{
			if(half == DoubleBlockHalf.UPPER && facing == Direction.UP && !state.canSurvive(world, currentPos))
				return Blocks.AIR.defaultBlockState();

			if(state.canSurvive(world, currentPos))
				return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
		}

		return Blocks.AIR.defaultBlockState();
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockPos pos = ctx.getClickedPos();

		return pos.getY() > 0 && ctx.getLevel().getBlockState(pos.below()).canBeReplaced(ctx) ? super.getStateForPlacement(ctx) : null;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
	{
		if(state.getValue(HALF) == DoubleBlockHalf.UPPER)
		{
			if(canSupportCenter(world, pos.above(), Direction.DOWN))
			{
				BlockPos downPos = pos.below();
				BlockState stateBelow = world.getBlockState(downPos);

				return stateBelow.isAir(world, downPos);
			}
			else return false;
		}
		else
		{
			BlockState aboveState = world.getBlockState(pos.above());

			return aboveState.getBlock() == this && aboveState.getValue(HALF) == DoubleBlockHalf.UPPER;
		}
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack)
	{
		super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{
		DoubleBlockHalf half = state.getValue(HALF);
		BlockPos otherHalfPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState otherHalfState = world.getBlockState(otherHalfPos);

		if(otherHalfState.getBlock() == this && otherHalfState.getValue(HALF) != half)
		{
			world.setBlock(otherHalfPos, Blocks.AIR.defaultBlockState(), 35);
			world.levelEvent(player, 2001, otherHalfPos, Block.getId(otherHalfState));

			if(!world.isClientSide && !player.isCreative())
			{
				dropResources(state, world, pos, null, player, player.getMainHandItem());
				dropResources(otherHalfState, world, otherHalfPos, null, player, player.getMainHandItem());
			}
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(state.getValue(HALF) == DoubleBlockHalf.LOWER)
		{
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block,BlockState> builder)
	{
		builder.add(HALF);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		return state.getValue(HALF) == DoubleBlockHalf.LOWER ? Arrays.asList(new ItemStack(LOTRItems.ORC_TORCH.get())) : new ArrayList<>();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(LOTRItems.ORC_TORCH.get());
	}
}