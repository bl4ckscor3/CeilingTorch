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
		super(properties, BlockInit.ULTRA_TORCH1);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, 0.0D, -0.2D, 0.0D);
	}
}
