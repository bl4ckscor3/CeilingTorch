package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
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
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == Direction.DOWN && world.isAirBlock(placeAt))
		{
			ItemStack held = event.getItemStack();
			ResourceLocation rl = held.getItem().getRegistryName();
			Map<String,ICeilingTorchCompat> compatList = CeilingTorch.getCompatList();
			String modid = rl.getNamespace();

			if(compatList.containsKey(modid))
			{
				Map<ResourceLocation,Block> placeEntries = compatList.get(modid).getPlaceEntries();

				if(placeEntries.containsKey(rl))
				{
					Block block = placeEntries.get(rl);
					BlockState state = block.getDefaultState();

					placeTorch(event, held, block, pos, placeAt, world, state);
				}
			}
		}
	}

	public static boolean placeTorch(RightClickBlock event, ItemStack held, Block block, BlockPos pos, BlockPos placeAt, World world, BlockState state)
	{
		if(block.isValidPosition(state, world, placeAt))
		{
			SoundType soundType;

			world.setBlockState(placeAt, state);
			soundType = block.getSoundType(state, world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swingArm(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);

			return true;
		}

		return false;
	}
}
