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
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class MagicalTorchesCompat implements ICeilingTorchCompat
{
	public static Block smallCeilingTorch;
	public static Block mediumCeilingTorch;
	public static Block grandCeilingTorch;
	public static Block megaCeilingTorch;
	public static Block soundMufflingCeilingTorch;
	public static Block chickenEggCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(smallCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), SmallTorch.registry_name, SmallTorchSpawnBlocker::new).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_small_torch")));
		event.getRegistry().register(mediumCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), MediumTorch.registry_name, MediumTorchSpawnBlocker::new).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_medium_torch")));
		event.getRegistry().register(grandCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), GrandTorch.registry_name, GrandTorchSpawnBlocker::new).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_grand_torch")));
		event.getRegistry().register(megaCeilingTorch = new SpawnBlockingCeilingTorchBlock(getProperties(), MegaTorch.registry_name, MegaTorchSpawnBlocker::new).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_mega_torch")));
		event.getRegistry().register(soundMufflingCeilingTorch = new SoundMufflingCeilingTorchBlock(getProperties()).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_sound_muffling_torch")));
		event.getRegistry().register(chickenEggCeilingTorch = new ChickenEggCeilingTorchBlock(getProperties()).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "magical_torches_chicken_egg_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(ModBlocks.SMALL_TORCH.getRegistryName(), smallCeilingTorch)
					.put(ModBlocks.MEDIUM_TORCH.getRegistryName(), mediumCeilingTorch)
					.put(ModBlocks.GRAND_TORCH.getRegistryName(), grandCeilingTorch)
					.put(ModBlocks.MEGA_TORCH.getRegistryName(), megaCeilingTorch)
					.put(ModBlocks.SOUND_MUFFLING_TORCH.getRegistryName(), soundMufflingCeilingTorch)
					.put(ModBlocks.CHICKEN_EGG_TORCH.getRegistryName(), chickenEggCeilingTorch).build();
		}

		return placeEntries;
	}

	private Block.Properties getProperties()
	{
		return Block.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).doesNotBlockMovement().lightValue(15);
	}
}
