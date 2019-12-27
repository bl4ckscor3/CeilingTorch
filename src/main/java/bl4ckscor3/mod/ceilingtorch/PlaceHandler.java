package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.BlockCeilingTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=CeilingTorch.MODID)
public class PlaceHandler
{
	private static final HashMap<ResourceLocation,IBlockState> PLACE_ENTRIES = new HashMap<>();

	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event)
	{
		EnumHand actualHand = EnumHand.values()[(event.getHand().ordinal() + 1) % 2]; //get opposite hand
		ItemStack held = event.getEntityPlayer().getHeldItem(actualHand);
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeTorch(event, actualHand, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeTorch(RightClickBlock event, EnumHand actualHand, ItemStack held, IBlockState state)
	{
		BlockPos pos = event.getPos();
		EnumFacing face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == EnumFacing.DOWN && world.isAirBlock(placeAt) && BlockCeilingTorch.canPlaceAt(world, placeAt, EnumFacing.DOWN))
		{
			SoundType soundType;

			world.setBlockState(placeAt, state);
			soundType = state.getBlock().getSoundType(state, world, pos, event.getEntityPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getEntityPlayer().swingArm(actualHand);

			if(!event.getEntityPlayer().isCreative())
				held.shrink(1);
		}
	}

	public static void registerPlaceEntry(ResourceLocation itemName, IBlockState ceilingTorchState)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, ceilingTorchState);
	}
}
