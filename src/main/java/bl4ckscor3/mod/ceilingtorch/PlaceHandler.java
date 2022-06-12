package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
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
			Level level = event.getWorld();

			if(face == Direction.DOWN && (level.isEmptyBlock(placeAt) || !level.getFluidState(placeAt).isEmpty()))
			{
				ItemStack held = event.getItemStack();
				ResourceLocation rl = CeilingTorch.getRegistryName(held.getItem());
				Map<String,ICeilingTorchCompat> compatList = CeilingTorch.getCompatList();
				String modid = rl.getNamespace();

				if(compatList.containsKey(modid))
				{
					ICeilingTorchCompat compat = compatList.get(modid);
					Map<ResourceLocation,Block> placeEntries = compat.getPlaceEntries();

					if(placeEntries.containsKey(rl))
						placeTorch(compat, event, held, placeAt, level, placeEntries.get(rl).defaultBlockState());
				}
			}
		}
	}

	public static boolean placeTorch(ICeilingTorchCompat compat, RightClickBlock event, ItemStack held, BlockPos placeAt, Level level, BlockState state)
	{
		state = compat.getStateToPlace(event, level, placeAt, state, held);

		if(state.canSurvive(level, placeAt))
		{
			SoundType soundType;

			level.setBlockAndUpdate(placeAt, state);
			compat.onPlace(event, placeAt, state);
			soundType = state.getBlock().getSoundType(state, level, placeAt, event.getPlayer());
			level.playSound(null, placeAt.getX(), placeAt.getY(), placeAt.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);

			MinecraftForge.EVENT_BUS.post(new EntityPlaceEvent(BlockSnapshot.create(level.dimension(), level, placeAt), level.getBlockState(event.getPos()), event.getPlayer()));
			return true;
		}

		return false;
	}
}
