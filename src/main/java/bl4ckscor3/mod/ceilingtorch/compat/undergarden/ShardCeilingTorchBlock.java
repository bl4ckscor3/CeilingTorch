package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGParticleTypes;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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
	public BlockEntity createTileEntity(BlockState state, BlockGetter world)
	{
		return UndergardenCompat.ETHER_CEILING_TORCH.get().create();
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
