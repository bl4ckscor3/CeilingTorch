package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MoShizCompatClient
{
	public static void addListeners()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(MoShizCompatClient::onParticleFactoryRegister);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(MoShizCompatClient::onColorHandlerBlock);
	}

	public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event)
	{
		Minecraft.getInstance().particleEngine.register(CeilingParticleTypes.DYED_CEILING_FLAME, DyedCeilingFlameParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(CeilingParticleTypes.DYED_CEILING_SMOKE, DyedCeilingSmokeParticle.Factory::new);
	}

	public static void onColorHandlerBlock(ColorHandlerEvent.Block event)
	{
		MoShizCompat.ceilingTorchParticleColors.forEach((torch, color) -> event.getBlockColors().register((state, level, pos, tintIndex) -> color, torch));
	}
}
