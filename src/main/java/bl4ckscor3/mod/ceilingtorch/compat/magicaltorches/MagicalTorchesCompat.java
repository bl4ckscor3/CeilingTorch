package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocks;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.GrandTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MediumTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.MegaTorch;
import de.geheimagentnr1.magical_torches.elements.blocks.torches.spawn_blocking.SmallTorch;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.spawn_blockers.GrandTorchSpawnBlocker;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.spawn_blockers.MediumTorchSpawnBlocker;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.spawn_blockers.MegaTorchSpawnBlocker;
import de.geheimagentnr1.magical_torches.elements.capabilities.spawn_blocking.spawn_blockers.SmallTorchSpawnBlocker;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MagicalTorchesCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<SpawnBlockingCeilingTorchBlock> SMALL_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_small_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), SmallTorch.registry_name, SmallTorchSpawnBlocker::new, ModBlocks.SMALL_TORCH));
	public static final DeferredBlock<SpawnBlockingCeilingTorchBlock> MEDIUM_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_medium_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), MediumTorch.registry_name, MediumTorchSpawnBlocker::new, ModBlocks.MEDIUM_TORCH));
	public static final DeferredBlock<SpawnBlockingCeilingTorchBlock> GRAND_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_grand_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), GrandTorch.registry_name, GrandTorchSpawnBlocker::new, ModBlocks.GRAND_TORCH));
	public static final DeferredBlock<SpawnBlockingCeilingTorchBlock> MEGA_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_mega_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), MegaTorch.registry_name, MegaTorchSpawnBlocker::new, ModBlocks.MEGA_TORCH));
	public static final DeferredBlock<SoundMufflingCeilingTorchBlock> SOUND_MUFFLING_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_sound_muffling_torch", () -> new SoundMufflingCeilingTorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().pushReaction(PushReaction.DESTROY).strength(3.0F).sound(SoundType.WOOD)));
	public static final DeferredBlock<ChickenEggCeilingTorchBlock> CHICKEN_EGG_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_chicken_egg_torch", () -> new ChickenEggCeilingTorchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().pushReaction(PushReaction.DESTROY).strength(3.0F).sound(SoundType.WOOD)));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(ModBlocks.SMALL_TORCH.getId(), SMALL_CEILING_TORCH.get())
					.put(ModBlocks.MEDIUM_TORCH.getId(), MEDIUM_CEILING_TORCH.get())
					.put(ModBlocks.GRAND_TORCH.getId(), GRAND_CEILING_TORCH.get())
					.put(ModBlocks.MEGA_TORCH.getId(), MEGA_CEILING_TORCH.get())
					.put(ModBlocks.SOUND_MUFFLING_TORCH.getId(), SOUND_MUFFLING_CEILING_TORCH.get())
					.put(ModBlocks.CHICKEN_EGG_TORCH.getId(), CHICKEN_EGG_CEILING_TORCH.get()).build();
			//@formatter:on
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(PlayerInteractEvent.RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		if (state.getBlock() instanceof SpawnBlockingCeilingTorchBlock)
			return state.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);

		return state;
	}

	private static Block.Properties getProperties() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noCollission().pushReaction(PushReaction.DESTROY).lightLevel(state -> 15);
	}
}
