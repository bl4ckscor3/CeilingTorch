package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.event.RegistryEvent;

public class CeilingParticleTypes
{
	public static final SimpleParticleType DYED_CEILING_FLAME = new SimpleParticleType(false);
	public static final SimpleParticleType DYED_CEILING_SMOKE = new SimpleParticleType(false);

	public static void onRegisterParticleTypes(RegistryEvent.Register<ParticleType<?>> event)
	{
		event.getRegistry().register(DYED_CEILING_FLAME.setRegistryName("dyed_ceiling_flame"));
		event.getRegistry().register(DYED_CEILING_SMOKE.setRegistryName("dyed_ceiling_smoke"));
	}
}
