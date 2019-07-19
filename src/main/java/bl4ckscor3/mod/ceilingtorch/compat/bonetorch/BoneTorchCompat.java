package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class BoneTorchCompat implements ICeilingTorchCompat
{
	public static final Block BONETORCH_BONETORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockCeilingBoneTorch().setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bonetorch_bonetorch")).setTranslationKey("bonetorch:bonetorch"));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(new ResourceLocation("bonetorch", "bonetorch"), BONETORCH_BONETORCH);
	}
}
