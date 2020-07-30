package bl4ckscor3.mod.ceilingtorch.compat.modernity;

import java.util.Random;

import modernity.common.block.fluid.WaterlogType;
import modernity.common.event.MDBlockEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModernityExtinguishableCeilingTorchBlock extends ModernityCeilingTorchBlock
{
	private final Block extinguished;

	public ModernityExtinguishableCeilingTorchBlock(Properties properties, boolean burning, Block extinguished)
	{
		super(properties, burning);

		this.extinguished = extinguished;
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		if(state.get(WATERLOGGED) != WaterlogType.NONE)
		{
			MDBlockEvents.TORCH_EXTINGUISH.play(world, pos);
			world.setBlockState(pos, extinguished.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)));

			Random rand = world.getRandom();
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			for(int i = 0; i < 4; i++)
			{
				double px = x + rand.nextDouble() * 0.4D - 0.2D;
				double py = y + rand.nextDouble() * 0.4D - 0.2D;
				double pz = z + rand.nextDouble() * 0.4D - 0.2D;

				world.addParticle(ParticleTypes.SMOKE, px, py, pz, 0, 0, 0);
			}

			world.playSound(null, pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F);
		}
	}
}
