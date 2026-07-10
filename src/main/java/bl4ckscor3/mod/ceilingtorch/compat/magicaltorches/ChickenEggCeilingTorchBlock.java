package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocks;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.chicken_egg_spawning.ChickenEggTorch;
import de.geheimagentnr1.magical_torches.elements.capabilities.ModAttachments;
import de.geheimagentnr1.magical_torches.elements.capabilities.chicken_egg_spawning.ChickenEggSpawningCapability;
import de.geheimagentnr1.magical_torches.elements.capabilities.chicken_egg_spawning.chicken_egg_blockers.ChickenEggTorchBlocker;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.ISpawnBlockerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChickenEggCeilingTorchBlock extends CeilingTorchBlock {
	private static final VoxelShape SHAPE = Block.box(6.5D, 6.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	private final ISpawnBlockerFactory spawnBlockerFactory = ChickenEggTorchBlocker::new;

	public ChickenEggCeilingTorchBlock(Properties properties) {
		super(properties, null, ModBlocks.CHICKEN_EGG_TORCH);
		ChickenEggSpawningCapability.registerChickenEggBlocker(ResourceLocation.fromNamespaceAndPath(CeilingTorch.MODID, ChickenEggTorch.registry_name), spawnBlockerFactory);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!level.isClientSide) {
			ChickenEggSpawningCapability capability = level.getData(ModAttachments.CHICKEN_EGG_SPAWNING);
			capability.addSpawnBlocker(spawnBlockerFactory.build(pos));
		}
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!level.isClientSide) {
			ChickenEggSpawningCapability capability = level.getData(ModAttachments.CHICKEN_EGG_SPAWNING);
			capability.removeSpawnBlocker(spawnBlockerFactory.build(pos));
		}

		super.onRemove(state, level, pos, newState, isMoving);
	}
}
