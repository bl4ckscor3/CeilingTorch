package bl4ckscor3.mod.ceilingtorch;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRedstoneCeilingTorch extends BlockCeilingTorch
{
	private static final Map<World, List<BlockRedstoneCeilingTorch.Toggle>> toggles = new java.util.WeakHashMap<World, List<Toggle>>(); // FORGE - fix vanilla MC-101233
	private final boolean isOn;

	protected BlockRedstoneCeilingTorch(boolean isOn)
	{
		this.isOn = isOn;

		setLightLevel(isOn ? 0.5F : 0.0F);
	}

	private boolean isBurnedOut(World world, BlockPos pos, boolean turnOff)
	{
		if(!toggles.containsKey(world))
			toggles.put(world, Lists.newArrayList());

		List<BlockRedstoneCeilingTorch.Toggle> list = toggles.get(world);

		if(turnOff)
			list.add(new BlockRedstoneCeilingTorch.Toggle(pos, world.getTotalWorldTime()));

		int i = 0;

		for(int j = 0; j < list.size(); ++j)
		{
			BlockRedstoneCeilingTorch.Toggle toggle = list.get(j);

			if(toggle.pos.equals(pos))
			{
				++i;

				if(i >= 8)
					return true;
			}
		}

		return false;
	}

	@Override
	public int tickRate(World world)
	{
		return 2;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if(isOn)
		{
			for(EnumFacing facing : EnumFacing.values())
			{
				world.notifyNeighborsOfStateChange(pos.offset(facing), this, false);
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if(isOn)
		{
			for(EnumFacing facing : EnumFacing.values())
			{
				world.notifyNeighborsOfStateChange(pos.offset(facing), this, false);
			}
		}
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side)
	{
		return isOn && EnumFacing.DOWN != side ? 15 : 0;
	}

	private boolean shouldBeOff(World world, BlockPos pos, IBlockState state)
	{
		EnumFacing facing = EnumFacing.DOWN.getOpposite();

		return world.isSidePowered(pos.offset(facing), facing);
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random)
	{
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		boolean flag = shouldBeOff(world, pos, state);
		List<BlockRedstoneCeilingTorch.Toggle> list = toggles.get(world);

		while(list != null && !list.isEmpty() && world.getTotalWorldTime() - (list.get(0)).time > 60L)
		{
			list.remove(0);
		}

		if(isOn)
		{
			if(flag)
			{
				world.setBlockState(pos, CeilingTorch.UNLIT_REDSTONE_TORCH.getDefaultState(), 3);

				if(isBurnedOut(world, pos, true))
				{
					world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

					for(int i = 0; i < 5; ++i)
					{
						double x = pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
						double y = pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
						double z = pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;

						world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
					}

					world.scheduleUpdate(pos, world.getBlockState(pos).getBlock(), 160);
				}
			}
		}
		else if(!flag && !isBurnedOut(world, pos, false))
			world.setBlockState(pos, CeilingTorch.REDSTONE_TORCH.getDefaultState(), 3);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		if(!onNeighborChangeInternal(world, pos, state))
		{
			if(isOn == shouldBeOff(world, pos, state))
				world.scheduleUpdate(pos, this, tickRate(world));
		}
	}

	@Override
	public int getStrongPower(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.DOWN ? state.getWeakPower(access, pos, side) : 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.REDSTONE_TORCH);
	}

	@Override
	public boolean canProvidePower(IBlockState state)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if(isOn)
		{
			double x = pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double y = pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D;
			double z = pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			EnumFacing facing = EnumFacing.DOWN;

			if(facing.getAxis().isHorizontal())
			{
				EnumFacing oppositeFacing = facing.getOpposite();

				x += 0.27D * oppositeFacing.getXOffset();
				y += 0.22D;
				z += 0.27D * oppositeFacing.getZOffset();
			}

			world.spawnParticle(EnumParticleTypes.REDSTONE, x, y - (facing == EnumFacing.DOWN ? 0.25D : 0.0D), z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean isAssociatedBlock(Block other)
	{
		return other == CeilingTorch.UNLIT_REDSTONE_TORCH || other == CeilingTorch.REDSTONE_TORCH;
	}

	static class Toggle
	{
		BlockPos pos;
		long time;

		public Toggle(BlockPos pos, long time)
		{
			this.pos = pos;
			this.time = time;
		}
	}
}