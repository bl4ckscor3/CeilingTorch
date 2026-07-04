package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UltimateInvisibleCeilingTorchBlock extends CeilingTorchBlock {
	public UltimateInvisibleCeilingTorchBlock(Properties properties, ParticleOptions particleData, Supplier<? extends Block> originalBlock) {
		super(properties, particleData, originalBlock);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return context.isHoldingItem(getOriginalBlock().asItem()) ? CEILING_SHAPE : Shapes.empty();
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
	}
}
