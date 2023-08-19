package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Random;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IDreadBlock;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.enums.EnumParticles;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DreadCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock {
	public DreadCeilingTorchBlock(Properties properties) {
		super(properties, DustParticleOptions.REDSTONE, IafBlockRegistry.DREAD_TORCH);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		IceAndFire.PROXY.spawnParticle(EnumParticles.Dread_Torch, pos.getX() + 0.5D, pos.getY() + 0.4D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
	}
}
