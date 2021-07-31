package bl4ckscor3.mod.ceilingtorch.compat.endergetic;

import java.util.Random;

import com.minecraftabnormals.endergetic.client.particles.EEParticles;
import com.minecraftabnormals.endergetic.core.registry.EEBlocks;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class EnderCeilingTorchBlock extends CeilingTorchBlock
{
	public EnderCeilingTorchBlock(Properties properties)
	{
		super(properties, null, EEBlocks.ENDER_TORCH);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.4D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(EEParticles.ENDER_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
