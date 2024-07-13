package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class UpgradeAquaticCompatClient {
	private UpgradeAquaticCompatClient() {}

	public static void addListeners() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(UpgradeAquaticCompatClient::onFMLClientSetup);
	}

	public static void onFMLClientSetup(FMLClientSetupEvent event) {
		RenderType translucent = RenderType.translucent();

		UpgradeAquaticCompat.CEILING_TORCHES.forEach(torch -> ItemBlockRenderTypes.setRenderLayer(torch.get(), translucent));
	}
}
