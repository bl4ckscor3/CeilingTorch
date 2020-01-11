package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockJellyCeilingTorch extends BlockCeilingTorch implements IWaterLoggable
{
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BlockJellyCeilingTorch(Properties properties, JellyTorchType torchType)
	{
		super(properties);

		this.torchType = torchType;
		setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.1D) : (Math.random() * 0.1D);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.1D) : (Math.random() * 0.1D);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.1D) : (Math.random() * 0.1D);
		double d0 = pos.getX() + 0.5D + xOffset;
		double d1 = pos.getY() + 0.5D + yOffset;
		double d2 = pos.getZ() + 0.5D + zOffset;

		world.addParticle(BlockJellyTorch.getTorchParticleType(torchType), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.get(WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return super.updatePostPlacement(state, facing, state, world, currentPos, facingPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		IFluidState fluidState = ctx.getWorld().getFluidState(ctx.getPos());

		return super.getStateForPlacement(ctx).with(WATERLOGGED, Boolean.valueOf(fluidState.getFluid() == Fluids.WATER));
	}

	@Override
	public Fluid pickupFluid(IWorld world, BlockPos pos, BlockState state)
	{
		if(state.get(WATERLOGGED))
		{
			world.setBlockState(pos, state.with(WATERLOGGED, false), 3);
			return Fluids.WATER;
		}
		else return Fluids.EMPTY;
	}

	@Override
	public IFluidState getFluidState(BlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluid)
	{
		return !state.get(WATERLOGGED) && fluid == Fluids.WATER;
	}

	@Override
	public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, IFluidState fluidState)
	{
		if(!state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER)
		{
			if(!world.isRemote())
			{
				world.setBlockState(pos, state.with(WATERLOGGED, true), 3);
				world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
			}

			return true;
		}
		else return false;
	}

	@Override
	public ResourceLocation getLootTable()
	{
		switch(torchType)
		{
			default: case PINK:
				return UABlocks.JELLY_TORCH_PINK.getLootTable();
			case PURPLE:
				return UABlocks.JELLY_TORCH_PURPLE.getLootTable();
			case BLUE:
				return UABlocks.JELLY_TORCH_BLUE.getLootTable();
			case GREEN:
				return UABlocks.JELLY_TORCH_GREEN.getLootTable();
			case YELLOW:
				return UABlocks.JELLY_TORCH_YELLOW.getLootTable();
			case ORANGE:
				return UABlocks.JELLY_TORCH_ORANGE.getLootTable();
			case RED:
				return UABlocks.JELLY_TORCH_RED.getLootTable();
			case WHITE:
				return UABlocks.JELLY_TORCH_WHITE.getLootTable();
		}
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		switch(torchType)
		{
			default: case PINK:
				return new ItemStack( UABlocks.JELLY_TORCH_PINK);
			case PURPLE:
				return new ItemStack( UABlocks.JELLY_TORCH_PURPLE);
			case BLUE:
				return new ItemStack( UABlocks.JELLY_TORCH_BLUE);
			case GREEN:
				return new ItemStack( UABlocks.JELLY_TORCH_GREEN);
			case YELLOW:
				return new ItemStack( UABlocks.JELLY_TORCH_YELLOW);
			case ORANGE:
				return new ItemStack( UABlocks.JELLY_TORCH_ORANGE);
			case RED:
				return new ItemStack( UABlocks.JELLY_TORCH_RED);
			case WHITE:
				return new ItemStack( UABlocks.JELLY_TORCH_WHITE);
		}
	}
}
