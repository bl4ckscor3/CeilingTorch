package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UpgradeAquaticCompatClient {
	@SuppressWarnings("removal")
	public static void onFMLClientSetup(FMLClientSetupEvent event) {
		RenderType translucent = RenderType.translucent();

		UpgradeAquaticCompat.CEILING_TORCHES.forEach(torch -> ItemBlockRenderTypes.setRenderLayer(torch.get(), translucent));
	}
}
