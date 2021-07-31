package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UpgradeAquaticCompatClient
{
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		RenderType translucent = RenderType.translucent();

		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.pinkJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.purpleJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.blueJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.greenJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.yellowJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.orangeJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.redJellyTorch, translucent);
		ItemBlockRenderTypes.setRenderLayer(UpgradeAquaticCompat.whiteJellyTorch, translucent);
	}
}
