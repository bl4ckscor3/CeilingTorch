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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChickenEggCeilingTorchBlock extends CeilingTorchBlock
{
	private static final VoxelShape SHAPE = Block.makeCuboidShape(6.5D, 6.0D, 6.5D, 9.5D, 16.0D, 9.5D);
	private final ISpawnBlockerFactory spawnBlockerFactory;

	public ChickenEggCeilingTorchBlock(Properties properties)
	{
		super(properties);
		ChickenEggSpawningCapability.registerChickenEggBlocker(new ResourceLocation(CeilingTorch.MODID, ChickenEggTorch.registry_name), spawnBlockerFactory = ChickenEggTorchBlocker::new);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return VoxelShapes.empty();
	}

	@Override
	public PushReaction getPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.CHICKEN_EGG_SPAWNING).ifPresent(capability -> capability.addSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		super.onReplaced(state, world, pos, newState, isMoving);

		world.getCapability(ModCapabilities.CHICKEN_EGG_SPAWNING).ifPresent(capability -> capability.removeSpawnBlocker(spawnBlockerFactory.build(pos)));
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return ModBlocks.CHICKEN_EGG_TORCH.getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ModBlocks.CHICKEN_EGG_TORCH);
	}
}
