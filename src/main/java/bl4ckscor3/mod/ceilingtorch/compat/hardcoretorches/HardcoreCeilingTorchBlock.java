package bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

import com.github.wolfiewaffle.hardcore_torches.block.HardcoreFloorTorchBlock;
import com.github.wolfiewaffle.hardcore_torches.blockentity.TorchBlockEntity;
import com.github.wolfiewaffle.hardcore_torches.config.Config;
import com.github.wolfiewaffle.hardcore_torches.util.ETorchState;
import com.github.wolfiewaffle.hardcore_torches.util.TorchGroup;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HardcoreCeilingTorchBlock extends HardcoreFloorTorchBlock {
	private final Supplier<? extends Block> originalBlock;

	public HardcoreCeilingTorchBlock(Properties properties, SimpleParticleType particle, ETorchState burnState, TorchGroup group, IntSupplier maxFuel, Supplier<? extends Block> originalBlock) {
		super(properties.lootFrom(originalBlock), particle, burnState, group, maxFuel);

		this.originalBlock = originalBlock;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		//the check in AbstractHardcoreTorchBlock checks the block entity type, not the block entity class
		if (Config.fuelMessage.get() && !level.isClientSide && player.getItemInHand(hand).isEmpty() && level.getBlockEntity(pos) instanceof TorchBlockEntity tbe)
			player.displayClientMessage(Component.literal("Fuel: " + tbe.getFuel()), true);

		return super.use(state, level, pos, player, hand, hit);
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
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		if (particle != null) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.45D;
			double z = pos.getZ() + 0.5D;

			level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return new ItemStack(getOriginalBlock());
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (burnState == ETorchState.LIT || burnState == ETorchState.SMOLDERING)
			return type == HardcoreTorchesCompat.CEILING_TORCH_BLOCK_ENTITY.get() ? (l, p, s, be) -> ((TorchBlockEntity) be).tick() : super.getTicker(level, state, type);

		return null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HardcoreCeilingTorchBlockEntity(pos, state);
	}

	public Block getOriginalBlock() {
		return originalBlock.get();
	}
}
