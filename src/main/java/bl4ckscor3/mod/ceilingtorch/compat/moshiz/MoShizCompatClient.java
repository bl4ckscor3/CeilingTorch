package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

@Mod(value = CeilingTorch.MODID, dist = Dist.CLIENT)
public class MoShizCompatClient {
	public MoShizCompatClient(IEventBus modEventBus) {
		if (ModList.get().isLoaded("ms")) {
			modEventBus.addListener(MoShizCompatClientRegistration::onParticleFactoryRegister);
			modEventBus.addListener(MoShizCompatClientRegistration::onColorHandlerBlock);
		}
	}
}
