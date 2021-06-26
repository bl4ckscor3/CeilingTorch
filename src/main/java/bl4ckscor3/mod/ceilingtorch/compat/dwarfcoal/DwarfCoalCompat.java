package bl4ckscor3.mod.ceilingtorch.compat.dwarfcoal;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import sora.dwarfcoal.init.ModBlocks;

public class DwarfCoalCompat implements ICeilingTorchCompat
{
	public static Block dwarfCeilingTorch;
	public static Block stoneDwarfCeilingTorch;
	public static Block stoneCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(dwarfCeilingTorch = new DwarfCeilingTorchBlock(Block.Properties.from(Blocks.TORCH)
				.setLightLevel(state -> 10),
				ModBlocks.DWARF_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_dwarf_torch")));
		event.getRegistry().register(stoneDwarfCeilingTorch = new DwarfCeilingTorchBlock(Block.Properties.from(Blocks.TORCH)
				.setLightLevel(state -> 10),
				ModBlocks.STONE_DWARF_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_stone_dwarf_torch")));
		event.getRegistry().register(stoneCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH)
				.setLightLevel(state -> 14),
				ParticleTypes.FLAME, ModBlocks.STONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "dwarfcoal_stone_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(ModBlocks.DWARF_TORCH.get().getRegistryName(), dwarfCeilingTorch,
					ModBlocks.STONE_DWARF_TORCH.get().getRegistryName(), stoneDwarfCeilingTorch,
					ModBlocks.STONE_TORCH.get().getRegistryName(), stoneCeilingTorch);
		}

		return placeEntries;
	}
}
