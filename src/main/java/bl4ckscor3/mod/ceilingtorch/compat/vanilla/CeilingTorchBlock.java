package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CeilingTorchBlock extends TorchBlock
{
	public static final VoxelShape CEILING_SHAPE = Block.box(6.0D, 6.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final Supplier<? extends Block> originalBlock;

	public CeilingTorchBlock(Block.Properties properties, ParticleOptions particleData, Supplier<? extends Block> originalBlock)
	{
		super(properties.lootFrom(originalBlock), particleData);

		this.originalBlock = originalBlock;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		return canSupportCenter(level, pos.above(), Direction.DOWN);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player)
	{
		return new ItemStack(getOriginalBlock());
	}

	public Block getOriginalBlock()
	{
		return originalBlock.get();
	}
}
