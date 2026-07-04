package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class InvisibleTorchesCompatClient {
	private InvisibleTorchesCompatClient() {}

	public static void addListeners() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(InvisibleTorchesCompatClient::onRegisterRenderers);
	}

	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(InvisibleTorchesCompat.CEILING_INVISIBLE_TORCH_BLOCK_ENTITY.get(), InvisibleCeilingTorchRenderer::new);
		event.registerBlockEntityRenderer(InvisibleTorchesCompat.CEILING_INVISIBLE_SOUL_TORCH_BLOCK_ENTITY.get(), InvisibleCeilingSoulTorchRenderer::new);
	}
}
