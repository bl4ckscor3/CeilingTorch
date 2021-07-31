package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.TickableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags.Entities;

public class CeilingShardTorchTileEntity extends BlockEntity implements TickableBlockEntity
{
	public CeilingShardTorchTileEntity()
	{
		super(UndergardenCompat.ETHER_CEILING_TORCH.get());
	}

	@Override
	public void tick()
	{
		if(level.getGameTime() % 20L == 0L)
		{
			level.getEntitiesOfClass(LivingEntity.class, new AABB(worldPosition.getX() - 4, worldPosition.getY() - 4, worldPosition.getZ() - 4, worldPosition.getX() + 4, worldPosition.getY() + 4, worldPosition.getZ() + 4), e -> e.getType().is(Entities.ROTSPAWN))
			.forEach(e -> e.hurt(UGDamageSources.SHARD_TORCH, 4.0F));
		}
	}
}
