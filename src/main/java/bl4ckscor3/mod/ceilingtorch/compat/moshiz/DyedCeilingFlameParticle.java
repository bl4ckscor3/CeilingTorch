package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

public class DyedCeilingFlameParticle extends TextureSheetParticle {
	private DyedCeilingFlameParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
		super(level, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);

		int color = MoShizCompat.ceilingTorchParticleColors.get(CeilingTorch.getRegistryName(level.getBlockState(new BlockPos(xCoord, yCoord, zCoord)).getBlock())).getRight();

		xd = xd * 0.009999999776482582D + xSpeed;
		yd = yd * 0.009999999776482582D + ySpeed;
		zd = zd * 0.009999999776482582D + zSpeed;
		x += (random.nextFloat() - random.nextFloat()) * 0.05F;
		y += (random.nextFloat() - random.nextFloat()) * 0.05F;
		z += (random.nextFloat() - random.nextFloat()) * 0.05F;
		lifetime = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
		rCol = ((color & 0xFF0000) >> 16) / 255.0F;
		gCol = ((color & 0xFF00) >> 8) / 255.0F;
		bCol = (color & 0xFF) / 255.0F;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z) {
		setBoundingBox(getBoundingBox().move(x, y, z));
		setLocationFromBoundingbox();
	}

	@Override
	public void tick() {
		xo = x;
		yo = y;
		zo = z;

		if (age++ >= lifetime)
			remove();
		else {
			move(xd, yd, zd);
			xd *= 0.9599999785423279D;
			yd *= 0.9599999785423279D;
			zd *= 0.9599999785423279D;

			if (onGround) {
				xd *= 0.699999988079071D;
				zd *= 0.699999988079071D;
			}
		}
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private SpriteSet spriteSet;

		public Factory(SpriteSet sprite) {
			this.spriteSet = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			DyedCeilingFlameParticle particle = new DyedCeilingFlameParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);

			particle.pickSprite(spriteSet);
			return particle;
		}
	}
}