package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocks;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.chicken_egg_spawning.ChickenEggTorch;
import de.geheimagentnr1.magical_torches.elements.capabilities.ModCapabilities;
import de.geheimagentnr1.magical_torches.elements.capabilities.chicken_egg_spawning.ChickenEggSpawningCapability;
import de.geheimagentnr1.magical_torches.elements.capabilities.chicken_egg_spawning.chicken_egg_blockers.ChickenEggTorchBlocker;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.ISpawnBlockerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChickenEggCeilingTorchBlock extends CeilingTorchBlock
{
	private static final VoxelShape SHAPE = Block.box(6.5D, 6.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	private final ISpawnBlockerFactory spawnBlockerFactory;

	public ChickenEggCeilingTorchBlock(Properties properties)
	{
		super(properties, null, () -> ModBlocks.CHICKEN_EGG_TORCH);
		ChickenEggSpawningCapability.registerChickenEggBlocker(new ResourceLocation(CeilingTorch.MODID, ChickenEggTorch.registry_name), spawnBlockerFactory = ChickenEggTorchBlocker::new);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return Shapes.empty();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.CHICKEN_EGG_SPAWNING).ifPresent(capability -> capability.addSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		super.onRemove(state, world, pos, newState, isMoving);

		world.getCapability(ModCapabilities.CHICKEN_EGG_SPAWNING).ifPresent(capability -> capability.removeSpawnBlocker(spawnBlockerFactory.build(pos)));
	}
}
