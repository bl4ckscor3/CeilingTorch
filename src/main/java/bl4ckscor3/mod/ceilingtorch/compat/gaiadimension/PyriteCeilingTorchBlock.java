package bl4ckscor3.mod.ceilingtorch.compat.gaiadimension;

import java.util.Random;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModParticles;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PyriteCeilingTorchBlock extends CeilingTorchBlock
{
	public PyriteCeilingTorchBlock(Block.Properties properties)
	{
		super(properties, ModParticles.PYRITE, ModBlocks.pyrite_torch);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + rand.nextDouble() * 0.7D;
		double y = pos.getY() + rand.nextDouble() * 0.7D;
		double z = pos.getZ() + rand.nextDouble() * 0.7D;

		world.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
