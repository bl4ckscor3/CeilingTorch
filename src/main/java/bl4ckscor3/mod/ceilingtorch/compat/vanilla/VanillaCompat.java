package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(CeilingTorch.MODID)
public class VanillaCompat implements ICeilingTorchCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;
	public static final Block UNLIT_REDSTONE_TORCH = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockCeilingTorch().setRegistryName(CeilingTorch.MODID, "torch").setTranslationKey("torch"));
		event.getRegistry().register(new BlockRedstoneCeilingTorch(false).setRegistryName(CeilingTorch.MODID, "unlit_redstone_torch").setTranslationKey("notGate"));
		event.getRegistry().register(new BlockRedstoneCeilingTorch(true).setRegistryName(CeilingTorch.MODID, "redstone_torch").setTranslationKey("notGate"));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(Blocks.TORCH.getRegistryName(), TORCH);
		PlaceHandler.registerPlaceEntry(Blocks.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH);
	}
}
