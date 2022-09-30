package bl4ckscor3.mod.ceilingtorch.compat.projecte;

import moze_intel.projecte.gameObjs.block_entities.InterdictionTorchBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class InterdictionCeilingTorchBlockEntity extends InterdictionTorchBlockEntity
{
	public InterdictionCeilingTorchBlockEntity(BlockPos pos, BlockState state)
	{
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType()
	{
		return ProjectECompat.INTERDICTION_CEILING_TORCH_BLOCK_ENTITY.get();
	}
}
