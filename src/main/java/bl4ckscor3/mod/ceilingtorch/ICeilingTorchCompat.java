package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public interface ICeilingTorchCompat
{
	/**
	 * Called from CeilingTorch's block register event. Register all your compat blocks here.
	 * @param event The Block register event.
	 */
	public void registerBlocks(RegistryEvent.Register<Block> event);

	/**
	 * Used to find out which block to place when an item from the mod of this compat is rightclicked on the bottom of a block.
	 * Ideally, you would not return a new map every time this is called.
	 * @return A map with the key being the ResourceLocation of the item that the player rightclicked with, and the ceiling torch block to place for that item
	 */
	public Map<ResourceLocation,Block> getPlaceEntries();
}
