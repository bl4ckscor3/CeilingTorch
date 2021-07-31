package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

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
		return block.defaultBlockState();
	}

	/**
	 * Called when a block registered by this ICeilingTorchCompat is being placed
	 * @param event The event which triggered a block being placed
	 * @param placeAt The position to place the block at
	 * @param state The state to place
	 */
	public default void onPlace(RightClickBlock event, BlockPos placeAt, BlockState state){}

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
