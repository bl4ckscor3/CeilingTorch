package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.common.block.ether.EtherBlockEntity;
import com.sammy.malum.common.item.ether.EtherItem;
import com.sammy.malum.registry.common.MalumParticles;
import com.sammy.malum.visual_effects.SparkParticleEffects;
import com.sammy.malum.visual_effects.SpiritLightSpecs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import team.lodestar.lodestone.handlers.LodestoneRenderHandler;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.ParticleEffectSpawner;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

public class CeilingEtherTorchBlockEntity extends EtherBlockEntity {
	public CeilingEtherTorchBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public void tick() {
		if (firstColor == null)
			return;

		RandomSource random = level.random;
		Color firstColorToUse = new Color(firstColor.rgb());
		Color secondColorToUse = new Color(secondColor == null ? firstColor.rgb() : secondColor.rgb());
		double x = worldPosition.getX() + 0.5F;
		double y = worldPosition.getY() + 0.25F;
		double z = worldPosition.getZ() + 0.5F;
		Vec3 sparkPos = new Vec3(x, y, z);

		if (level.getGameTime() % 2L == 0) {
			ColorParticleData color = ColorParticleData.create(firstColorToUse, secondColorToUse).setCoefficient(1.5F).setEasing(Easing.SINE_IN_OUT).build();
			int lifeTime = RandomHelper.randomBetween(random, 50, 60);
			float scale = RandomHelper.randomBetween(random, 0.7F, 0.9F);
			float velocity = RandomHelper.randomBetween(random, 0.02F, 0.025F);
			ParticleEffectSpawner lightSpecs = SpiritLightSpecs.spiritLightSpecs(level, sparkPos, color);

			lightSpecs.getBuilder()
				.setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
				.setLifetime(lifeTime)
				.setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
				.setTransparencyData(GenericParticleData.create(0.05f, 0.2f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
				.addMotion(0, velocity * 1.2f, 0);
			lightSpecs.spawnParticlesRaw();
		}

		if (level.getGameTime() % 4L == 0) {
			ColorParticleData color = ColorParticleData.create(firstColorToUse, secondColorToUse).setCoefficient(2.5F).setEasing(Easing.SINE_IN_OUT).build();
			int lifeTime = RandomHelper.randomBetween(random, 50, 60);
			float scale = RandomHelper.randomBetween(random, 0.3f, 0.5f);
			float velocity = RandomHelper.randomBetween(random, 0.02f, 0.025f);
			ParticleEffectSpawner lightSpecs = SparkParticleEffects.spiritMotionSparks(level, sparkPos, color);

			lightSpecs.getBuilder()
				.setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
				.setLifetime(lifeTime)
				.setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN_OUT).build())
				.setTransparencyData(GenericParticleData.create(0.1f, 0.6f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN_OUT).build())
				.addMotion(0, velocity * 1.4f, 0)
				.setRandomOffset(0.1f);
			lightSpecs.spawnParticlesRaw();
		}

		if (level.getGameTime() % 16L == 0) {
			ColorParticleData color = ColorParticleData.create(firstColorToUse, secondColorToUse).setCoefficient(0.6F).setEasing(Easing.SINE_IN_OUT).build();
			int lifeTime = RandomHelper.randomBetween(random, 50, 60);
			float scale = RandomHelper.randomBetween(random, 0.9F, 1.2F);

			WorldParticleBuilder.create(MalumParticles.GIANT_GLOWING_STAR)
				.setTransparencyData(GenericParticleData.create(0F, 0.2F, 0F).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
				.setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN).build())
				.setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
				.setLifetime(lifeTime)
				.setColorData(color)
				.enableNoClip()
				.spawn(level, x, y, z);
		}

		if (level.getGameTime() % 4L == 0) {
			ColorParticleData color = ColorParticleData.create(firstColorToUse, secondColorToUse).setCoefficient(0.6F).setEasing(Easing.SINE_IN_OUT).build();
			int lifeTime = RandomHelper.randomBetween(random, 20, 30);
			float scale = RandomHelper.randomBetween(random, 0.25F, 0.35F);

			WorldParticleBuilder.create(MalumParticles.STAR)
				.setTransparencyData(GenericParticleData.create(0F, 0.6f, 0F).setEasing(Easing.SINE_IN_OUT, Easing.SINE_IN_OUT).build())
				.setScaleData(GenericParticleData.create(scale, 0).setEasing(Easing.SINE_IN).build())
				.setRenderTarget(LodestoneRenderHandler.LATE_DEFERRED_RENDER)
				.setLifetime(lifeTime)
				.setColorData(color)
				.enableNoClip()
				.spawn(level, x, y, z);
		}
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
		super.loadAdditional(tag, lookupProvider);

		if (getBlockState().is(MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get()))
			secondColor = DyedItemColor.CODEC.parse(NbtOps.INSTANCE, tag.get("secondColor")).result().orElse(EtherItem.DEFAULT_SECOND_COLOR);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookupProvider) {
		super.saveAdditional(tag, lookupProvider);

		if (getBlockState().is(MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get()))
			tag.put("secondColor", DyedItemColor.CODEC.encodeStart(NbtOps.INSTANCE, secondColor).getOrThrow());
	}

	@Override
	public BlockEntityType<?> getType() {
		return MalumCompat.ETHER_CEILING_TORCH_BLOCK_ENTITY.get();
	}
}
