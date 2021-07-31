package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.function.Supplier;

import com.teammetallurgy.atum.api.God;
import com.teammetallurgy.atum.blocks.lighting.AtumTorchBlock;
import com.teammetallurgy.atum.blocks.lighting.INebuTorch;
import com.teammetallurgy.atum.init.AtumParticles;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

public class AtumCeilingTorchBlock extends CeilingTorchBlock implements INebuTorch
{
	public AtumCeilingTorchBlock(int lightValue, ParticleOptions particleType, Supplier<Block> originalBlock)
	{
		super(Block.Properties.of(Material.DECORATION).noCollission().strength(0.0F).lightLevel(s -> lightValue).sound(SoundType.WOOD), particleType, originalBlock);
	}

	public AtumCeilingTorchBlock(int lightValue, Supplier<Block> originalBlock)
	{
		this(lightValue, ParticleTypes.FLAME, originalBlock);
	}

	public AtumCeilingTorchBlock(God god, Supplier<Block> originalBlock)
	{
		this(14, god == null ? AtumParticles.NEBU_FLAME : AtumTorchBlock.GOD_FLAMES.get(god), originalBlock);
	}

	@Override
	public boolean isNebuTorch()
	{
		return flameParticle != ParticleTypes.FLAME;
	}

	@Override
	public God getGod()
	{
		return AtumTorchBlock.GODS.get(flameParticle);
	}
}
