package bl4ckscor3.mod.ceilingtorch.compat.malum;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MalumCompatClient {
	public static void addListeners() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(MalumCompatClient::registerColorHandlers);
	}

	public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
		event.register((state, level, pos, tintIndex) -> {
			if (tintIndex == 1 && level.getBlockEntity(pos) instanceof CeilingEtherTorchBlockEntity be && be.firstColor != null)
				return be.firstColor.getRGB();

			return 0xFFFFFF;
		}, MalumCompat.ETHER_CEILING_TORCH.get(), MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get());
	}
}
