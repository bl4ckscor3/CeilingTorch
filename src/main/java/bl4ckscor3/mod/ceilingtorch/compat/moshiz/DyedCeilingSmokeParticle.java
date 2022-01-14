package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;

public class DyedCeilingSmokeParticle extends SpriteTexturedParticle
{
	private IAnimatedSprite animatedSprite;

	public DyedCeilingSmokeParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, float size, IAnimatedSprite sprite) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);

		int color = MoShizCompat.ceilingTorchParticleColors.get(world.getBlockState(new BlockPos(xCoord, yCoord, zCoord)).getBlock());

		animatedSprite = sprite;
		xd *= 0.10000000149011612D;
		yd *= 0.10000000149011612D;
		zd *= 0.10000000149011612D;
		xd += xSpeed;
		yd += ySpeed;
		zd += zSpeed;
		quadSize *= 0.75F * size;
		lifetime = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		lifetime *= (int)size;
		lifetime = Math.max(lifetime, 1);
		setSpriteFromAge(sprite);
		rCol = ((color & 0xFF0000) >> 16) / 255.0F;
		gCol = ((color & 0xFF00) >> 8) / 255.0F;
		bCol = (color & 0xFF) / 255.0F;
	}

	@Override
	public IParticleRenderType getRenderType()
	{
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		xo = x;
		yo = y;
		zo = z;

		if(age++ >= lifetime)
			remove();
		else
		{
			setSpriteFromAge(animatedSprite);
			yd += 0.004D;
			move(xd, yd, zd);

			if(y == yo)
			{
				xd *= 1.1D;
				zd *= 1.1D;
			}

			xd *= 0.9599999785423279D;
			yd *= 0.9599999785423279D;
			zd *= 0.9599999785423279D;

			if(onGround)
			{
				xd *= 0.699999988079071D;
				zd *= 0.699999988079071D;
			}
		}
	}

	public static class Factory implements IParticleFactory<BasicParticleType>
	{
		private IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite sprite)
		{
			this.spriteSet = sprite;
		}

		@Override
		public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
		{
			return new DyedCeilingSmokeParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, 1.0F, this.spriteSet);
		}
	}
}