package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

public class ILikeWoodCompat implements ICeilingTorchCompat
{
	public static Block oakTorch;
	public static Block spruceTorch;
	public static Block birchTorch;
	public static Block jungleTorch;
	public static Block acaciaTorch;
	public static Block darkOakTorch;
	public static Block warpedTorch;
	public static Block crimsonTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(oakTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.OAK));
		event.getRegistry().register(spruceTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.SPRUCE));
		event.getRegistry().register(birchTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.BIRCH));
		event.getRegistry().register(jungleTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.JUNGLE));
		event.getRegistry().register(acaciaTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.ACACIA));
		event.getRegistry().register(darkOakTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.DARK_OAK));
		event.getRegistry().register(warpedTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.WARPED));
		event.getRegistry().register(crimsonTorch = new WoodenCeilingTorchBlock(VanillaWoodTypes.CRIMSON));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(registryNameOf(VanillaWoodTypes.OAK), oakTorch)
					.put(registryNameOf(VanillaWoodTypes.SPRUCE), spruceTorch)
					.put(registryNameOf(VanillaWoodTypes.BIRCH), birchTorch)
					.put(registryNameOf(VanillaWoodTypes.JUNGLE), jungleTorch)
					.put(registryNameOf(VanillaWoodTypes.ACACIA), acaciaTorch)
					.put(registryNameOf(VanillaWoodTypes.DARK_OAK), darkOakTorch)
					.put(registryNameOf(VanillaWoodTypes.WARPED), warpedTorch)
					.put(registryNameOf(VanillaWoodTypes.CRIMSON), crimsonTorch).build();
		}

		return placeEntries;
	}

	private ResourceLocation registryNameOf(IWoodType woodType)
	{
		return new ResourceLocation("ilikewood", Util.toRegistryName(woodType.getName(), WoodenBlockType.TORCH.getName()));
	}
}
