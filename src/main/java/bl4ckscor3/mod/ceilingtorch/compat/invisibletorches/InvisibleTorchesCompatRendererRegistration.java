package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class InvisibleTorchesCompatRendererRegistration {
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(InvisibleTorchesCompat.CEILING_INVISIBLE_TORCH_BLOCK_ENTITY.get(), InvisibleCeilingTorchRenderer::new);
		event.registerBlockEntityRenderer(InvisibleTorchesCompat.CEILING_INVISIBLE_SOUL_TORCH_BLOCK_ENTITY.get(), InvisibleCeilingSoulTorchRenderer::new);
	}
}
