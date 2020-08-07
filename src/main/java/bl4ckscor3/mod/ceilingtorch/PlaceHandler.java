package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.compat.modernity.ModernityCompat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
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
	public static void onRightClickBlock(RightClickBlock event)
	{
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == Direction.DOWN)
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

					if(CeilingTorch.isModernityLoaded())
					{
						ModernityCompat.handlePlacement(compat, event, held, block, world, pos, placeAt, face);
						return;
					}
					else
					{
						boolean air = world.isAirBlock(placeAt);
						boolean water = world.getFluidState(placeAt).getFluid() == Fluids.WATER;

						if(!air && !water)
							return;

						BlockState state = compat.getStateToPlace(held, block);

						if(block instanceof IWaterLoggable)
							state = state.with(BlockStateProperties.WATERLOGGED, water);

						placeTorch(event, held, block, pos, placeAt, world, state);
					}
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
		else return false;
	}
}
