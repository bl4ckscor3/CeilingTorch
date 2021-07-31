package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.Random;
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SpawnBlockingCeilingTorchBlock extends CeilingTorchBlock implements IWaterLoggable
{
	private final ISpawnBlockerFactory spawnBlockerFactory;
	private final String name;
	private final VoxelShape shape;

	public SpawnBlockingCeilingTorchBlock(Block.Properties properties, String spawnBlockRegistryName, ISpawnBlockerFactory spawnBlockerFactory, Supplier<Block> originalBlock)
	{
		super(properties, null, originalBlock);

		registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
		SpawnBlockingCapability.registerSpawnBlocker(new ResourceLocation(CeilingTorch.MODID, spawnBlockRegistryName), this.spawnBlockerFactory = spawnBlockerFactory);
		name = spawnBlockRegistryName;

		switch(name)
		{
			case SmallTorch.registry_name: shape = Block.box(7.0D, 6.0D, 7.0D, 9.0D, 16.0D, 9.0D); break;
			case MediumTorch.registry_name: shape = Block.box(6.5D, 5.0D, 6.5D, 9.5D, 16.0D, 9.5D); break;
			case GrandTorch.registry_name: shape = Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D); break;
			case MegaTorch.registry_name: shape = Block.box(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D); break;
			default: shape = VoxelShapes.block();
		}
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return shape;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return VoxelShapes.empty();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.addSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		super.onRemove(state, world, pos, newState, isMoving);

		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.removeSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		BlockPos pos = context.getClickedPos();
		BlockState state = context.getLevel().getBlockState(pos);

		if(state.getBlock() == this)
			return state.setValue(BlockStateProperties.WATERLOGGED, false);
		else
			return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, context.getLevel().getFluidState(pos).getType() == Fluids.WATER);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.getValue(BlockStateProperties.WATERLOGGED))
			world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.WATERLOGGED);
	}
}
