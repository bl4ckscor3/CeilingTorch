package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid = CeilingTorch.MODID, value = Dist.CLIENT)
public class CeilingTorchClient {
	private CeilingTorchClient() {}

	@SubscribeEvent
	public static void onInterModProcess(InterModProcessEvent event) {
		for (ICeilingTorchCompat compat : CeilingTorch.getCompatList().values()) {
			for (Block block : compat.getPlaceEntries().values()) {
				if (compat.hasCutoutMippedRenderType(block))
					ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
			}
		}
	}
}
