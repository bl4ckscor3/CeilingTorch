package bl4ckscor3.mod.ceilingtorch.compat.modernity;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
import modernity.common.block.base.WaterloggedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockModernityCeilingTorch extends WaterloggedBlock
{
	private final boolean burning;

	public BlockModernityCeilingTorch(Properties properties, boolean burning)
	{
		super(properties);

		this.burning = burning;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return BlockCeilingTorch.CEILING_SHAPE;
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(burning)
		{
			double x = 0.27D + pos.getX() + 0.5D;
			double y = pos.getY() + 0.92D;
			double z = 0.27D + pos.getZ() + 0.5D;

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
}
