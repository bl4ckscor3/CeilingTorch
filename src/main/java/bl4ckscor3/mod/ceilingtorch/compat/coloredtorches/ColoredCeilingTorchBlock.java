package bl4ckscor3.mod.ceilingtorch.compat.coloredtorches;

import java.util.function.Supplier;

import com.mrbysco.coloredtorches.client.ParticleColor;
import com.mrbysco.coloredtorches.client.particle.ColoredFlameParticle;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ColoredCeilingTorchBlock extends CeilingTorchBlock {
	public final ParticleColor color;

	public ColoredCeilingTorchBlock(Properties properties, DyeColor dyeColor, Supplier<Block> original) {
		super(properties, ParticleTypes.FLAME, original);
		color = new ParticleColor(dyeColor.getFireworkColor());
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(ColoredFlameParticle.Provider.createData(color), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
