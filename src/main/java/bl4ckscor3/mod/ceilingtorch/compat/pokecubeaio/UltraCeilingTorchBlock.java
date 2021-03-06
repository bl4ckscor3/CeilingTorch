package bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pokecube.legends.init.BlockInit;

public class UltraCeilingTorchBlock extends CeilingTorchBlock
{
	public UltraCeilingTorchBlock(Properties properties)
	{
		super(properties, ParticleTypes.CAMPFIRE_COSY_SMOKE, BlockInit.ULTRA_TORCH1);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + 0.77D, pos.getY() + 0.08D, pos.getZ() + 0.77D, 0.0D, 0.0D, 0.0D);
	}
}
