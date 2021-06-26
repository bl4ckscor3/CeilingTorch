package bl4ckscor3.mod.ceilingtorch.compat.midnight;

import java.util.Random;
import java.util.function.Supplier;

import com.mushroom.midnight.common.block.SporchBlock.SporchType;
import com.mushroom.midnight.common.registry.MidnightParticleTypes;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CeilingSporchBlock extends CeilingTorchBlock
{
	private final SporchType type;

	public CeilingSporchBlock(SporchType type, Block.Properties properties, Supplier<Block> originalBlock)
	{
		super(properties, originalBlock);

		this.type = type;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(getParticleType(), x, y, z, 0.0D, 0.004D, 0.0D);
	}

	public BasicParticleType getParticleType()
	{
		switch(type)
		{
			case BOGSHROOM: return MidnightParticleTypes.BOGSHROOM_SPORCH;
			case DEWSHROOM: return MidnightParticleTypes.DEWSHROOM_SPORCH;
			case NIGHTSHROOM: return MidnightParticleTypes.NIGHTSHROOM_SPORCH;
			default: case VIRIDSHROOM: return MidnightParticleTypes.VIRIDSHROOM_SPORCH;
		}
	}
}
