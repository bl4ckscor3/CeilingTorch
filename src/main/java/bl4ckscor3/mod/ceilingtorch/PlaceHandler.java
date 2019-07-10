package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=CeilingTorch.MODID)
public class PlaceHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event)
	{
		EnumHand actualHand = EnumHand.values()[(event.getHand().ordinal() + 1) % 2]; //get opposite hand
		ItemStack held = event.getEntityPlayer().getHeldItem(actualHand);

		if(held.getItem().getRegistryName().toString().equals("minecraft:torch"))
			placeTorch(event, actualHand, held, CeilingTorch.TORCH);
		else if(held.getItem().getRegistryName().toString().equals("minecraft:redstone_torch"))
			placeTorch(event, actualHand, held, CeilingTorch.REDSTONE_TORCH);
	}

	private static void placeTorch(RightClickBlock event, EnumHand actualHand, ItemStack held, Block block)
	{
		BlockPos pos = event.getPos();
		EnumFacing face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == EnumFacing.DOWN && world.isAirBlock(placeAt) && BlockCeilingTorch.canPlaceAt(world, placeAt, EnumFacing.DOWN))
		{
			world.setBlockState(placeAt, block.getDefaultState());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.WOOD.getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getEntityPlayer().swingArm(actualHand);

			if(!event.getEntityPlayer().isCreative())
				held.shrink(1);
		}
	}
}
