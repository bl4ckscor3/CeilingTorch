package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.function.Supplier;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IDreadBlock;
import com.github.alexthe666.iceandfire.enums.EnumParticles;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class DreadCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock {
	public DreadCeilingTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends Block> originalBlock) {
		super(properties, null, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		IceAndFire.PROXY.spawnParticle(EnumParticles.Dread_Torch, pos.getX() + 0.5D, pos.getY() + 0.4D, pos.getZ() + 0.5D, 0.0, 0.0, 0.0);
	}
}
