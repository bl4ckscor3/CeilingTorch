package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.magicaltorches.MagicalTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.torchbandolier.TorchBandolierCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	private static final Map<String,ICeilingTorchCompat> COMPAT_LIST = new HashMap<>();
	private static Map<String,Supplier<ICeilingTorchCompat>> preliminaryCompatList = new HashMap<>();

	public CeilingTorch()
	{
		preliminaryCompatList.put("minecraft", VanillaCompat::new);

		//cannot use addCompat because then the compat class will be classloaded which may crash if the mod is not present
		if(ModList.get().isLoaded("bonetorch"))
			preliminaryCompatList.put("bonetorch", BoneTorchCompat::new);

		if(ModList.get().isLoaded("magical_torches"))
			preliminaryCompatList.put("magical_torches", MagicalTorchesCompat::new);

		if(ModList.get().isLoaded("torchbandolier"))
			preliminaryCompatList.put("torchbandolier", TorchBandolierCompat::new);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Entry<String,Supplier<ICeilingTorchCompat>> entry : preliminaryCompatList.entrySet())
		{
			COMPAT_LIST.put(entry.getKey(), entry.getValue().get());
		}

		preliminaryCompatList = null;

		for(ICeilingTorchCompat compat : COMPAT_LIST.values())
		{
			compat.registerBlocks(event);
		}
	}

	/**
	 * Adds ceiling torch compatibility for the mod with the given modid, if that mod is loaded
	 * @param modid The modid of the mod to add compatibility to
	 * @param compat The compatibility class
	 */
	public static void addCompat(String modid, Supplier<ICeilingTorchCompat> compat)
	{
		if(ModList.get().isLoaded(modid))
			preliminaryCompatList.put(modid, compat);
	}

	public static Map<String,ICeilingTorchCompat> getCompatList()
	{
		return COMPAT_LIST;
	}
}
