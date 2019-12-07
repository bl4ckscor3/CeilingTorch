package bl4ckscor3.mod.ceilingtorch.compat.primalcore;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class PrimalCoreCompat implements ICeilingTorchCompat
{
	public static final Block PRIMAL_TORCH_WOOD = null;
	public static final Block PRIMAL_TORCH_NETHER = null;
	public static final Block PRIMAL_TORCH_NETHER_LIT = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new PrimalCeilingTorch.Wood(0.9375F, true, true, SoundType.WOOD).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "primal_torch_wood")).setTranslationKey("primal.torch_wood"));
		event.getRegistry().register(new PrimalCeilingTorch.Nether(0.8375F, false, false, SoundType.STONE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "primal_torch_nether")).setTranslationKey("primal.torch_nether"));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(new ResourceLocation("primal", "torch_wood"), PRIMAL_TORCH_WOOD.getDefaultState());
		PlaceHandler.registerPlaceEntry(new ResourceLocation("primal", "torch_nether"), PRIMAL_TORCH_NETHER.getDefaultState());
		PlaceHandler.registerPlaceEntry(new ResourceLocation("primal", "torch_nether_lit"), PRIMAL_TORCH_NETHER.getDefaultState().withProperty(PropertyBool.create("lit"), true));
	}
}
