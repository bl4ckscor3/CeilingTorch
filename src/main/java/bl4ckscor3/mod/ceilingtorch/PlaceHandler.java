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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.EntityPlaceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=CeilingTorch.MODID)
public class PlaceHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event)
	{
		if(!event.getPlayer().isSpectator()) //because apparently this is a thing
		{
			BlockPos pos = event.getPos();
			Direction face = event.getFace();
			BlockPos placeAt = pos.relative(face);
			World world = event.getWorld();

			if(face == Direction.DOWN && (world.isEmptyBlock(placeAt) || !world.getFluidState(placeAt).isEmpty()))
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
						BlockState state = compat.getStateToPlace(event, held, block);

						placeTorch(compat, event, held, block, placeAt, world, state);
					}
				}
			}
		}
	}

	public static boolean placeTorch(ICeilingTorchCompat compat, RightClickBlock event, ItemStack held, Block block, BlockPos placeAt, World world, BlockState state)
	{
		if(state.canSurvive(world, placeAt))
		{
			SoundType soundType;

			if(state.hasProperty(BlockStateProperties.WATERLOGGED) && world.getFluidState(placeAt).getType() == Fluids.WATER)
				state = state.setValue(BlockStateProperties.WATERLOGGED, true);

			world.setBlockAndUpdate(placeAt, state);
			compat.onPlace(event, placeAt, state);
			soundType = block.getSoundType(state, world, placeAt, event.getPlayer());
			world.playSound(null, placeAt.getX(), placeAt.getY(), placeAt.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);

			MinecraftForge.EVENT_BUS.post(new EntityPlaceEvent(BlockSnapshot.create(world.dimension(), world, placeAt), world.getBlockState(event.getPos()), event.getPlayer()));
			return true;
		}

		return false;
	}
}
