package bl4ckscor3.mod.ceilingtorch.compat.malum;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

@Mod(value = CeilingTorch.MODID, dist = Dist.CLIENT)
public class MalumCompatClient {
	public MalumCompatClient(IEventBus modEventBus) {
		if (ModList.get().isLoaded("malum"))
			modEventBus.addListener(MalumCompatColorHandler::registerColorHandlers);
	}
}
