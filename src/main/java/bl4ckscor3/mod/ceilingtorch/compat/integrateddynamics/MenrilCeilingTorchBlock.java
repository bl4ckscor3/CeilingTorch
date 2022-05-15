package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.Random;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MenrilCeilingTorchBlock extends CeilingTorchBlock
{
	public MenrilCeilingTorchBlock(Properties properties, IParticleData particleData, Supplier<Block> originalBlock)
	{
		super(properties, particleData, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, World level, BlockPos pos, Random rand) {}
}
