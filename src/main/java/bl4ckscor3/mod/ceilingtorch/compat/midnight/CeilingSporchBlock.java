package bl4ckscor3.mod.ceilingtorch.compat.midnight;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CeilingSporchBlock extends CeilingTorchBlock {
	private final Supplier<? extends ParticleOptions> particleData;

	public CeilingSporchBlock(Properties properties, Supplier<? extends ParticleOptions> particleData, Supplier<? extends Block> originalBlock) {
		super(properties, null, originalBlock);
		this.particleData = Suppliers.memoize(particleData::get);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.25D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(particleData.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
