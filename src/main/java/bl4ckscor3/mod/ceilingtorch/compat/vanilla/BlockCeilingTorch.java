package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCeilingTorch extends Block
{
	protected static final AxisAlignedBB CEILING_AABB = new AxisAlignedBB(0.4000000059604645D, 1.0D - 0.6000000238418579D, 0.4000000059604645D, 0.6000000238418579D, 1.0D, 0.6000000238418579D);

	public BlockCeilingTorch()
	{
		super(Material.CIRCUITS);

		setTickRandomly(true);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setHardness(0.0F);
		setLightLevel(0.9375F);
		setSoundType(SoundType.WOOD);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.TORCH);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;

		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y - 0.25D, z, 0.0D, 0.0D, 0.0D);
		world.spawnParticle(EnumParticleTypes.FLAME, x, y - 0.25D, z, 0.0D, 0.0D, 0.0D);
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
}