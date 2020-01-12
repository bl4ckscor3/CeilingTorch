package bl4ckscor3.mod.ceilingtorch.compat.druidcraft;

import java.util.List;
import java.util.Random;

import com.vulp.druidcraft.registry.BlockRegistry;
import com.vulp.druidcraft.registry.ItemRegistry;
import com.vulp.druidcraft.registry.ParticleRegistry;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FieryCeilingTorchBlock extends CeilingTorchBlock implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public FieryCeilingTorchBlock(Block.Properties properties)
	{
		super(properties);

		setDefaultState(stateContainer.getBaseState().with(FieryCeilingTorchBlock.WATERLOGGED, false));
	}

	@Override
	public void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FieryCeilingTorchBlock.WATERLOGGED);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos)
	{
		if(state.get(FieryCeilingTorchBlock.WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return super.updatePostPlacement(state, facing, state, world, pos, facingPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		IFluidState fluidState = context.getWorld().getFluidState(context.getPos());

		return super.getStateForPlacement(context).with(FieryCeilingTorchBlock.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	public Fluid pickupFluid(IWorld world, BlockPos pos, BlockState state)
	{
		if(state.get(FieryCeilingTorchBlock.WATERLOGGED))
		{
			world.setBlockState(pos, state.with(FieryCeilingTorchBlock.WATERLOGGED, false), 3);
			return Fluids.WATER;
		}

		return Fluids.EMPTY;
	}

	@Override
	public IFluidState getFluidState(BlockState state)
	{
		return state.get(FieryCeilingTorchBlock.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluid)
	{
		return !(boolean)state.get(FieryCeilingTorchBlock.WATERLOGGED) && fluid == Fluids.WATER;
	}

	@Override
	public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, IFluidState fluidState)
	{
		if(!(boolean)state.get(FieryCeilingTorchBlock.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER)
		{
			if(!world.isRemote())
			{
				world.setBlockState(pos, state.with(FieryCeilingTorchBlock.WATERLOGGED, true), 3);
				world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
			}

			return true;
		}

		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		if(world == null)
			return;

		tooltip.add(new TranslationTextComponent("block.druidcraft.fiery_torch.description1", new Object[0]));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double d0 = pos.getX() + 0.5D;
		double d2 = pos.getY() + 0.7D;
		double d3 = pos.getZ() + 0.5D;
		float limit = 0.05F;
		float offset0 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));
		float offset2 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));
		float offset3 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));

		world.addParticle(ParticleRegistry.fiery_spark, false, d0 + offset0, d2 + offset2 - 0.3D, d3 + offset3, 0.0, 0.0, 0.0);
		world.addParticle(ParticleRegistry.fiery_glow, false, d0, d2 - 0.3D, d3, 0.0, 0.0, 0.0);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return BlockRegistry.fiery_torch.getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ItemRegistry.fiery_torch);
	}
}
