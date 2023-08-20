package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.common.block.ether.EtherBlockEntity;
import com.sammy.malum.registry.client.ParticleRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.render.ColorHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;

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

		Color firstColor = ColorHelper.darker(this.firstColor, 1);
		Color secondColor = this.secondColor == null ? this.firstColor : ColorHelper.brighter(this.secondColor, 1);
		double x = worldPosition.getX() + 0.5D;
		double y = worldPosition.getY() + 0.25D;
		double z = worldPosition.getZ() + 0.5D;
		int lifeTime = 10 + level.random.nextInt(4);
		float scale = 0.17F + level.random.nextFloat() * 0.03F;
		float velocity = 0.04F + level.random.nextFloat() * 0.02F;

		//@formatter:off
		WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
			.setScaleData(GenericParticleData.create(scale, 0.0F).build())
			.setScaleData(GenericParticleData.create(scale, 0.0F).build())
			.setTransparencyData(GenericParticleData.create(0.75F, 0.25F).build())
			.setColorData(ColorParticleData
				.create(firstColor, secondColor)
				.setCoefficient(1.4F)
				.setEasing(Easing.BOUNCE_IN_OUT)
				.build())
			.setSpinData(SpinParticleData
				.create(0.2F, 0.4F)
				.setSpinOffset(level.getGameTime() * 0.2F % 6.28F)
				.setEasing(Easing.QUARTIC_IN)
				.build())
			.setLifetime(lifeTime)
			.addMotion(0.0D, velocity, 0.0D)
			.enableNoClip()
			.spawn(level, x, y, z);
		WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
			.setScaleData(GenericParticleData.create(scale * 2.0F, scale * 0.1F).build())
			.setTransparencyData(GenericParticleData.create(0.25F, 0.0F).build())
			.setColorData(ColorParticleData
				.create(firstColor, secondColor)
				.setEasing(Easing.SINE_IN)
				.setCoefficient(2.25F)
				.build())
			.setSpinData(SpinParticleData
				.create(0.0F, 2.0F)
				.setEasing(Easing.QUARTIC_IN)
				.build())
			.setLifetime(lifeTime)
			.enableNoClip()
			.spawn(level, x, y, z);
		//@formatter:on

		if (level.getGameTime() % 2L == 0L) {
			y += 0.15000000596046448;

			if (level.random.nextFloat() < 0.5F) {
				//@formatter:off
				WorldParticleBuilder.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
					.setScaleData(GenericParticleData.create(0.5F, 0.75F, 0.0F).build())
					.setColorData(ColorParticleData
						.create(firstColor, secondColor)
						.setEasing(Easing.CIRC_IN_OUT)
						.setCoefficient(2.5F)
						.build())
					.setTransparencyData(GenericParticleData
						.create(0.2F, 1.0F, 0.0F)
						.setEasing(Easing.SINE_IN, Easing.QUAD_IN)
						.setCoefficient(3.5F)
						.build())
					.setRandomOffset(0.15000000596046448D, 0.20000000298023224D)
					.addMotion(0.0D, 0.0035000001080334187D, 0.0D)
					.setRandomMotion(0.0010000000474974513D, 0.004999999888241291D)
					.addActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale((0.985F - level.random.nextFloat() * 0.04F))))
					.enableNoClip()
					.setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
					.spawn(level, x, y, z);
				//@formatter:on
			}

			if (level.random.nextFloat() < 0.25F) {
				//@formatter:off
				WorldParticleBuilder.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
					.setScaleData(GenericParticleData.create(0.3F, 0.5F, 0.0F).build())
					.setColorData(ColorParticleData
						.create(firstColor, secondColor)
						.setEasing(Easing.CIRC_IN_OUT)
						.setCoefficient(3.5F)
						.build())
					.setTransparencyData(GenericParticleData
						.create(0.2F, 1.0F, 0.0F)
						.setEasing(Easing.SINE_IN, Easing.CIRC_IN_OUT)
						.setCoefficient(3.5F)
						.build())
					.setRandomOffset(0.10000000149011612D, 0.22499999403953552D)
					.addMotion(0.0D, (velocity / 2.0F), 0.0D)
					.setRandomMotion(0.0D, 0.014999999664723873D)
					.addActor(p -> p.setParticleSpeed(p.getParticleSpeed().scale((0.97F - level.random.nextFloat() * 0.025F))))
					.enableNoClip()
					.setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
					.spawn(level, x, y, z);
				//@formatter:on
			}
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
