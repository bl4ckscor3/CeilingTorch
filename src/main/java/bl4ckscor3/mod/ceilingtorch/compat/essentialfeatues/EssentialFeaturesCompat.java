package bl4ckscor3.mod.ceilingtorch.compat.essentialfeatues;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.williambl.essentialfeatures.common.block.ModBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.RedstoneCeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class EssentialFeaturesCompat implements ICeilingTorchCompat
{
	public static final String[] COLORS = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};
	public static final Block[] TORCHES = new Block[COLORS.length];
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		for(int i = 0; i < COLORS.length; i++)
		{
			final int index = i;

			//the properties are not like the vanilla redstone torch, but Essential Features has it like this, so i'm doing it as well
			event.getRegistry().register(TORCHES[i] = new RedstoneCeilingTorchBlock(Block.Properties.create(Material.REDSTONE_LIGHT)
					.setLightLevel(state -> state.get(RedstoneTorchBlock.LIT) ? 7 : 0),
					() -> (Block)ModBlocks.STAINED_REDSTONE_TORCHES[index].getLeft()).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "essentialfeatures_" + COLORS[i] + "_stained_redstone_torch")));
		}
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			ImmutableMap.Builder<ResourceLocation,Block> builder = ImmutableMap.builder();

			for(int i = 0; i < COLORS.length; i++)
			{
				builder.put(((Block)ModBlocks.STAINED_REDSTONE_TORCHES[i].getLeft()).getRegistryName(), TORCHES[i]);
			}

			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
