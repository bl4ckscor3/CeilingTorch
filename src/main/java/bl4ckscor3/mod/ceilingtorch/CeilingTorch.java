package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus = Bus.MOD)
public class CeilingTorch {
	public static final String MODID = "ceilingtorch";
	private static final Map<String, ICeilingTorchCompat> COMPAT_LIST = new HashMap<>();
	private static Map<String, Supplier<ICeilingTorchCompat>> preliminaryCompatList = new HashMap<>();
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
	private static boolean initialized = false;
	public static final RegistryObject<SimpleParticleType> MO_SHIZ_DYED_CEILING_FLAME = PARTICLE_TYPES.register("dyed_ceiling_flame", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> MO_SHIZ_DYED_CEILING_SMOKE = PARTICLE_TYPES.register("dyed_ceiling_smoke", () -> new SimpleParticleType(false));

	public CeilingTorch() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		CompatConfig.init(ModLoadingContext.get());
		BLOCKS.register(modBus);
		BLOCK_ENTITIES.register(modBus);
		PARTICLE_TYPES.register(modBus);
		preliminaryCompatList.put("minecraft", VanillaCompat::new);
		CompatConfig.getConfig().getBuiltInCompat().forEach((modid, compatInfo) -> {
			if (compatInfo.config().get() && ModList.get().isLoaded(modid))
				preliminaryCompatList.put(modid, compatInfo.ceilingTorchCompat().get());
		});
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event) {
		if (!initialized) {
			for (Entry<String, Supplier<ICeilingTorchCompat>> entry : preliminaryCompatList.entrySet()) {
				COMPAT_LIST.put(entry.getKey(), entry.getValue().get());
			}

			initialized = true;
			preliminaryCompatList = null;
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

	public static ResourceLocation getRegistryName(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block);
	}

	public static ResourceLocation getRegistryName(Item item) {
		return ForgeRegistries.ITEMS.getKey(item);
	}

	public static boolean isModCompatActive(String modid) {
		return CompatConfig.getConfig().getBuiltInCompat().get(modid).config().get() && ModList.get().isLoaded(modid);
	}
}
