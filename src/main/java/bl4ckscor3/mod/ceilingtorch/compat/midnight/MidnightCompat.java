package bl4ckscor3.mod.ceilingtorch.compat.midnight;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mushroom.midnight.common.block.SporchBlock.SporchType;
import com.mushroom.midnight.common.registry.MidnightBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class MidnightCompat implements ICeilingTorchCompat
{
	public static Block bogshroomCeilingSporch;
	public static Block dewshroomCeilingSporch;
	public static Block nightshroomCeilingSporch;
	public static Block viridshroomCeilingSporch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(bogshroomCeilingSporch = new CeilingSporchBlock(SporchType.BOGSHROOM, Block.Properties.from(Blocks.TORCH), () -> MidnightBlocks.BOGSHROOM_SPORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "midnight_bogshroom_sporch")));
		event.getRegistry().register(dewshroomCeilingSporch = new CeilingSporchBlock(SporchType.DEWSHROOM, Block.Properties.from(Blocks.TORCH), () -> MidnightBlocks.DEWSHROOM_SPORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "midnight_dewshroom_sporch")));
		event.getRegistry().register(nightshroomCeilingSporch = new CeilingSporchBlock(SporchType.NIGHTSHROOM, Block.Properties.from(Blocks.TORCH), () -> MidnightBlocks.NIGHTSHROOM_SPORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "midnight_nightshroom_sporch")));
		event.getRegistry().register(viridshroomCeilingSporch = new CeilingSporchBlock(SporchType.VIRIDSHROOM, Block.Properties.from(Blocks.TORCH), () -> MidnightBlocks.VIRIDSHROOM_SPORCH)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "midnight_viridshroom_sporch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(MidnightBlocks.BOGSHROOM_SPORCH.getRegistryName(), bogshroomCeilingSporch,
					MidnightBlocks.DEWSHROOM_SPORCH.getRegistryName(), dewshroomCeilingSporch,
					MidnightBlocks.NIGHTSHROOM_SPORCH.getRegistryName(), nightshroomCeilingSporch,
					MidnightBlocks.VIRIDSHROOM_SPORCH.getRegistryName(), viridshroomCeilingSporch);
		}

		return placeEntries;
	}
}
