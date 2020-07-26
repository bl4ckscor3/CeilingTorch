package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocks;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.GrandTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MediumTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MegaTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.SmallTorch;
import de.geheimagentnr1.magical_torches.elements.capabilities.ModCapabilities;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.ISpawnBlockFactory;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.SpawnBlockingCapability;
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

public class SpawnBlockingCeilingTorchBlock extends CeilingTorchBlock
{
	private final ISpawnBlockFactory spawnBlockFactory;
	private final String name;
	private final VoxelShape shape;

	public SpawnBlockingCeilingTorchBlock(Block.Properties properties, String spawnBlockRegistryName, ISpawnBlockFactory spawnBlockFactory)
	{
		super(properties);

		SpawnBlockingCapability.registerSpawnBlocker(new ResourceLocation(CeilingTorch.MODID, spawnBlockRegistryName), this.spawnBlockFactory = spawnBlockFactory);

		name = spawnBlockRegistryName;

		switch(name)
		{
			case SmallTorch.registry_name: shape = Block.makeCuboidShape(7.0D, 6.0D, 7.0D, 9.0D, 16.0D, 9.0D); break;
			case MediumTorch.registry_name: shape = Block.makeCuboidShape(6.5D, 5.0D, 6.5D, 9.5D, 16.0D, 9.5D); break;
			case GrandTorch.registry_name: shape = Block.makeCuboidShape(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D); break;
			case MegaTorch.registry_name: shape = Block.makeCuboidShape(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D); break;
			default: shape = VoxelShapes.fullCube();
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
	public PushReaction getPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public ResourceLocation getLootTable()
	{
		switch(name)
		{
			case SmallTorch.registry_name: return ModBlocks.SMALL_TORCH.getLootTable();
			case MediumTorch.registry_name: return ModBlocks.MEDIUM_TORCH.getLootTable();
			case GrandTorch.registry_name: return ModBlocks.GRAND_TORCH.getLootTable();
			case MegaTorch.registry_name: return ModBlocks.MEGA_TORCH.getLootTable();
			default: return super.getLootTable();
		}
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		switch(name)
		{
			case SmallTorch.registry_name: return new ItemStack(ModBlocks.SMALL_TORCH);
			case MediumTorch.registry_name: return new ItemStack(ModBlocks.MEDIUM_TORCH);
			case GrandTorch.registry_name: return new ItemStack(ModBlocks.GRAND_TORCH);
			case MegaTorch.registry_name: return new ItemStack(ModBlocks.MEGA_TORCH);
			default: return ItemStack.EMPTY;
		}
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.addSpawnBlocker(spawnBlockFactory.buildSpawnBlocker(pos)));
	}

	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		super.onReplaced(state, world, pos, newState, isMoving);

		world.getCapability(ModCapabilities.SPAWN_BLOCKING).ifPresent(capability -> capability.removeSpawnBlocker(spawnBlockFactory.buildSpawnBlocker(pos)));
	}
}
