package bl4ckscor3.mod.ceilingtorch.compat.modernity;

import modernity.api.util.EWaterlogType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockModernityExtinguishableCeilingTorch extends BlockModernityCeilingTorch
{
	private final Block extinguished;

	public BlockModernityExtinguishableCeilingTorch(Properties properties, boolean burning, Block extinguished)
	{
		super(properties, burning);

		this.extinguished = extinguished;
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		if(state.get(WATERLOGGED) != EWaterlogType.NONE)
			world.setBlockState(pos, extinguished.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)));
	}
}
