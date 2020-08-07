package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
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

	/**
	 * Used to find out which blockstate to place based on the ItemStack that was rightclicked with
	 * @stack The stack that was rightclicked to place the ceiling torch
	 * @block The block to place
	 */
	public default BlockState getStateToPlace(ItemStack stack, Block block)
	{
		return block.getDefaultState();
	}

	/**
	 * If this returns true, CeilingTorch will handle setting the render type. If this returns false, the mod adding compatibility needs to handle setting the render type themselves.
	 * @param The block to check
	 * @return true if the given block has the default cutout mipped render type, false otherwise
	 */
	public default boolean hasCutoutMippedRenderType(Block b)
	{
		return true;
	}
}
