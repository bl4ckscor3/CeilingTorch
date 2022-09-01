package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.common.blockentity.EtherBlockEntity;
import com.sammy.malum.core.setup.client.ParticleRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.setup.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.rendering.particle.ParticleBuilders;
import team.lodestar.lodestone.systems.rendering.particle.SimpleParticleOptions.SpecialRemovalProtocol;

public class CeilingEtherTorchBlockEntity extends EtherBlockEntity
{
	public CeilingEtherTorchBlockEntity(BlockPos pos, BlockState state)
	{
		super(pos, state);
	}

	@Override
	public void tick()
	{
		if(firstColor == null)
			return;

		Color firstColor = ColorHelper.darker(this.firstColor, 1);
		Color secondColor = this.secondColor == null ? this.firstColor : ColorHelper.brighter(this.secondColor, 1);
		double x = worldPosition.getX() + 0.5D;
		double y = worldPosition.getY() + 0.4D;
		double z = worldPosition.getZ() + 0.5D;
		int lifeTime = 10 + level.random.nextInt(4);
		float scale = 0.17F + level.random.nextFloat() * 0.03F;
		float velocity = 0.04F + level.random.nextFloat() * 0.02F;

		ParticleBuilders.create(LodestoneParticleRegistry.WISP_PARTICLE)
		.setScale(scale, 0.0F)
		.setAlpha(0.75F, 0.25F)
		.setLifetime(lifeTime)
		.setColor(firstColor, secondColor)
		.setColorCoefficient(1.4F)
		.setColorEasing(Easing.BOUNCE_IN_OUT)
		.setSpinOffset(level.getGameTime() * 0.2F % 6.28F)
		.setSpin(0.2F, 0.4F)
		.setSpinEasing(Easing.QUARTIC_IN)
		.addMotion(0.0D, velocity, 0.0D)
		.enableNoClip()
		.spawn(level, x, y, z);
		ParticleBuilders.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
		.setScale(scale * 2.0F, scale * 0.1F)
		.setLifetime(lifeTime)
		.setAlpha(0.25F, 0.0F)
		.setColor(firstColor, secondColor)
		.setColorEasing(Easing.SINE_IN)
		.setColorCoefficient(2.25F)
		.setSpin(0.0F, 2.0F)
		.setSpinEasing(Easing.QUARTIC_IN)
		.enableNoClip()
		.spawn(level, x, y, z);

		if(level.getGameTime() % 2L == 0L)
		{
			y += 0.15000000596046448;

			if(level.random.nextFloat() < 0.5F)
			{
				ParticleBuilders.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
				.setScale(0.5F, 0.75F, 0.0F)
				.setColor(firstColor, secondColor)
				.setColorEasing(Easing.CIRC_IN_OUT)
				.setColorCoefficient(2.5F)
				.setAlpha(0.2F, 1.0F, 0.0F)
				.setAlphaEasing(Easing.SINE_IN, Easing.QUAD_IN)
				.setAlphaCoefficient(3.5F)
				.randomOffset(0.15000000596046448D, 0.20000000298023224D)
				.addMotion(0.0D, 0.0035000001080334187D, 0.0D)
				.randomMotion(0.0010000000474974513D, 0.004999999888241291D)
				.setMotionCoefficient(0.985F - level.random.nextFloat() * 0.04F)
				.enableNoClip()
				.overwriteRemovalProtocol(SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
				.spawn(level, x, y, z);
				//@formatter:on
			}

			if(level.random.nextFloat() < 0.25F)
			{
				ParticleBuilders.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
				.setScale(0.3F, 0.5F, 0.0F)
				.setColor(firstColor, secondColor)
				.setColorEasing(Easing.CIRC_IN_OUT)
				.setColorCoefficient(3.5F)
				.setAlpha(0.2F, 1.0F, 0.0F)
				.setAlphaEasing(Easing.SINE_IN, Easing.CIRC_IN_OUT)
				.setAlphaCoefficient(3.5F)
				.randomOffset(0.10000000149011612D, 0.22499999403953552D)
				.addMotion(0.0D, velocity / 2.0F, 0.0F)
				.randomMotion(0.0D, 0.014999999664723873D)
				.setMotionCoefficient(0.97F - level.random.nextFloat() * 0.025F)
				.enableNoClip()
				.overwriteRemovalProtocol(SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
				.spawn(level, x, y, z);
				//@formatter:on
			}
		}
	}

	@Override
	public void load(CompoundTag tag)
	{
		if(getBlockState().getBlock() == MalumCompat.iridescentEtherCeilingTorch)
		{
			if(tag.contains("secondColor"))
				setSecondColor(tag.getInt("secondColor"));
			else
				setSecondColor(4607909);
		}

		super.load(tag);
	}

	@Override
	protected void saveAdditional(CompoundTag tag)
	{
		if(getBlockState().getBlock() == MalumCompat.iridescentEtherCeilingTorch && secondColor != null && secondColorRGB != 4607909)
			tag.putInt("secondColor", secondColorRGB);

		super.saveAdditional(tag);
	}

	@Override
	public BlockEntityType<?> getType()
	{
		return MalumCompat.ETHER_CEILING_TORCH.get();
	}
}
