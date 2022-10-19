package bl4ckscor3.mod.ceilingtorch.compat.reliquary;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import reliquary.blocks.InterdictionTorchBlock;
import reliquary.init.ModBlocks;

public class InterdictionCeilingTorchBlock extends InterdictionTorchBlock {
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.UP && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportCenter(level, pos.above(), Direction.DOWN);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		level.addParticle(ParticleTypes.ENTITY_EFFECT, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return new ItemStack(ModBlocks.INTERDICTION_TORCH.get());
	}

	@Override
	public ResourceLocation getLootTable() {
		return ModBlocks.INTERDICTION_TORCH.get().getLootTable();
	}
}
