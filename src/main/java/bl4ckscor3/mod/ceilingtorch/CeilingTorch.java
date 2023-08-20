package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus = Bus.MOD)
public class CeilingTorch {
	public static final String MODID = "ceilingtorch";
	private static final Map<String, ICeilingTorchCompat> COMPAT_LIST = new HashMap<>();
	private static Map<String, Supplier<ICeilingTorchCompat>> preliminaryCompatList = new HashMap<>();
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CeilingTorch.MODID);

	public CeilingTorch() {
		CompatConfig.init(ModLoadingContext.get());
		BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		preliminaryCompatList.put("minecraft", VanillaCompat::new);
		CompatConfig.getConfig().getBuiltInCompatList().forEach((configValue, compatInfo) -> {
			String modid = compatInfo.modid();

			if (configValue.get() && ModList.get().isLoaded(modid))
				preliminaryCompatList.put(modid, compatInfo.ceilingTorchCompat());
		});
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Entry<String, Supplier<ICeilingTorchCompat>> entry : preliminaryCompatList.entrySet()) {
			COMPAT_LIST.put(entry.getKey(), entry.getValue().get());
		}

		preliminaryCompatList = null;

		for (ICeilingTorchCompat compat : COMPAT_LIST.values()) {
			compat.registerBlocks(event);
		}
	}

	/**
	 * Adds ceiling torch compatibility for the mod with the given modid, if that mod is loaded
	 *
	 * @param modid The modid of the mod to add compatibility to
	 * @param compat The compatibility class
	 */
	public static void addCompat(String modid, Supplier<ICeilingTorchCompat> compat) {
		if (ModList.get().isLoaded(modid))
			preliminaryCompatList.put(modid, compat);
	}

	public static Map<String, ICeilingTorchCompat> getCompatList() {
		return COMPAT_LIST;
	}
}
