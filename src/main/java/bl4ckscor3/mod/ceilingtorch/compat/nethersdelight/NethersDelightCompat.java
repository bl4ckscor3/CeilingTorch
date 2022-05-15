package bl4ckscor3.mod.ceilingtorch.compat.nethersdelight;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.umpaz.nethers_delight.core.registry.NDBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class NethersDelightCompat implements ICeilingTorchCompat
{
	public static Block ceilingPropelplantTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(ceilingPropelplantTorch = new CeilingTorchBlock(Block.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel(state -> 12), ParticleTypes.FLAME, NDBlocks.PROPELPLANT_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "nethersdelight_propelplant_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(NDBlocks.PROPELPLANT_TORCH.get().getRegistryName(), ceilingPropelplantTorch);

		return placeEntries;
	}
}
