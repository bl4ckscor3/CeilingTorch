package bl4ckscor3.mod.ceilingtorch.compat.extlights;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ToggleableUpsideDownGroundBlock extends UpsideDownGroundBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public ToggleableUpsideDownGroundBlock(Properties properties, Supplier<Block> originalBlock, Supplier<VoxelShape> shapeSupplier) {
		super(properties, originalBlock, shapeSupplier);
		registerDefaultState(stateDefinition.any().setValue(LIT, true));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		SoundEvent sound;
		if (state.getValue(LIT)) {
			sound = SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF;
		}
		else {
			sound = SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON;
		}

		level.setBlock(pos, state.cycle(BlockStateProperties.LIT), Block.UPDATE_ALL_IMMEDIATE);
		level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
		level.playSound(player, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
