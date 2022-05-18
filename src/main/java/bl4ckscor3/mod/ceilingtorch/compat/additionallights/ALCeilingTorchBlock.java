package bl4ckscor3.mod.ceilingtorch.compat.additionallights;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.mgen256.al.FireTypes;
import com.mgen256.al.blocks.IHasFire;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

public class ALCeilingTorchBlock extends CeilingTorchBlock implements IHasFire
{
	public ALCeilingTorchBlock(Supplier<Block> originalBlock)
	{
		super(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.0F), null, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;
		ParticleOptions flameParticle = state.getValue(FIRE_TYPE) == FireTypes.SOUL ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, Entity entity)
	{
		return getOriginalBlock().getSoundType(state, level, pos, entity);
	}

	@Override
	public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos)
	{
		return getOriginalBlock().getLightEmission(state, level, pos);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		return getOriginalBlock().getDrops(state, builder);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FIRE_TYPE, PREVIOUS_FIRE_TYPE);
	}
}
