package bl4ckscor3.mod.ceilingtorch.compat.malum;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MalumCompatClient
{
	public static void addListeners()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(MalumCompatClient::onFMLClientSetup);
	}

	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		Minecraft.getInstance().getBlockColors().register((state, level, pos, tintIndex) -> {
			if(tintIndex == 0 && level.getBlockEntity(pos) instanceof CeilingEtherTorchBlockEntity be && be.firstColor != null)
				return be.firstColor.getRGB();

			return 0xFFFFFF;
		}, MalumCompat.etherCeilingTorch, MalumCompat.iridescentEtherCeilingTorch);
	}
}
