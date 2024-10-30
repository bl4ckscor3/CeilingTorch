package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MenrilCeilingTorchBlock extends CeilingTorchBlock {
	public MenrilCeilingTorchBlock(BlockBehaviour.Properties properties, Supplier<Block> originalBlock) {
		super(properties, null, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {}
}
