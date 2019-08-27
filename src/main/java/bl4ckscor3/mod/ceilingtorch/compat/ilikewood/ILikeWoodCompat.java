package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.objectholders.torch.WoodenTorchItems;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodType.WoodTypeProperties;
import yamahari.ilikewood.util.WoodTypes;
import yamahari.ilikewood.util.WoodenObjectType;

@ObjectHolder(CeilingTorch.MODID)
public class ILikeWoodCompat implements ICeilingTorchCompat
{
	public static final Block ILIKEWOOD_OAK_TORCH = null;
	public static final Block ILIKEWOOD_SPRUCE_TORCH = null;
	public static final Block ILIKEWOOD_BIRCH_TORCH = null;
	public static final Block ILIKEWOOD_JUNGLE_TORCH = null;
	public static final Block ILIKEWOOD_ACACIA_TORCH = null;
	public static final Block ILIKEWOOD_DARK_OAK_TORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_oak", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.OAK.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.OAK.getEnchantingPowerBonus())));
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_spruce", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.SPRUCE.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.SPRUCE.getEnchantingPowerBonus())));
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_birch", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.BIRCH.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.BIRCH.getEnchantingPowerBonus())));
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_jungle", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.JUNGLE.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.JUNGLE.getEnchantingPowerBonus())));
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_acacia", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.ACACIA.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.ACACIA.getEnchantingPowerBonus())));
		event.getRegistry().register(new BlockWoodenCeilingTorch(new WoodType("ilikewood_dark_oak", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.DARK_OAK.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.DARK_OAK.getEnchantingPowerBonus())));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.OAK.getRegistryName(), ILIKEWOOD_OAK_TORCH);
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.SPRUCE.getRegistryName(), ILIKEWOOD_SPRUCE_TORCH);
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.BIRCH.getRegistryName(), ILIKEWOOD_BIRCH_TORCH);
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.JUNGLE.getRegistryName(), ILIKEWOOD_JUNGLE_TORCH);
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.ACACIA.getRegistryName(), ILIKEWOOD_ACACIA_TORCH);
		PlaceHandler.registerPlaceEntry(WoodenTorchItems.DARK_OAK.getRegistryName(), ILIKEWOOD_DARK_OAK_TORCH);
	}
}
