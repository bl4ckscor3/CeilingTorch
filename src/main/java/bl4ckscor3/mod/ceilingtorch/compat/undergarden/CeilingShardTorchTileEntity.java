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
		super(UndergardenCompat.ETHER_CEILING_TORCH.get());
	}

	@Override
	public void tick()
	{
		if(world.getGameTime() % 20L == 0L)
		{
			world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4), e -> e.getType().isContained(Entities.ROTSPAWN))
			.forEach(e -> e.attackEntityFrom(UGDamageSources.SHARD_TORCH, 4.0F));
		}
	}
}
