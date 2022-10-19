package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MenrilCeilingTorchBlock extends CeilingTorchBlock {
	public MenrilCeilingTorchBlock(Properties properties, ParticleOptions particleData, Supplier<Block> originalBlock) {
		super(properties, particleData, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {}
}
