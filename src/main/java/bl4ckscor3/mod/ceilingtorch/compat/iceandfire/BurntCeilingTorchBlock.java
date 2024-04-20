package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.function.Supplier;

import com.github.alexthe666.iceandfire.block.IDreadBlock;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BurntCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock {
	public BurntCeilingTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends Block> originalBlock) {
		super(properties, null, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {}
}
