package bl4ckscor3.mod.ceilingtorch.compat.torchbandolier;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.silentchaos512.torchbandolier.init.ModItems;
import net.silentchaos512.torchbandolier.item.TorchBandolierItem;

public class TorchBandolierCompat implements ICeilingTorchCompat {
	public TorchBandolierCompat() {
		MinecraftForge.EVENT_BUS.addListener(TorchBandolierCompat::onRightClickBlock);
	}

	public static void onRightClickBlock(RightClickBlock event) {
		if (event.getFace() == Direction.DOWN && event.getItemStack().getItem() instanceof TorchBandolierItem) {
			Player player = event.getPlayer();
			ItemStack stack = event.getItemStack();
			TorchBandolierItem bandolier = (TorchBandolierItem) stack.getItem();
			Block torch = bandolier.getTorchBlock();
			boolean consumeTorch = player != null && !player.isCreative();
			int torchCount = TorchBandolierItem.getTorchCount(stack);

			if (player == null || torch == null || torch instanceof AirBlock || (torchCount <= 0 && consumeTorch))
				return;

			ResourceLocation rl = torch.asItem().getRegistryName();
			Map<String, ICeilingTorchCompat> compatList = CeilingTorch.getCompatList();
			String modid = rl.getNamespace();

			if (compatList.containsKey(modid)) {
				ICeilingTorchCompat compat = compatList.get(modid);
				Map<ResourceLocation, Block> placeEntries = compat.getPlaceEntries();

				if (placeEntries.containsKey(rl)) {
					if (PlaceHandler.placeTorch(compat, event, ItemStack.EMPTY, event.getPos().relative(event.getFace()), event.getWorld(), placeEntries.get(rl).defaultBlockState())) {
						if (consumeTorch)
							TorchBandolierItem.setTorchCount(stack, --torchCount);

						if (torchCount == 0 && player != null)
							player.getInventory().setItem(player.getInventory().findSlotMatchingItem(stack), new ItemStack(ModItems.EMPTY_TORCH_BANDOLIER.get()));

						event.setCancellationResult(InteractionResult.SUCCESS);
						return;
					}
				}
			}
		}
	}

	@Override
	public void registerBlocks(Register<Block> event) {}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		return ImmutableMap.of();
	}
}
