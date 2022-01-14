package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.awt.Color;

import com.sammy.malum.common.blockentity.EtherTileEntity;
import com.sammy.malum.core.helper.ColorHelper;
import com.sammy.malum.core.registry.misc.ParticleRegistry;
import com.sammy.malum.core.systems.rendering.RenderUtilities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CeilingEtherTorchBlockEntity extends EtherTileEntity
{
	public CeilingEtherTorchBlockEntity(BlockPos pos, BlockState state)
	{
		super(pos, state);
	}

	@Override
	public void tick()
	{
		if(firstColor == null || secondColor == null)
			return;

		Color firstColor = ColorHelper.darker(this.firstColor, 1);
		Color secondColor = ColorHelper.brighter(this.secondColor, 1);
		double x = worldPosition.getX() + 0.5D;
		double y = worldPosition.getY() + 0.4D;
		double z = worldPosition.getZ() + 0.5D;
		int lifeTime = 10 + level.random.nextInt(4);
		float scale = 0.17F + level.random.nextFloat() * 0.03F;
		float velocity = 0.04F + level.random.nextFloat() * 0.02F;

		RenderUtilities.create(ParticleRegistry.SPARKLE_PARTICLE).setScale(scale * 2.0F, 0.0F).setLifetime(lifeTime).setAlpha(0.2F).setColor(firstColor, secondColor).setColorCurveMultiplier(1.5F).spawn(level, x, y, z);
		RenderUtilities.create(ParticleRegistry.WISP_PARTICLE).setScale(scale, 0.0F).setLifetime(lifeTime).setAlpha(0.9F, 0.5F).setColor(firstColor, secondColor).setColorCurveMultiplier(2.0F).addVelocity(0.0D, velocity, 0.0D).setSpin(level.random.nextFloat() * 0.5F).spawn(level, x, y, z);

		if(level.getGameTime() % 2L == 0L && level.random.nextFloat() < 0.25F)
		{
			y += 0.05000000074505806;
			RenderUtilities.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE).setScale(0.75F, 0.0F).setColor(firstColor, secondColor).setColorCurveMultiplier(3.0F).randomOffset(0.15000000596046448D, 0.25D).addVelocity(0.0D, 0.019999999552965164D, 0.0D).spawn(level, x, y, z);
			RenderUtilities.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE).setScale(0.5F, 0.0F).setColor(firstColor, secondColor).setColorCurveMultiplier(3.0F).randomOffset(0.10000000149011612D, 0.25D).addVelocity(0.0D, velocity, 0.0D).spawn(level, x, y, z);
		}
	}

	@Override
	public BlockEntityType<?> getType()
	{
		return MalumCompat.ETHER_CEILING_TORCH.get();
	}
}
