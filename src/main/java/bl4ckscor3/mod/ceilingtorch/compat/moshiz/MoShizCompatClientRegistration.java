package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class MoShizCompatClientRegistration {
	public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(MoShizCompat.DYED_CEILING_FLAME.get(), DyedCeilingFlameParticle.Factory::new);
		event.registerSpriteSet(MoShizCompat.DYED_CEILING_SMOKE.get(), DyedCeilingSmokeParticle.Factory::new);
	}

	public static void onColorHandlerBlock(RegisterColorHandlersEvent.Block event) {
		MoShizCompat.ceilingTorchParticleColors.forEach((torch, color) -> event.register((state, level, pos, tintIndex) -> color, torch));
	}
}
