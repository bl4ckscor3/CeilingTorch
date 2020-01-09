package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;

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
	private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();

	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeTorch(event, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeTorch(RightClickBlock event, ItemStack held, Block block)
	{
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();

		if(face == Direction.DOWN && Block.func_220055_a(world, pos, Direction.DOWN))
		{
			boolean air = world.isAirBlock(placeAt);
			boolean water = world.getFluidState(placeAt).getFluid() == Fluids.WATER;
			boolean waterloggable = block instanceof IWaterLoggable;

			if((!air && !water) || (!air && water && !waterloggable))
				return;

			BlockState state = block.getDefaultState();
			SoundType soundType;

			if(waterloggable)
				state = state.with(BlockStateProperties.WATERLOGGED, water);

			world.setBlockState(placeAt, state);
			soundType = block.getSoundType(state, world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swingArm(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);
		}
	}

	public static void registerPlaceEntry(ResourceLocation itemName, Block ceilingTorch)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, ceilingTorch);
	}
}
