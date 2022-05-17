package bl4ckscor3.mod.ceilingtorch.compat.additionallights;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.mgen256.al.FireTypes;
import com.mgen256.al.blocks.IHasFire;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ALCeilingTorchBlock extends CeilingTorchBlock implements IHasFire
{
	public ALCeilingTorchBlock(Supplier<Block> originalBlock)
	{
		super(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.0F), null, originalBlock);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;
		IParticleData flameParticle = state.getValue(FIRE_TYPE) == FireTypes.SOUL ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, Entity entity)
	{
		return getOriginalBlock().getSoundType(state, world, pos, entity);
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
	{
		return getOriginalBlock().getLightValue(state, world, pos);
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder)
	{
		return getOriginalBlock().getDrops(state, builder);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FIRE_TYPE, PREVIOUS_FIRE_TYPE);
	}
}
