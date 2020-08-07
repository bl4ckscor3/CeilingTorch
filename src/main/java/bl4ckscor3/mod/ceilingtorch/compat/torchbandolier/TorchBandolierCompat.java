package bl4ckscor3.mod.ceilingtorch.compat.torchbandolier;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import bl4ckscor3.mod.ceilingtorch.compat.modernity.ModernityCompat;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
				Map<ResourceLocation,Block> placeEntries = compatList.get(modid).getPlaceEntries();

				if(placeEntries.containsKey(rl))
				{
					Block block = placeEntries.get(rl);
					World world = event.getWorld();
					Direction dir = event.getFace();
					BlockPos pos = event.getPos();
					BlockPos placeAt = pos.offset(dir);
					boolean placed;

					if(CeilingTorch.isModernityLoaded())
						placed = ModernityCompat.handlePlacement(event, ItemStack.EMPTY, block, world, pos, placeAt, dir);
					else
					{
						boolean air = world.isAirBlock(placeAt);
						boolean water = world.getFluidState(placeAt).getFluid() == Fluids.WATER;

						if(!air && !water)
							return;

						BlockState state = block.getDefaultState();

						if(block instanceof IWaterLoggable)
							state = state.with(BlockStateProperties.WATERLOGGED, water);

						placed = PlaceHandler.placeTorch(event, ItemStack.EMPTY, block, pos, placeAt, world, state);
					}

					if(placed)
					{
						if(consumeTorch)
							TorchBandolierItem.setTorchCount(stack, --torchCount);

						if(torchCount == 0  && player != null)
							player.replaceItemInInventory(getSlotFor(stack, player.inventory), new ItemStack(ModItems.emptyTorchBandolier));

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
		for(int i = 0; i < inv.mainInventory.size(); ++i)
		{
			ItemStack stack2 = inv.mainInventory.get(i);
			if(!stack2.isEmpty() && (stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2)))
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
