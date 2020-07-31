package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import yamahari.ilikewood.objectholders.torch.WoodenTorchBlocks;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodType.WoodTypeProperties;
import yamahari.ilikewood.util.WoodTypes;
import yamahari.ilikewood.util.WoodenObjectType;

public class ILikeWoodCompat implements ICeilingTorchCompat
{
	public static final WoodType OAK_TYPE = new WoodType("ilikewood_oak", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.OAK.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.OAK.getEnchantingPowerBonus());
	public static final WoodType SPRUCE_TYPE = new WoodType("ilikewood_spruce", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.SPRUCE.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.SPRUCE.getEnchantingPowerBonus());
	public static final WoodType BIRCH_TYPE = new WoodType("ilikewood_birch", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.BIRCH.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.BIRCH.getEnchantingPowerBonus());
	public static final WoodType JUNGLE_TYPE = new WoodType("ilikewood_jungle", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.JUNGLE.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.JUNGLE.getEnchantingPowerBonus());
	public static final WoodType ACACIA_TYPE = new WoodType("ilikewood_acacia", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.ACACIA.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.ACACIA.getEnchantingPowerBonus());
	public static final WoodType DARK_OAK_TYPE = new WoodType("ilikewood_dark_oak", CeilingTorch.MODID, new ImmutableMap.Builder<WoodenObjectType,WoodTypeProperties>().put(WoodenObjectType.TORCH, WoodTypes.DARK_OAK.getWoodTypeProperties(WoodenObjectType.TORCH)).build(), () -> (double)WoodTypes.DARK_OAK.getEnchantingPowerBonus());
	public static Block oakTorch;
	public static Block spruceTorch;
	public static Block birchTorch;
	public static Block jungleTorch;
	public static Block acaciaTorch;
	public static Block darkOakTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(oakTorch = new WoodenCeilingTorchBlock(OAK_TYPE));
		event.getRegistry().register(spruceTorch = new WoodenCeilingTorchBlock(SPRUCE_TYPE));
		event.getRegistry().register(birchTorch = new WoodenCeilingTorchBlock(BIRCH_TYPE));
		event.getRegistry().register(jungleTorch = new WoodenCeilingTorchBlock(JUNGLE_TYPE));
		event.getRegistry().register(acaciaTorch = new WoodenCeilingTorchBlock(ACACIA_TYPE));
		event.getRegistry().register(darkOakTorch = new WoodenCeilingTorchBlock(DARK_OAK_TYPE));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(WoodenTorchBlocks.OAK.getRegistryName(), oakTorch)
					.put(WoodenTorchBlocks.SPRUCE.getRegistryName(), spruceTorch)
					.put(WoodenTorchBlocks.BIRCH.getRegistryName(), birchTorch)
					.put(WoodenTorchBlocks.JUNGLE.getRegistryName(), jungleTorch)
					.put(WoodenTorchBlocks.ACACIA.getRegistryName(), acaciaTorch)
					.put(WoodenTorchBlocks.DARK_OAK.getRegistryName(), darkOakTorch).build();
		}

		return placeEntries;
	}
}
