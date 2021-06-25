package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.function.Supplier;

import com.teammetallurgy.atum.api.God;
import com.teammetallurgy.atum.blocks.lighting.AtumTorchBlock;
import com.teammetallurgy.atum.blocks.lighting.INebuTorch;
import com.teammetallurgy.atum.init.AtumParticles;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

public class AtumCeilingTorchBlock extends CeilingTorchBlock implements INebuTorch
{
	private final Supplier<Block> originalBlock;

	public AtumCeilingTorchBlock(int lightValue, IParticleData particleType, Supplier<Block> originalBlock)
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).setLightLevel(s -> lightValue).sound(SoundType.WOOD).lootFrom(originalBlock), particleType);

		this.originalBlock = originalBlock;
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(originalBlock.get());
	}

	@Override
	public boolean isNebuTorch()
	{
		return particleData != ParticleTypes.FLAME;
	}

	@Override
	public God getGod()
	{
		return AtumTorchBlock.GODS.get(particleData);
	}
}
