package bl4ckscor3.mod.ceilingtorch.compat.torcherino;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import torcherino.api.TorcherinoAPI;

public class TorcherinoCompat implements ICeilingTorchCompat
{
	public static final List<CeilingTorcherinoBlock> CEILING_TORCHERINOS = new ArrayList<>();
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		TorcherinoAPI.INSTANCE.getTiers().keySet().forEach(tierId -> {
			if(tierId.getNamespace().equals("torcherino"))
			{
				ResourceLocation particleID = loc("torcherino", tierId, "flame");
				CeilingTorcherinoBlock block = (CeilingTorcherinoBlock)new CeilingTorcherinoBlock(tierId, particleID).setRegistryName(loc(CeilingTorch.MODID, "torcherino_", tierId, "torcherino"));

				CEILING_TORCHERINOS.add(block);
				event.getRegistry().register(block);
			}
		});
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			ImmutableMap.Builder<ResourceLocation,Block> builder = ImmutableMap.builder();

			for(CeilingTorcherinoBlock ceilingTorcherino : CEILING_TORCHERINOS)
			{
				Block torcherino = ceilingTorcherino.getVanillaTorcherino();

				if(torcherino != Blocks.AIR)
					builder.put(torcherino.getRegistryName(), ceilingTorcherino);
			}

			placeEntries = builder.build();
		}

		return placeEntries;
	}

	public static ResourceLocation loc(String modId, ResourceLocation tierId, String type)
	{
		return loc(modId, "", tierId, type);
	}

	public static ResourceLocation loc(String modId, String prefix, ResourceLocation tierId, String type)
	{
		if(tierId.getPath().equals("normal"))
			return new ResourceLocation(modId, prefix + type);

		return new ResourceLocation(modId, prefix + tierId.getPath() + "_" + type);
	}
}
