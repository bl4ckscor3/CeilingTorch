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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class MagicalTorchesCompat implements ICeilingTorchCompat {
	public static Block smallCeilingTorch;
	public static Block mediumCeilingTorch;
	public static Block grandCeilingTorch;
	public static Block megaCeilingTorch;
	public static Block soundMufflingCeilingTorch;
	public static Block chickenEggCeilingTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(smallCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), SmallTorch.registry_name, SmallTorchSpawnBlocker::new, () -> ModBlocks.SMALL_TORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_small_torch")));
		event.getRegistry().register(mediumCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), MediumTorch.registry_name, MediumTorchSpawnBlocker::new, () -> ModBlocks.MEDIUM_TORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_medium_torch")));
		event.getRegistry().register(grandCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), GrandTorch.registry_name, GrandTorchSpawnBlocker::new, () -> ModBlocks.GRAND_TORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_grand_torch")));
		event.getRegistry().register(megaCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), MegaTorch.registry_name, MegaTorchSpawnBlocker::new, () -> ModBlocks.MEGA_TORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_mega_torch")));
		event.getRegistry().register(soundMufflingCeilingTorch = new SoundMufflingCeilingTorchBlock(Block.Properties.of(Material.WOOD)
				.strength(3.0F)
				.sound(SoundType.WOOD)
				.noCollission())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_sound_muffling_torch")));
		event.getRegistry().register(chickenEggCeilingTorch = new ChickenEggCeilingTorchBlock(getProperties())
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_chicken_egg_torch")));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(ModBlocks.SMALL_TORCH.getRegistryName(), smallCeilingTorch)
					.put(ModBlocks.MEDIUM_TORCH.getRegistryName(), mediumCeilingTorch)
					.put(ModBlocks.GRAND_TORCH.getRegistryName(), grandCeilingTorch)
					.put(ModBlocks.MEGA_TORCH.getRegistryName(), megaCeilingTorch)
					.put(ModBlocks.SOUND_MUFFLING_TORCH.getRegistryName(), soundMufflingCeilingTorch)
					.put(ModBlocks.CHICKEN_EGG_TORCH.getRegistryName(), chickenEggCeilingTorch).build();
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

	private Block.Properties getProperties() {
		return Block.Properties.of(Material.WOOD).strength(3.0F).sound(SoundType.WOOD).noCollission().lightLevel(state -> 15);
	}
}
