package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import pokecube.legends.init.BlockInit;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UltraCeilingTorchBlock extends CeilingTorchBlock
{
	public UltraCeilingTorchBlock(Properties properties)
	{
		super(properties, ParticleTypes.CAMPFIRE_COSY_SMOKE, BlockInit.ULTRA_TORCH1);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + 0.77D, pos.getY() + 0.08D, pos.getZ() + 0.77D, 0.0D, 0.0D, 0.0D);
	}
}
