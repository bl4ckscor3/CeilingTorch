package bl4ckscor3.mod.ceilingtorch.compat.torchbandolier;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.silentchaos512.torchbandolier.init.ModItems;
import net.silentchaos512.torchbandolier.item.TorchBandolierItem;

public class TorchBandolierCompat implements ICeilingTorchCompat
{
	public TorchBandolierCompat()
	{
		MinecraftForge.EVENT_BUS.addListener(TorchBandolierCompat::onRightClickBlock);
	}

	public static void onRightClickBlock(RightClickBlock event)
	{
		if(event.getFace() == Direction.DOWN && event.getItemStack().getItem() instanceof TorchBandolierItem)
		{
			PlayerEntity player = event.getPlayer();
			ItemStack stack = event.getItemStack();
			TorchBandolierItem bandolier = (TorchBandolierItem)stack.getItem();
			Block torch = bandolier.getTorchBlock();
			boolean consumeTorch = player != null && !player.isCreative();
			int torchCount = TorchBandolierItem.getTorchCount(stack);

			if(player == null || torch == null || torch instanceof AirBlock || (torchCount <= 0 && consumeTorch))
				return;

			ResourceLocation rl = torch.asItem().getRegistryName();
			Map<String,ICeilingTorchCompat> compatList = CeilingTorch.getCompatList();
			String modid = rl.getNamespace();

			if(compatList.containsKey(modid))
			{
				ICeilingTorchCompat compat = compatList.get(modid);
				Map<ResourceLocation,Block> placeEntries = compat.getPlaceEntries();

				if(placeEntries.containsKey(rl))
				{
					Block block = placeEntries.get(rl);
					BlockPos pos = event.getPos();
					BlockState state = compat.getStateToPlace(stack, block);

					if(PlaceHandler.placeTorch(compat, event, ItemStack.EMPTY, block, pos, pos.relative(event.getFace()), event.getWorld(), state))
					{
						if(consumeTorch)
							TorchBandolierItem.setTorchCount(stack, --torchCount);

						if(torchCount == 0  && player != null)
							player.setSlot(getSlotFor(stack, player.inventory), new ItemStack(ModItems.EMPTY_TORCH_BANDOLIER.get()));

						event.setCancellationResult(ActionResultType.SUCCESS);
						return;
					}
				}
			}
		}
	}

	//PlayerInventory#getSlotFor is client side only
	public static int getSlotFor(ItemStack stack1, PlayerInventory inv)
	{
		for(int i = 0; i < inv.items.size(); ++i)
		{
			ItemStack stack2 = inv.items.get(i);
			if(!stack2.isEmpty() && (stack1.getItem() == stack2.getItem() && ItemStack.tagMatches(stack1, stack2)))
				return i;
		}

		return -1;
	}

	@Override
	public void registerBlocks(Register<Block> event) {}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		return ImmutableMap.of();
	}
}
