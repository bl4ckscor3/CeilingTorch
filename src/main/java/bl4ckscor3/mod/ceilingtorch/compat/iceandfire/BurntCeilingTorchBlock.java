package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Random;

import com.github.alexthe666.iceandfire.block.IDreadBlock;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BurntCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock
{
	public BurntCeilingTorchBlock(Properties properties)
	{
		super(properties, RedstoneParticleData.REDSTONE, () -> IafBlockRegistry.BURNT_TORCH);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}
}
