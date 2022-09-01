package bl4ckscor3.mod.ceilingtorch.compat.additionallights;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;
import com.mgen256.al.AdditionalLights;
import com.mgen256.al.FireTypes;
import com.mgen256.al.ModBlockList;
import com.mgen256.al.blocks.IHasFire;
import com.mgen256.al.items.SoulWand;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.registries.RegistryObject;

public class AdditionalLightsCompat implements ICeilingTorchCompat
{
	private static final HashMap<Supplier<Block>,Supplier<Block>> CEILING_TO_NORMAL = new HashMap<>();
	private Map<ResourceLocation,Block> placeEntries;

	static
	{
		ModBlockList[] supportedBlocks = {
				ModBlockList.ALTorch_Acacia,
				ModBlockList.ALTorch_Birch,
				ModBlockList.ALTorch_Oak,
				ModBlockList.ALTorch_Dark_Oak,
				ModBlockList.ALTorch_Jungle,
				ModBlockList.ALTorch_Spruce,
				ModBlockList.ALTorch_Stone,
				ModBlockList.ALTorch_CobbleStone,
				ModBlockList.ALTorch_Mossy_CobbleStone,
				ModBlockList.ALTorch_End_Stone,
				ModBlockList.ALTorch_Sand_Stone,
				ModBlockList.ALTorch_Stone_Bricks,
				ModBlockList.ALTorch_Mossy_Stone_Bricks,
				ModBlockList.ALTorch_End_Stone_Bricks,
				ModBlockList.ALTorch_Nether_Bricks,
				ModBlockList.ALTorch_Red_Nether_Bricks,
				ModBlockList.ALTorch_Smooth_Stone,
				ModBlockList.ALTorch_Glass,
				ModBlockList.ALTorch_Iron,
				ModBlockList.ALTorch_Gold,
				ModBlockList.ALTorch_Diamond,
				ModBlockList.ALTorch_Ice,
				ModBlockList.ALTorch_Pink_Wool,
				ModBlockList.ALTorch_Magenta_Wool,
				ModBlockList.ALTorch_Crimson,
				ModBlockList.ALTorch_Warped,
				ModBlockList.ALTorch_BlackStone
		};

		for(ModBlockList block : supportedBlocks)
		{
			Supplier<Block> originalBlock = () -> (Block) AdditionalLights.modBlocks.get(block);
			RegistryObject<Block> registeredBlock;
			String registryName = block.name().toLowerCase(Locale.ENGLISH).replace("altorch", "additional_lights_al_torch");

			if(block == ModBlockList.ALTorch_Oak || block == ModBlockList.ALTorch_Spruce || block == ModBlockList.ALTorch_Birch || block == ModBlockList.ALTorch_Jungle || block == ModBlockList.ALTorch_Acacia || block == ModBlockList.ALTorch_Dark_Oak || block == ModBlockList.ALTorch_Crimson || block == ModBlockList.ALTorch_Warped)
				registryName += "_planks";

			registeredBlock = CeilingTorch.BLOCKS.register(registryName, () -> new ALCeilingTorchBlock(originalBlock));
			CEILING_TO_NORMAL.put(registeredBlock, originalBlock);
		}
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			ImmutableMap.Builder<ResourceLocation,Block> builder = ImmutableMap.builder();

			CEILING_TO_NORMAL.forEach((ceiling, normal) -> builder.put(getRegistryName(normal.get()), ceiling.get()));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack)
	{
		return state.setValue(IHasFire.FIRE_TYPE, event.getEntity().getOffhandItem().getItem() instanceof SoulWand ? FireTypes.SOUL : FireTypes.NORMAL);
	}
}
