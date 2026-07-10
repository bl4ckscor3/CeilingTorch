package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import wallywhip.InvisibleTorches.block.TorchTile;

public class InvisibleCeilingTorchBlockEntity extends TorchTile {
	public InvisibleCeilingTorchBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return InvisibleTorchesCompat.CEILING_INVISIBLE_TORCH_BLOCK_ENTITY.get();
	}
}
