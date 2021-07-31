package bl4ckscor3.mod.ceilingtorch.compat.lotr;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LotrCeilingTorchBlock extends CeilingTorchBlock
{
	public static final VoxelShape CEILING_SHAPE = Block.box(6.0D, 6.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final List<Supplier<? extends ParticleOptions>> particles;

	public LotrCeilingTorchBlock(Block.Properties properties, Supplier<Block> originalBlock, Supplier<? extends ParticleOptions>... particles)
	{
		super(properties, ParticleTypes.FLAME, originalBlock);

		this.particles = Arrays.asList(particles);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;
		double offset = 0.0D;

		for(Supplier<? extends ParticleOptions> particle : particles)
		{
			ParticleOptions ipd = particle.get();

			if(ipd == ParticleTypes.SMOKE || ipd == ParticleTypes.FLAME)
				offset = 0.15D;
			else
				offset = 0.0D;

			world.addParticle(ipd, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		}
	}
}
