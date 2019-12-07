package bl4ckscor3.mod.ceilingtorch.compat.primalcore;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import nmd.primal.core.api.PrimalAPI;
import nmd.primal.core.common.blocks.lighting.Torch;
import nmd.primal.core.common.init.ModConfig;

public class PrimalCeilingTorch extends Torch
{
	protected static final AxisAlignedBB CEILING_AABB = new AxisAlignedBB(0.4000000059604645D, 1.0D - 0.6000000238418579D, 0.4000000059604645D, 0.6000000238418579D, 1.0D, 0.6000000238418579D);

	public PrimalCeilingTorch(float light, boolean rateFlag, boolean canExpire, SoundType sound)
	{
		super(light, rateFlag ? ModConfig.Flammability.TORCH_ENTITY_BURN : 60, canExpire, sound);
	}

	@Override
	public boolean doPickup(World world, BlockPos pos, IBlockState state, EnumFacing facing, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = new ItemStack(getItemDropped(state, RANDOM, 0));

		if(world.setBlockToAir(pos))
		{
			ItemHandlerHelper.giveItemToPlayer(player, stack);
			return true;
		}
		else return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return CEILING_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	private static boolean canPlaceOn(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		return state.getBlock().canPlaceTorchOnTop(state, world, pos);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return canPlaceAt(world, pos, EnumFacing.DOWN);
	}

	public static boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing)
	{
		BlockPos oppositePos = pos.offset(facing.getOpposite());
		IBlockState oppositeState = world.getBlockState(oppositePos);
		Block block = oppositeState.getBlock();
		BlockFaceShape bfs = oppositeState.getBlockFaceShape(world, oppositePos, facing);

		if(facing.equals(EnumFacing.UP) && canPlaceOn(world, oppositePos))
			return true;
		else if(facing != EnumFacing.UP)
			return !isExceptBlockForAttachWithPiston(block) && bfs == BlockFaceShape.SOLID;
		else return false;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(LIT) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(LIT, meta == 1);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		checkForDrop(world, pos, state);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		onNeighborChangeInternal(world, pos, state);
	}

	@Override
	protected boolean onNeighborChangeInternal(World world, BlockPos pos, IBlockState state)
	{
		if(!checkForDrop(world, pos, state))
			return true;
		else
		{
			EnumFacing facing = EnumFacing.DOWN;
			EnumFacing.Axis axis = facing.getAxis();
			EnumFacing oppositeFacing = facing.getOpposite();
			BlockPos oppositePos = pos.offset(oppositeFacing);
			boolean flag = false;

			if(axis.isHorizontal() && world.getBlockState(oppositePos).getBlockFaceShape(world, oppositePos, facing) != BlockFaceShape.SOLID)
				flag = true;
			else if(axis.isVertical() && !canPlaceOn(world, oppositePos))
				flag = true;

			if(flag)
			{
				dropBlockAsItem(world, pos, state, 0);
				world.setBlockToAir(pos);
				return true;
			}
			else return false;
		}
	}

	@Override
	protected boolean checkForDrop(World world, BlockPos pos, IBlockState state)
	{
		if(state.getBlock() == this && canPlaceAt(world, pos, EnumFacing.DOWN))
			return true;
		else
		{
			if(world.getBlockState(pos).getBlock() == this)
			{
				dropBlockAsItem(world, pos, state, 0);
				world.setBlockToAir(pos);
			}

			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if(isLit(world, pos, state))
		{
			final EnumParticleTypes smoke = world.isRainingAt(pos) ? EnumParticleTypes.SMOKE_LARGE : EnumParticleTypes.SMOKE_NORMAL;
			final double d0 = pos.getX() + 0.5;
			final double d2 = pos.getY() + 0.3;
			final double d3 = pos.getZ() + 0.5;

			world.spawnParticle(smoke, d0, d2, d3, 0.0, 0.0, 0.0, new int[0]);
			world.spawnParticle(EnumParticleTypes.FLAME, d0, d2, d3, 0.0, 0.0, 0.0, new int[0]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	public static class Wood extends PrimalCeilingTorch
	{
		public Wood(float light, boolean rateFlag, boolean canExpire, SoundType sound)
		{
			super(light, rateFlag, canExpire, sound);
		}

		@Override
		public ItemStack getItem(World world, BlockPos pos, IBlockState state)
		{
			return new ItemStack(PrimalAPI.Blocks.TORCH_WOOD);
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune)
		{
			return Item.getItemFromBlock(PrimalAPI.Blocks.TORCH_WOOD);
		}
	}

	public static class Nether extends PrimalCeilingTorch
	{
		public Nether(float light, boolean rateFlag, boolean canExpire, SoundType sound)
		{
			super(light, rateFlag, canExpire, sound);
		}

		@Override
		public ItemStack getItem(World world, BlockPos pos, IBlockState state)
		{
			return new ItemStack(PrimalAPI.Blocks.TORCH_NETHER);
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune)
		{
			return state.getValue(LIT) ? PrimalAPI.Items.TORCH_NETHER_LIT : Item.getItemFromBlock(PrimalAPI.Blocks.TORCH_NETHER);
		}
	}
}
