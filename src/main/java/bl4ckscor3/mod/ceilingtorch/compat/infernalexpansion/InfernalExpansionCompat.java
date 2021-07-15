package bl4ckscor3.mod.ceilingtorch.compat.infernalexpansion;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IEItems;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class InfernalExpansionCompat implements ICeilingTorchCompat
{
	public static Block glowlightCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(glowlightCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH),
				ParticleTypes.CRIT, IEBlocks.GLOW_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "infernalexpansion_glowlight_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(IEItems.GLOW_TORCH.get().getRegistryName(), glowlightCeilingTorch);

		return placeEntries;
	}
}
