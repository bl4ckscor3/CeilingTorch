package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.common.block.ether.EtherBlockEntity;
import com.sammy.malum.registry.client.ParticleRegistry;
import com.sammy.malum.visual_effects.SpiritLightSpecs;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.ParticleEffectSpawner;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions.ParticleDiscardFunctionType;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

public class CeilingEtherTorchBlockEntity extends EtherBlockEntity {
	public CeilingEtherTorchBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public void tick() {
		if (needsSync) {
			init();
			needsSync = false;
		}

		if (firstColor == null)
			return;

		RandomSource random = level.random;
		Color firstColorToUse = ColorHelper.darker(this.firstColor, 1);
		Color secondColorToUse = secondColor == null ? firstColorToUse : ColorHelper.brighter(secondColor, 1);
		double x = worldPosition.getX() + 0.5F;
		double y = worldPosition.getY() + 0.25F;
		double z = worldPosition.getZ() + 0.5F;
		ColorParticleData colorData = ColorParticleData.create(firstColorToUse, secondColorToUse).setCoefficient(1.5F).setEasing(Easing.BOUNCE_IN_OUT).build();

		if (level.getGameTime() % 8L == 0L) {
			int lifeTime = RandomHelper.randomBetween(random, 40, 60);
			float scale = RandomHelper.randomBetween(random, 0.6F, 0.7F);
			float velocity = RandomHelper.randomBetween(random, 0.02F, 0.03F);
			ParticleEffectSpawner lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, new Vec3(x, y, z), colorData);

			//@formatter:off
			lightSpecs.getBuilder()
				.setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
				.setLifetime(lifeTime)
				.setScaleData(GenericParticleData
						.create(scale, 0.0F)
						.setEasing(Easing.SINE_IN_OUT)
						.build())
				.setTransparencyData(GenericParticleData.create(0.3F, 0.6F, 0.0F).build())
				.addMotion(0.0, velocity * 1.2F, 0.0);
			//@formatter:on
			lightSpecs.spawnParticlesRaw();
		}

		if (level.getGameTime() % 2L == 0L) {
			int lifeTime = RandomHelper.randomBetween(random, 10, 15);
			float velocity = RandomHelper.randomBetween(random, 0.02F, 0.03F);

			//@formatter:off
			WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
				.setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
				.setScaleData(GenericParticleData
						.create(RandomHelper.randomBetween(random, 0.15F, 0.2F), 0.0F)
						.setEasing(Easing.SINE_IN)
						.build())
				.setTransparencyData(GenericParticleData
						.create(0.4F, 0.8F, 0.2F)
						.setEasing(Easing.QUAD_OUT)
						.build())
				.setColorData(colorData)
				.setSpinData(SpinParticleData
						.create(0.2F, 0.4F)
						.setSpinOffset(level.getGameTime() * 0.2F % 6.28F)
						.setEasing(Easing.QUARTIC_IN)
						.build())
				.setLifetime(lifeTime)
				.addMotion(0.0, velocity * 1.5F, 0.0)
				.enableNoClip()
				.spawn(level, x, y, z);
			WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
				.setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
				.setScaleData(GenericParticleData.create(0.5F, 0.0F).build())
				.setTransparencyData(GenericParticleData.create(0.2F, 0.8F).build())
				.setColorData(ColorParticleData
						.create(firstColorToUse, secondColorToUse)
						.setEasing(Easing.SINE_IN)
						.setCoefficient(0.5F)
						.build())
				.setSpinData(SpinParticleData
						.create(0.0F, 0.4F)
						.setEasing(Easing.QUARTIC_IN)
						.build())
				.setLifetime(20)
				.enableNoClip()
				.spawn(level, x, y, z);
			//@formatter:on
		}

		if (level.getGameTime() % 4L == 0L) {
			long gameTime = level.getGameTime();
			float scale = RandomHelper.randomBetween(random, 0.6F, 0.75F);
			float velocity = RandomHelper.randomBetween(random, 0.0F, 0.02F);
			float angle = gameTime % 24L / 24.0F * (float) Math.PI * 2.0F;
			Vec3 offset = new Vec3(Math.sin(angle), 0.0, Math.cos(angle)).normalize();
			Vec3 offsetPosition = new Vec3(x + offset.x * 0.075F, y - 0.05F, z + offset.z * 0.075F);

			//@formatter:off
			WorldParticleBuilder.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
				.setRenderTarget(RenderHandler.LATE_DELAYED_RENDER)
				.setScaleData(GenericParticleData.create(scale * 0.75F, scale, 0.0F).build())
				.setColorData(ColorParticleData
						.create(firstColorToUse, secondColorToUse)
						.setEasing(Easing.CIRC_IN_OUT)
						.setCoefficient(2.5F)
						.build())
				.setTransparencyData(GenericParticleData
						.create(0.0F, 1.0F, 0.0F)
						.setEasing(Easing.SINE_IN, Easing.QUAD_IN)
						.setCoefficient(3.5F)
						.build())
				.addMotion(0.0, velocity, 0.0)
				.addTickActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale(1.0F - random.nextFloat() * 0.0F)))
				.enableNoClip()
				.setDiscardFunction(ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
				.spawn(level, offsetPosition.x, offsetPosition.y, offsetPosition.z);
			//@formatter:on
		}
	}

	@Override
	public void load(CompoundTag tag) {
		if (getBlockState().is(MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get())) {
			if (tag.contains("secondColor"))
				setSecondColor(tag.getInt("secondColor"));
			else
				setSecondColor(tag.contains("secondColor") ? tag.getInt("secondColor") : 4607909);
		}

		super.load(tag);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		if (getBlockState().is(MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get()) && secondColor != null && secondColor.getRGB() != 4607909)
			tag.putInt("secondColor", secondColor.getRGB());

		super.saveAdditional(tag);
	}

	@Override
	public BlockEntityType<?> getType() {
		return MalumCompat.ETHER_CEILING_TORCH_BLOCK_ENTITY.get();
	}
}
