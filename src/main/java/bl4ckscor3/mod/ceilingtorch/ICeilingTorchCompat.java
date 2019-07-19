package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public interface ICeilingTorchCompat
{
	public void registerBlocks(RegistryEvent.Register<Block> event);
	public void registerPlaceEntries();
}
