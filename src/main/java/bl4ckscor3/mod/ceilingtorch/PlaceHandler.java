package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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
			BlockPos placeAt = pos.relative(face);
			Level world = event.getWorld();

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
						BlockState state = compat.getStateToPlace(held, block);

						placeTorch(compat, event, held, block, pos, placeAt, world, state);
					}
				}
			}
		}
	}

	public static boolean placeTorch(ICeilingTorchCompat compat, RightClickBlock event, ItemStack held, Block block, BlockPos pos, BlockPos placeAt, Level world, BlockState state)
	{
		if(block.canSurvive(state, world, placeAt))
		{
			SoundType soundType;

			if(state.hasProperty(BlockStateProperties.WATERLOGGED) && world.getFluidState(placeAt).getType() == Fluids.WATER)
				state = state.setValue(BlockStateProperties.WATERLOGGED, true);

			world.setBlockAndUpdate(placeAt, state);
			compat.onPlace(event, placeAt, state);
			soundType = block.getSoundType(state, world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);

			return true;
		}

		return false;
	}
}
