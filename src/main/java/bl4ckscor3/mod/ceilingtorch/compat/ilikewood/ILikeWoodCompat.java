//package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;
//
//import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
//import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
//import net.minecraft.block.Block;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.event.RegistryEvent;
//import yamahari.ilikewood.util.Util;
//import yamahari.ilikewood.util.WoodType;
//import yamahari.ilikewood.util.WoodenObjectType;
//
//public class ILikeWoodCompat implements ICeilingTorchCompat
//{
//	public static Block oakTorch = null;
//	public static Block spruceTorch = null;
//	public static Block birchTorch = null;
//	public static Block jungleTorch = null;
//	public static Block acaciaTorch = null;
//	public static Block darkOakTorch = null;
//
//	@Override
//	public void registerBlocks(RegistryEvent.Register<Block> event)
//	{
//		event.getRegistry().register(oakTorch = new WoodenCeilingTorchBlock(WoodType.OAK));
//		event.getRegistry().register(spruceTorch = new WoodenCeilingTorchBlock(WoodType.SPRUCE));
//		event.getRegistry().register(birchTorch = new WoodenCeilingTorchBlock(WoodType.BIRCH));
//		event.getRegistry().register(jungleTorch = new WoodenCeilingTorchBlock(WoodType.JUNGLE));
//		event.getRegistry().register(acaciaTorch = new WoodenCeilingTorchBlock(WoodType.ACACIA));
//		event.getRegistry().register(darkOakTorch = new WoodenCeilingTorchBlock(WoodType.DARK_OAK));
//	}
//
//	@Override
//	public void registerPlaceEntries()
//	{
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.OAK), oakTorch);
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.SPRUCE), spruceTorch);
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.BIRCH), birchTorch);
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.JUNGLE), jungleTorch);
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.ACACIA), acaciaTorch);
//		PlaceHandler.registerPlaceEntry(registryNameOf(WoodType.DARK_OAK), darkOakTorch);
//	}
//
//	private ResourceLocation registryNameOf(WoodType woodType)
//	{
//		return new ResourceLocation("ilikewood", Util.toRegistryName(woodType.toString(), WoodenObjectType.TORCH.toString()));
//	}
//}
