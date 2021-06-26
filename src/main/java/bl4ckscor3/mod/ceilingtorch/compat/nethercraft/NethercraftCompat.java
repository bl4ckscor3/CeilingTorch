package bl4ckscor3.mod.ceilingtorch.compat.nethercraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.legacy.nethercraft.block.NetherBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class NethercraftCompat implements ICeilingTorchCompat
{
	public static Block charcoalCeilingTorch;
	public static Block fouliteCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(charcoalCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH), () -> NetherBlocks.charcoal_torch)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "nethercraft_charcoal_torch")));
		event.getRegistry().register(fouliteCeilingTorch = new CeilingTorchBlock(Block.Properties.from(Blocks.TORCH), () -> NetherBlocks.foulite_torch)
				.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "nethercraft_foulite_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(NetherBlocks.charcoal_torch.getRegistryName(), charcoalCeilingTorch,
					NetherBlocks.foulite_torch.getRegistryName(), fouliteCeilingTorch);
		}

		return placeEntries;
	}
}
