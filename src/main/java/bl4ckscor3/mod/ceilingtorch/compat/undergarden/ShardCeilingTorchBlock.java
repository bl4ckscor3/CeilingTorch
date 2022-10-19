package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGBlocks;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGParticleTypes;
import quek.undergarden.registry.UGTags;

public class ShardCeilingTorchBlock extends CeilingTorchBlock {
	public ShardCeilingTorchBlock(Properties properties) {
		super(properties, ParticleTypes.FLAME, UGBlocks.SHARD_TORCH);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		//@formatter:off
		level.getEntitiesOfClass(LivingEntity.class,
				new AABB(pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4),
				entity -> entity.getType().is(UGTags.Entities.ROTSPAWN))
		.forEach(entity -> entity.hurt(UGDamageSources.SHARD_TORCH, 4.0F));
		//@formatter:on
		level.scheduleTick(pos, this, 20);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(UGParticleTypes.SHARD.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
