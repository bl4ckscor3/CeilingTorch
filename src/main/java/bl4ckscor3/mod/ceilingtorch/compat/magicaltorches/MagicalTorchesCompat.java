package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocksRegisterFactory;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.neoforged.neoforge.registries.RegistryObject;

public class MagicalTorchesCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> SMALL_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_small_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), SmallTorch.registry_name, SmallTorchSpawnBlocker::new, () -> ModBlocksRegisterFactory.SMALL_TORCH));
	public static final RegistryObject<Block> MEDIUM_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_medium_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), MediumTorch.registry_name, MediumTorchSpawnBlocker::new, () -> ModBlocksRegisterFactory.MEDIUM_TORCH));
	public static final RegistryObject<Block> GRAND_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_grand_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), GrandTorch.registry_name, GrandTorchSpawnBlocker::new, () -> ModBlocksRegisterFactory.GRAND_TORCH));
	public static final RegistryObject<Block> MEGA_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_mega_torch", () -> new SpawnBlockingCeilingTorchBlock(getProperties(), MegaTorch.registry_name, MegaTorchSpawnBlocker::new, () -> ModBlocksRegisterFactory.MEGA_TORCH));
	//@formatter:off
	public static final RegistryObject<Block> SOUND_MUFFLING_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_sound_muffling_torch", () -> new SoundMufflingCeilingTorchBlock(Block.Properties.of()
			.strength(3.0F)
			.sound(SoundType.WOOD)
			.noCollission()));
	//@formatter:on
	public static final RegistryObject<Block> CHICKEN_EGG_CEILING_TORCH = CeilingTorch.BLOCKS.register("magical_torches_chicken_egg_torch", () -> new ChickenEggCeilingTorchBlock(getProperties()));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(getRegistryName(ModBlocksRegisterFactory.SMALL_TORCH), SMALL_CEILING_TORCH.get())
					.put(getRegistryName(ModBlocksRegisterFactory.MEDIUM_TORCH), MEDIUM_CEILING_TORCH.get())
					.put(getRegistryName(ModBlocksRegisterFactory.GRAND_TORCH), GRAND_CEILING_TORCH.get())
					.put(getRegistryName(ModBlocksRegisterFactory.MEGA_TORCH), MEGA_CEILING_TORCH.get())
					.put(getRegistryName(ModBlocksRegisterFactory.SOUND_MUFFLING_TORCH), SOUND_MUFFLING_CEILING_TORCH.get())
					.put(getRegistryName(ModBlocksRegisterFactory.CHICKEN_EGG_TORCH), CHICKEN_EGG_CEILING_TORCH.get()).build();
			//@formatter:on
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		if (state.getBlock() instanceof SpawnBlockingCeilingTorchBlock)
			return state.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);

		return state;
	}

	private static Block.Properties getProperties() {
		return Block.Properties.of().strength(3.0F).sound(SoundType.WOOD).noCollission().lightLevel(state -> 15);
	}
}
