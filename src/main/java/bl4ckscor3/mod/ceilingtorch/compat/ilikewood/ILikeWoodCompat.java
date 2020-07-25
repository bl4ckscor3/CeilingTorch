package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public class ILikeWoodCompat implements ICeilingTorchCompat
{
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
		event.getRegistry().register(oakTorch = new WoodenCeilingTorchBlock(WoodType.OAK));
		event.getRegistry().register(spruceTorch = new WoodenCeilingTorchBlock(WoodType.SPRUCE));
		event.getRegistry().register(birchTorch = new WoodenCeilingTorchBlock(WoodType.BIRCH));
		event.getRegistry().register(jungleTorch = new WoodenCeilingTorchBlock(WoodType.JUNGLE));
		event.getRegistry().register(acaciaTorch = new WoodenCeilingTorchBlock(WoodType.ACACIA));
		event.getRegistry().register(darkOakTorch = new WoodenCeilingTorchBlock(WoodType.DARK_OAK));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(registryNameOf(WoodType.OAK), oakTorch)
					.put(registryNameOf(WoodType.SPRUCE), spruceTorch)
					.put(registryNameOf(WoodType.BIRCH), birchTorch)
					.put(registryNameOf(WoodType.JUNGLE), jungleTorch)
					.put(registryNameOf(WoodType.ACACIA), acaciaTorch)
					.put(registryNameOf(WoodType.DARK_OAK), darkOakTorch).build();
		}

		return placeEntries;
	}

	private ResourceLocation registryNameOf(WoodType woodType)
	{
		return new ResourceLocation("ilikewood", Util.toRegistryName(woodType.toString(), WoodenObjectType.TORCH.toString()));
	}
}
