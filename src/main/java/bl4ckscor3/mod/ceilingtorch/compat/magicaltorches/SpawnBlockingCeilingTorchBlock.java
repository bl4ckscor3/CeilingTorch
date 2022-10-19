package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.GrandTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MediumTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MegaTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.SmallTorch;
import de.geheimagentnr1.magical_torches.elements.capabilities.ModCapabilities;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.ISpawnBlockerFactory;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.SpawnBlockingCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpawnBlockingCeilingTorchBlock extends CeilingTorchBlock implements SimpleWaterloggedBlock {
	private final ISpawnBlockerFactory spawnBlockerFactory;
	private final String name;
	private final VoxelShape shape;

	public SpawnBlockingCeilingTorchBlock(Block.Properties properties, String spawnBlockRegistryName, ISpawnBlockerFactory spawnBlockerFactory, Supplier<Block> originalBlock) {
		super(properties, null, originalBlock);

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
		SpawnBlockingCapability.registerSpawnBlocker(new ResourceLocation(CeilingTorch.MODID, spawnBlockRegistryName), this.spawnBlockerFactory = spawnBlockerFactory);
		name = spawnBlockRegistryName;
		shape = switch (name) {
			case SmallTorch.registry_name -> Block.box(7.0D, 6.0D, 7.0D, 9.0D, 16.0D, 9.0D);
			case MediumTorch.registry_name -> Block.box(6.5D, 5.0D, 6.5D, 9.5D, 16.0D, 9.5D);
			case GrandTorch.registry_name -> Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
			case MegaTorch.registry_name -> Block.box(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);
			default -> Shapes.block();
		};
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shape;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.addSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onRemove(state, world, pos, newState, isMoving);

		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.removeSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		BlockState state = context.getLevel().getBlockState(pos);

		if (state.getBlock() == this)
			return state.setValue(BlockStateProperties.WATERLOGGED, false);
		else
			return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(pos).getType() == Fluids.WATER);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(BlockStateProperties.WATERLOGGED))
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}
}
