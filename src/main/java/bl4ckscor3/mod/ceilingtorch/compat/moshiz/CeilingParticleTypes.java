package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;

public class CeilingParticleTypes
{
	public static final BasicParticleType DYED_CEILING_FLAME = new BasicParticleType(false);
	public static final BasicParticleType DYED_CEILING_SMOKE = new BasicParticleType(false);

	public static void onRegisterParticleTypes(RegistryEvent.Register<ParticleType<?>> event)
	{
		event.getRegistry().register(DYED_CEILING_FLAME.setRegistryName("dyed_ceiling_flame"));
		event.getRegistry().register(DYED_CEILING_SMOKE.setRegistryName("dyed_ceiling_smoke"));
	}
}
