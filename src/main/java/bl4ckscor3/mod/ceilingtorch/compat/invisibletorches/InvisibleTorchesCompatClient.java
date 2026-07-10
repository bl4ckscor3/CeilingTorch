package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

@Mod(value = CeilingTorch.MODID, dist = Dist.CLIENT)
public class InvisibleTorchesCompatClient {
	public InvisibleTorchesCompatClient(IEventBus modEventBus) {
		if (ModList.get().isLoaded("it"))
			modEventBus.addListener(InvisibleTorchesCompatRendererRegistration::onRegisterRenderers);
	}

}
