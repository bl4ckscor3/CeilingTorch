package bl4ckscor3.mod.ceilingtorch.compat.secretrooms;

import com.wynprice.secretrooms.server.blocks.SecretBlocks;
import com.wynprice.secretrooms.server.items.SecretItems;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CeilingTorchLeverBlock extends CeilingTorchBlock
{
	private static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public CeilingTorchLeverBlock(Properties properties)
	{
		super(properties);
		setDefaultState(getDefaultState().with(POWERED, false));
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace)
	{
		state = state.cycle(POWERED);

		if(!world.isRemote)
		{
			world.setBlockState(pos, state, 3);
			world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, state.get(POWERED) ? 0.6F : 0.5F);
			updateNeighbors(world, pos);
		}

		return true;
	}

	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		if(!isMoving && state.getBlock() != newState.getBlock())
		{
			if(state.get(POWERED))
				updateNeighbors(world, pos);

			super.onReplaced(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean canProvidePower(BlockState state)
	{
		return true;
	}

	@Override
	public int getWeakPower(BlockState state, IBlockReader access, BlockPos pos, Direction side)
	{
		return state.get(BlockStateProperties.POWERED) ? 15 : 0;
	}

	@Override
	public int getStrongPower(BlockState state, IBlockReader access, BlockPos pos, Direction side)
	{
		return state.get(POWERED) && Direction.DOWN == side ? 15 : 0;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(POWERED);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return SecretBlocks.TORCH_LEVER.get().getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(SecretItems.TORCH_LEVER.get());
	}

	private void updateNeighbors(World world, BlockPos pos)
	{
		world.notifyNeighborsOfStateChange(pos, this);
		world.notifyNeighborsOfStateChange(pos.offset(Direction.UP), this);
	}
}
