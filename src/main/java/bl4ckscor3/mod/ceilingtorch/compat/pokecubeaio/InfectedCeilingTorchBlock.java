package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import pokecube.legends.init.BlockInit;
import pokecube.legends.init.ParticleInit;

public class InfectedCeilingTorchBlock extends CeilingTorchBlock {
	public InfectedCeilingTorchBlock(Properties properties) {
		super(properties, ParticleTypes.DRAGON_BREATH, BlockInit.INFECTED_TORCH);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(ParticleInit.INFECTED_FIRE_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
