package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import wallywhip.InvisibleTorches.block.SoulTorchTile;

public class InvisibleCeilingSoulTorchBlockEntity extends SoulTorchTile {
	public InvisibleCeilingSoulTorchBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return InvisibleTorchesCompat.CEILING_INVISIBLE_SOUL_TORCH_BLOCK_ENTITY.get();
	}
}
