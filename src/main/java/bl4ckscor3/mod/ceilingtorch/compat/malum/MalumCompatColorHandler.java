package bl4ckscor3.mod.ceilingtorch.compat.malum;

import net.minecraft.util.FastColor;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class MalumCompatColorHandler {
	public static void registerColorHandlers(RegisterColorHandlersEvent.Block event) {
		event.register((state, level, pos, tintIndex) -> {
			if (tintIndex == 1 && level.getBlockEntity(pos) instanceof CeilingEtherTorchBlockEntity be && be.firstColor != null)
				return FastColor.ARGB32.opaque(be.firstColor.rgb());

			return 0xFFFFFFFF;
		}, MalumCompat.ETHER_CEILING_TORCH.get(), MalumCompat.IRIDESCENT_ETHER_CEILING_TORCH.get());
	}
}
