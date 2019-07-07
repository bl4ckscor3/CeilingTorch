package bl4ckscor3.mod.ceilingtorch;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCeilingTorch extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	protected static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645D, 0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
	protected static final AxisAlignedBB CEILING_AABB = new AxisAlignedBB(0.4000000059604645D, 1.0D - 0.6000000238418579D, 0.4000000059604645D, 0.6000000238418579D, 1.0D, 0.6000000238418579D);
	protected static final AxisAlignedBB TORCH_NORTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.699999988079071D, 0.6499999761581421D, 0.800000011920929D, 1.0D);
	protected static final AxisAlignedBB TORCH_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.0D, 0.6499999761581421D, 0.800000011920929D, 0.30000001192092896D);
	protected static final AxisAlignedBB TORCH_WEST_AABB = new AxisAlignedBB(0.699999988079071D, 0.20000000298023224D, 0.3499999940395355D, 1.0D, 0.800000011920929D, 0.6499999761581421D);
	protected static final AxisAlignedBB TORCH_EAST_AABB = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 0.800000011920929D, 0.6499999761581421D);

	protected BlockCeilingTorch()
	{
		super(Material.CIRCUITS);

		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setHardness(0.0F);
		setLightLevel(0.9375F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch(state.getValue(FACING))
		{
			case EAST:
				return TORCH_EAST_AABB;
			case WEST:
				return TORCH_WEST_AABB;
			case SOUTH:
				return TORCH_SOUTH_AABB;
			case NORTH:
				return TORCH_NORTH_AABB;
			case DOWN:
				return CEILING_AABB;
			default:
				return STANDING_AABB;
		}
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

	private boolean canPlaceOn(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		return state.getBlock().canPlaceTorchOnTop(state, world, pos);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		for(EnumFacing facing : FACING.getAllowedValues())
		{
			if(canPlaceAt(world, pos, facing))
				return true;
		}

		return false;
	}

	private boolean canPlaceAt(World world, BlockPos pos, EnumFacing facing)
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
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		if(canPlaceAt(world, pos, facing))
			return getDefaultState().withProperty(FACING, facing);
		else
		{
			for(EnumFacing horizontalFacing : EnumFacing.Plane.HORIZONTAL)
			{
				if(canPlaceAt(world, pos, horizontalFacing))
					return getDefaultState().withProperty(FACING, horizontalFacing);
			}

			return getDefaultState();
		}
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

	protected boolean onNeighborChangeInternal(World world, BlockPos pos, IBlockState state)
	{
		if(!checkForDrop(world, pos, state))
			return true;
		else
		{
			EnumFacing facing = state.getValue(FACING);
			EnumFacing.Axis axis = facing.getAxis();
			EnumFacing oppositeFacing = facing.getOpposite();
			BlockPos oppositePos = pos.offset(oppositeFacing);
			boolean flag = false;

			if(axis.isHorizontal() && world.getBlockState(oppositePos).getBlockFaceShape(world, oppositePos, facing) != BlockFaceShape.SOLID)
				flag = true;
			else if(axis.isVertical() && !this.canPlaceOn(world, oppositePos))
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

	protected boolean checkForDrop(World world, BlockPos pos, IBlockState state)
	{
		if(state.getBlock() == this && canPlaceAt(world, pos, state.getValue(FACING)))
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
		EnumFacing facing = state.getValue(FACING);
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;

		if(facing.getAxis().isHorizontal())
		{
			EnumFacing oppositeFacing = facing.getOpposite();

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + 0.27D * oppositeFacing.getXOffset(), y + 0.22D, z + 0.27D * oppositeFacing.getZOffset(), 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, x + 0.27D * oppositeFacing.getXOffset(), y + 0.22D, z + 0.27D * oppositeFacing.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else
		{
			double offset = facing == EnumFacing.DOWN ? -0.25D : 0.0D;

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y + offset, z, 0.0D, 0.0D, 0.0D);
			world.spawnParticle(EnumParticleTypes.FLAME, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState state = getDefaultState();

		switch(meta)
		{
			case 1: return state.withProperty(FACING, EnumFacing.EAST);
			case 2: return state.withProperty(FACING, EnumFacing.WEST);
			case 3: return state.withProperty(FACING, EnumFacing.SOUTH);
			case 4: return state.withProperty(FACING, EnumFacing.NORTH);
			case 6: return state.withProperty(FACING, EnumFacing.DOWN);
			default: return state.withProperty(FACING, EnumFacing.UP);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		switch(state.getValue(FACING))
		{
			case EAST:
				i = i | 1;
				break;
			case WEST:
				i = i | 2;
				break;
			case SOUTH:
				i = i | 3;
				break;
			case NORTH:
				i = i | 4;
				break;
			case DOWN:
				i = i | 6;
				break;
			case UP:
				i = i | 5;
		}

		return i;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror)
	{
		return state.withRotation(mirror.toRotation(state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
}