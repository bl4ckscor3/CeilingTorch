package bl4ckscor3.mod.ceilingtorch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	public static final String NAME = "Ceiling Torch";
	private static List<Supplier<ICeilingTorchCompat>> compatList = new ArrayList<>();

	public CeilingTorch()
	{
		compatList.add(VanillaCompat::new);

		//		if(ModList.get().isLoaded("ilikewood"))
		//			compatList.add(ILikeWoodCompat::new);
		//
		//		if(ModList.get().isLoaded("bonetorch"))
		//			compatList.add(BoneTorchCompat::new);
		//
		//		if(ModList.get().isLoaded("torchmaster"))
		//			compatList.add(TorchmasterCompat::new);
		//
		//		if(ModList.get().isLoaded("silentgear"))
		//			compatList.add(SilentGearCompat::new);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Supplier<ICeilingTorchCompat> compat : compatList)
		{
			compat.get().registerBlocks(event);
		}
	}

	@SubscribeEvent
	public static void onInterModEnqueue(InterModEnqueueEvent event)
	{
		for(Supplier<ICeilingTorchCompat> compat : compatList)
		{
			compat.get().registerPlaceEntries();
		}
	}
}
