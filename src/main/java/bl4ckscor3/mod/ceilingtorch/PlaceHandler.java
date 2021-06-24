package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
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
		if(!event.getPlayer().isSpectator()) //because apparently this is a thing
		{
			BlockPos pos = event.getPos();
			Direction face = event.getFace();
			BlockPos placeAt = pos.offset(face);
			World world = event.getWorld();

			if(face == Direction.DOWN && (world.isAirBlock(placeAt) || !world.getFluidState(placeAt).isEmpty()))
			{
				ItemStack held = event.getItemStack();
				ResourceLocation rl = held.getItem().getRegistryName();
				Map<String,ICeilingTorchCompat> compatList = CeilingTorch.getCompatList();
				String modid = rl.getNamespace();

				if(compatList.containsKey(modid))
				{
					ICeilingTorchCompat compat = compatList.get(modid);
					Map<ResourceLocation,Block> placeEntries = compat.getPlaceEntries();

					if(placeEntries.containsKey(rl))
					{
						Block block = placeEntries.get(rl);
						BlockState state = compat.getStateToPlace(held, block);

						placeTorch(compat, event, held, block, pos, placeAt, world, state);
					}
				}
			}
		}
	}

	public static boolean placeTorch(ICeilingTorchCompat compat, RightClickBlock event, ItemStack held, Block block, BlockPos pos, BlockPos placeAt, World world, BlockState state)
	{
		if(block.isValidPosition(state, world, placeAt))
		{
			SoundType soundType;

			if(state.hasProperty(BlockStateProperties.WATERLOGGED) && world.getFluidState(placeAt).getFluid() == Fluids.WATER)
				state = state.with(BlockStateProperties.WATERLOGGED, true);

			world.setBlockState(placeAt, state);
			compat.onPlace(event, placeAt, state);
			soundType = block.getSoundType(state, world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swingArm(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);

			return true;
		}
		else return false;
	}
}
