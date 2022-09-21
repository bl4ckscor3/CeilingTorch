package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.additionallights.AdditionalLightsCompat;
import bl4ckscor3.mod.ceilingtorch.compat.aquatictorches.AquaticTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.ilikewood.ILikeWoodCompat;
import bl4ckscor3.mod.ceilingtorch.compat.infernalexpansion.InfernalExpansionCompat;
import bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics.IntegratedDynamicsCompat;
import bl4ckscor3.mod.ceilingtorch.compat.magicaltorches.MagicalTorchesCompat;
import bl4ckscor3.mod.ceilingtorch.compat.nethersdelight.NethersDelightCompat;
import bl4ckscor3.mod.ceilingtorch.compat.occultism.OccultismCompat;
import bl4ckscor3.mod.ceilingtorch.compat.pokecubeaio.PokecubeAIOCompat;
import bl4ckscor3.mod.ceilingtorch.compat.tofucraft.TofuCraftCompat;
import bl4ckscor3.mod.ceilingtorch.compat.torchbandolier.TorchBandolierCompat;
import bl4ckscor3.mod.ceilingtorch.compat.torchmaster.TorchmasterCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import bl4ckscor3.mod.ceilingtorch.compat.xycraftworld.XyCraftWorldCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	private static final Map<String,ICeilingTorchCompat> COMPAT_LIST = new HashMap<>();
	private static Map<String,Supplier<ICeilingTorchCompat>> preliminaryCompatList = new HashMap<>();
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
	private static boolean initialized = false;

	public CeilingTorch()
	{
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		preliminaryCompatList.put("minecraft", VanillaCompat::new);

		//cannot use addCompat because then the compat class will be classloaded which may crash if the mod is not present
		if(ModList.get().isLoaded("additional_lights"))
			preliminaryCompatList.put("additional_lights", AdditionalLightsCompat::new);

		if(ModList.get().isLoaded("aquatictorches"))
			preliminaryCompatList.put("aquatictorches", AquaticTorchesCompat::new);

		if(ModList.get().isLoaded("bonetorch"))
			preliminaryCompatList.put("bonetorch", BoneTorchCompat::new);

		if(ModList.get().isLoaded("ilikewood"))
			preliminaryCompatList.put("ilikewood", ILikeWoodCompat::new);

		if(ModList.get().isLoaded("infernalexp"))
			preliminaryCompatList.put("infernalexp", InfernalExpansionCompat::new);

		if(ModList.get().isLoaded("integrateddynamics"))
			preliminaryCompatList.put("integrateddynamics", IntegratedDynamicsCompat::new);

		if(ModList.get().isLoaded("nethersdelight"))
			preliminaryCompatList.put("nethersdelight", NethersDelightCompat::new);

		if(ModList.get().isLoaded("magical_torches"))
			preliminaryCompatList.put("magical_torches", MagicalTorchesCompat::new);

		if(ModList.get().isLoaded("occultism"))
			preliminaryCompatList.put("occultism", OccultismCompat::new);

		if(ModList.get().isLoaded("pokecube_legends"))
			preliminaryCompatList.put("pokecube_legends", PokecubeAIOCompat::new);

		if(ModList.get().isLoaded("tofucraft"))
			preliminaryCompatList.put("tofucraft", TofuCraftCompat::new);

		if(ModList.get().isLoaded("torchbandolier"))
			preliminaryCompatList.put("torchbandolier", TorchBandolierCompat::new);

		if(ModList.get().isLoaded("torchmaster"))
			preliminaryCompatList.put("torchmaster", TorchmasterCompat::new);

		if(ModList.get().isLoaded("xycraft_world"))
			preliminaryCompatList.put("xycraft_world", XyCraftWorldCompat::new);
	}

	@SubscribeEvent
	public static void onRegister(RegisterEvent event)
	{
		if(!initialized)
		{
			for(Entry<String,Supplier<ICeilingTorchCompat>> entry : preliminaryCompatList.entrySet())
			{
				COMPAT_LIST.put(entry.getKey(), entry.getValue().get());
			}

			initialized = true;
			preliminaryCompatList = null;
		}
	}

	/**
	 * Adds ceiling torch compatibility for the mod with the given modid, if that mod is loaded
	 * @param modid The modid of the mod to add compatibility to
	 * @param compat The compatibility class
	 */
	public static void addCompat(String modid, Supplier<ICeilingTorchCompat> compat)
	{
		if(ModList.get().isLoaded(modid))
			preliminaryCompatList.put(modid, compat);
	}

	public static Map<String,ICeilingTorchCompat> getCompatList()
	{
		return COMPAT_LIST;
	}

	public static ResourceLocation getRegistryName(Block block)
	{
		return ForgeRegistries.BLOCKS.getKey(block);
	}

	public static ResourceLocation getRegistryName(Item item)
	{
		return ForgeRegistries.ITEMS.getKey(item);
	}
}
