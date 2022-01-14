package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

public class ShardCeilingTorchBlock extends CeilingTorchBlock
{
	public ShardCeilingTorchBlock(Properties properties)
	{
		super(properties, ParticleTypes.FLAME, UGBlocks.SHARD_TORCH);
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return UndergardenCompat.SHARD_CEILING_TORCH.get().create();
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
