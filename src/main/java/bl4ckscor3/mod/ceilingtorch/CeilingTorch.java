package bl4ckscor3.mod.ceilingtorch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.ilikewood.ILikeWoodCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

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

		if(ModList.get().isLoaded("ilikewood"))
			compatList.add(ILikeWoodCompat::new);
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
	public static void onInterModProcess(InterModProcessEvent event)
	{
		for(Supplier<ICeilingTorchCompat> compat : compatList)
		{
			compat.get().registerPlaceEntries();
		}
	}
}
