package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class RedstoneCeilingTorchBlock extends RedstoneTorchBlock {
	private final Supplier<Block> originalBlock;

	public RedstoneCeilingTorchBlock(Properties properties, Supplier<Block> originalBlock) {
		super(properties);

		this.originalBlock = originalBlock;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random) {
		return facing == Direction.UP && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, tickAccess, currentPos, facing, facingPos, facingState, random);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return canSupportCenter(world, pos.above(), Direction.DOWN);
	}

	@Override
	public int getSignal(BlockState state, BlockGetter access, BlockPos pos, Direction side) {
		return state.getValue(LIT) && Direction.DOWN != side ? 15 : 0;
	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter access, BlockPos pos, Direction side) {
		return side == Direction.UP ? state.getSignal(access, pos, side) : 0;
	}

	@Override
	protected boolean hasNeighborSignal(Level world, BlockPos pos, BlockState state) {
		return world.hasSignal(pos.above(), Direction.UP);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
		if (state.getValue(LIT)) {
			double x = pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
			double y = pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D;
			double z = pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;

			world.addParticle(DustParticleOptions.REDSTONE, x, y - 0.25D, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		return new ItemStack(originalBlock.get());
	}

	@Override
	protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
		var drops = getOriginalBlock().getLootTable();

		if (drops.isEmpty())
			return Collections.emptyList();
		else {
			LootParams lootParams = params.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			ServerLevel level = lootParams.getLevel();
			LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(drops.get());

			return lootTable.getRandomItems(lootParams);
		}
	}

	public Block getOriginalBlock() {
		return originalBlock.get();
	}
}
