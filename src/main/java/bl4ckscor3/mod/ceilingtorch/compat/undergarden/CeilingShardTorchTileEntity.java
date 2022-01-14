package bl4ckscor3.mod.ceilingtorch.compat.undergarden;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import quek.undergarden.registry.UGDamageSources;
import quek.undergarden.registry.UGTags.Entities;

public class CeilingShardTorchTileEntity extends TileEntity implements ITickableTileEntity
{
	public CeilingShardTorchTileEntity()
	{
		super(UndergardenCompat.SHARD_CEILING_TORCH.get());
	}

	@Override
	public void tick()
	{
		if(level.getGameTime() % 20L == 0L)
		{
			level.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(worldPosition.getX() - 4, worldPosition.getY() - 4, worldPosition.getZ() - 4, worldPosition.getX() + 4, worldPosition.getY() + 4, worldPosition.getZ() + 4), e -> e.getType().is(Entities.ROTSPAWN))
			.forEach(e -> e.hurt(UGDamageSources.SHARD_TORCH, 4.0F));
		}
	}
}
