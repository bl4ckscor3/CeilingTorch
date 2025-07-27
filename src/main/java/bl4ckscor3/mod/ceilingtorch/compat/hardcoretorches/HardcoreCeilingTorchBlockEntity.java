package bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches;

import com.github.wolfiewaffle.hardcore_torches.blockentity.TorchBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HardcoreCeilingTorchBlockEntity extends TorchBlockEntity {
	public HardcoreCeilingTorchBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return HardcoreTorchesCompat.CEILING_TORCH_BLOCK_ENTITY.get();
	}
}
