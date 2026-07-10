package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import java.util.function.Supplier;

import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JellyCeilingTorchBlock extends JellyTorchBlock {
	protected final JellyTorchType torchType;
	private final Supplier<Block> originalBlock;

	public JellyCeilingTorchBlock(Properties properties, JellyTorchType torchType, Supplier<Block> originalBlock) {
		super(properties.lootFrom(originalBlock), torchType);

		this.torchType = torchType;
		this.originalBlock = originalBlock;
	}

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
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
		return new ItemStack(getOriginalBlock());
	}

	public Block getOriginalBlock() {
		return originalBlock.get();
	}
}
