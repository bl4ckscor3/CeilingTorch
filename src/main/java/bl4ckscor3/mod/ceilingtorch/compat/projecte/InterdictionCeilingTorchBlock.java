package bl4ckscor3.mod.ceilingtorch.compat.projecte;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class InterdictionCeilingTorchBlock extends CeilingTorchBlock implements EntityBlock
{
	public InterdictionCeilingTorchBlock(Properties properties, ParticleOptions particleData, Supplier<Block> originalBlock)
	{
		super(properties, particleData, originalBlock);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		return new InterdictionCeilingTorchBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
	{
		return type == ProjectECompat.INTERDICTION_CEILING_TORCH_BLOCK_ENTITY.get() ? (l, p, s, be) -> InterdictionCeilingTorchBlockEntity.tick(l, p, s, (InterdictionCeilingTorchBlockEntity)be) : null;
	}
}
