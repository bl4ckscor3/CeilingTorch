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

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class AdditionalLightsCompat implements ICeilingTorchCompat
{
	private static final HashMap<Block,Supplier<Block>> CEILING_TO_NORMAL = new HashMap<>();
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
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
			Block blockToRegister = new ALCeilingTorchBlock(originalBlock);
			String registryName = block.name().toLowerCase(Locale.ENGLISH).replace("altorch", "additional_lights_al_torch");

			if(block == ModBlockList.ALTorch_Oak || block == ModBlockList.ALTorch_Spruce || block == ModBlockList.ALTorch_Birch || block == ModBlockList.ALTorch_Jungle || block == ModBlockList.ALTorch_Acacia || block == ModBlockList.ALTorch_Dark_Oak || block == ModBlockList.ALTorch_Crimson || block == ModBlockList.ALTorch_Warped)
				registryName += "_planks";

			blockToRegister.setRegistryName(registryName);
			event.getRegistry().register(blockToRegister);
			CEILING_TO_NORMAL.put(blockToRegister, originalBlock);
		}
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			ImmutableMap.Builder<ResourceLocation,Block> builder = ImmutableMap.builder();

			CEILING_TO_NORMAL.forEach((ceiling, normal) -> builder.put(normal.get().getRegistryName(), ceiling));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, ItemStack stack, Block block)
	{
		return block.defaultBlockState().setValue(IHasFire.FIRE_TYPE, event.getPlayer().getOffhandItem().getItem() instanceof SoulWand ? FireTypes.SOUL : FireTypes.NORMAL);
	}
}
