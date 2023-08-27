package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid = CeilingTorch.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class CeilingTorchClient {
	@SuppressWarnings("removal")
	@SubscribeEvent
	public static void onInterModProcess(InterModProcessEvent event) {
		for (ICeilingTorchCompat compat : CeilingTorch.getCompatList().values()) {
			for (Block block : compat.getPlaceEntries().values()) {
				if (compat.hasCutoutMippedRenderType(block))
					ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
			}
		}
	}

	@SubscribeEvent
	public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
		if (!CeilingTorch.isModCompatActive("ms")) {
			event.registerSpriteSet(CeilingTorch.MO_SHIZ_DYED_CEILING_FLAME.get(), FlameParticle.Provider::new);
			event.registerSpriteSet(CeilingTorch.MO_SHIZ_DYED_CEILING_SMOKE.get(), SmokeParticle.Provider::new);
		}
	}
}
