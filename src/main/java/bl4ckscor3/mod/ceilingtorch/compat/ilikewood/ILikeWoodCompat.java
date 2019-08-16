package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.objectholders.ModItems;
import yamahari.ilikewood.util.WoodType;

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
		for(WoodType type : WoodType.values())
		{
			event.getRegistry().register(new BlockWoodenCeilingTorch(type).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ilikewood_" + type.getName() + "_torch")));
		}
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(ModItems.oak_torch.getRegistryName(), ILIKEWOOD_OAK_TORCH);
		PlaceHandler.registerPlaceEntry(ModItems.spruce_torch.getRegistryName(), ILIKEWOOD_SPRUCE_TORCH);
		PlaceHandler.registerPlaceEntry(ModItems.birch_torch.getRegistryName(), ILIKEWOOD_BIRCH_TORCH);
		PlaceHandler.registerPlaceEntry(ModItems.jungle_torch.getRegistryName(), ILIKEWOOD_JUNGLE_TORCH);
		PlaceHandler.registerPlaceEntry(ModItems.acacia_torch.getRegistryName(), ILIKEWOOD_ACACIA_TORCH);
		PlaceHandler.registerPlaceEntry(ModItems.dark_oak_torch.getRegistryName(), ILIKEWOOD_DARK_OAK_TORCH);
	}
}
