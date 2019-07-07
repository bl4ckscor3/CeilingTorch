package bl4ckscor3.mod.ceilingtorch;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=CeilingTorch.MODID)
public class PlaceHandler
{
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{
		ItemStack held = event.getItemStack();

		if(held.getItem().getRegistryName().toString().equals("minecraft:torch"))
			placeTorch(event, held, CeilingTorch.TORCH);
		if(held.getItem().getRegistryName().toString().equals("minecraft:redstone_torch"))
			placeTorch(event, held, CeilingTorch.REDSTONE_TORCH);
	}

	private static void placeTorch(RightClickBlock event, ItemStack held, Block block)
	{
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == Direction.DOWN && world.isAirBlock(placeAt) && Block.func_220055_a(world, pos, Direction.DOWN))
		{
			world.setBlockState(placeAt, block.getDefaultState());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.WOOD.getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getEntityPlayer().swingArm(event.getHand());

			if(!event.getEntityPlayer().isCreative())
				held.shrink(1);
		}
	}
}
