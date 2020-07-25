package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UpgradeAquaticCompatClient
{
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		RenderType translucent = RenderType.getTranslucent();

		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.pinkJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.purpleJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.blueJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.greenJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.yellowJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.orangeJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.redJellyTorch, translucent);
		RenderTypeLookup.setRenderLayer(UpgradeAquaticCompat.whiteJellyTorch, translucent);
	}
}
