package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.common.blocks.lighting.IColor;
import com.sammy.malum.core.init.particles.MalumParticles;
import com.sammy.malum.core.systems.particles.ParticleManager;
import com.sammy.malum.core.systems.tileentities.SimpleTileEntity;

import net.minecraft.tileentity.ITickableTileEntity;

public class CeilingLightingTileEntity extends SimpleTileEntity implements ITickableTileEntity
{
	public CeilingLightingTileEntity()
	{
		super(MalumCompat.ETHER_CEILING_TORCH.get());
	}

	@Override
	public void tick()
	{
		if(world.isRemote)
		{
			IColor color = (IColor)getBlockState().getBlock();
			Color finalColor = color.getColor();
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.4D;
			double z = pos.getZ() + 0.5D;
			int lifeTime = 8 + world.rand.nextInt(2);
			float scale = 0.17F + world.rand.nextFloat() * 0.03F;
			float velocity = 0.04F + world.rand.nextFloat() * 0.02F;

			ParticleManager.create(MalumParticles.SPARKLE_PARTICLE).setScale(scale * 2.0F, 0.0F).setLifetime(lifeTime).setAlpha(0.2F).setColor(finalColor, finalColor).spawn(world, x, y, z);

			if(world.rand.nextFloat() < 0.9F)
				ParticleManager.create(MalumParticles.WISP_PARTICLE).setScale(scale, 0.0F).setLifetime(lifeTime).setAlpha(0.9F, 0.75F).setColor(finalColor, MalumHelper.darker(finalColor, 2)).addVelocity(0.0D, velocity, 0.0D).setSpin(world.rand.nextFloat() * 0.5F).spawn(world, x, y, z);

			if(world.getGameTime() % 4L == 0L && world.rand.nextFloat() < 0.5F)
			{
				ParticleManager.create(MalumParticles.SPIRIT_FLAME).setScale(0.75F, 0.0F).setColor(finalColor, MalumHelper.darker(finalColor, 1)).randomOffset(0.20000000298023224D, 0.30000001192092896D).addVelocity(0.0D, 0.019999999552965164D, 0.0D).spawn(world, x, y - 0.1D, z);
				ParticleManager.create(MalumParticles.SPIRIT_FLAME).setScale(0.5F, 0.0F).setColor(finalColor, MalumHelper.darker(finalColor, 1)).randomOffset(0.125D, 0.30000001192092896D).addVelocity(0.0D, velocity, 0.0D).spawn(world, x, y - 0.1D, z);
			}
		}
	}
}