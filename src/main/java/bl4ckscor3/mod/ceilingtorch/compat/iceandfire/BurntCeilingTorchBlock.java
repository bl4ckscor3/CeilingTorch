package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Random;

import com.github.alexthe666.iceandfire.block.IDreadBlock;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BurntCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock {
	public BurntCeilingTorchBlock(Properties properties) {
		super(properties, DustParticleOptions.REDSTONE, IafBlockRegistry.BURNT_TORCH);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {}
}
